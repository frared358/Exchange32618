package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.os.Handler;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;
import com.google.gson.JsonElement;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;


public class MarketDataAdapter extends RecyclerView.Adapter<MarketDataAdapter.MyViewHolder> {

    Context contextMarket;
    private List<MarketData> dataList;

    Handler handler = new Handler();
    ArrayList<String> arrayExposerName = new ArrayList<String>();
    ArrayList<String> arrayExposerValue = new ArrayList<String>();


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtVRunnerName,txtVBackData,txtVBackChips,txtVLayData,txtVLayChips,txtVExposerData,txtVActiveResult;
        TextView txtVBackData2,txtVBackData3,txtVLayData2,txtVLayData3,txtVLayChips2,txtVLayChips3,txtVBackChips2,txtVBackChips3;
        LinearLayout llBack,llLay,llMarketData;
        LinearLayout llBack2,llBack3,llLay2,llLay3;
        LinearLayout llMarketDataBack,llMarketDataLay;
        public MyViewHolder(View view) {
            super(view);
            txtVRunnerName = view.findViewById(R.id.txtVRunnerName);
            txtVBackData = view.findViewById(R.id.txtVBackData);
            txtVBackChips = view.findViewById(R.id.txtVBackChips);
            txtVLayData = view.findViewById(R.id.txtVLayData);
            txtVLayChips = view.findViewById(R.id.txtVLayChips);
            txtVExposerData = view.findViewById(R.id.txtVExposerData);
            txtVActiveResult = view.findViewById(R.id.txtVActiveResult);
            llBack = view.findViewById(R.id.llBack);
            llLay = view.findViewById(R.id.llLay);
            llMarketData = view.findViewById(R.id.llMarketData);

            txtVBackData2 = view.findViewById(R.id.txtVBackData2);
            txtVBackData3 = view.findViewById(R.id.txtVBackData3);
            txtVLayData2 = view.findViewById(R.id.txtVLayData2);
            txtVLayData3 = view.findViewById(R.id.txtVLayData3);
            txtVBackChips2 = view.findViewById(R.id.txtVBackChips2);
            txtVBackChips3 = view.findViewById(R.id.txtVBackChips3);
            txtVLayChips2 = view.findViewById(R.id.txtVLayChips2);
            txtVLayChips3 = view.findViewById(R.id.txtVLayChips3);

            llBack2 = view.findViewById(R.id.llBack2);
            llBack3 = view.findViewById(R.id.llBack3);
            llLay2 = view.findViewById(R.id.llLay2);
            llLay3 = view.findViewById(R.id.llLay3);

            llMarketDataBack = view.findViewById(R.id.llMarketDataBack);
            llMarketDataLay = view.findViewById(R.id.llMarketDataLay);
            /*llMarketDataBack.setVisibility(View.VISIBLE);
            llMarketDataLay.setVisibility(View.VISIBLE);*/
        }
    }

    public MarketDataAdapter(Context contextMarket, List<MarketData> dataList){
        this.contextMarket = contextMarket;
        this.dataList = dataList;
    }


    String mRunnerName,mBack,mChipsBack,mLay,mChipsLay;
    boolean EXPOSER_EXCUTE = true;
    int EXPOSER_COUNT=0;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MarketData market = dataList.get(position);

        if (EXPOSER_EXCUTE) {
            new getExposerAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/ExposureBook?mktid="+DataHolder.getData(contextMarket,"keyMarketId"));
            EXPOSER_EXCUTE = false;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                mRunnerName=market.RunnerName;
                mBack=market.Back;
                mChipsBack=market.ChipsBack;
                mLay=market.Lay;
                mChipsLay=market.ChipsLay;

                holder.txtVRunnerName.setText(market.RunnerName);
                holder.txtVBackData.setText(market.Back);
                holder.txtVLayData.setText(market.Lay);
                holder.txtVBackData2.setText(market.Back2);
                holder.txtVBackData2.setText(market.Back2);
                holder.txtVLayData2.setText(market.Lay2);
                holder.txtVBackData3.setText(market.Back3);
                holder.txtVLayData3.setText(market.Lay3);

                holder.txtVBackChips.setText(market.ChipsBack);
                holder.txtVLayChips.setText(market.ChipsLay);
                holder.txtVBackChips2.setText(market.ChipsBack2);
                holder.txtVLayChips2.setText(market.ChipsLay2);
                holder.txtVBackChips3.setText(market.ChipsBack3);
                holder.txtVLayChips3.setText(market.ChipsLay3);


                if((market.Back == null || market.Back.equals("")) && (market.Lay==null || market.Lay.equals(""))){
                    holder.txtVActiveResult.setVisibility(View.VISIBLE);
                    holder.txtVActiveResult.setText("Suspend");
                    holder.llMarketData.setVisibility(View.GONE);
                    holder.llMarketDataBack.setVisibility(View.GONE);
                    holder.llMarketDataLay.setVisibility(View.GONE);
                }
            }
        });





        for(int i =0; i< arrayExposerName.size();i++){
            if(arrayExposerName.get(i).equalsIgnoreCase(market.RunnerName)){
                holder.txtVExposerData.setText(arrayExposerValue.get(i));

                if(Double.valueOf(arrayExposerValue.get(i))>0){
                    holder.txtVExposerData.setTextColor(ContextCompat.getColor(contextMarket, R.color.colorGreen));
                }else {
                    holder.txtVExposerData.setTextColor(ContextCompat.getColor(contextMarket, R.color.colorRed));
                }
                break;
            }
        }


        //displaySignalRData(market.bfid,holder.txtVBackData,holder.txtVLayData,market);

