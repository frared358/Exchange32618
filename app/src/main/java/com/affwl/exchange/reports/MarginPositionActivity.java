package com.affwl.exchange.reports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.affwl.exchange.R;

public class MarginPositionActivity extends AppCompatActivity {

    Toolbar margin_position_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margin_position);

        margin_position_toolbar=findViewById(R.id.margin_position_toolbar);
        setSupportActionBar(margin_position_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    }
