package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class subtabTinMoi extends Fragment {

    public static subtabTinMoi newInstance(int position) {
        Bundle args = new Bundle();
        subtabTinMoi fragment = new subtabTinMoi();
        args.putInt("position",position);
        fragment.setArguments(args);
        Log.i("Subtab", "tin moi");
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
        return rootView;
    }
}
