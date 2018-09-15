package com.affwl.exchange.crypto;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


public class MarketActivityBinance extends FragmentActivity implements View.OnClickListener{

    TabLayout tabLayoutMarket;
    ViewPager viewPagerMarket;
    String[] tabText = {"Favorite","BNB","BTC","ETH","USDT"};
    ImageView imgVFavoriteList;
    int marketTab;

    //Bottom
    LinearLayout llHomeMarket,llTradeMarket,llFundMarket,llMarketMarket,llAccountMarket;
    ImageView imgVHomeMarket,imgVTradeMarket,imgVFundMarket,imgVMarketMarket,imgVAccountMarket;
    TextView txtVHomeMarket,txtVTradeMarket,txtVFundMarket,txtVMarketMarket,txtVAccountMarket;
    ImageView imgVMarketSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_binance);

        imgVMarketSearch = findViewById(R.id.imgVMarketSearch);
        imgVMarketSearch.setOnClickListener(this);

        Intent intentMarketTab = getIntent();
        marketTab = intentMarketTab.getIntExtra("MARKET_TAB",0);


        tabLayoutMarket = findViewById(R.id.tabLayoutMarket);
        viewPagerMarket = findViewById(R.id.viewPagerMarket);

        for (int i=0;i<5;i++){
            tabLayoutMarket.addTab(tabLayoutMarket.newTab().setText(tabText[i]));
        }

        tabLayoutMarket.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerMarket.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerMarket.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMarket));

        //Toast.makeText(this, ""+marketTab, Toast.LENGTH_SHORT).show();


        //change ViewPager page when tab selected
//        viewPagerMarket.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//
//                viewPagerMarket.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });

        tabLayoutMarket.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMarket.setCurrentItem(tab.getPosition());
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

        if(marketTab == 3){
            //tabLayoutMarket.getTabAt(2).select();
            viewPagerMarket.setCurrentItem(2);

            //Toast.makeText(this, ".."+marketTab, Toast.LENGTH_SHORT).show();
        }

        imgVFavoriteList = findViewById(R.id.imgVFavoriteList);
        imgVFavoriteList.setOnClickListener(this);

        llHomeMarket = findViewById(R.id.llHomeMarket);
        llMarketMarket = findViewById(R.id.llMarketMarket);
        llTradeMarket = findViewById(R.id.llTradeMarket);
        llFundMarket = findViewById(R.id.llFundMarket);
        llAccountMarket = findViewById(R.id.llAccountMarket);

        imgVHomeMarket = findViewById(R.id.imgVHomeMarket);
        imgVMarketMarket = findViewById(R.id.imgVMarketMarket);
        imgVTradeMarket = findViewById(R.id.imgVTradeMarket);
        imgVFundMarket = findViewById(R.id.imgVFundMarket);
        imgVAccountMarket = findViewById(R.id.imgVAccountMarket);

        txtVHomeMarket = findViewById(R.id.txtVHomeMarket);
        txtVMarketMarket = findViewById(R.id.txtVMarketMarket);
        txtVTradeMarket = findViewById(R.id.txtVTradeMarket);
        txtVFundMarket = findViewById(R.id.txtVFundMarket);
        txtVAccountMarket = findViewById(R.id.txtVAccountMarket);


        llHomeMarket.setOnClickListener(this);
        llTradeMarket.setOnClickListener(this);
        llFundMarket.setOnClickListener(this);
        llAccountMarket.setOnClickListener(this);

        txtVHomeMarket.setOnClickListener(this);
        txtVTradeMarket.setOnClickListener(this);
        txtVFundMarket.setOnClickListener(this);
        txtVAccountMarket.setOnClickListener(this);

        imgVHomeMarket.setOnClickListener(this);
        imgVTradeMarket.setOnClickListener(this);
        imgVFundMarket.setOnClickListener(this);
        imgVAccountMarket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgVFavoriteList:
                startActivity(new Intent(this,FavoriteActivity.class));
                break;

            case R.id.llHomeMarket:case R.id.txtVHomeMarket:case R.id.imgVHomeMarket:
                startActivity(new Intent(this,BinancesActivity.class));
                break;

            case R.id.llTradeMarket:case R.id.txtVTradeMarket:case R.id.imgVTradeMarket:
                startActivity(new Intent(this,TradeActivity.class));
                break;

            case R.id.llFundMarket:case R.id.txtVFundMarket:case R.id.imgVFundMarket:
                startActivity(new Intent(this,FundActivity.class));
                break;

            case R.id.llAccountMarket:case R.id.txtVAccountMarket:case R.id.imgVAccountMarket:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imgVMarketSearch:
                startActivity(new Intent(this,MarketSearchActivity.class));
                break;
        }
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position ==0) {
                return new FragmentBTC();
            } else if (position == 1) {
                return new FragmentBTC();
            } else if (position ==2) {
                return new FragmentBTC();
            } else if (position == 3) {
                return new FragmentBTC();
            }else {
                return new FragmentBTC();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}
