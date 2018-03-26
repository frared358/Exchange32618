package com.affwl.exchange.indie;

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

public class IndieMarketFragment extends Fragment {


    TabLayout tabLayoutIndieMarket;
    ViewPager viewPagerIndieMarket;
    private String[] pageTitle = {"Indices", "Derivatives", "Currency"};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_indie_market, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutIndieMarket = (TabLayout)view.findViewById(R.id.tabLayoutIndieMarket);
        viewPagerIndieMarket = (ViewPager)view.findViewById(R.id.viewPagerIndieMarket);

        for (int i = 0; i < 3; i++) {
            tabLayoutIndieMarket.addTab(tabLayoutIndieMarket.newTab().setText(pageTitle[i]));
        }

        tabLayoutIndieMarket.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerIndieMarket.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerIndieMarket.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutIndieMarket));

        //change ViewPager page when tab selected
        tabLayoutIndieMarket.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerIndieMarket.setCurrentItem(tab.getPosition());
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
                return new IndieMarketIndicesFragment();
            } else if (position == 1) {
                return new IndieMarketDerivativeFragment();
            } else {
                return new IndieMarketCurrencyFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
