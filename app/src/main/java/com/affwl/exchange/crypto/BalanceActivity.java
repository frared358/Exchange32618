package com.affwl.exchange.crypto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class BalanceActivity extends Activity implements View.OnClickListener {

    TextView txtVBackBalance,txtVBalanceWithdraw,txtVBalanceDeposite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        txtVBackBalance= findViewById(R.id.txtVBackBalance);
        txtVBackBalance.setOnClickListener(this);

        txtVBalanceDeposite= findViewById(R.id.txtVBalanceDeposite);
        txtVBalanceDeposite.setOnClickListener(this);

        txtVBalanceWithdraw= findViewById(R.id.txtVBalanceWithdraw);
        txtVBalanceWithdraw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackBalance:
                onBackPressed();
                break;
            case R.id.txtVBalanceDeposite:
                startActivity(new Intent(this,DepositeActivity.class));
                break;
            case R.id.txtVBalanceWithdraw:
                startActivity(new Intent(this,WithDrawalActivity.class));
                break;
        }
    }
}
