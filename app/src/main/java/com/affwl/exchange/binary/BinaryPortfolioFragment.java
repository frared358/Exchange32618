package com.affwl.exchange.binary;


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

import com.affwl.exchange.*;

public class BinaryPortfolioFragment extends Fragment {



    TabLayout tabLayoutBinaryPortfolioProduct;
    ViewPager viewPagerBinaryPortfolioProduct;
    private String[] pageTitle = {"60 Sec", "Binary\nOption", "Long\nTerm","Pairs"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_portfolio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutBinaryPortfolioProduct = (TabLayout)view.findViewById(R.id.tabLayoutBinaryPortfolioProduct);
        viewPagerBinaryPortfolioProduct = (ViewPager)view.findViewById(R.id.viewPagerBinaryPortfolioProduct);

        for (int i = 0; i < 4; i++) {
            tabLayoutBinaryPortfolioProduct.addTab(tabLayoutBinaryPortfolioProduct.newTab().setText(pageTitle[i]));
        }

        tabLayoutBinaryPortfolioProduct.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerBinaryPortfolioProduct.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerBinaryPortfolioProduct.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutBinaryPortfolioProduct));

        //change ViewPager page when tab selected
        tabLayoutBinaryPortfolioProduct.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerBinaryPortfolioProduct.setCurrentItem(tab.getPosition());
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
                return new BinaryPortfolioDataFragment();
            } else if (position == 1) {
                return new BinaryPortfolioDataFragment();
            }else if (position == 2) {
                return new BinaryPortfolioDataFragment();
            } else {
                return new BinaryPortfolioDataFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
