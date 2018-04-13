package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class subtabAhihi extends subtab {
    public static subtabAhihi newInstance(int position) {
        Bundle args = new Bundle();
        subtabAhihi fragment = new subtabAhihi();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtabAhihi() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ahihi, container, false);
        return rootView;
    }
}
