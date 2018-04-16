package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

public class subtabTinNong extends subtab {

    public static subtabTinNong newInstance(int position) {
        Bundle args = new Bundle();
        subtabTinNong fragment = new subtabTinNong();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtabTinNong() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tinnong, container, false);

        //init swipe refresh layout
        initRecyclerView(rootView);
        return rootView;
    }

    @Override
    public void initRecyclerView(View rootView) {
        final RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        final ArrayList<MyItem> listItems = new ArrayList<>();
        listItems.add(new Item1Pic(R.drawable.antimage, "Antimage from Dota2", "Dota2", 10, stringToTimestamp("2018-04-12 11:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.axe, "Axe from Dota2","Dota2", 69, stringToTimestamp("2018-04-11 11:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.es, "Earthshaker from Dota2", "Dota2", 0, stringToTimestamp("2018-04-12 05:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.marutaro, "Marutaro from Chà-pan", "Kênh 14", 5, stringToTimestamp("2018-04-12 10:00:00.000")));
        listItems.add(new ItemLabel("League Of Legends"));
        listItems.add(new Item1Pic(R.drawable.rumble, "Rumble's new skin released!", "Riot", 4, stringToTimestamp("2018-04-02 14:10:00.000")));
        listItems.add(new Item3Pics(R.drawable.projecticon, R.drawable.yiproject, R.drawable.zedproject, "\"Project\" skin series first starts with Yi Project and Zed Project", "Riot", 80, stringToTimestamp("2018-04-13 10:00:00.000")));
        listItems.add(new ItemButton("Read more..."));
        listItems.add(new Item1Pic(R.drawable.shironeko, "Shironeko from Chà-pan","Chà-pan", 70, stringToTimestamp("2018-04-02 11:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.sven, "Sven from Dota2","Dota2", 0, stringToTimestamp("2018-04-12 01:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.templar, "Lanaya - Templar Assassin from Dota2","Dota2", 10, stringToTimestamp("2018-04-12 11:08:00.000")));
        //listItems.add(new ItemVideo(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test),"Today is Friday 13th...", "Kênh  14", 5, stringToTimestamp("2018-04-12 11:48:00.000")));
        final MyAdapter myAdapter = new MyAdapter(listItems, getContext(),recyclerView);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                listItems.add(null);
                myAdapter.notifyItemInserted(listItems.size() - 1);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //remove loading item
                        listItems.remove(listItems.size() - 1);
                        myAdapter.notifyItemRemoved(listItems.size());

                        //load data
                        int index = listItems.size();
                        myAdapter.updateListItems(loadRandomata(listItems,index));
                        //myAdapter.notifyDataSetChanged();
                        myAdapter.setLoaded();
                    }
                },3000);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<MyItem> newList = new ArrayList<>();
                        myAdapter.updateListItems(refreshData(listItems, newList));
                        //myAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    private ArrayList<MyItem> refreshData(ArrayList<MyItem> listItems, ArrayList<MyItem> newList) {
        //Collections.shuffle(listItems);
        //return listItems;
        Random randomGen = new Random();
        for (int i = 0; i <= listItems.size(); i++) {
            int itemIndex = randomGen.nextInt(listItems.size());
            MyItem newItem = listItems.get(itemIndex);
            newList.add(newItem);
        }
        return newList;
    }

    private ArrayList<MyItem> loadRandomata(ArrayList<MyItem> listItems, int index) {
        Random randomGen = new Random();
        int end = index + 10;
        for(int i = index; i < end; i++) {
            int itemIndex = randomGen.nextInt(listItems.size());
            MyItem newItem = listItems.get(itemIndex);
            listItems.add(newItem);
        }
        return listItems;
    }
}
