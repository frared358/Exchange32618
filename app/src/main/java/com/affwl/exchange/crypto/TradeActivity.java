package com.affwl.exchange.crypto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class TradeActivity extends FragmentActivity implements View.OnClickListener{

    NestedScrollView nestedScrollView;
    RelativeLayout rlTradePage;
    LinearLayout llMarketTradeData,llOpenOrder,llPrice,llStopLimit;
    TextView txtVOpenOrder,txtVTradeOrderHistory,txtVDecimalValue,txtVOrderSelection,txtVEquivalentValue,txtVMarketPrice;
    TextView txtV25PercentAmt,txtV50PercentAmt,txtV75PercentAmt,txtV100PercentAmt,txtVTradeBuy,txtVTradeSell;
    ImageView imgVOpenOrder;
    ImageView imgVTradeGraph;
    Spinner spinnerOrderTrade;
    View viewTradeSell,viewTradeBuy;
    Button btnBuy,btnSell;
    String OrderTrade;
    EditText editTxtPriceValue,editTxtAmtValue,editTxtStopValue,editTxtLimitValue,editTxtTotal;
    ImageView imgVDecreasePriceValue,imgVIncreasePriceValue,imgVDecreaseAmtValue,imgVIncreaseAmtValue,imgVIncreaseStopValue,imgVDecreaseStopValue,
        imgVIncreaseLimitValue,imgVDecreaseLimitValue;

    //Bottom
    LinearLayout llHomeTrade,llMarketTrade,llFundTrade,llTradeTrade,llAccountTrade;
    ImageView imgVHomeTrade,imgVMarketTrade,imgVFundTrade,imgVTradeTrade,imgVAccountTrade;
    TextView txtVHomeTrade,txtVMarketTrade,txtVFundTrade,txtVTradeTrade,txtVAccountTrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);



        rlTradePage = findViewById(R.id.rlTradePage);
        imgVTradeGraph = findViewById(R.id.imgVTradeGraph);
        imgVTradeGraph.setOnClickListener(this);

        llOpenOrder = findViewById(R.id.llOpenOrder);
        txtVOpenOrder = findViewById(R.id.txtVOpenOrder);
        imgVOpenOrder = findViewById(R.id.imgVOpenOrder);
        llOpenOrder.setOnClickListener(this);
        txtVOpenOrder.setOnClickListener(this);
        imgVOpenOrder.setOnClickListener(this);

        txtVTradeOrderHistory = findViewById(R.id.txtVTradeOrderHistory);
        txtVTradeOrderHistory.setOnClickListener(this);

        final Toolbar toolbarTrade = findViewById(R.id.toolbarTrade);
        AppBarLayout appbar = findViewById(R.id.appbar);

        //Set a listener to know the current visible state of CollapseLayout
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) {
                    toolbarTrade.setBackgroundColor(getResources().getColor(R.color.colorTrasparent));
                    toolbarTrade.setVisibility(View.GONE);
                }else{
                    toolbarTrade.setBackgroundColor(getResources().getColor(R.color.colorTrasparent));
                    toolbarTrade.setVisibility(View.GONE);
                }
            }
        });

