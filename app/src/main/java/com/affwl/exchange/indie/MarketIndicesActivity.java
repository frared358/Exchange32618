package com.affwl.exchange.indie;

import android.content.Intent;
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
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class MarketIndicesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static ViewPager viewPagerMarketIndices;
    private DrawerLayout drawerLayoutMarketIndices;
    private TabLayout tabLayoutMarketIndices;
    private String[] pageTitle = {"Overview", "Gainers", "Losers", "Futures", "Stocks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_indices);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPagerMarketIndices = (ViewPager)findViewById(R.id.viewPagerMarketIndices);
        drawerLayoutMarketIndices = (DrawerLayout) findViewById(R.id.drawerLayoutMarketIndices);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutMarketIndices, toolbar,
                R.string.home_page, R.string.logout);
        drawerLayoutMarketIndices.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayoutMarketIndices = (TabLayout) findViewById(R.id.tabLayoutMarketIndices);
        for (int i = 0; i < 5; i++) {
            tabLayoutMarketIndices.addTab(tabLayoutMarketIndices.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutMarketIndices.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewMarketIndices);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(MarketIndicesActivity.this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMarketIndices.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMarketIndices.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMarketIndices));

        //change ViewPager page when tab selected
        tabLayoutMarketIndices.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMarketIndices.setCurrentItem(tab.getPosition());
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
            DataHolder.navigationForTab = 0;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.tabMarketWatch) {
            DataHolder.navigationForTab = 1;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.tabMarketMovers) {
            DataHolder.navigationForTab = 2;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.tabPortfolio) {
            DataHolder.navigationForTab = 3;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.home) {
            startActivity(new Intent(this,MainActivity.class));
        } else if (id == R.id.pivot) {
            startActivity(new Intent(this,PivotActivity.class));
        } else if (id == R.id.liveTips) {
            startActivity(new Intent(this,LiveTipsActivity.class));
        } else if (id == R.id.charts) {

        } else if (id == R.id.newHilo) {
            startActivity(new Intent(this,NewHiloActivity.class));
        } else if (id == R.id.scanner) {

        } else if (id == R.id.dataQuery) {

        }

        drawerLayoutMarketIndices.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutMarketIndices != null;
        if (drawerLayoutMarketIndices.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMarketIndices.closeDrawer(GravityCompat.START);
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
                return new FragmentMarketIndicesOverview();
            } else if (position == 1) {
                return new FragmentMarketIndicesGainers();
            } else if (position == 2) {
                return new FragmentMarketIndicesLosers();
            } else if (position == 3) {
                return new FragmentMarketIndicesFutures();
            }else {
                return new FragmentMarketIndicesStocks();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
