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


public class IndieActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    public static ViewPager viewPagerIndie;
    private DrawerLayout drawerLayoutIndie;
    private TabLayout tabLayoutIndie;
    private String[] pageTitle = {"Market", "Market Watch", "Market Movers", "Portfolio"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indie);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarIndie);
        setSupportActionBar(toolbar);

        viewPagerIndie = (ViewPager)findViewById(R.id.viewPagerIndie);
        drawerLayoutIndie = (DrawerLayout) findViewById(R.id.drawerLayoutIndie);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutIndie, toolbar,
                R.string.home_page, R.string.logout);
        drawerLayoutIndie.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayoutIndie = (TabLayout) findViewById(R.id.tabLayoutIndie);
        for (int i = 0; i < 4; i++) {
            tabLayoutIndie.addTab(tabLayoutIndie.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutIndie.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewIndie);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(IndieActivity.this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerIndie.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerIndie.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutIndie));

        //change ViewPager page when tab selected
        if (DataHolder.navigationForTab!=0) {
            viewPagerIndie.setCurrentItem(DataHolder.navigationForTab);
        }
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.tabMarket) {
            viewPagerIndie.setCurrentItem(0);
        } else if (id == R.id.tabMarketWatch) {
            viewPagerIndie.setCurrentItem(1);
        } else if (id == R.id.tabMarketMovers) {
            viewPagerIndie.setCurrentItem(2);
        } else if (id == R.id.tabPortfolio) {
            viewPagerIndie.setCurrentItem(3);
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
        drawerLayoutIndie.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutIndie != null;
        if (drawerLayoutIndie.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutIndie.closeDrawer(GravityCompat.START);
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
                return new IndieMarketFragment();
            } else if (position == 1) {
                return new IndieMarketWatchFragment();
            } else if (position == 2) {
                return new IndieMarketMoversFragment();
            } else {
                return new IndiePortfolioFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}


