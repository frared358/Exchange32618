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

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class MarketDerivativesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ViewPager viewPagerMarketDerivative;
    private DrawerLayout drawerLayoutMarketDerivative;
    private TabLayout tabLayoutMarketDerivative;

    private String[] pageTitle = {"Overview", "Market Depth","Futures"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_derivatives);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPagerMarketDerivative = (ViewPager)findViewById(R.id.viewPagerMarketDerivative);
        drawerLayoutMarketDerivative = (DrawerLayout) findViewById(R.id.drawerLayoutMarketDerivative);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutMarketDerivative, toolbar,
                R.string.home_page, R.string.logout);
        drawerLayoutMarketDerivative.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayoutMarketDerivative = (TabLayout) findViewById(R.id.tabLayoutMarketDerivative);
        for (int i = 0; i < 3; i++) {
            tabLayoutMarketDerivative.addTab(tabLayoutMarketDerivative.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutMarketDerivative.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewMarketDerivative);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMarketDerivative.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMarketDerivative.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMarketDerivative));

        //change ViewPager page when tab selected
        tabLayoutMarketDerivative.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMarketDerivative.setCurrentItem(tab.getPosition());
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

        drawerLayoutMarketDerivative.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutMarketDerivative != null;
        if (drawerLayoutMarketDerivative.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMarketDerivative.closeDrawer(GravityCompat.START);
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
                return new FragmentMarketDerivativeDepth();
            } else {
                return new FragmentMarketIndicesFutures();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
