package com.affwl.exchange.crypto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


public class BinancesActivity extends FragmentActivity implements View.OnClickListener{

    LinearLayout llHomeFavorite,llHomeDeposit,llHomeWithdraw;
    TextView txtVHomeFavorite,txtVHomeDeposit,txtVHomeWithdraw,txtVGainer,txtVLoser;
    ImageView imgVHomeFavorite,imgVHomeDeposit,imgVHomeWithdraw,imgVGainer,imgVLoser,imgVGainerPointer,imgVLoserPointer;
    RelativeLayout rlBTC24Top;
    LinearLayout llGainerData,llLoserData;

    //Bottom
    LinearLayout llHomeMain,llTradeMain,llFundMain,llMarketMain,llAccountMain;
    ImageView imgVHomeMain,imgVTradeMain,imgVFundMain,imgVMarketMain,imgVAccountMain;
    TextView txtVHomeMain,txtVTradeMain,txtVFundMain,txtVMarketMain,txtVAccountMain,txtVMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binances);


        llHomeFavorite = findViewById(R.id.llHomeFavorite);
        imgVHomeFavorite = findViewById(R.id.imgVHomeFavorite);
        txtVHomeFavorite = findViewById(R.id.txtVHomeFavorite);
        llHomeFavorite.setOnClickListener(this);
        imgVHomeFavorite.setOnClickListener(this);
        txtVHomeFavorite.setOnClickListener(this);

        llHomeDeposit = findViewById(R.id.llHomeDeposit);
        imgVHomeDeposit = findViewById(R.id.imgVHomeDeposit);
        txtVHomeDeposit = findViewById(R.id.txtVHomeDeposit);
        llHomeDeposit.setOnClickListener(this);
        imgVHomeDeposit.setOnClickListener(this);
        txtVHomeDeposit.setOnClickListener(this);

        llHomeWithdraw = findViewById(R.id.llHomeWithdraw);
        imgVHomeWithdraw = findViewById(R.id.imgVHomeWithdraw);
        txtVHomeWithdraw = findViewById(R.id.txtVHomeWithdraw);
        llHomeWithdraw.setOnClickListener(this);
        imgVHomeWithdraw.setOnClickListener(this);
        txtVHomeWithdraw.setOnClickListener(this);

        rlBTC24Top= findViewById(R.id.rlBTC24Top);
        rlBTC24Top.setOnClickListener(this);

        txtVGainer = findViewById(R.id.txtVGainer);
        txtVLoser = findViewById(R.id.txtVLoser);

        imgVGainer = findViewById(R.id.imgVGainer);
        imgVLoser = findViewById(R.id.imgVLoser);

        imgVGainerPointer = findViewById(R.id.imgVGainerPointer);
        imgVLoserPointer = findViewById(R.id.imgVLoserPointer);

        txtVGainer.setOnClickListener(this);
        txtVLoser.setOnClickListener(this);
        imgVGainer.setOnClickListener(this);
        imgVLoser.setOnClickListener(this);
        imgVGainerPointer.setOnClickListener(this);
        imgVLoserPointer.setOnClickListener(this);

        llGainerData = findViewById(R.id.llGainerData);
        llLoserData = findViewById(R.id.llLoserData);


        llHomeMain = findViewById(R.id.llHomeMain);
        llMarketMain = findViewById(R.id.llMarketMain);
        llTradeMain = findViewById(R.id.llTradeMain);
        llFundMain = findViewById(R.id.llFundMain);
        llAccountMain = findViewById(R.id.llAccountMain);

        imgVHomeMain = findViewById(R.id.imgVHomeMain);
        imgVMarketMain = findViewById(R.id.imgVMarketMain);
        imgVTradeMain = findViewById(R.id.imgVTradeMain);
        imgVFundMain = findViewById(R.id.imgVFundMain);
        imgVAccountMain = findViewById(R.id.imgVAccountMain);

        txtVHomeMain = findViewById(R.id.txtVHomeMain);
        txtVMarketMain = findViewById(R.id.txtVMarketMain);
        txtVTradeMain = findViewById(R.id.txtVTradeMain);
        txtVFundMain = findViewById(R.id.txtVFundMain);
        txtVAccountMain = findViewById(R.id.txtVAccountMain);
        txtVMore = findViewById(R.id.txtVMore);

        llMarketMain.setOnClickListener(this);
        llTradeMain.setOnClickListener(this);
        llFundMain.setOnClickListener(this);
        llAccountMain.setOnClickListener(this);

        txtVMarketMain.setOnClickListener(this);
        txtVTradeMain.setOnClickListener(this);
        txtVFundMain.setOnClickListener(this);
        txtVAccountMain.setOnClickListener(this);
        txtVMore.setOnClickListener(this);

        imgVMarketMain.setOnClickListener(this);
        imgVTradeMain.setOnClickListener(this);
        imgVFundMain.setOnClickListener(this);
        imgVAccountMain.setOnClickListener(this);


