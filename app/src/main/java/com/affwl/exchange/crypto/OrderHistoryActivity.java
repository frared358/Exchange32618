package com.affwl.exchange.crypto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

public class OrderHistoryActivity extends Activity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    TextView txtVBackOrderHistory;
    DrawerLayout drawerOrderFilter;
    NavigationView navigationView;
    ImageView imgVFilter;
    LinearLayout llOrderHistoryData;
    TextView txtVDate1Day,txtVDate1Week,txtVDate1Month,txtVDate3Month,txtVDateAll,txtVPairAll,txtVTypeBuySell,txtVTypeSell,txtVTypeBuy,txtVResetFilter,txtVCompleteFilter;
    EditText editTxtPairCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        txtVBackOrderHistory = findViewById(R.id.txtVBackOrderHistory);
        txtVBackOrderHistory.setOnClickListener(this);

        imgVFilter = findViewById(R.id.imgVFilter);
        imgVFilter.setOnClickListener(this);

        llOrderHistoryData = findViewById(R.id.llOrderHistoryData);
        llOrderHistoryData.setOnClickListener(this);

        drawerOrderFilter =(DrawerLayout) findViewById(R.id.drawerOrderFilter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerOrderFilter, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerOrderFilter.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        View drawerData=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        txtVDate1Day = drawerData.findViewById(R.id.txtVDate1Day);
        txtVDate1Week = drawerData.findViewById(R.id.txtVDate1Week);
        txtVDate1Month = drawerData.findViewById(R.id.txtVDate1Month);
        txtVDate3Month = drawerData.findViewById(R.id.txtVDate3Month);
        txtVDateAll = drawerData.findViewById(R.id.txtVDateAll);
        txtVPairAll = drawerData.findViewById(R.id.txtVPairAll);
        txtVTypeBuySell = drawerData.findViewById(R.id.txtVTypeBuySell);
        txtVTypeSell = drawerData.findViewById(R.id.txtVTypeSell);
        txtVTypeBuy = drawerData.findViewById(R.id.txtVTypeBuy);
        editTxtPairCoin = drawerData.findViewById(R.id.editTxtPairCoin);

        txtVDate1Day.setOnClickListener(this);
        txtVDate1Week.setOnClickListener(this);
        txtVDate1Month.setOnClickListener(this);
        txtVDate3Month.setOnClickListener(this);
        txtVDateAll.setOnClickListener(this);
        txtVPairAll.setOnClickListener(this);
        txtVTypeBuySell.setOnClickListener(this);
        txtVTypeSell.setOnClickListener(this);
        txtVTypeBuy.setOnClickListener(this);

        txtVResetFilter=drawerData.findViewById(R.id.txtVResetFilter);
        txtVCompleteFilter=drawerData.findViewById(R.id.txtVCompleteFilter);
        txtVResetFilter.setOnClickListener(this);
        txtVCompleteFilter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackOrderHistory:
                onBackPressed();
                break;
            case R.id.imgVFilter:
                drawerOrderFilter.openDrawer(navigationView);
                break;
            case R.id.llOrderHistoryData:
                startActivity(new Intent(this,OrderDetailsActivity.class));
                break;
            case R.id.txtVDate1Day:
                txtVDate1Day.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVDate1Day.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVDate1Week.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Week.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate3Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate3Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDateAll.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDateAll.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.txtVDate1Week:
                txtVDate1Day.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Day.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Week.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVDate1Week.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVDate1Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate3Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate3Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDateAll.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDateAll.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.txtVDate1Month:
                txtVDate1Day.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Day.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Week.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Week.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Month.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVDate1Month.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVDate3Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate3Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDateAll.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDateAll.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.txtVDate3Month:
                txtVDate1Day.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Day.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Week.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Week.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate3Month.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVDate3Month.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVDateAll.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDateAll.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.txtVDateAll:
                txtVDate1Day.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Day.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Week.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Week.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate3Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate3Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDateAll.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVDateAll.setTextColor(getResources().getColor(R.color.colorYellowDark));
                break;

            case R.id.txtVTypeBuySell:
                txtVTypeBuySell.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVTypeBuySell.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVTypeBuy.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeBuy.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVTypeSell.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeSell.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.txtVTypeBuy:
                txtVTypeBuySell.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeBuySell.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVTypeBuy.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVTypeBuy.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVTypeSell.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeSell.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.txtVTypeSell:
                txtVTypeBuySell.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeBuySell.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVTypeBuy.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeBuy.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVTypeSell.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVTypeSell.setTextColor(getResources().getColor(R.color.colorYellowDark));
                break;

            case R.id.txtVResetFilter:
                txtVTypeBuySell.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVTypeBuySell.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVDate1Week.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Week.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate1Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate3Month.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDate3Month.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDateAll.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVDateAll.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVDate1Day.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtVDate1Day.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVTypeBuy.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeBuy.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVTypeSell.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtVTypeSell.setTextColor(getResources().getColor(R.color.colorWhite));
                editTxtPairCoin.setText("");
                txtVPairAll.setText("All");
                break;

            case R.id.txtVCompleteFilter:
                if (drawerOrderFilter.isDrawerOpen(GravityCompat.END)) {
                    drawerOrderFilter.closeDrawer(GravityCompat.END);
                }
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {

        if (drawerOrderFilter.isDrawerOpen(GravityCompat.END)) {
            drawerOrderFilter.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

}
