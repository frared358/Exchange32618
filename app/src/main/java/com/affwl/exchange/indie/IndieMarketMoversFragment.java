package com.affwl.exchange.indie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.affwl.exchange.R;

public class IndieMarketMoversFragment extends Fragment {

    TabLayout tabLayoutIndieMarketMovers;
    ViewPager viewPagerIndieMarketMovers;
    private String[] pageTitle = {"Equity", "Derivatives"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indie_market_movers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutIndieMarketMovers = (TabLayout)view.findViewById(R.id.tabLayoutIndieMarketMovers);
        viewPagerIndieMarketMovers = (ViewPager)view.findViewById(R.id.viewPagerIndieMarketMovers);

        for (int i = 0; i < 2; i++) {
            tabLayoutIndieMarketMovers.addTab(tabLayoutIndieMarketMovers.newTab().setText(pageTitle[i]));
        }

        tabLayoutIndieMarketMovers.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerIndieMarketMovers.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerIndieMarketMovers.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutIndieMarketMovers));

        //change ViewPager page when tab selected
        tabLayoutIndieMarketMovers.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerIndieMarketMovers.setCurrentItem(tab.getPosition());
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
                return new IndieMarketMoversEquityFragment();
            } else return new IndieMarketMoversDerivativesFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
