package com.example.ducnguyenvan.uidemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class tabTinTuc extends Fragment{

    public static tabTinTuc newInstance() {
        tabTinTuc fragment = new tabTinTuc();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tintuc, container,  false);
        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.container_main);
        SectionsPagerAdapter mSectionPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionPagerAdapter);
        return rootView;
    }

    private  class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return subtabTinNong.newInstance(1);
                case 1:
                    return subtabTinMoi.newInstance(2);
                case 2:
                    return subtabBongDaVN.newInstance(3);
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
            switch (position) {
                case 0:
                    return "Tin nóng";
                case 1:
                    return "Tin mới";
                case 2:
                    return "Bóng dá VN";

            }
            return null;
        }
    }
}
