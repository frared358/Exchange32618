package com.affwl.exchange.alerts;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.affwl.exchange.R;


public class AlertsActivity extends AppCompatActivity {

    public static ViewPager viewPagerMainAlert;
    private TabLayout tabLayoutmainalert;
    Toolbar toolbarAlert;
    private String[] pageTitle = {"General","Crypto","FX","Indie","60 Sec","Sports","Teen Patti"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerts_main);
        toolbarAlert = (Toolbar)findViewById(R.id.toolbarAlert);
        setSupportActionBar(toolbarAlert);
        /*toolbar.setTitle("DTS Setting");*/

        viewPagerMainAlert = (ViewPager)findViewById(R.id.viewPager_main_alert);
        tabLayoutmainalert = (TabLayout) findViewById(R.id.tabs_main_alert);

        for (int i = 0; i < pageTitle.length; i++) {
            tabLayoutmainalert.addTab(tabLayoutmainalert.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutmainalert.setTabGravity(TabLayout.GRAVITY_FILL);



        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMainAlert.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMainAlert.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutmainalert));

        tabLayoutmainalert.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMainAlert.setCurrentItem(tab.getPosition());
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
                return new GeneralAlertFragment();
            } else if (position == 1) {
                return new CryptoAlertFragment();
            } else if (position == 2) {
                return new FXAlertFragment();
            } else if (position == 3) {
                return new IndieAlertFragment();
            } else if (position == 4) {
                return new SixtySecAlertFragment();
            } else if (position == 5) {
                return new SportsAlertFragment();
            }
            else  if (position == 6) {
                return new TeenPattiAlertFragment();
            }
            else
            {
                return new GeneralAlertFragment();
            }
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }
    }

}
