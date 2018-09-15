package com.affwl.exchange.crypto;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class OpenOrderActivity extends Activity implements View.OnClickListener{

    TextView txtVOrderHistory,txtVBackOpenOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_order);
        txtVOrderHistory= findViewById(R.id.txtVOrderHistory);
        txtVOrderHistory.setOnClickListener(this);

        txtVBackOpenOrder= findViewById(R.id.txtVBackOpenOrder);
        txtVBackOpenOrder.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVOrderHistory:
                startActivity(new Intent(this,OrderHistoryActivity.class));
                break;

            case R.id.txtVBackOpenOrder:
                onBackPressed();
                break;
        }
    }


}
