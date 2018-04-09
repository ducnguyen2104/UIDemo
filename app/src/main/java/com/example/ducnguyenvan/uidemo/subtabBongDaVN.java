package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class subtabBongDaVN extends Fragment {

    public static subtabBongDaVN newInstance(int position) {
        Bundle args = new Bundle();
        subtabBongDaVN fragment = new subtabBongDaVN();

        args.putInt("position",position);
        fragment.setArguments(args);
        Log.i("Subtab", "bong da");
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
