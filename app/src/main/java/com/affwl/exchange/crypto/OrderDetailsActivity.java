package com.affwl.exchange.crypto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class OrderDetailsActivity extends Activity implements View.OnClickListener {

    TextView txtVBackOrderDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        txtVBackOrderDetail = findViewById(R.id.txtVBackOrderDetail);
        txtVBackOrderDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtVBackOrderDetail:
                onBackPressed();
                break;
        }
    }
}
