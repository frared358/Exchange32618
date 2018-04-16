package com.affwl.exchange.trades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.affwl.exchange.R;

public class TradesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //private DrawerLayout drawerLayoutIndie;
    public static ViewPager viewPagerMainTrades;
    private TabLayout tabLayoutmainTrades;
    Toolbar toolbar;
    private String[] pageTitle = {"Trades","Order"};
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trades);
        toolbar = (Toolbar)findViewById(R.id.toolbarTrades);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.trades_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.trades_nav_view);
        navigationView.setNavigationItemSelectedListener(TradesActivity.this);


        viewPagerMainTrades = (ViewPager)findViewById(R.id.viewPager_main_trades);
        tabLayoutmainTrades = (TabLayout) findViewById(R.id.tabs_main_trades);

        for (int i = 0; i < pageTitle.length; i++) {
            tabLayoutmainTrades.addTab(tabLayoutmainTrades.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutmainTrades.setTabGravity(TabLayout.GRAVITY_FILL);



        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMainTrades.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMainTrades.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutmainTrades));

        tabLayoutmainTrades.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMainTrades.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return new TradesFragment();
            } else if (position == 1) {
                return new TradesFragment();
            }
            else {
                return new TradesFragment();
            }
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }
    }

}
