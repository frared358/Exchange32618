
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


public class FavFragment extends Fragment {

    TabLayout tabLayoutFav;
    ViewPager viewPagerFav;
    private int[] icon = {R.drawable.play,R.drawable.star_small};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutFav = (TabLayout)view.findViewById(R.id.tabLayoutFav);
        viewPagerFav = (ViewPager)view.findViewById(R.id.viewPagerFav);

        for (int i = 0; i < 2; i++) {
            tabLayoutFav.addTab(tabLayoutFav.newTab().setIcon(icon[i]));
        }

        tabLayoutFav.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerFav.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerFav.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutFav));

        //change ViewPager page when tab selected
        tabLayoutFav.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerFav.setCurrentItem(tab.getPosition());
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
                return new FragmentBlankSport();
            } else {
                return new FragmentBlankSport();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}