package com.affwl.exchange.crypto;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class HistoryDepositeWithdrawActivity extends FragmentActivity implements View.OnClickListener {

    TabLayout tabLayoutHistory;
    ViewPager viewPagerHistory;
    String[] tabText = {"Deposite History","Withdrawals History"};
    TextView txtVBackHistoryFund;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_deposite_withdraw);

        tabLayoutHistory = findViewById(R.id.tabLayoutHistory);
        viewPagerHistory = findViewById(R.id.viewPagerHistory);
        txtVBackHistoryFund = findViewById(R.id.txtVBackHistoryFund);
        txtVBackHistoryFund.setOnClickListener(this);

        for (int i=0;i<2;i++){
            tabLayoutHistory.addTab(tabLayoutHistory.newTab().setText(tabText[i]));
        }

        tabLayoutHistory.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerHistory.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerHistory.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutHistory));

        tabLayoutHistory.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerHistory.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabReselected: " + tab.getPosition());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackHistoryFund:
                onBackPressed();
        }
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position ==0) {
                return new FragmentHistoryData();
            } else {
                return new FragmentHistoryData();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