//        CoordinatorLayout coordinatorLayoutMain = findViewById(R.id.coordinatorLayoutMain);
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
//        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
//        if (behavior != null) {
//            behavior.onNestedFling(coordinatorLayoutMain, appbar, null, 0, 0, true);
//        }

        txtVDecimalValue = findViewById(R.id.txtVDecimalValue);
        txtVDecimalValue.setOnClickListener(this);

        txtVOrderSelection = findViewById(R.id.txtVOrderSelection);
        txtVOrderSelection.setOnClickListener(this);

        llPrice = findViewById(R.id.llPrice);
        llStopLimit = findViewById(R.id.llStopLimit);
        txtVEquivalentValue = findViewById(R.id.txtVEquivalentValue);
        txtVMarketPrice = findViewById(R.id.txtVMarketPrice);

        OrderTrade = "Limit Order";
        spinnerOrderTrade = findViewById(R.id.spinnerOrderTrade);
        spinnerOrderTrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                OrderTrade = selectedItem;
                if (selectedItem.equalsIgnoreCase("Limit Order")){
                    llPrice.setVisibility(View.VISIBLE);
                    txtVEquivalentValue.setVisibility(View.VISIBLE);
                    llStopLimit.setVisibility(View.GONE);
                    txtVMarketPrice.setVisibility(View.GONE);
                    editTxtTotal.setVisibility(View.VISIBLE);

                }else if (selectedItem.equalsIgnoreCase("Market Order")){
                    llPrice.setVisibility(View.GONE);
                    txtVEquivalentValue.setVisibility(View.GONE);
                    llStopLimit.setVisibility(View.GONE);
                    txtVMarketPrice.setVisibility(View.VISIBLE);
                    editTxtTotal.setVisibility(View.GONE);

                }else if (selectedItem.equalsIgnoreCase("Stop Limit")){
                    llPrice.setVisibility(View.GONE);
                    txtVEquivalentValue.setVisibility(View.VISIBLE);
                    llStopLimit.setVisibility(View.VISIBLE);
                    txtVMarketPrice.setVisibility(View.GONE);
                    editTxtTotal.setVisibility(View.VISIBLE);
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        txtV25PercentAmt = findViewById(R.id.txtV25PercentAmt);
        txtV50PercentAmt = findViewById(R.id.txtV50PercentAmt);
        txtV75PercentAmt = findViewById(R.id.txtV75PercentAmt);
        txtV100PercentAmt = findViewById(R.id.txtV100PercentAmt);
        txtV25PercentAmt.setOnClickListener(this);
        txtV50PercentAmt.setOnClickListener(this);
        txtV75PercentAmt.setOnClickListener(this);
        txtV100PercentAmt.setOnClickListener(this);

        txtVTradeBuy = findViewById(R.id.txtVTradeBuy);
        txtVTradeSell = findViewById(R.id.txtVTradeSell);
        txtVTradeBuy.setOnClickListener(this);
        txtVTradeSell.setOnClickListener(this);
        viewTradeBuy = findViewById(R.id.viewTradeBuy);
        viewTradeSell = findViewById(R.id.viewTradeSell);
        viewTradeBuy.setOnClickListener(this);
        viewTradeSell.setOnClickListener(this);

        btnBuy = findViewById(R.id.btnBuy);
        btnSell = findViewById(R.id.btnSell);
        btnBuy.setOnClickListener(this);
        btnSell.setOnClickListener(this);

        imgVDecreasePriceValue = findViewById(R.id.imgVDecreasePriceValue);
        imgVIncreasePriceValue = findViewById(R.id.imgVIncreasePriceValue);
        editTxtPriceValue= findViewById(R.id.editTxtPriceValue);
        imgVIncreasePriceValue.setOnClickListener(this);
        imgVDecreasePriceValue.setOnClickListener(this);

        imgVDecreaseAmtValue = findViewById(R.id.imgVDecreaseAmtValue);
        imgVIncreaseAmtValue = findViewById(R.id.imgVIncreaseAmtValue);
        editTxtAmtValue= findViewById(R.id.editTxtAmtValue);
        imgVIncreaseAmtValue.setOnClickListener(this);
        imgVDecreaseAmtValue.setOnClickListener(this);

        imgVDecreaseStopValue = findViewById(R.id.imgVDecreaseStopValue);
        imgVIncreaseStopValue = findViewById(R.id.imgVIncreaseStopValue);
        editTxtStopValue= findViewById(R.id.editTxtStopValue);
        imgVIncreaseStopValue.setOnClickListener(this);
        imgVDecreaseStopValue.setOnClickListener(this);

        imgVDecreaseLimitValue = findViewById(R.id.imgVDecreaseLimitValue);
        imgVIncreaseLimitValue = findViewById(R.id.imgVIncreaseLimitValue);
        editTxtLimitValue= findViewById(R.id.editTxtLimitValue);
        imgVIncreaseLimitValue.setOnClickListener(this);
        imgVDecreaseLimitValue.setOnClickListener(this);

        editTxtTotal = findViewById(R.id.editTxtTotal);

        editTxtPriceValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0 && s.length() <=11) {
                    totalValue();
                }
                else {
                    editTxtPriceValue.setText("0.0");
                }
            }
        });

        editTxtLimitValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0 && s.length() <=11) {
                    totalValue();
                }
                else {
                    editTxtLimitValue.setText("0.0");
                }
            }
        });

        editTxtAmtValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0 && s.length() <=11) {
                    totalValue();
                }
                else {
                    editTxtLimitValue.setText("0.0");
                }
            }
        });

        llHomeTrade = findViewById(R.id.llHomeTrade);
        llMarketTrade = findViewById(R.id.llMarketTrade);
        llTradeTrade = findViewById(R.id.llTradeTrade);
        llFundTrade = findViewById(R.id.llFundTrade);
        llAccountTrade = findViewById(R.id.llAccountTrade);

        imgVHomeTrade = findViewById(R.id.imgVHomeTrade);
        imgVMarketTrade = findViewById(R.id.imgVMarketTrade);
        imgVTradeTrade = findViewById(R.id.imgVTradeTrade);
        imgVFundTrade = findViewById(R.id.imgVFundTrade);
        imgVAccountTrade = findViewById(R.id.imgVAccountTrade);

        txtVHomeTrade = findViewById(R.id.txtVHomeTrade);
        txtVMarketTrade = findViewById(R.id.txtVMarketTrade);
        txtVTradeTrade = findViewById(R.id.txtVTradeTrade);
        txtVFundTrade = findViewById(R.id.txtVFundTrade);
        txtVAccountTrade = findViewById(R.id.txtVAccountTrade);


        llHomeTrade.setOnClickListener(this);
        llMarketTrade.setOnClickListener(this);
        llFundTrade.setOnClickListener(this);
        llAccountTrade.setOnClickListener(this);

        txtVHomeTrade.setOnClickListener(this);
        txtVMarketTrade.setOnClickListener(this);
        txtVFundTrade.setOnClickListener(this);
        txtVAccountTrade.setOnClickListener(this);

        imgVHomeTrade.setOnClickListener(this);
        imgVMarketTrade.setOnClickListener(this);
        imgVFundTrade.setOnClickListener(this);
        imgVAccountTrade.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.imgVTradeGraph:
                startActivity(new Intent(this,GraphBinanceActivity.class));
                break;

            case R.id.llOpenOrder:case R.id.txtVOpenOrder:case R.id.imgVOpenOrder:
                startActivity(new Intent(this,OpenOrderActivity.class));
                break;

            case R.id.txtVTradeOrderHistory:
                startActivity(new Intent(this,OrderHistoryActivity.class));
                break;

            case R.id.txtVOrderSelection:
                initiatePopupWindow(false);
                break;

            case R.id.txtVDecimalValue:
                initiatePopupWindow(true);
                break;

            case R.id.txtV4Decimal:
                selctDecimalValue("4 Decimal");
                break;

            case R.id.txtV5Decimal:
                selctDecimalValue("5 Decimal");
                break;

            case R.id.txtV6Decimal:
                selctDecimalValue("6 Decimal");
                break;

            case R.id.txtV7Decimal:
                selctDecimalValue("7 Decimal");
                break;

            case R.id.txtVDefaultOrder:
                selctOrder("Default");
                break;

            case R.id.txtVSellOrder:
                selctOrder("Sell Order");
                break;

            case R.id.txtVBuyOrder:
                selctOrder("Buy Order");
                break;

            case R.id.txtV25PercentAmt:
                txtV25PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtV50PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV75PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV100PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV25PercentAmt.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtV50PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV75PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV100PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                break;

            case R.id.txtV50PercentAmt:
                txtV25PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV50PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtV75PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV100PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV25PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV50PercentAmt.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtV75PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV100PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                break;

            case R.id.txtV75PercentAmt:
                txtV25PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV50PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV75PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtV100PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV25PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV50PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV75PercentAmt.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtV100PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                break;

            case R.id.txtV100PercentAmt:
                txtV25PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV50PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV75PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_grey));
                txtV100PercentAmt.setBackground(getResources().getDrawable(R.drawable.border_yellow));
                txtV25PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV50PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV75PercentAmt.setTextColor(getResources().getColor(R.color.colorGreay));
                txtV100PercentAmt.setTextColor(getResources().getColor(R.color.colorYellowDark));
                break;

            case R.id.viewTradeBuy: case R.id.txtVTradeBuy:
                txtVTradeBuy.setTextColor(getResources().getColor(R.color.colorYellowDark));
                txtVTradeSell.setTextColor(getResources().getColor(R.color.colorWhite));
                viewTradeBuy.setVisibility(View.VISIBLE);
                viewTradeSell.setVisibility(View.GONE);
                btnBuy.setVisibility(View.VISIBLE);
                btnSell.setVisibility(View.GONE);
                break;

            case R.id.viewTradeSell: case R.id.txtVTradeSell:
                txtVTradeBuy.setTextColor(getResources().getColor(R.color.colorWhite));
                txtVTradeSell.setTextColor(getResources().getColor(R.color.colorYellowDark));
                viewTradeBuy.setVisibility(View.GONE);
                viewTradeSell.setVisibility(View.VISIBLE);
                btnBuy.setVisibility(View.GONE);
                btnSell.setVisibility(View.VISIBLE);
                break;

            case R.id.imgVIncreasePriceValue:
                incrementValue(editTxtPriceValue,0.000001,6);
                break;

            case R.id.imgVDecreasePriceValue:
                decrementValue(editTxtPriceValue,0.000001,6);
                break;

            case R.id.imgVIncreaseAmtValue:
                incrementValue(editTxtAmtValue,0.01,2);
                break;

            case R.id.imgVDecreaseAmtValue:
                decrementValue(editTxtAmtValue,0.01,2);
                break;

            case R.id.imgVIncreaseStopValue:
                incrementValue(editTxtStopValue,0.000001,6);
                break;

            case R.id.imgVDecreaseStopValue:
                decrementValue(editTxtStopValue,0.000001,6);
                break;

            case R.id.imgVIncreaseLimitValue:
                incrementValue(editTxtLimitValue,0.000001,6);
                break;

            case R.id.imgVDecreaseLimitValue:
                decrementValue(editTxtLimitValue,0.000001,6);
                break;

            case R.id.llHomeTrade:case R.id.txtVHomeTrade:case R.id.imgVHomeTrade:
                startActivity(new Intent(this,BinancesActivity.class));
                break;

            case R.id.llMarketTrade:case R.id.txtVMarketTrade:case R.id.imgVMarketTrade:
                startActivity(new Intent(this,MarketActivityBinance.class));
                break;

            case R.id.llFundTrade:case R.id.txtVFundTrade:case R.id.imgVFundTrade:
                startActivity(new Intent(this,FundActivity.class));
                break;

            case R.id.llAccountTrade:case R.id.txtVAccountTrade:case R.id.imgVAccountTrade:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void selctDecimalValue(String val){
        txtVDecimalValue.setText(val);
        if (mDeciamlDropdown != null){
            mDeciamlDropdown.dismiss();
        }
    }

    public void selctOrder(String val){
        txtVOrderSelection.setText(val);
        if (mDeciamlDropdown != null){
            mDeciamlDropdown.dismiss();
        }
    }

    public void decrementValue(EditText editText,double dec,int count){
        double value;
        String strDe = editText.getText().toString();
        if (!strDe.equals("") && strDe != null){
            value = Double.valueOf(editText.getText().toString());
            if (value>0) {
                double val = value-dec;
                editText.setText(String.format("%."+count+"f", val));
                totalValue();
            } else {
                editText.setText("0.0");
                totalValue();
            }
        }
        else {
            editText.setText("0.0");
            totalValue();
        }
    }

     public void incrementValue(EditText editText,double dec,int count){
         double value;
         String strIn = editText.getText().toString();
         if (!strIn.equals("") && strIn != null){
             value = Double.valueOf(editText.getText().toString());
             double val = value+dec;
             editText.setText(String.format("%."+count+"f", val));
             totalValue();
         }
         else {
             editText.setText(String.format("%."+count+"f", dec));
             totalValue();
         }
     }


     public void totalValue(){
         double amt,other;
         String AmtValue,PriceValue,LimitValue;

         AmtValue = editTxtAmtValue.getText().toString();
         PriceValue = editTxtPriceValue.getText().toString();
         LimitValue = editTxtLimitValue.getText().toString();

         if (OrderTrade.equalsIgnoreCase("Limit Order")){
             if (!AmtValue.equals("") && !PriceValue.equals("") && PriceValue != null && AmtValue != null) {
                 amt = Double.valueOf(AmtValue);
                 other = Double.valueOf(PriceValue);
                 editTxtTotal.setText(String.format("%.10f",(amt*other)));
             }

         }else if (OrderTrade.equalsIgnoreCase("Stop Limit")){
             if (!AmtValue.equals("") && !LimitValue.equals("") && PriceValue != null && LimitValue != null) {
                 amt = Double.valueOf(AmtValue);
                 other = Double.valueOf(LimitValue);
                 editTxtTotal.setText(String.format("%.10f", (amt * other)));
             }
         }
     }

    LayoutInflater mInflater;
    PopupWindow mDeciamlDropdown;
    TextView txtV4Decimal,txtV5Decimal,txtV6Decimal,txtV7Decimal,txtVDefaultOrder,txtVSellOrder,txtVBuyOrder;
    private PopupWindow initiatePopupWindow(boolean bol) {
        try {
            mInflater = (LayoutInflater) getApplicationContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.dialog_decimal, null);
            //If you want to add any listeners to your textviews, these are two
            // textviews.
            txtV4Decimal = (TextView) layout.findViewById(R.id.txtV4Decimal);
            txtV5Decimal = (TextView) layout.findViewById(R.id.txtV5Decimal);
            txtV6Decimal = (TextView) layout.findViewById(R.id.txtV6Decimal);
            txtV7Decimal = (TextView) layout.findViewById(R.id.txtV7Decimal);
            txtV4Decimal.setOnClickListener(this);
            txtV5Decimal.setOnClickListener(this);
            txtV6Decimal.setOnClickListener(this);
            txtV7Decimal.setOnClickListener(this);

            txtVDefaultOrder = (TextView) layout.findViewById(R.id.txtVDefaultOrder);
            txtVSellOrder = (TextView) layout.findViewById(R.id.txtVSellOrder);
            txtVBuyOrder = (TextView) layout.findViewById(R.id.txtVBuyOrder);
            txtVDefaultOrder.setOnClickListener(this);
            txtVSellOrder.setOnClickListener(this);
            txtVBuyOrder.setOnClickListener(this);


            layout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mDeciamlDropdown = new PopupWindow(layout,FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT,true);
            if (bol) {
                LinearLayout llDecimalValue = layout.findViewById(R.id.llDecimalValue);
                llDecimalValue.setVisibility(View.VISIBLE);
                mDeciamlDropdown.showAsDropDown(txtVDecimalValue, 0, -300);
            }else {
                LinearLayout llOrder = layout.findViewById(R.id.llOrder);
                llOrder.setVisibility(View.VISIBLE);
                mDeciamlDropdown.showAsDropDown(txtVOrderSelection, 0, -250);
            }

            //mDeciamlDropdown.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.bottom_bar));
            //mDeciamlDropdown.showAtLocation(layout, Gravity.LEFT, 100, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDeciamlDropdown;
    }
}
