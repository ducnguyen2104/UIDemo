package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class subtabOMG extends subtab {
    public static subtabOMG newInstance(int position) {
        Bundle args = new Bundle();
        subtabOMG fragment = new subtabOMG();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtabOMG() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.omg, container, false);
        return rootView;
    }
}
