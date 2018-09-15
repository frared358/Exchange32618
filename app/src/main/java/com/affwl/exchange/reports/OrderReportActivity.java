package com.affwl.exchange.reports;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.affwl.exchange.R;

public class OrderReportActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar order_report_toolbar;
    LinearLayout order_status,list_order_report;
    Button refresh_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report);

        order_report_toolbar=findViewById(R.id.order_report_toolbar);
        setSupportActionBar(order_report_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        order_status=findViewById(R.id.order_status);
        list_order_report=findViewById(R.id.list_order_report);
        refresh_order=findViewById(R.id.refresh_order);

        order_status.setOnClickListener(this);
        refresh_order.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.order_status:
                if(list_order_report.getVisibility()==View.VISIBLE)
                {
                    list_order_report.setVisibility(View.GONE);
                }
                else {
                    list_order_report.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.refresh_order:
                Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
