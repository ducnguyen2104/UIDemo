package com.example.ducnguyenvan.uidemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class subtabMoi extends subtab {

    public static subtabMoi newInstance(int position) {
        Bundle args = new Bundle();
        subtabMoi fragment = new subtabMoi();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtabMoi() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.moi, container, false);
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
        listItems.add(new ItemVideo(Uri.parse("android.resource://"+getContext().getPackageName()+"/"+R.raw.test),"Today is Friday 13th...", "Kênh  14", 5, stringToTimestamp("2018-04-12 11:48:00.000")));
        MyAdapter myAdapter = new MyAdapter(listItems, getContext(),recyclerView);
        recyclerView.setAdapter(myAdapter);
    }
}
