package com.affwl.exchange.reports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.affwl.exchange.R;

public class NetPositionActivity extends AppCompatActivity {

    Toolbar net_position_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_position);

        net_position_toolbar=findViewById(R.id.net_position_toolbar);
        setSupportActionBar(net_position_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    }
