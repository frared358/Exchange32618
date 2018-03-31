package com.affwl.exchange.binary;

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
import android.view.View;

import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;


public class TradeActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayoutBinaryTrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_trade);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayoutBinaryTrade = (DrawerLayout)findViewById(R.id.drawerLayoutBinaryTrade);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutBinaryTrade, toolbar,
                R.string.open, R.string.close);
        drawerLayoutBinaryTrade.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewBinaryTrade);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
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
            startActivity(new Intent(this,BinaryPotfolioActivity.class));
        } else if (id == R.id.mHome) {
            startActivity(new Intent(this,MainActivity.class));
        }

        drawerLayoutBinaryTrade.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutBinaryTrade != null;
        if (drawerLayoutBinaryTrade.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutBinaryTrade.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
