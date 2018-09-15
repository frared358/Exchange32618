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


public class IndiePortfolioFragment extends Fragment {


    TabLayout tabLayoutIndiePortfolio;
    ViewPager viewPagerIndiePortfolio;
    private String[] pageTitle = {"Live", "Custom"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_indie_portfolio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutIndiePortfolio =(TabLayout)view.findViewById(R.id.tabLayoutIndiePortfolio);
        viewPagerIndiePortfolio =(ViewPager)view.findViewById(R.id.viewPagerIndiePortfolio);

        for (int i = 0; i < 2; i++) {
            tabLayoutIndiePortfolio.addTab(tabLayoutIndiePortfolio.newTab().setText(pageTitle[i]));
        }

        tabLayoutIndiePortfolio.setTabGravity(TabLayout.GRAVITY_FILL);

        IndiePortfolioFragment.ViewPagerAdapter pagerAdapter = new IndiePortfolioFragment.ViewPagerAdapter(getChildFragmentManager());
        viewPagerIndiePortfolio.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerIndiePortfolio.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutIndiePortfolio));


        tabLayoutIndiePortfolio.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerIndiePortfolio.setCurrentItem(tab.getPosition());
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
                return new IndiePortfolioLiveFragment();
            } else return new IndiePortfolioCustomFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
