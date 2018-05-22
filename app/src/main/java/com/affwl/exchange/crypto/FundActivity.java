package com.affwl.exchange.crypto;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


public class FundActivity extends FragmentActivity implements View.OnClickListener{

    TextView txtVHistoryClick;
    LinearLayout llFundDeposit,llFundWithdraw;
    ImageView imgVFundDeposit,imgVFundWithdraw;
    TextView txtVFundDeposit,txtVFundWithdraw;
    RelativeLayout rlBalanceData;

    //Bottom
    LinearLayout llHomeFund,llTradeFund,llFundFund,llMarketFund,llAccountFund;
    ImageView imgVHomeFund,imgVTradeFund,imgVFundFund,imgVMarketFund,imgVAccountFund;
    TextView txtVHomeFund,txtVTradeFund,txtVFundFund,txtVMarketFund,txtVAccountFund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund);


        txtVHistoryClick= findViewById(R.id.txtVHistoryClick);
        txtVHistoryClick.setOnClickListener(this);

        rlBalanceData= findViewById(R.id.rlBalanceData);
        rlBalanceData.setOnClickListener(this);

        txtVFundDeposit= findViewById(R.id.txtVFundDeposit);
        txtVFundWithdraw= findViewById(R.id.txtVFundWithdraw);

        imgVFundDeposit= findViewById(R.id.imgVFundDeposit);
        imgVFundWithdraw= findViewById(R.id.imgVFundWithdraw);

        llFundDeposit= findViewById(R.id.llFundDeposit);
        llFundWithdraw= findViewById(R.id.llFundWithdraw);

        imgVFundDeposit.setOnClickListener(this);
        txtVFundDeposit.setOnClickListener(this);
        llFundDeposit.setOnClickListener(this);

        imgVFundWithdraw.setOnClickListener(this);
        txtVFundWithdraw.setOnClickListener(this);
        llFundWithdraw.setOnClickListener(this);

        llHomeFund = findViewById(R.id.llHomeFund);
        llMarketFund = findViewById(R.id.llMarketFund);
        llTradeFund = findViewById(R.id.llTradeFund);
        llFundFund = findViewById(R.id.llFundFund);
        llAccountFund = findViewById(R.id.llAccountFund);

        imgVHomeFund = findViewById(R.id.imgVHomeFund);
        imgVMarketFund = findViewById(R.id.imgVMarketFund);
        imgVTradeFund = findViewById(R.id.imgVTradeFund);
        imgVFundFund = findViewById(R.id.imgVFundFund);
        imgVAccountFund = findViewById(R.id.imgVAccountFund);

        txtVHomeFund = findViewById(R.id.txtVHomeFund);
        txtVMarketFund = findViewById(R.id.txtVMarketFund);
        txtVTradeFund = findViewById(R.id.txtVTradeFund);
        txtVFundFund = findViewById(R.id.txtVFundFund);
        txtVAccountFund = findViewById(R.id.txtVAccountFund);


        llMarketFund.setOnClickListener(this);
        llTradeFund.setOnClickListener(this);
        llHomeFund.setOnClickListener(this);
        llAccountFund.setOnClickListener(this);

        txtVMarketFund.setOnClickListener(this);
        txtVTradeFund.setOnClickListener(this);
        txtVHomeFund.setOnClickListener(this);
        txtVAccountFund.setOnClickListener(this);

        imgVMarketFund.setOnClickListener(this);
        imgVTradeFund.setOnClickListener(this);
        imgVHomeFund.setOnClickListener(this);
        imgVAccountFund.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBalanceData:
                startActivity(new Intent(this,BalanceActivity.class));
                break;

            case R.id.txtVHistoryClick:
                startActivity(new Intent(this,HistoryDepositeWithdrawActivity.class));
                break;

            case R.id.txtVFundDeposit: case R.id.imgVFundDeposit: case R.id.llFundDeposit:
                startActivity(new Intent(this,DepositeActivity.class));
                break;

            case R.id.txtVFundWithdraw: case R.id.imgVFundWithdraw: case R.id.llFundWithdraw:
                startActivity(new Intent(this,WithDrawalActivity.class));
                break;

            case R.id.llMarketFund:case R.id.txtVMarketFund:case R.id.imgVMarketFund:
                startActivity(new Intent(this,MarketActivityBinance.class));
                break;

            case R.id.llTradeFund:case R.id.txtVTradeFund:case R.id.imgVTradeFund:
                startActivity(new Intent(this,TradeActivity.class));
                break;

            case R.id.llHomeFund:case R.id.txtVHomeFund:case R.id.imgVHomeFund:
                startActivity(new Intent(this,BinancesActivity.class));
                break;

            case R.id.llAccountFund:case R.id.txtVAccountFund:case R.id.imgVAccountFund:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
