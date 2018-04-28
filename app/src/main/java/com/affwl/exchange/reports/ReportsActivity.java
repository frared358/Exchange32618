package com.affwl.exchange.reports;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class ReportsActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar reports_toolbar;
    TextView txt_order_report,txt_trade_report,txt_net_positions,txt_margin_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports );

        reports_toolbar=findViewById(R.id.reports_toolbar);
        setSupportActionBar(reports_toolbar);

        txt_order_report=findViewById(R.id.txt_order_report);
        txt_trade_report=findViewById(R.id.txt_trade_report);
        txt_net_positions=findViewById(R.id.txt_net_positions);
        txt_margin_position=findViewById(R.id.txt_margin_position);

        txt_order_report.setOnClickListener(this);
        txt_trade_report.setOnClickListener(this);
        txt_net_positions.setOnClickListener(this);
        txt_margin_position.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.txt_order_report:
                startActivity(new Intent(this,OrderReportActivity.class));
                break;

            case R.id.txt_trade_report:
                startActivity(new Intent(this,TradeReportActivity.class));
                break;

            case R.id.txt_net_positions:
                startActivity(new Intent(this,NetPositionActivity.class));
                break;

            case R.id.txt_margin_position:
                startActivity(new Intent(this,MarginPositionActivity.class));
                break;
        }

    }
}
