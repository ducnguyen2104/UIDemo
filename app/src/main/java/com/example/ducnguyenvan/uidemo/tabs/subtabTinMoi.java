package com.example.ducnguyenvan.uidemo.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ducnguyenvan.uidemo.MyAdapter;
import com.example.ducnguyenvan.uidemo.R;
import com.example.ducnguyenvan.uidemo.model.MyItem;

import java.util.ArrayList;

public class subtabTinMoi extends subtab {

    public static subtabTinMoi newInstance(int position) {
        Bundle args = new Bundle();
        subtabTinMoi fragment = new subtabTinMoi();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtabTinMoi() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tinmoi, container, false);
        initRecyclerView(rootView);
        return rootView;
    }

    @Override
    public void initRecyclerView(View rootView) {
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ArrayList<MyItem> listItems = new ArrayList<>();
        //listItems.add(new Item1Pic(R.drawable.antimage, "Antimage from Dota2", "Dota2", 10, stringToTimestamp("2018-04-12 11:10:00.000")));
        //listItems.add(new Item1Pic(R.drawable.axe, "Axe from Dota2","Dota2", 69, stringToTimestamp("2018-04-11 11:10:00.000")));
        //listItems.add(new Item1Pic(R.drawable.es, "Earthshaker from Dota2", "Dota2", 0, stringToTimestamp("2018-04-12 05:10:00.000")));
        //listItems.add(new Item1Pic(R.drawable.marutaro, "Marutaro from Chà-pan", "Kênh 14", 5, stringToTimestamp("2018-04-12 10:00:00.000")));
        //listItems.add(new ItemLabel("League Of Legends"));
        //listItems.add(new Item1Pic(R.drawable.rumble, "Rumble's new skin released!", "Riot", 4, stringToTimestamp("2018-04-02 14:10:00.000")));
        //listItems.add(new Item3Pics(R.drawable.projecticon, R.drawable.yiproject, R.drawable.zedproject, "\"Project\" skin series first starts with Yi Project and Zed Project", "Riot", 80, stringToTimestamp("2018-04-13 10:00:00.000")));
        //listItems.add(new ItemButton("Read more..."));
        //listItems.add(new Item1Pic(R.drawable.shironeko, "Shironeko from Chà-pan","Chà-pan", 70, stringToTimestamp("2018-04-02 11:10:00.000")));
        //listItems.add(new Item1Pic(R.drawable.sven, "Sven from Dota2","Dota2", 0, stringToTimestamp("2018-04-12 01:10:00.000")));
        //listItems.add(new Item1Pic(R.drawable.templar, "Lanaya - Templar Assassin from Dota2","Dota2", 10, stringToTimestamp("2018-04-12 11:08:00.000")));
        //listItems.add(new ItemVideo(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test),"Today is Friday 13th...", "Kênh  14", 5, stringToTimestamp("2018-04-12 11:48:00.000")));
        MyAdapter myAdapter = new MyAdapter(listItems, getContext(),recyclerView);
        recyclerView.setAdapter(myAdapter);
    }
}
