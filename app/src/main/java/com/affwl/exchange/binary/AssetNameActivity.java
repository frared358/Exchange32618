package com.affwl.exchange.binary;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class AssetNameActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayoutBinaryAsset;
    LinearLayout llSelectAsset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_asset_name);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayoutBinaryAsset = (DrawerLayout)findViewById(R.id.drawerLayoutBinaryAsset);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutBinaryAsset, toolbar,
                R.string.open, R.string.close);
        drawerLayoutBinaryAsset.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewBinaryAsset);
        navigationView.setItemIconTintList(null);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        llSelectAsset = (LinearLayout)findViewById(R.id.llSelectAsset);
        llSelectAsset.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mProduct) {
            startActivity(new Intent(this,Sec60Activity.class));
        } else if (id == R.id.mAsset) {

        } else if (id == R.id.mTrade) {
            startActivity(new Intent(this,AssetListActivity.class));
        } else if (id == R.id.mAccount) {
            startActivity(new Intent(this,MainActivity.class));
        } else if (id == R.id.mPortfolio) {
            startActivity(new Intent(this,BinaryPotfolioActivity.class));
        } else if (id == R.id.mHome) {
            startActivity(new Intent(this,MainActivity.class));
        }

        drawerLayoutBinaryAsset.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutBinaryAsset != null;
        if (drawerLayoutBinaryAsset.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutBinaryAsset.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llSelectAsset: startActivity(new Intent(this,AssetListActivity.class)); break;
        }
    }
}
