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

public class MarketCurrencyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static ViewPager viewPagerMarketCurrency;
    private DrawerLayout drawerLayoutMarketCurrency;
    private TabLayout tabLayoutMarketCurrency;

    private String[] pageTitle = {"Overview", "Market Depth","Futures"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_currency);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPagerMarketCurrency = (ViewPager)findViewById(R.id.viewPagerMarketCurrency);
        drawerLayoutMarketCurrency = (DrawerLayout) findViewById(R.id.drawerLayoutMarketCurrency);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutMarketCurrency, toolbar,
                R.string.home_page, R.string.logout);
        drawerLayoutMarketCurrency.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayoutMarketCurrency = (TabLayout) findViewById(R.id.tabLayoutMarketCurrency);
        for (int i = 0; i < 3; i++) {
            tabLayoutMarketCurrency.addTab(tabLayoutMarketCurrency.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutMarketCurrency.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewMarketCurrency);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMarketCurrency.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMarketCurrency.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMarketCurrency));

        //change ViewPager page when tab selected
        tabLayoutMarketCurrency.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMarketCurrency.setCurrentItem(tab.getPosition());
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

        drawerLayoutMarketCurrency.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutMarketCurrency != null;
        if (drawerLayoutMarketCurrency.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMarketCurrency.closeDrawer(GravityCompat.START);
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
