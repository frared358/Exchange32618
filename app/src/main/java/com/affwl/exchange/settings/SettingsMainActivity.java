package com.affwl.exchange.settings;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.affwl.exchange.R;
import com.affwl.exchange.fx.Fx_Fragment_Settings;

public class SettingsMainActivity extends AppCompatActivity {

    //private DrawerLayout drawerLayoutIndie;
    public static ViewPager viewPagerMainSetting;
    private TabLayout tabLayoutmainsetting;
    Toolbar toolbar;
    private String[] pageTitle = {"General","FX","Indie","Sports"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);
        toolbar = (Toolbar)findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        /*toolbar.setTitle("DTS Setting");*/

        viewPagerMainSetting = (ViewPager)findViewById(R.id.viewPager_main_setting);
        tabLayoutmainsetting = (TabLayout) findViewById(R.id.tabs_main_setting);

        for (int i = 0; i < pageTitle.length; i++) {
            tabLayoutmainsetting.addTab(tabLayoutmainsetting.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutmainsetting.setTabGravity(TabLayout.GRAVITY_FILL);



        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMainSetting.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMainSetting.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutmainsetting));

        tabLayoutmainsetting.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMainSetting.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

        if (position == 0) {
                return new GeneralSettingFragment();
            } else if (position == 1) {
                return new FXSettingFragment();
            } else if (position == 2) {
                return new IndieSettingFragment();
            }else if (position == 3) {
                return new SportsSettingFragment();
            }
            else {
                return new GeneralSettingFragment();
            }
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }
    }

}
