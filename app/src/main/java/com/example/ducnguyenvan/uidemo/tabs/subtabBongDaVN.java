package com.example.ducnguyenvan.uidemo.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ducnguyenvan.uidemo.R;

public class subtabBongDaVN extends subtab {

    public static subtabBongDaVN newInstance(int position) {
        Bundle args = new Bundle();
        subtabBongDaVN fragment = new subtabBongDaVN();

        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtabBongDaVN() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bongda, container, false);
        return rootView;
    }
}
