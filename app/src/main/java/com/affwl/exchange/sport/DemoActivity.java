
package com.affwl.exchange.sport;

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
import android.widget.Button;

import com.affwl.exchange.R;

public class DemoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerDemo;
    Button right,left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        drawerDemo = (DrawerLayout) findViewById(R.id.drawerDemo);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerDemo, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerDemo.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view1);
        navigationView1.setNavigationItemSelectedListener(this);

        final NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerDemo.openDrawer(navigationView1);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerDemo.openDrawer(navigationView2);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {

        if (drawerDemo.isDrawerOpen(GravityCompat.START)) {
            drawerDemo.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}