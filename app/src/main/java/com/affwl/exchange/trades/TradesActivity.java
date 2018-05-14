package com.affwl.exchange.trades;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.affwl.exchange.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TradesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    public static ViewPager viewPager_main_trades;
    private DrawerLayout trades_drawer_layout;
    private TabLayout tabs_main_trades;
    private String[] pageTitle = {"Indie", "Fx", "60 Sec", "Crypto","Sports"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trades);
        Toolbar toolbarTrades = (Toolbar)findViewById(R.id.toolbarTrades);
        setSupportActionBar(toolbarTrades);

        viewPager_main_trades = (ViewPager)findViewById(R.id.viewPager_main_trades);
        trades_drawer_layout = (DrawerLayout) findViewById(R.id.trades_drawer_layout);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, trades_drawer_layout, toolbarTrades, R.string.home_page, R.string.logout);
        trades_drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabs_main_trades = (TabLayout) findViewById(R.id.tabs_main_trades);
        for (int i = 0; i < pageTitle.length; i++) {
            tabs_main_trades.addTab(tabs_main_trades.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabs_main_trades.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView trades_nav_view = (NavigationView) findViewById(R.id.trades_nav_view);
        assert trades_nav_view != null;
        trades_nav_view.setNavigationItemSelectedListener(TradesActivity.this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager_main_trades.setAdapter(pagerAdapter);


        //change Tab selection when swipe ViewPager
        viewPager_main_trades.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs_main_trades));

        //change ViewPager page when tab selected

        tabs_main_trades.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager_main_trades.setCurrentItem(tab.getPosition());
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.tabMarket) {
            viewPager_main_trades.setCurrentItem(0);
        } else if (id == R.id.tabMarketWatch) {
            viewPager_main_trades.setCurrentItem(1);
        } else if (id == R.id.tabMarketMovers) {
            viewPager_main_trades.setCurrentItem(2);
        } else if (id == R.id.tabPortfolio) {
            viewPager_main_trades.setCurrentItem(3);
        }
        trades_drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert trades_drawer_layout != null;
        if (trades_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            trades_drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position ==0) {
                return new TradesIndieFragment();
            } else if (position == 1) {
                return new TradesFxFragment();
            } else if (position == 2) {
                return new TradesSixtySecFragment();
            }  else if (position == 3) {
                return new TradesCryptoFragment();
            }
            else if (position == 4) {
                return new TradesSportsFragment();
            }
            else {
                return new TradesIndieFragment();
            }
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }
    }

}


