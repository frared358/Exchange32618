package com.affwl.exchange.crypto;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


public class FragmentBottom extends Fragment{


    ImageView imgVHome,imgVMarket,imgVTrade,imgVFund,imgVAccount;
    TextView txtVHome,txtVMarket,txtVTrade,txtVFund,txtVAccount;
    LinearLayout llHome,llMarket,llTrade,llFund,llAccount;
    View.OnClickListener onClickListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crypto_fragment_bottom, container, false);
        imgVHome = v.findViewById(R.id.imgVHome);
        imgVMarket = v.findViewById(R.id.imgVMarket);
        imgVTrade = v.findViewById(R.id.imgVTrade);
        imgVFund = v.findViewById(R.id.imgVFund);
        imgVAccount = v.findViewById(R.id.imgVAccount);

        txtVHome = v.findViewById(R.id.txtVHome);
        txtVMarket = v.findViewById(R.id.txtVMarket);
        txtVTrade = v.findViewById(R.id.txtVTrade);
        txtVFund = v.findViewById(R.id.txtVFund);
        txtVAccount = v.findViewById(R.id.txtVAccount);

        llHome = v.findViewById(R.id.llHome);
        llMarket = v.findViewById(R.id.llMarket);
        llTrade = v.findViewById(R.id.llTrade);
        llFund = v.findViewById(R.id.llFund);
        llAccount = v.findViewById(R.id.llAccount);

        selectionImage();

        imgVHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.HOME) {
                    DataHolderBinance.bottomClick(false,true,true,true,true);
                    startActivity(new Intent(getActivity(),BinancesActivity.class));
                }
            }
        });

        imgVMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.MARKET) {
                    DataHolderBinance.bottomClick(true,false,true,true,true);
                    startActivity(new Intent(getActivity(),MarketActivityBinance.class));
                }
            }
        });

        imgVFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.FUND) {
                    DataHolderBinance.bottomClick(true,true,true,false,true);
                    startActivity(new Intent(getActivity(),FundActivity.class));
                }
            }
        });

        imgVTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.TRADE) {
                    DataHolderBinance.bottomClick(true,true,false,true,true);
                    startActivity(new Intent(getActivity(),TradeActivity.class));
                }
            }
        });

        txtVHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.HOME) {
                    DataHolderBinance.bottomClick(false,true,true,true,true);
                    startActivity(new Intent(getActivity(),BinancesActivity.class));
                }
            }
        });

        txtVMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.MARKET) {
                    DataHolderBinance.bottomClick(true,false,true,true,true);
                    startActivity(new Intent(getActivity(),MarketActivityBinance.class));
                }
            }
        });

        txtVFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.FUND) {
                    DataHolderBinance.bottomClick(true,true,true,false,true);
                    startActivity(new Intent(getActivity(),FundActivity.class));
                }
            }
        });

        txtVTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.TRADE) {
                    DataHolderBinance.bottomClick(true,true,false,true,true);
                    startActivity(new Intent(getActivity(),TradeActivity.class));
                }
            }
        });

        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.HOME) {
                    DataHolderBinance.bottomClick(false,true,true,true,true);
                    startActivity(new Intent(getActivity(),BinancesActivity.class));
                }
            }
        });

        llMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.MARKET) {
                    DataHolderBinance.bottomClick(true,false,true,true,true);
                    startActivity(new Intent(getActivity(),MarketActivityBinance.class));
                }
            }
        });

        llFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.FUND) {
                    DataHolderBinance.bottomClick(true,true,true,false,true);
                    startActivity(new Intent(getActivity(),FundActivity.class));
                }
            }
        });

        llTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderBinance.TRADE) {
                    DataHolderBinance.bottomClick(true,true,false,true,true);
                    startActivity(new Intent(getActivity(),TradeActivity.class));
                }
            }
        });


//        imgVTrade.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DataHolderBinance.MARKET) {
//                    startActivity(new Intent(getActivity(),MarketActivityBinance.class));
//                }
//            }
//        });
//

//
//        imgVAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DataHolderBinance.MARKET) {
//                    startActivity(new Intent(getActivity(),MarketActivityBinance.class));
//                }
//            }
//        });
        return v;
    }

    private void selectionImage(){

        if (!DataHolderBinance.HOME){
            imgVHome.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.home_yellow_128));
            txtVHome.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorYellowDark));
        }else if (!DataHolderBinance.MARKET){
            imgVMarket.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.chart_yellow_128));
            txtVMarket.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorYellowDark));
        }else if (!DataHolderBinance.TRADE){
            imgVTrade.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.trade_yellow_128));
            txtVTrade.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorYellowDark));
        }else if (!DataHolderBinance.FUND){
            imgVFund.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.wallet_yellow_128));
            txtVFund.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorYellowDark));
        }else if (!DataHolderBinance.ACCOUNT){
            imgVAccount.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.account_yellow_128));
            txtVAccount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorYellowDark));
        }

    }

}
