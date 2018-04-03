package com.affwl.exchange.settings;

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


public class IndieSettingFragment extends Fragment {

    public static ViewPager viewPagerIndie;
    private TabLayout tabLayoutIndie;
    private String[] pageTitle = {"General","Profile"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indie_setting, container, false);
        return view;
    }

        //Do your main coding of this page betweem this
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        viewPagerIndie = (ViewPager) view.findViewById(R.id.viewPagerIndie);
        tabLayoutIndie = (TabLayout) view.findViewById(R.id.tab_layout_Indie);

        for (int i = 0; i < 2; i++) {
            tabLayoutIndie.addTab(tabLayoutIndie.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutIndie.setTabGravity(TabLayout.GRAVITY_FILL);


        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerIndie.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerIndie.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutIndie));

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
                return new GeneralIndieSettingFragment();
            }  else {
                return new ProfileIndieSettingFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
