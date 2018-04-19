package com.affwl.exchange.trades;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.affwl.exchange.R;

public class TradesIndieFragment extends Fragment {


    TabLayout tabLayoutIndie;
    LinearLayout ll_fragment_indie;
    ViewPager viewPagerIndie;
    private String[] pageTitle = {"Trades", "Order"};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trades_indie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_fragment_indie=view.findViewById(R.id.ll_fragment_indie);
        ll_fragment_indie.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(v.getContext(), "Fragment ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        tabLayoutIndie = (TabLayout)view.findViewById(R.id.tabLayoutIndie);
        viewPagerIndie = (ViewPager)view.findViewById(R.id.viewPagerIndie);

        for (int i = 0; i < pageTitle.length; i++) {
            tabLayoutIndie.addTab(tabLayoutIndie.newTab().setText(pageTitle[i]));
        }

        tabLayoutIndie.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerIndie.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerIndie.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutIndie));

        //change ViewPager page when tab selected
        tabLayoutIndie.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerIndie.setCurrentItem(tab.getPosition());
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
                return new TIndieTradeFragment();
            } else if (position == 1) {
                return new TIndieTradeFragment();
            } else {
                return new TIndieTradeFragment();
            }
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }
    }
}
