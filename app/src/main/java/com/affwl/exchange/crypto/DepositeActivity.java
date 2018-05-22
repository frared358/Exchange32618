package com.affwl.exchange.crypto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

public class DepositeActivity extends Activity implements View.OnClickListener {

    TextView txtVBackDepositeFund,txtVDepositeHistoryClick;
    RelativeLayout rlDepositeCoinSelection;
    TextView txtVDepositeCoinSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposite);

        txtVBackDepositeFund = findViewById(R.id.txtVBackDepositeFund);
        txtVBackDepositeFund.setOnClickListener(this);
        txtVDepositeHistoryClick = findViewById(R.id.txtVDepositeHistoryClick);
        txtVDepositeHistoryClick.setOnClickListener(this);
        rlDepositeCoinSelection = findViewById(R.id.rlDepositeCoinSelection);
        rlDepositeCoinSelection.setOnClickListener(this);
        txtVDepositeCoinSelection = findViewById(R.id.txtVDepositeCoinSelection);
        txtVDepositeCoinSelection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackDepositeFund:
                onBackPressed();
                break;

            case R.id.txtVDepositeHistoryClick:
                startActivity(new Intent(this,HistoryDepositeWithdrawActivity.class));
                break;

            case R.id.rlDepositeCoinSelection: case R.id.txtVDepositeCoinSelection:
                startActivity(new Intent(this,CoinSelectionActivity.class));
                break;
        }
    }
}
