package com.affwl.exchange.crypto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

public class WithDrawalActivity extends Activity implements View.OnClickListener{

    TextView txtVBackWithdrawFund,txtVWithdrawHistoryClick;
    RelativeLayout rlWithdrawCoinSelection;
    TextView txtVWithdrawCoinSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_drawal);

        txtVBackWithdrawFund = findViewById(R.id.txtVBackWithdrawFund);
        txtVBackWithdrawFund.setOnClickListener(this);
        txtVWithdrawHistoryClick = findViewById(R.id.txtVWithdrawHistoryClick);
        txtVWithdrawHistoryClick.setOnClickListener(this);

        rlWithdrawCoinSelection = findViewById(R.id.rlWithdrawCoinSelection);
        rlWithdrawCoinSelection.setOnClickListener(this);
        txtVWithdrawCoinSelection = findViewById(R.id.txtVWithdrawCoinSelection);
        txtVWithdrawCoinSelection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackWithdrawFund:
                onBackPressed();
                break;
            case R.id.txtVWithdrawHistoryClick:
                startActivity(new Intent(this,HistoryDepositeWithdrawActivity.class));
                break;
            case R.id.rlWithdrawCoinSelection: case R.id.txtVWithdrawCoinSelection:
                startActivity(new Intent(this,CoinSelectionActivity.class));
                break;
        }
    }
}
