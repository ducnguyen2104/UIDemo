package com.example.ducnguyenvan.uidemo.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class subtab extends Fragment {

    protected static subtab newInstance(int position){
        Bundle args = new Bundle();
        subtab fragment = new subtab();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    public subtab() {

    }

    public Timestamp stringToTimestamp(String string) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+07:00"));
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Timestamp(parsedDate.getTime());
    }

    public void initRecyclerView(View rootView) {

    }
}
