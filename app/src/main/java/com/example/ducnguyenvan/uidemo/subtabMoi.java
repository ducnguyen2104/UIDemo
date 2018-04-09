package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class subtabMoi extends Fragment {

    public static subtabMoi newInstance(int position) {
        Bundle args = new Bundle();
        subtabMoi fragment = new subtabMoi();
        args.putInt("position",position);
        fragment.setArguments(args);
        Log.i("Subtab", "tin moi");
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
        return rootView;
    }
}