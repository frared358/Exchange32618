package com.affwl.exchange.indie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class MarketWatchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayoutIndieMarketWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_watch);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayoutIndieMarketWatch =(DrawerLayout)findViewById(R.id.drawerLayoutIndieMarketWatch);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutIndieMarketWatch, toolbar,
                R.string.open, R.string.close);
        drawerLayoutIndieMarketWatch.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewIndieMarketWatch);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

        } else if (id == R.id.logout) {
            finish();
        }

        drawerLayoutIndieMarketWatch.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutIndieMarketWatch != null;
        if (drawerLayoutIndieMarketWatch.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutIndieMarketWatch.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