//        holder.llBack.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),Double.valueOf(market.Back));
//                Log.i("TAG12356","ll");
//            }
//        });
//
//        holder.llLay.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),Double.valueOf(market.Lay));
//                Log.i("TAG12356","ll");
//            }
//        });
//
//        holder.txtVBackData.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),Double.valueOf(market.Back));
//                Log.i("TAG12356","Data");
//            }
//        });
//
//        holder.txtVLayData.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),Double.valueOf(market.Lay));
//                Log.i("TAG12356","Data");
//            }
//        });

//        holder.txtVBackChips.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),Double.valueOf(market.Back));
//                Log.i("TAG12356","Chips");
//            }
//        });
//
//        holder.txtVLayChips.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),Double.valueOf(market.Lay));
//                Log.i("TAG12356","Chips");
//            }
//        });

        holder.llLay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(DataHolder.getData(contextMarket,"OneClickBet").equals("true")){
                        dialogOneClickBet(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),"Lay",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                    }else {
                        dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),"Lay",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                        //Log.i("TAG12356","Touchll");
                    }
                }
                return false;
            }
        });

        holder.llLay2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(DataHolder.getData(contextMarket,"OneClickBet").equals("true")){
                        dialogOneClickBet(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay2),"Lay",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                    }else {
                        dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay2),"Lay",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                        //Log.i("TAG12356","Touchll");
                    }
                }
                return false;
            }
        });

        holder.llLay3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(DataHolder.getData(contextMarket,"OneClickBet").equals("true")){
                        dialogOneClickBet(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay3),"Lay",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                    }else {
                        dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay3),"Lay",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                        //Log.i("TAG12356","Touchll");
                    }
                }
                return false;
            }
        });

        holder.llBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(DataHolder.getData(contextMarket,"OneClickBet").equals("true")){
                        dialogOneClickBet(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),"Back",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                    }else {
                        dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),"Back",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                        //Log.i("TAG12356","Touchll");
                    }
                }
                return false;
            }
        });

        holder.llBack2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(DataHolder.getData(contextMarket,"OneClickBet").equals("true")){
                        dialogOneClickBet(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back2),"Back",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                    }else {
                        dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back2),"Back",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                        //Log.i("TAG12356","Touchll");
                    }
                }
                return false;
            }
        });

        holder.llBack3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(DataHolder.getData(contextMarket,"OneClickBet").equals("true")){
                        dialogOneClickBet(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back3),"Back",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                    }else {
                        dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back3),"Back",Integer.parseInt(DataHolder.getData(contextMarket,"Match_Id")),Integer.parseInt(DataHolder.getData(contextMarket,"keyMarketId")));
                        //Log.i("TAG12356","Touchll");
                    }
                }
                return false;
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_market_data,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    TextView txtVProfitValue,txtVOddIncrement,txtVOddDecrement,txtVStackDecrement,txtVStackIncrement;
    EditText editTxtStackValue,editTxtVOddValue;
    Button btnBetPlace;
    LinearLayout llDialogBetPlace;
    String ODDVALUE,STACKVALUE,PROFITVALUE,P_L;

    public void dialogBetPlace(final String RunnerTitle,int color,final double oddValue,final String BackLay,final int mMatchId,final int mMarketId){

        final Dialog dialog = new Dialog(contextMarket,R.style.Dialog);
        dialog.setContentView(R.layout.dialog_bet_place);
        dialog.setTitle(RunnerTitle);
        dialog.getWindow().setBackgroundDrawableResource(color);

        //txtVRunnerTitle = dialog.findViewById(R.id.txtVRunnerTitle);
        llDialogBetPlace = dialog.findViewById(R.id.llDialogBetPlace);
        editTxtStackValue = dialog.findViewById(R.id.editTxtStackValue);
        editTxtVOddValue = dialog.findViewById(R.id.editTxtVOddValue);
        txtVProfitValue = dialog.findViewById(R.id.txtVProfitValue);
        txtVOddIncrement = dialog.findViewById(R.id.txtVOddIncrement);
        txtVOddDecrement = dialog.findViewById(R.id.txtVOddDecrement);
        txtVStackDecrement = dialog.findViewById(R.id.txtVStackDecrement);
        txtVStackIncrement = dialog.findViewById(R.id.txtVStackIncrement);
        btnBetPlace = dialog.findViewById(R.id.btnBetPlace);

        //llDialogBetPlace.setBackgroundColor(ContextCompat.getColor(contextMarket, color));
        //txtVRunnerTitle.setText(RunnerTitle);
        STACKVALUE = String.valueOf(DataHolder.STACK_VALUE);
        editTxtStackValue.setText(STACKVALUE);
        ODDVALUE = String.valueOf(oddValue);
        editTxtVOddValue.setText(ODDVALUE);

        if (BackLay.equals("Back")){
            P_L = "Profit";
            txtVProfitValue.setTextColor(ContextCompat.getColor(contextMarket,R.color.colorGreen));
        }else {
            P_L = "Liability";
            txtVProfitValue.setTextColor(ContextCompat.getColor(contextMarket,R.color.colorRed));
        }


        ProfitLiability(ODDVALUE,STACKVALUE,P_L);

        txtVOddIncrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double oddVal = 0.0;
                try {
                    oddVal = Double.valueOf(ODDVALUE);
                } catch (NumberFormatException e) {
                    oddVal = 0.0;
                    e.printStackTrace();
                }
                double inc = DataHolder.increment(oddVal);
                ODDVALUE = String.format("%.2f",inc);
                editTxtVOddValue.setText(ODDVALUE);
                ProfitLiability(ODDVALUE,STACKVALUE,P_L);
            }
        } );

        txtVOddDecrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double oddVal = 0.0;
                try {
                    oddVal = Double.valueOf(ODDVALUE);
                } catch (NumberFormatException e) {
                    oddVal = 0.0;
                    e.printStackTrace();
                }

                double dec = DataHolder.decrement(oddVal);
                ODDVALUE = String.format("%.2f",dec);
                editTxtVOddValue.setText(ODDVALUE);
                ProfitLiability(ODDVALUE,STACKVALUE,P_L);

            }
        });

        txtVStackDecrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double stackVal = 0.0;
                try {
                    stackVal = Double.valueOf(STACKVALUE);
                } catch (NumberFormatException e) {
                    stackVal = 0.0;
                    e.printStackTrace();
                }
                double dec = DataHolder.decrement(stackVal);
                STACKVALUE = String.format("%.2f",dec);
                editTxtStackValue.setText(STACKVALUE);
                ProfitLiability(ODDVALUE,STACKVALUE,P_L);
            }
        });

        txtVStackIncrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double stackVal = 0.0;
                try {
                    stackVal = Double.valueOf(STACKVALUE);
                } catch (NumberFormatException e) {
                    stackVal = 0.0;
                    e.printStackTrace();
                }
                double inc = DataHolder.increment(stackVal);
                STACKVALUE = String.format("%.2f",inc);
                editTxtStackValue.setText(STACKVALUE);
                ProfitLiability(ODDVALUE,STACKVALUE,P_L);
            }
        });

        btnBetPlace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (editTxtVOddValue.getText().toString().equals("")){
                    Snackbar snackbar = Snackbar.make(BetActivity.scrollBetActivity, "Odds Value is Missing", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if (editTxtStackValue.getText().toString().equals("")){
                    Snackbar snackbar = Snackbar.make(BetActivity.scrollBetActivity, "Stake Value is Missing", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    DataHolder.showProgress(contextMarket);
                    new BetPlaceAsyncTask().execute(BackLay,String.valueOf(mMarketId),String.valueOf(mMatchId),ODDVALUE,STACKVALUE,PROFITVALUE,RunnerTitle);
                }

                dialog.cancel();
            }
        });

        editTxtVOddValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if(Double.valueOf(s.toString().trim()) <1000)  {
                        ODDVALUE = s.toString();
                        ProfitLiability(ODDVALUE,STACKVALUE,P_L);
                    }
                    else {
                        txtVProfitValue.setText("0.0");
                        editTxtVOddValue.setText("0.0");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        editTxtStackValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if(Double.valueOf(s.toString().trim()) <10000000)  {
                        STACKVALUE = s.toString();
                        ProfitLiability(ODDVALUE,STACKVALUE,P_L);
                    }else  {
                        txtVProfitValue.setText("0.0");
                        editTxtStackValue.setText(String.valueOf(before));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        dialog.show();
    }

    public void ProfitLiability(String ODDVALUE,String STACKVALUE,String P_L){
            PROFITVALUE = String.format(P_L+" CHIPS %.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
            txtVProfitValue.setText(PROFITVALUE);
    }

    public void dialogOneClickBet(final String RunnerTitle,int color,final double oddValue,final String BackLay,final int mMatchId,final int mMarketId){

        final Dialog dialog = new Dialog(contextMarket,R.style.Dialog);
        dialog.setContentView(R.layout.dialog_one_click_bet);
        dialog.setTitle("Please Confirm Your Bet");
        dialog.getWindow().setBackgroundDrawableResource(color);

        //txtVRunnerTitle = dialog.findViewById(R.id.txtVRunnerTitle);
        LinearLayout llOneClickBet = dialog.findViewById(R.id.llOneClickBet);
        TextView txtVOneClickTitle = dialog.findViewById(R.id.txtVOneClickTitle);
        TextView txtVOddOneClickValue = dialog.findViewById(R.id.txtVOddOneClickValue);
        TextView txtVStackOneClickValue = dialog.findViewById(R.id.txtVStackOneClickValue);
        TextView txtVProfitOneClickValue = dialog.findViewById(R.id.txtVProfitOneClickValue);
        Button btnOneClickCancel = dialog.findViewById(R.id.btnOneClickCancel);
        Button btnOneClickConfirm = dialog.findViewById(R.id.btnOneClickConfirm);

        txtVOneClickTitle.setText(RunnerTitle);

        STACKVALUE = String.valueOf(DataHolder.STACK_VALUE);
        txtVStackOneClickValue.setText(STACKVALUE);

        ODDVALUE = String.valueOf(oddValue);
        txtVOddOneClickValue.setText(ODDVALUE);

        PROFITVALUE = String.format("%.2f", DataHolder.profit(oddValue,DataHolder.STACK_VALUE));
        txtVProfitOneClickValue.setText(PROFITVALUE);

        btnOneClickConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataHolder.showProgress(contextMarket);
                new BetPlaceAsyncTask().execute(BackLay,String.valueOf(mMarketId),String.valueOf(mMatchId),ODDVALUE,STACKVALUE,PROFITVALUE,RunnerTitle);
                dialog.cancel();
            }
        });

        btnOneClickCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public String  BetPlaceApi(String backlay,String marketId,String matchId,String odds,String stake,String profit,String runnerName){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://173.212.248.188/pclient/Prince.svc/Bets/PlaceMOBet");

            //Log.i("ghji",""+odds+" "+backlay+" "+marketId+" "+matchId+" "+odds+" "+profit+" "+runnerName+" "+stake);
            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("backlay",backlay);
            jsonObject.accumulate("info","Android");
            jsonObject.accumulate("marketId",marketId);
            jsonObject.accumulate("matchId",matchId);
            jsonObject.accumulate("odds",odds);
            jsonObject.accumulate("profit",profit);
            jsonObject.accumulate("runnerName",runnerName);
            jsonObject.accumulate("source","Mobile");
            jsonObject.accumulate("stake",stake);

            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json");

            httpPost.setEntity(new StringEntity(json));

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Token", DataHolder.LOGIN_TOKEN);

            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                try {
                    result = DataHolder.convertInputStreamToString(inputStream);
                }
                catch (Exception e){
                    Log.e("ERROR ",""+e);
                }
            }
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("ERROR ", ""+e);
        }
        return result;
    }

    private class BetPlaceAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {

            return BetPlaceApi(urls[0],urls[1],urls[2],urls[3],urls[4],urls[5],urls[6]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check",""+result);
            //Toast.makeText(contextMarket, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String status = jsonObjMain.getString("status");
                String msg = jsonObjMain.getString("result");
                if(status.equalsIgnoreCase("Success")){
                    Snackbar snackbar = Snackbar.make(BetActivity.scrollBetActivity, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    Snackbar snackbar = Snackbar.make(BetActivity.scrollBetActivity, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            DataHolder.cancelProgress();
            new getExposerAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/ExposureBook?mktid="+DataHolder.getData(contextMarket,"keyMarketId"));

        }
    }

    private class getExposerAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("CheckExposer",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();

                for(int i=0; i<len; i++){
                    JSONObject key = jsonArray.getJSONObject(i);
                    arrayExposerName.add(key.getString("Key"));
                    arrayExposerValue.add(key.getString("Value"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}