//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        FragmentBottom bottom = new FragmentBottom();
//        fragmentTransaction.add(R.id.llBootomFragmentHolder, bottom, "BOTTOM");
//        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llHomeFavorite: case R.id.imgVHomeFavorite: case R.id.txtVHomeFavorite:
                startActivity(new Intent(this,MarketActivityBinance.class));
                break;

            case R.id.llHomeDeposit: case R.id.imgVHomeDeposit: case R.id.txtVHomeDeposit:
                startActivity(new Intent(this,DepositeActivity.class));
                break;

            case R.id.llHomeWithdraw: case R.id.imgVHomeWithdraw: case R.id.txtVHomeWithdraw:
                startActivity(new Intent(this,WithDrawalActivity.class));
                break;

            case R.id.rlBTC24Top:
                Intent i = new Intent(this,MarketActivityBinance.class);
                i.putExtra("MARKET_TAB",3);
                startActivity(i);
                break;

            case R.id.txtVGainer:case R.id.imgVGainer:
                txtVGainer.setTextColor(getResources().getColor(R.color.colorYellowDark));
                imgVGainer.setImageResource(R.drawable.chart_yellow_high_128);
                imgVGainerPointer.setVisibility(View.VISIBLE);
                txtVLoser.setTextColor(getResources().getColor(R.color.colorWhite));
                imgVLoser.setImageResource(R.drawable.chart_white_low_128);
                imgVLoserPointer.setVisibility(View.GONE);
                llGainerData.setVisibility(View.VISIBLE);
                llLoserData.setVisibility(View.GONE);
                break;

            case R.id.txtVLoser:
                txtVLoser.setTextColor(getResources().getColor(R.color.colorYellowDark));
                imgVLoser.setImageResource(R.drawable.chart_yellow_low_128);
                imgVLoserPointer.setVisibility(View.VISIBLE);
                txtVGainer.setTextColor(getResources().getColor(R.color.colorWhite));
                imgVGainer.setImageResource(R.drawable.chart_white_high_128);
                imgVGainerPointer.setVisibility(View.GONE);
                llGainerData.setVisibility(View.GONE);
                llLoserData.setVisibility(View.VISIBLE);
                break;

            case R.id.llMarketMain:case R.id.txtVMarketMain:case R.id.imgVMarketMain: case R.id.txtVMore:
                startActivity(new Intent(this,MarketActivityBinance.class));
                break;

            case R.id.llTradeMain:case R.id.txtVTradeMain:case R.id.imgVTradeMain:
                startActivity(new Intent(this,TradeActivity.class));
                break;

            case R.id.llFundMain:case R.id.txtVFundMain:case R.id.imgVFundMain:
                startActivity(new Intent(this,FundActivity.class));
                break;

            case R.id.llAccountMain:case R.id.txtVAccountMain:case R.id.imgVAccountMain:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
