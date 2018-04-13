package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class tabVideo extends Fragment{
    public static tabVideo newInstance() {
        tabVideo fragment = new tabVideo();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video, container,  false);
        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.container_main);
        tabVideo.SectionsPagerAdapter mSectionPagerAdapter = new tabVideo.SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionPagerAdapter);
        return rootView;
    }

    private  class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.i("subtab position", ""+ position);
            switch (position) {
                case 0:
                    return subtabMoi.newInstance(1);
                case 1:
                    return subtabAhihi.newInstance(2);
                case 2:
                    return subtabOMG.newInstance(3);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.i("subtab title", ""+position);
            switch (position) {
                case 0:
                    return "Mới";
                case 1:
                    return "Ahihi";
                case 2:
                    return "OMG";

            }
            return null;
        }
    }
}
