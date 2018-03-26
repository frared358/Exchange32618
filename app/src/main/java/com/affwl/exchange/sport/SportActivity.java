package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class SportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayoutSport;
    ViewPager viewPagerSport;
    DrawerLayout drawerSport;
    int[] icon = {R.drawable.stop_watch_greay,R.drawable.trophy_greay,R.drawable.fav_contact_greay};
    int[] iconWhite = {R.drawable.stop_watch_white,R.drawable.trophy_white,R.drawable.fav_contact_white};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerSport = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerSport, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerSport.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayoutSport = (TabLayout)findViewById(R.id.tabLayoutSport);
        viewPagerSport = (ViewPager)findViewById(R.id.viewPagerSport);


        for (int i = 0; i < 3; i++) {
            tabLayoutSport.addTab(tabLayoutSport.newTab().setIcon(icon[i]));
        }

        tabLayoutSport.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerSport.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerSport.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutSport));

        //change ViewPager page when tab selected
        tabLayoutSport.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position ==0) {

                    tabLayoutSport.getTabAt(1).setIcon(icon[1]);
                    tabLayoutSport.getTabAt(2).setIcon(icon[2]);
                    tabLayoutSport.getTabAt(0).setIcon(iconWhite[0]);

                } else if (position == 1) {

                    tabLayoutSport.getTabAt(0).setIcon(icon[0]);
                    tabLayoutSport.getTabAt(2).setIcon(icon[2]);
                    tabLayoutSport.getTabAt(1).setIcon(iconWhite[1]);

                } else {

                    tabLayoutSport.getTabAt(0).setIcon(icon[0]);
                    tabLayoutSport.getTabAt(1).setIcon(icon[1]);
                    tabLayoutSport.getTabAt(2).setIcon(iconWhite[2]);

                }
                viewPagerSport.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //Toast.makeText(this, ""+getIntent().getStringExtra("KEY_SELECT"), Toast.LENGTH_SHORT).show();
        Intent i = getIntent();
        if(i.getStringExtra("KEY_SELECT")!= null){
            if(i.getStringExtra("KEY_SELECT").toString().equals("true")){
                viewPagerSport.setCurrentItem(2);
            }
        }

    }

    @Override
    public void onBackPressed() {

        if (drawerSport.isDrawerOpen(GravityCompat.START)) {
            drawerSport.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            Intent i = new Intent(SportActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            dialogLogout();
        }


        drawerSport.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position ==0) {
                return new TimerFragment();
            } else if (position == 1) {
                return new FragmentAllSport();
            } else {
                return new FavFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    void dialogLogout(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_logout);

        TextView txtVOK =  dialog.findViewById(R.id.txtVOK);
        TextView txtVCancel =  dialog.findViewById(R.id.txtVCancel);

        txtVOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        txtVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
