package com.lantian.lt_smart_pasture.view.home.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class BottomNavigationAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public BottomNavigationAdapter(FragmentManager fragmentManager, List<Fragment> fragments)
        {
            super(fragmentManager);
            this.fragmentList = fragments;
        }
        @Override
        public Fragment getItem(int i) {
            return this.fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return this.fragmentList.size();
        }
    }

