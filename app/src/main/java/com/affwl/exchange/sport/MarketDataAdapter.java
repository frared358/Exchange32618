package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
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
    Handler handlerExposer = new Handler();
    ArrayList<String> arrayExposerName = new ArrayList<String>();
    ArrayList<String> arrayExposerValue = new ArrayList<String>();


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtVRunnerName,txtVBackData,txtVBackChips,txtVLayData,txtVLayChips,txtVExposerData;
        LinearLayout llBack,llLay;
        public MyViewHolder(View view) {
            super(view);
            txtVRunnerName = view.findViewById(R.id.txtVRunnerName);
            txtVBackData = view.findViewById(R.id.txtVBackData);
            txtVBackChips = view.findViewById(R.id.txtVBackChips);
            txtVLayData = view.findViewById(R.id.txtVLayData);
            txtVLayChips = view.findViewById(R.id.txtVLayChips);
            txtVExposerData = view.findViewById(R.id.txtVExposerData);
            llBack = view.findViewById(R.id.llBack);
            llLay = view.findViewById(R.id.llLay);
        }
    }

    public MarketDataAdapter(Context contextMarket, List<MarketData> dataList){
        this.contextMarket = contextMarket;
        this.dataList = dataList;
    }

    boolean ONCES=true;
    String mRunnerName,mBack,mChipsBack,mLay,mChipsLay;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MarketData market = dataList.get(position);
        //if (ONCES) {

            mRunnerName=market.RunnerName;
            mBack=market.Back;
            mChipsBack=market.ChipsBack;
            mLay=market.Lay;
            mChipsLay=market.ChipsLay;

            holder.txtVRunnerName.setText(market.RunnerName);
            holder.txtVBackData.setText(market.Back);
            holder.txtVBackChips.setText(market.ChipsBack);
            holder.txtVLayData.setText(market.Lay);
            holder.txtVLayChips.setText(market.ChipsLay);




                new getExposerAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/ExposureBook?mktid=130");
                for(int i =0; i< arrayExposerName.size();i++){
                    if(arrayExposerName.get(i).equalsIgnoreCase(market.RunnerName)){
                        holder.txtVExposerData.setText(arrayExposerValue.get(i));
                        if(Integer.parseInt(arrayExposerValue.get(i))>0){
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
                    dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),Double.valueOf(market.Lay));
                    Log.i("TAG12356","Touchll");
                }
                return false;
            }
        });

        holder.llBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),Double.valueOf(market.Back));
                    Log.i("TAG12356","Touchll");
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

    TextView txtVRunnerTitle,txtVProfitValue,txtVOddIncrement,txtVOddDecrement,txtVStackDecrement,txtVStackIncrement;
    EditText editTxtStackValue,editTxtVOddValue;
    Button btnBetPlace;
    LinearLayout llDialogBetPlace;
    String ODDVALUE,STACKVALUE,PROFITVALUE;

    public void dialogBetPlace(String RunnerTitle,int color,final double oddValue,double market){

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

        PROFITVALUE = String.format("Profit CHIPS %.2f", profit(oddValue,DataHolder.STACK_VALUE));
        txtVProfitValue.setText(PROFITVALUE);



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
                double inc = increment(oddVal);
                ODDVALUE = String.format("%.2f",inc);
                editTxtVOddValue.setText(ODDVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText(PROFITVALUE);
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

                double dec = decrement(oddVal);
                ODDVALUE = String.format("%.2f",dec);
                editTxtVOddValue.setText(ODDVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText(PROFITVALUE);
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
                Log.i("TAG",stackVal+"");
                double dec = decrement(stackVal);
                Log.i("TAG",dec+"");
                STACKVALUE = String.format("%.2f",dec);
                editTxtStackValue.setText(STACKVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText(PROFITVALUE);
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
                double inc = increment(stackVal);
                STACKVALUE = String.format("%.2f",inc);
                editTxtStackValue.setText(STACKVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText(PROFITVALUE);
            }
        });

        btnBetPlace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //new BetPlaceAsyncTask().execute("back","","",ODDVALUE,STACKVALUE,"","","");
            }
        });

        editTxtVOddValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0 && s.length() <=5)  {
                    ODDVALUE = s.toString();
                    PROFITVALUE = String.format("Profit CHIPS %.2f", profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                    txtVProfitValue.setText(PROFITVALUE);
                }
                else {
                    txtVProfitValue.setText("0.0");
                    editTxtVOddValue.setText("0.0");
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
                if(s.length() != 0 && Double.valueOf(s.toString().trim()) <=100000 && s.length() < 10)  {
                    STACKVALUE = s.toString();
                    PROFITVALUE = String.format("Profit CHIPS %.2f", profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                    txtVProfitValue.setText(PROFITVALUE);
                }else  {
                    txtVProfitValue.setText("0.0");
                    editTxtStackValue.setText("0.0");
                }
            }
        });

        dialog.show();
    }

    private double increment(double val){

        if(val >=0.00 && val <1.00){
            val = val+0.02;
        }
        else if(val >=1.0 && val <10.0){
            val = val+0.5;
        }
        else if(val >=10.0 && val <100.0) {
            val = val+5.0;
        }
        else if(val >=100.0 && val <1000.0) {
            val = val+20.0;
        }
        else if(val >=1000.0 ) {
            val = 0;
        }
        return val;
    }

    private double decrement(double val){

        if(val >0.05 && val <1.00){
            val = val-0.02;
        }
        else if(val >=1.0 && val <10.0){
            val = val-0.5;
        }
        else if(val >=10.0 && val <100.0) {
            val = val-5.0;
        }
        else if(val >=10.0 && val <1000.0) {
            val = val-5.0;
        }
        else if(val <= 0.05) {
            val = 0;
        }
        return val;
    }

    private double profit(double odd,double stack){
        return (odd-1)*stack;
    }

    HubConnection _connection;
    HubProxy _hub;
    SignalRFuture<Void> awaitConnection;
    Handler handler = new Handler();
    MarketData marketData;
    String back1,lay1,runner;

    public void displaySignalRData(final String bfid,final TextView backData,final TextView layData,MarketData market){
        marketData = market;

        Platform.loadPlatformComponent( new AndroidPlatformComponent() );
        _connection=new HubConnection("http://178.238.236.221:10800");
        _hub=_connection.createHubProxy("BetAngelHub");

        try {
            awaitConnection = _connection.start();
            awaitConnection.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        _hub.invoke("SubscribeMarket",bfid);

        try{
            _connection.received(new MessageReceivedHandler() {
                @Override
                public void onMessageReceived(JsonElement json) {

                    Log.i("Signalr",""+json);
                    //Toast.makeText(BetActivity.this, ""+json, Toast.LENGTH_SHORT).show();

//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    },1000);

                    try {


                        JSONObject jsonMain = new JSONObject(json.toString());
                        String jsonData = jsonMain.getString("A");
                        JSONArray jsonArray = new JSONArray(jsonData);
                        final JSONObject key = jsonArray.getJSONObject(0);
                        Log.i("TAG",key.getString("runner"));
                        back1 = key.getString("back1");
                        lay1 = key.getString("lay1");
                        runner = key.getString("runner");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(BetActivity.this, ""+json, Toast.LENGTH_SHORT).show();
                    for(int i=0;i<BetActivity.MarketDataArray.size();i++) {
                        marketData = dataList.get(i);
                        if (BetActivity.MarketDataArray.get(i).equalsIgnoreCase(runner)) {
                            marketData = dataList.get(i);
                            Log.i("TAG1234", BetActivity.MarketDataArray.get(i) + " " + runner +" "+marketData.RunnerName);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    backData.setText(marketData.Back);
                                    layData.setText(marketData.Lay);
                                }
                            },1000);
                        }
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            //Log.i("graph",e.toString());}
        }
    }

    public String  BetPlaceApi(String backlay,String marketId,String matchId,String odds,String stake,String profit,String runnerName,String source){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://173.212.248.188/pclient/Prince.svc/Bets/PlaceMOBet");

            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("backlay",backlay);
            jsonObject.accumulate("info","Android");
            jsonObject.accumulate("marketId",marketId);
            jsonObject.accumulate("matchId",matchId);
            jsonObject.accumulate("odds",odds);
            jsonObject.accumulate("profit",profit);
            jsonObject.accumulate("runnerName",runnerName);
            jsonObject.accumulate("source",source);
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
                    result = convertInputStreamToString(inputStream);
                }
                catch (Exception e){
                    Log.e("Check",""+e);
                }
            }
            else
                result = "Did not work!";
            Log.e("Check","how "+result);

        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }
        return result;
    }

    private class BetPlaceAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(final String... urls) {


            return BetPlaceApi(urls[0],urls[1],urls[2],urls[3],urls[4],urls[5],urls[6],urls[7]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONObject jsonObjData = new JSONObject(jsonObjMain.getString("data"));
                String runnerData = jsonObjData.getString("runnerData");
                Log.i("TAG",""+runnerData);
                JSONArray arrayData = new JSONArray(runnerData);
                int length = arrayData.length();

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String back1 = key.getString("back1");
                    String lay1 = key.getString("lay1");
                    String backSize1 = key.getString("backSize1");
                    String laySize1 = key.getString("laySize1");
                    String runnerName = key.getString("runnerName");



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        inputStream.close();
        return result;
    }

    public String  getExposerApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet Httpget = new HttpGet(url);

            Httpget.setHeader("Accept", "application/json");
            Httpget.setHeader("Content-type", "application/json");
            Httpget.setHeader("Token", DataHolder.LOGIN_TOKEN);

            HttpResponse httpResponse = httpclient.execute(Httpget);
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                try {
                    result = convertInputStreamToString(inputStream);
                }
                catch (Exception e){
                    Log.e("Check",""+e);
                }
            }
            else
                result = "Did not work!";


        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }
        return result;
    }

    private class getExposerAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getExposerApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

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
