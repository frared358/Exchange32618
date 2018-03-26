package com.affwl.exchange.sport;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affwl.exchange.R;


public class TimerFragment extends Fragment {

    TabLayout tabLayoutTime;
    ViewPager viewPagerTime;
    private int[] icon = {R.drawable.football,R.drawable.horse_head,R.drawable.cricket};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutTime = (TabLayout)view.findViewById(R.id.tabLayoutTime);
        viewPagerTime = (ViewPager)view.findViewById(R.id.viewPagerTime);

        for (int i = 0; i < 3; i++) {
            tabLayoutTime.addTab(tabLayoutTime.newTab().setIcon(icon[i]));
        }

        tabLayoutTime.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerTime.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerTime.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutTime));

        //change ViewPager page when tab selected
        tabLayoutTime.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerTime.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position ==0) {
                return new FragmentAllSport();
            }else if(position ==1) {
                return new FragmentAllSport();
            }
            else {
                return new FragmentAllSport();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
