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

public class LiveTipsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayoutIndieLiveTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tips);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayoutIndieLiveTips = (DrawerLayout)findViewById(R.id.drawerLayoutIndieLiveTips);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutIndieLiveTips, toolbar,
                R.string.open, R.string.close);
        drawerLayoutIndieLiveTips.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewIndieLiveTips);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(LiveTipsActivity.this);
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

        } else if (id == R.id.charts) {

        } else if (id == R.id.newHilo) {
            startActivity(new Intent(this,NewHiloActivity.class));
        } else if (id == R.id.scanner) {

        } else if (id == R.id.dataQuery) {

        } else if (id == R.id.logout) {
            finish();
        }

        drawerLayoutIndieLiveTips.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutIndieLiveTips != null;
        if (drawerLayoutIndieLiveTips.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutIndieLiveTips.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
