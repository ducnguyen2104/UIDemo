package com.example.ducnguyenvan.uidemo.tabs;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ducnguyenvan.uidemo.MyAdapter;
import com.example.ducnguyenvan.uidemo.R;
import com.example.ducnguyenvan.uidemo.decoration.MyDivider;
import com.example.ducnguyenvan.uidemo.decoration.MyItemDecorationCallback;
import com.example.ducnguyenvan.uidemo.diffutil.OnLoadMoreListener;
import com.example.ducnguyenvan.uidemo.model.Item1Pic;
import com.example.ducnguyenvan.uidemo.model.Item3Pics;
import com.example.ducnguyenvan.uidemo.model.ItemButton;
import com.example.ducnguyenvan.uidemo.model.ItemLabel;
import com.example.ducnguyenvan.uidemo.model.ItemLoading;
import com.example.ducnguyenvan.uidemo.model.MyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class subtabTinNong extends subtab {
    private static class MyLinearLayoutManager extends LinearLayoutManager {
        @Override
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public MyLinearLayoutManager(Context context, int orientation, Boolean rev) {
            super(context,orientation,rev);
        }
    }

    private List<RecyclerView.ItemDecoration> mItemDecorationList = new ArrayList<>();

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
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        //MyDivider myDivider = new MyDivider(recyclerView.getContext());
        //recyclerView.addItemDecoration(myDivider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);

        final ArrayList<MyItem> listItems = new ArrayList<>();
        listItems.add(new Item1Pic(R.drawable.antimage, "Road to Herald with Antimage: buy Blink Dagger as the first item, then build Dagon5 ASAP. Basic combo: Dagger, Dagon, W to run, ez money, ez life, blah blah blah blah blah ", "Dota2", 10, stringToTimestamp("2018-04-12 11:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.axe, "Axe from Dota2","Dota2", 69, stringToTimestamp("2018-04-11 11:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.es, "Earthshaker from Dota2", "Dota2", 0, stringToTimestamp("2018-04-12 05:10:00.000")));
        listItems.add(new ItemLabel("League Of Legends"));
        listItems.add(new Item1Pic(R.drawable.rumble, "Rumble's new skin released!", "Riot", 4, stringToTimestamp("2018-04-02 14:10:00.000")));
        listItems.add(new Item3Pics(R.drawable.projecticon, R.drawable.yiproject, R.drawable.zedproject, "\"Project\" skin series first starts with Yi Project and Zed Project", "Riot", 80, stringToTimestamp("2018-04-13 10:00:00.000")));
        listItems.add(new ItemButton("Read more..."));
        listItems.add(new ItemLabel("Pets"));
        listItems.add(new Item3Pics(R.drawable.marutaro, R.drawable.marutaro2, R.drawable.marutaro3,"Marutaro from Chà-pan", "Kênh 14", 5, stringToTimestamp("2018-04-12 10:00:00.000")));
        listItems.add(new Item1Pic(R.drawable.shironeko, "Shironeko from Chà-pan","Chà-pan", 70, stringToTimestamp("2018-04-02 11:10:00.000")));
        listItems.add(new ItemButton("Read more..."));
        listItems.add(new Item1Pic(R.drawable.sven, "Sven from Dota2","Dota2", 0, stringToTimestamp("2018-04-12 01:10:00.000")));
        listItems.add(new Item1Pic(R.drawable.templar, "Lanaya - Templar Assassin from Dota2","Dota2", 10, stringToTimestamp("2018-04-12 11:08:00.000")));
        //listItems.add(new ItemVideo(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test),"Today is Friday 13th...", "Kênh  14", 5, stringToTimestamp("2018-04-12 11:48:00.000")));
        final MyAdapter myAdapter = new MyAdapter(listItems, getContext(),recyclerView);
        recyclerView.setAdapter(myAdapter);
        addBtnDivider();
        addLabelDivider();
        addRestDivider();
        showItemDecoration(recyclerView);
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
        //newList = listItems;
        //Collections.shuffle(newList);
        //return newList;
        Random randomGen = new Random();
        for (int i = 0; i <= listItems.size(); i++) {
            int itemIndex = randomGen.nextInt(listItems.size());
            MyItem newItem = listItems.get(itemIndex);
            newList.add(newItem);
            listItems.remove(itemIndex);
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

    private void addBtnDivider() {
        mItemDecorationList.add(new MyDivider(ContextCompat.getDrawable(this.getContext(),R.drawable.divider_large),0, new MyItemDecorationCallback(ItemButton.class)));
    }

    private void addLabelDivider() {
        mItemDecorationList.add(new MyDivider(ContextCompat.getDrawable(this.getContext(),R.drawable.divider_large),0, new MyItemDecorationCallback(){
            @Override
            public boolean shouldDecor(MyItem item, @Nullable MyItem nextItem) {
                return (nextItem != null && nextItem instanceof ItemLabel);
            }
        }));
    }

    private void addRestDivider() {
        mItemDecorationList.add(new MyDivider(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_medium),10,new MyItemDecorationCallback(){
            @Override
            public boolean shouldDecor(MyItem item, @Nullable MyItem nextItem) {
                return (nextItem != null && !nextItem.getClass().isAssignableFrom(ItemLabel.class)&& !item.getClass().isAssignableFrom(ItemLabel.class) && !item.getClass().isAssignableFrom(ItemButton.class) && !item.getClass().isAssignableFrom(ItemLoading.class));
            }
        }));
    }
    private void showItemDecoration(RecyclerView recyclerView) {
        for (RecyclerView.ItemDecoration itemDecoration : mItemDecorationList) {
            recyclerView.addItemDecoration(itemDecoration);
        }
    }
}
