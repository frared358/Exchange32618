package com.affwl.exchange.reports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.affwl.exchange.R;

public class TradeReportActivity extends AppCompatActivity{

    Toolbar trade_report_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_report);

        trade_report_toolbar=findViewById(R.id.trade_report_toolbar);
        setSupportActionBar(trade_report_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    }
