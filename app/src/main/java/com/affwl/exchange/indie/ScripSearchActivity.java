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

public class ScripSearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    public static ViewPager viewPagerScripSearch;
    private DrawerLayout drawerLayoutScripSearch;
    private TabLayout tabLayoutScripSearch;

    private String[] pageTitle = {"Equity","Futures", "Currency\nFutures"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrip_search);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPagerScripSearch = (ViewPager)findViewById(R.id.viewPagerScripSearch);
        drawerLayoutScripSearch = (DrawerLayout) findViewById(R.id.drawerLayoutScripSearch);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutScripSearch, toolbar,
                R.string.home_page, R.string.logout);
        drawerLayoutScripSearch.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayoutScripSearch = (TabLayout) findViewById(R.id.tabLayoutScripSearch);
        for (int i = 0; i < 3; i++) {
            tabLayoutScripSearch.addTab(tabLayoutScripSearch.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutScripSearch.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewScripSearch);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ScripSearchActivity.ViewPagerAdapter pagerAdapter = new ScripSearchActivity.ViewPagerAdapter(getSupportFragmentManager());
        viewPagerScripSearch.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerScripSearch.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutScripSearch));

        //change ViewPager page when tab selected
        tabLayoutScripSearch.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerScripSearch.setCurrentItem(tab.getPosition());
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

        drawerLayoutScripSearch.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutScripSearch != null;
        if (drawerLayoutScripSearch.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutScripSearch.closeDrawer(GravityCompat.START);
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
                return new FragmentScripSearchEquity();
            } else if (position == 1) {
                return new FragmentScripSearchFutures();
            } else {
                return new FragmentScripSearchCurrencyFutures();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
