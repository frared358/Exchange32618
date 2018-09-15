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
import android.widget.RelativeLayout;

import com.affwl.exchange.*;


public class Sec60Activity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

    RelativeLayout rl60Sec,rlBinaryOption,rlLongTerm,rlPairs;
    private DrawerLayout drawerLayoutBinary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_sec60);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        rl60Sec = (RelativeLayout)findViewById(R.id.rl60Sec);
        rlBinaryOption = (RelativeLayout)findViewById(R.id.rlBinaryOption);
        rlLongTerm = (RelativeLayout)findViewById(R.id.rlLongTerm);
        rlPairs = (RelativeLayout)findViewById(R.id.rlPairs);

        rl60Sec.setOnClickListener(this);
        rlBinaryOption.setOnClickListener(this);
        rlLongTerm.setOnClickListener(this);
        rlPairs.setOnClickListener(this);


        drawerLayoutBinary = (DrawerLayout)findViewById(R.id.drawerLayoutBinary);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutBinary, toolbar,R.string.open, R.string.close);
        drawerLayoutBinary.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navViewBinary);
        navigationView.setItemIconTintList(null);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl60Sec: startActivity(new Intent(this,AssetNameActivity.class)); break;
            case R.id.rlBinaryOption: startActivity(new Intent(this,AssetNameActivity.class)); break;
            case R.id.rlLongTerm: startActivity(new Intent(this,AssetNameActivity.class)); break;
            case R.id.rlPairs: startActivity(new Intent(this,AssetNameActivity.class)); break;

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mProduct) {

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

        drawerLayoutBinary.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutBinary != null;
        if (drawerLayoutBinary.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutBinary.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
