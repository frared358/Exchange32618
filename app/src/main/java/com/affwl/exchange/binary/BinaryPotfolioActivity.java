package com.affwl.exchange.binary;

import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class BinaryPotfolioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayoutBinaryPortfolio;

    public static ViewPager viewPagerBinaryPortfolio;
    private TabLayout tabLayoutBinaryPortfolio;
    private String[] pageTitle = {"Open Position", "Expire Position"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_potfolio);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayoutBinaryPortfolio = (DrawerLayout)findViewById(R.id.drawerLayoutBinaryPortfolio);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutBinaryPortfolio, toolbar,
                R.string.open, R.string.close);
        drawerLayoutBinaryPortfolio.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewBinaryPortfolio);
        navigationView.setItemIconTintList(null);

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        viewPagerBinaryPortfolio = (ViewPager)findViewById(R.id.viewPagerBinaryPortfolio);
        tabLayoutBinaryPortfolio = (TabLayout) findViewById(R.id.tabLayoutBinaryPortfolio);
        for (int i = 0; i < 2; i++) {
            tabLayoutBinaryPortfolio.addTab(tabLayoutBinaryPortfolio.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayoutBinaryPortfolio.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerBinaryPortfolio.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerBinaryPortfolio.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutBinaryPortfolio));

        tabLayoutBinaryPortfolio.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerBinaryPortfolio.setCurrentItem(tab.getPosition());
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
        int id = item.getItemId();

        if (id == R.id.mProduct) {
            startActivity(new Intent(this,Sec60Activity.class));
        } else if (id == R.id.mAsset) {
            startActivity(new Intent(this,AssetNameActivity.class));
        } else if (id == R.id.mTrade) {
            startActivity(new Intent(this,AssetListActivity.class));
        } else if (id == R.id.mAccount) {
            startActivity(new Intent(this,MainActivity.class));
        } else if (id == R.id.mPortfolio) {

        } else if (id == R.id.mHome) {
            startActivity(new Intent(this,MainActivity.class));
        }

        drawerLayoutBinaryPortfolio.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutBinaryPortfolio != null;
        if (drawerLayoutBinaryPortfolio.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutBinaryPortfolio.closeDrawer(GravityCompat.START);
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
                return new BinaryPortfolioFragment();
            } else {
                return new BinaryPortfolioFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
