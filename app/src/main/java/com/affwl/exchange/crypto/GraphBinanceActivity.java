package com.affwl.exchange.crypto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

public class GraphBinanceActivity extends Activity implements View.OnClickListener{

    TextView txtVBackGraphTrade,txtVMarketTrades,txtVOrderBook;
    LinearLayout llMarketTrades,llOrderBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_binance);

        txtVBackGraphTrade = findViewById(R.id.txtVBackGraphTrade);
        txtVBackGraphTrade.setOnClickListener(this);

        txtVOrderBook= findViewById(R.id.txtVOrderBook);
        txtVMarketTrades= findViewById(R.id.txtVMarketTrades);
        txtVMarketTrades.setOnClickListener(this);
        txtVOrderBook.setOnClickListener(this);

        llOrderBook= findViewById(R.id.llOrderBook);
        llMarketTrades= findViewById(R.id.llMarketTrades);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackGraphTrade:
                onBackPressed();
                break;
            case R.id.txtVMarketTrades:
                txtVMarketTrades.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVMarketTrades.setBackgroundColor(getResources().getColor(R.color.colorGreyLiterDark));
                llMarketTrades.setVisibility(View.VISIBLE);
                txtVOrderBook.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVOrderBook.setBackgroundColor(getResources().getColor(R.color.colorGreyDark));
                llOrderBook.setVisibility(View.GONE);
                break;

            case R.id.txtVOrderBook:
                txtVOrderBook.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVOrderBook.setBackgroundColor(getResources().getColor(R.color.colorGreyLiterDark));
                llOrderBook.setVisibility(View.VISIBLE);
                txtVMarketTrades.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVMarketTrades.setBackgroundColor(getResources().getColor(R.color.colorGreyDark));
                llMarketTrades.setVisibility(View.GONE);
                break;
        }
    }
}
