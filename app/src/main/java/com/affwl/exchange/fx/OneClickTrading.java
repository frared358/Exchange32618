package com.affwl.exchange.fx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.affwl.exchange.R;

public class OneClickTrading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_click_trading);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


    }
}
