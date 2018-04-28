
package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.os.ResultReceiver;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.LoginActivity;
import com.affwl.exchange.R;
import com.google.gson.JsonElement;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

import microsoft.aspnet.signalr.client.Action;
import microsoft.aspnet.signalr.client.ConnectionState;
import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.StateChangedCallback;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;

import static com.affwl.exchange.DataHolder._connection;
import static com.affwl.exchange.DataHolder._hub;
//Bet
public class BetActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener/*, ResultReceiverSignalR.Receiver*/ {

    String matchName,matchDate,bfId;
    int marketId,matchId;
    ImageView imgVCheck,imgRightDrawer;
    //DrawerLayout drawerBet;
    NavigationView navigationView1,navigationView2;
    TextView txtVChipsStake,txtVTitleMatchName,txtLiveSymbol,txtMarketTime;
    ImageView imgVFav;
    Handler handler;
    Runnable runnable;
    boolean REFRESH_FANCY = false,REFRESH_BOOKMAKING = false,REFRESH_MARKET=false;
    LinearLayout llBookMaking,llFancyBet,llMatchOddData;
    public static ScrollView scrollBetActivity;

    RecyclerView recycleViewMarketData,recycleViewBookMakingData,recycleViewFancyBet;
    private List<MarketData> MarketDataList = new ArrayList<>();
    private List<MarketData> BookMakingDataList = new ArrayList<>();
    private List<MarketData> FancyDataList = new ArrayList<>();
    MarketDataAdapter marketDataAdapter;
    BookMakingAdapter  bookMakingAdapter;
    FancyAdapter fancyAdapter;

    Handler handlerMarketData,handlerBookMaking,handlerFancy;
    private BroadcastReceiverSignalr broadcastReceiverSignalr;

    SignalRFuture<Void> awaitConnection;

    public static ArrayList<String> MarketDataArray = new ArrayList<String>();
    public static ArrayList<String> BookMakingArray = new ArrayList<String>();
    public static ArrayList<String> FancyArray = new ArrayList<String>();

    public ResultReceiverSignalR mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* mReceiver = new ResultReceiverSignalR(new Handler());
        mReceiver.setReceiver(this);*/


        txtMarketTime = findViewById(R.id.txtMarketTime);
        txtLiveSymbol = findViewById(R.id.txtLiveSymbol);
        imgVCheck = findViewById(R.id.imgVCheck);
        imgVCheck.setOnClickListener(this);
        imgRightDrawer = findViewById(R.id.imgRightDrawer);
        imgRightDrawer.setOnClickListener(this);

        recycleViewMarketData = findViewById(R.id.recycleViewMarketData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleViewMarketData.setLayoutManager(mLayoutManager);
        recycleViewMarketData.setItemAnimator(new DefaultItemAnimator());
        marketDataAdapter = new MarketDataAdapter(this,MarketDataList);

        recycleViewBookMakingData = findViewById(R.id.recycleViewBookMakingData);
        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(this);
        recycleViewBookMakingData.setLayoutManager(nLayoutManager);
        recycleViewBookMakingData.setItemAnimator(new DefaultItemAnimator());
        bookMakingAdapter = new BookMakingAdapter(this,BookMakingDataList);

        recycleViewFancyBet = findViewById(R.id.recycleViewFancyBet);
        RecyclerView.LayoutManager fLayoutManager = new LinearLayoutManager(this);
        recycleViewFancyBet.setLayoutManager(fLayoutManager);
        recycleViewFancyBet.setItemAnimator(new DefaultItemAnimator());
        fancyAdapter = new FancyAdapter(this,FancyDataList);

        txtVChipsStake = findViewById(R.id.txtVChipsStake);
        txtVChipsStake.setOnClickListener(this);

        imgVFav = findViewById(R.id.imgVFav);
        imgVFav.setOnClickListener(this);

        //DataHolder.setLayout();
        scrollBetActivity = findViewById(R.id.scrollBetActivity);




        marketId = getIntent().getIntExtra("marketId",0);
        matchName = getIntent().getStringExtra("matchName");
        matchDate = DataHolder.MATCH_DATE;
        matchId = getIntent().getIntExtra("matchId",0);
        bfId = getIntent().getStringExtra("bfId");

        String[] date = matchDate.split(" ");
        txtMarketTime.setText(date[1].substring(0,5));
        /*DataHolder.setData(this,"keyMarketId",String.valueOf(marketId));*/
        DataHolder.setData(this,"Match_Id",String.valueOf(matchId));

        //Log.i("TAG4561",matchName+" "+marketId+" "+matchId);
        txtVTitleMatchName = findViewById(R.id.txtVTitleMatchName);
        txtVTitleMatchName.setText(matchName);

        llBookMaking = findViewById(R.id.llBookMaking);
        llFancyBet = findViewById(R.id.llFancyBet);
        llMatchOddData = findViewById(R.id.llMatchOddData);



        //Start IntentService
        Intent intentService = new Intent(this, SignalRService.class);
        intentService.putExtra("BfId", bfId);
        if(intentService != null){
            startService(intentService);
        }


//        Intent intentFBMService = new Intent(this, FancyBookMakingService.class);
//        intentFBMService.putExtra("matchId", String.valueOf(matchId));
//        if(intentFBMService != null){
//
//            startService(intentFBMService);
//        }


//        broadcastReceiverSignalr = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                Toast.makeText(context, "BroadcastReceiverSignalr "+action, Toast.LENGTH_SHORT).show();
//                String result = intent.getStringExtra(DataHolder.keySIGNALR);
//                Log.i("TAGG",result);
//            }
//        };



//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                IntentFilter intentFilter = new IntentFilter();
//                intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//                registerReceiver(broadcastReceiverSignalr, intentFilter);
//            }
//        },50);

        handler = new Handler();
        DataHolder.SIGNALR = true;
        //DataHolder.showProgress(getApplicationContext());
        new getStackAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Settings/GetBetStakeSetting");
        new getBookMakingAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/FancyData?mtid="+matchId);
        new getMartketDataAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/MktData?mtid="+matchId+"&mktid="+marketId);

        handlerFancy = new Handler();

    }

    Thread t;
    @Override
    protected void onResume() {
        super.onResume();

        txtVChipsStake.setText("CHIPS "+DataHolder.getSTACK(this,"ChipsValue"));
        try{
            DataHolder.STACK_VALUE = Double.valueOf(DataHolder.getSTACK(this,"ChipsValue"));
        }catch(NumberFormatException e){

        }

        t=new Thread(){

            @Override
            public void run(){
                while(!isInterrupted()){
                    try {
                        Thread.sleep(2000);  //1000ms = 1 sec

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new getBookMakingRefreshAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/FancyData?mtid="+matchId);
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        try {
            if (REFRESH_BOOKMAKING || REFRESH_FANCY) {
                t.start();
            }else {
                t.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            t.interrupt();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiverSignalr != null) {
            unregisterReceiver(broadcastReceiverSignalr);
        }
        MarketDataArray.clear();
        BookMakingArray.clear();
        FancyArray.clear();

        if (_connection != null){
            _connection.stop();
            //Toast.makeText(this, "Connection Closed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgVCheck:
                startActivity(new Intent(this,UnmatchMatchTabActivity.class));
                break;

            case R.id.txtVChipsStake:
                dialogStack();
                break;
            case R.id.imgVFav:

                MarketDataArray.clear();
                BookMakingArray.clear();
                FancyArray.clear();

                if (_connection != null){
                    _connection.stop();
                }

                Intent intent = new Intent(this,SportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("KEY_SELECT","true");
                startActivity(intent);
                break;
            case R.id.txtVStackValue1:
                stackInput(txtVStackValue1);
                break;
            case R.id.txtVStackValue2:
                stackInput(txtVStackValue2);
                break;
            case R.id.txtVStackValue3:
                stackInput(txtVStackValue3);
                break;
        }
    }

    public void stackInput(TextView stackTextView){
        editStackValue.setText(stackTextView.getText().toString().replace("CHIPS ",""));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    TextView txtVOK,txtVStackValue1,txtVStackValue2,txtVStackValue3;
    TextView txtVCancel;
    TextView txtVStackEdit;
    EditText editStackValue;
    void dialogStack(){
// custom dialog

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_stack);
        dialog.setTitle("Stack");
        txtVOK =  dialog.findViewById(R.id.txtVOK);
        txtVCancel =  dialog.findViewById(R.id.txtVCancel);
        txtVStackEdit = dialog.findViewById(R.id.txtVStackEdit);

        Switch switchOneClickBet = dialog.findViewById(R.id.switchOneClickBet);

        if (DataHolder.getData(BetActivity.this,"OneClickBet").equals("true")){
            switchOneClickBet.setChecked(true);
        }

        editStackValue = dialog.findViewById(R.id.editStackValue);
        txtVStackValue1 =  dialog.findViewById(R.id.txtVStackValue1);
        txtVStackValue2 =  dialog.findViewById(R.id.txtVStackValue2);
        txtVStackValue3 =  dialog.findViewById(R.id.txtVStackValue3);

        txtVStackValue1.setOnClickListener(this);
        txtVStackValue2.setOnClickListener(this);
        txtVStackValue3.setOnClickListener(this);

        txtVStackValue1.setText("CHIPS "+DataHolder.getSTACK(this,"StackValue1"));
        txtVStackValue2.setText("CHIPS "+DataHolder.getSTACK(this,"StackValue2"));
        txtVStackValue3.setText("CHIPS "+DataHolder.getSTACK(this,"StackValue3"));

        editStackValue.setText(DataHolder.getSTACK(this,"ChipsValue"));

        txtVOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editStackValue.getText().toString().equals("") && editStackValue.getText().toString() != null ){
                    txtVChipsStake.setText("CHIPS "+editStackValue.getText().toString());
                    DataHolder.setSTACK(BetActivity.this,"ChipsValue",editStackValue.getText().toString());
                    DataHolder.STACK_VALUE = Double.valueOf(editStackValue.getText().toString());


                    dialog.dismiss();
                }else {
                    editStackValue.setError("Enter Chips Here");
                }
            }
        });



        switchOneClickBet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DataHolder.setData(BetActivity.this,"OneClickBet","true");
                }else {
                    DataHolder.setData(BetActivity.this,"OneClickBet","false");
                }
            }
        });

        txtVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txtVStackEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(BetActivity.this,StackActivity.class));
            }
        });

        dialog.show();
    }


    String back1,lay1,runner,backSize1,laySize1;
    int index;

    public void dispSignalRData(final String json){

        try {

            JSONObject jsonMain = new JSONObject(json);
            String jsonData = jsonMain.getString("A");
            JSONArray jsonArray = new JSONArray(jsonData);
            final JSONObject key = jsonArray.getJSONObject(0);
            //Log.i("TAG",json.toString());
            back1 = key.getString("back1");
            lay1 = key.getString("lay1");
            backSize1 = key.getString("backSize1");
            laySize1 = key.getString("laySize1");
            runner = key.getString("runner");

            for(index=0;index<MarketDataArray.size();index++) {
                if (MarketDataArray.get(index).equalsIgnoreCase(runner)) {
                    if(index<MarketDataArray.size()){
                        //Log.i("TAG1234", MarketDataArray.get(index) + " " + runner +" "+runner+ " " +back1+ " " +lay1+ " " +backSize1+ " " +laySize1+ " " +bfId);
                        MarketDataList.remove(index);
                        MarketDataList.add(index,new MarketData(runner,back1,lay1,backSize1,laySize1));
                        marketDataAdapter.notifyDataSetChanged();
                    }
                    else {
                        index = 0;
                        break;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private class setSignalRDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return urls[0];
        }

        @Override
        protected void onPostExecute(String json) {
            try {

                JSONObject jsonMain = new JSONObject(json);
                String jsonData = jsonMain.getString("A");
                JSONArray jsonArray = new JSONArray(jsonData);
                final JSONObject key = jsonArray.getJSONObject(0);
                //Log.i("TAG",json.toString());
                back1 = key.getString("back1");
                lay1 = key.getString("lay1");
                backSize1 = key.getString("backSize1");
                laySize1 = key.getString("laySize1");
                runner = key.getString("runner");

                for(index=0;index<MarketDataArray.size();index++) {
                    if (MarketDataArray.get(index).equalsIgnoreCase(runner)) {
                        if(index<MarketDataArray.size()){
                            //Log.i("TAG1234", MarketDataArray.get(index) + " " + runner +" "+runner+ " " +back1+ " " +lay1+ " " +backSize1+ " " +laySize1+ " " +bfId);
                            MarketDataList.remove(index);
                            MarketDataList.add(index,new MarketData(runner,back1,lay1,backSize1,laySize1));
                            marketDataAdapter.notifyDataSetChanged();
                        }
                        else {
                            index = 0;
                            break;
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    String StackValue1,StackValue2,StackValue3;

    private class getStackAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                //Log.i("TAG",""+strData);

                JSONObject jsonObj = new JSONObject(jsonObjMain.getString("data"));

                StackValue1 = jsonObj.getString("stake1");
                StackValue2 = jsonObj.getString("stake2");
                StackValue3 = jsonObj.getString("stake3");
                txtVChipsStake.setText("CHIPS "+StackValue1);
                DataHolder.STACK_VALUE = Double.valueOf(StackValue1);

                DataHolder.setSTACK(BetActivity.this,"StackValue1",StackValue1);
                DataHolder.setSTACK(BetActivity.this,"StackValue2",StackValue2);
                DataHolder.setSTACK(BetActivity.this,"StackValue3",StackValue3);
                DataHolder.setSTACK(BetActivity.this,"ChipsValue",StackValue1);

            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(BetActivity.this,result);
            }
        }
    }

    private class getMartketDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {


            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check1",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONObject jsonObjData = new JSONObject(jsonObjMain.getString("data"));
                String isInplay = jsonObjData.getString("isInplay");
                String runnerData = jsonObjData.getString("runnerData");
                //Log.i("TAG",""+runnerData);

                JSONArray arrayData = new JSONArray(runnerData);
                int length = arrayData.length();

                if(length >0){
                    llMatchOddData.setVisibility(View.VISIBLE);
                    REFRESH_MARKET = true;
                }else {
                    REFRESH_MARKET = false;
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String back1 = key.getString("back1");
                    String lay1 = key.getString("lay1");
                    String backSize1 = key.getString("backSize1");
                    String laySize1 = key.getString("laySize1");
                    String runnerName = key.getString("runnerName");

                    //String matchStatus = key.getString("matchStatus");
                    MarketDataArray.add(key.getString("runnerName"));
                    MarketDataList.add(new MarketData(runnerName,back1,lay1,backSize1,laySize1,bfId,matchId,marketId));
                    marketDataAdapter.notifyDataSetChanged();

                    if(isInplay.equalsIgnoreCase("true") || isInplay.equalsIgnoreCase("1")){
                        txtLiveSymbol.setText("L");
                        txtLiveSymbol.setBackgroundColor(ContextCompat.getColor(BetActivity.this,R.color.colorGreen));
                    }else {
                        txtLiveSymbol.setText("G");
                        txtLiveSymbol.setBackgroundColor(ContextCompat.getColor(BetActivity.this,R.color.colorBlue));
                    }
                }
                recycleViewMarketData.setAdapter(marketDataAdapter);

                //register BroadcastReceiver
                try {
                    if (REFRESH_MARKET) {
                        broadcastReceiverSignalr = new BroadcastReceiverSignalr();
                        IntentFilter intentFilter = new IntentFilter(DataHolder.ACTION_SEND_ACTIVE);
                        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
                        registerReceiver(broadcastReceiverSignalr, intentFilter);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //displaySignalRData(bfId);
            } catch (JSONException e) {
                e.printStackTrace();
                unAuthorized(BetActivity.this,result);
            }
            //DataHolder.cancelProgress();
        }
    }

    private class getBookMakingAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String bookmakingData = jsonObjMain.getString("bookmakingData");
                JSONObject jsonBook = new JSONObject(bookmakingData);
                int BookId = jsonBook.getInt("id");
                String runnerData = jsonBook.getString("runnerData");
                JSONArray arrayData = new JSONArray(runnerData);
                int lengthBook = arrayData.length();

                if(lengthBook>0){

                    llBookMaking.setVisibility(View.VISIBLE);
                    for(int i =0 ; i<lengthBook;i++){
                        JSONObject key = arrayData.getJSONObject(i);
                        int back = key.getInt("backPrice");
                        int lay = key.getInt("layPrice");
                        int backSize = key.getInt("backSize");
                        int laySize = key.getInt("laySize");
                        String runnerName = key.getString("name");
                        String ballStatus = key.getString("ballStatus");
                        String book = key.getString("book");
                        int runnerId = key.getInt("id");
                        BookMakingArray.add(runnerName);


                        BookMakingDataList.add(new MarketData(BookId,runnerId,back,lay,backSize,laySize,runnerName,ballStatus,book));
                        bookMakingAdapter.notifyDataSetChanged();
                    }
                    recycleViewBookMakingData.setAdapter(bookMakingAdapter);
                    REFRESH_BOOKMAKING = true;
                }else {
                    REFRESH_BOOKMAKING =false;
                }

                String fancyData = jsonObjMain.getString("data");
                JSONArray fancyArrayData = new JSONArray(fancyData);
                int lengthFancy = fancyArrayData.length();
                //Log.i("TAG456",fancyData);

                if(lengthFancy>0){

                    llFancyBet.setVisibility(View.VISIBLE);

                    for(int i =0 ; i<lengthFancy;i++){
                        JSONObject key = fancyArrayData.getJSONObject(i);
                        String yesRate = key.getString("yesRate");
                        String yesScore = key.getString("yesScore");
                        String noRate = key.getString("noRate");
                        String noScore = key.getString("noScore");
                        String runnerName = key.getString("name");
                        String ballStatus = key.getString("ballStatus");
                        String book = key.getString("book");
                        int fancyId = key.getInt("id");
                        FancyArray.add(runnerName);

                        FancyDataList.add(new MarketData(yesRate,yesScore,noRate,noScore,runnerName,ballStatus,book,fancyId,matchId));
                        fancyAdapter.notifyDataSetChanged();
                    }
                    recycleViewFancyBet.setAdapter(fancyAdapter);
                    REFRESH_FANCY = true;
                    //register BroadcastReceiver
//                    try {
//                        IntentFilter intentFilter = new IntentFilter(DataHolder.ACTION_SEND_FANCY_BOOKMAKING);
//                        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//                        registerReceiver(broadcastReceiverSignalr, intentFilter);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }else {
                    REFRESH_FANCY =false;
                }

                if (REFRESH_BOOKMAKING || REFRESH_FANCY) {
                    t.start();
                }else {
                    t.interrupt();
                }


            } catch (JSONException e) {
                Log.i("ERROR ",""+e);
                e.printStackTrace();
                //DataHolder.unAuthorized(BetActivity.this,result);
            }
        }
    }


    private class getBookMakingRefreshAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("CheckRefresh",""+result);
            //Toast.makeText(BetActivity.this, "result \n"+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String bookmakingData = jsonObjMain.getString("bookmakingData");
                JSONObject jsonBook = new JSONObject(bookmakingData);
                int BookId = jsonBook.getInt("id");
                String runnerData = jsonBook.getString("runnerData");
                JSONArray arrayData = new JSONArray(runnerData);
                int lengthBook = arrayData.length();

                if(lengthBook>0){

                    llBookMaking.setVisibility(View.VISIBLE);

                    for(int i =0 ; i<lengthBook;i++){
                        JSONObject key = arrayData.getJSONObject(i);
                        int back = key.getInt("backPrice");
                        int lay = key.getInt("layPrice");
                        int backSize = key.getInt("backSize");
                        int laySize = key.getInt("laySize");
                        String runnerName = key.getString("name");
                        String ballStatus = key.getString("ballStatus");
                        String book = key.getString("book");
                        int runnerId = key.getInt("id");

                        for(int indexBookMaking=0;indexBookMaking<BookMakingArray.size();indexBookMaking++) {

                            if (BookMakingArray.get(indexBookMaking).equalsIgnoreCase(runnerName)) {
                                //Log.i("CheckRefreshindex",BookMakingArray.get(indexBookMaking)+" "+indexBookMaking+" "+BookMakingArray.size());
                                if(indexBookMaking<BookMakingArray.size()){
                                    BookMakingDataList.remove(indexBookMaking);

                                    BookMakingDataList.add(indexBookMaking,new MarketData(BookId,runnerId,back,lay,backSize,laySize,runnerName,ballStatus,book));
                                    bookMakingAdapter.notifyDataSetChanged();
                                }
                                else {
                                    indexBookMaking = 0;
                                    break;
                                }
                            }
                        }
                    }

                }

                String fancyData = jsonObjMain.getString("data");
                JSONArray fancyArrayData = new JSONArray(fancyData);
                int lengthFancy = fancyArrayData.length();
                //Log.i("TAG456",fancyData);

                if(lengthFancy>0){

                    llFancyBet.setVisibility(View.VISIBLE);

                    for(int i =0 ; i<lengthFancy; i++){
                        JSONObject key = fancyArrayData.getJSONObject(i);
                        String yesRate = key.getString("yesRate");
                        String yesScore = key.getString("yesScore");
                        String noRate = key.getString("noRate");
                        String noScore = key.getString("noScore");
                        String runnerName = key.getString("name");
                        String ballStatus = key.getString("ballStatus");
                        String book = key.getString("book");
                        int fancyId = key.getInt("id");


                        for(int indexFancy=0;indexFancy<FancyArray.size();indexFancy++) {
                            //Log.i("CheckRefreshindexf",FancyArray.size()+" "+indexFancy+" ");

                            if (FancyArray.get(indexFancy).equalsIgnoreCase(runnerName)) {
                                //Log.i("CheckRefreshindexf",FancyArray.get(indexFancy)+" "+indexFancy+" "+FancyArray.size());
                                if(indexFancy<FancyArray.size()){
                                    FancyDataList.remove(indexFancy);
                                    FancyDataList.add(indexFancy,new MarketData(yesRate,yesScore,noRate,noScore,runnerName,ballStatus,book,fancyId,matchId));
                                    fancyAdapter.notifyDataSetChanged();
                                }
                                else {
                                    indexFancy = 0;
                                    break;
                                }
                            }
                        }

                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
                unAuthorized(BetActivity.this,result);
            }
        }
    }

    public void unAuthorized(Context context,String result){
        try {
            JSONObject jsonObjMain = new JSONObject(result.toString());
            JSONObject jsonDes = new JSONObject(jsonObjMain.getString("description"));
            String UnAuthorized = jsonDes.getString("result");

            if(UnAuthorized.equalsIgnoreCase("UnAuthorized access found")){
                Toast.makeText(context, "Token Expire", Toast.LENGTH_SHORT).show();
                if (broadcastReceiverSignalr != null) {
                    try {
                        unregisterReceiver(broadcastReceiverSignalr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                MarketDataArray.clear();
                BookMakingArray.clear();
                FancyArray.clear();

                if (_connection != null){
                    _connection.stop();
                }
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    public class BroadcastReceiverSignalr extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //Log.i("TAGkl",""+action);
            if (action.equalsIgnoreCase(DataHolder.ACTION_SEND_ACTIVE)) {
                String result = intent.getStringExtra(DataHolder.keySIGNALR);
                //Log.i("TAGG",result);
                //dispSignalRData(result);
                new setSignalRDataAsyncTask().execute(result);
            }/*else if (action.equalsIgnoreCase(DataHolder.ACTION_SEND_FANCY_BOOKMAKING)){
                String data = intent.getStringExtra(DataHolder.keyFANCY_BOOKMAKING);
                Log.i("TAGG",data);
            }*/
        }
    }
}


/*public void displaySignalRData(final String bfid){

        Platform.loadPlatformComponent( new AndroidPlatformComponent() );
        _connection=new HubConnection("http://178.238.236.221:10800");
        _hub=_connection.createHubProxy("BetAngelHub");

        try {
            awaitConnection = _connection.start(new ServerSentEventsTransport(_connection.getLogger()));
            awaitConnection.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        _hub.invoke("SubscribeMarket",bfid);

        // Subscribe to the received event
        _connection.received(new MessageReceivedHandler() {
            @Override
            public void onMessageReceived(final JsonElement json) {
                handler.postDelayed(runnable = new Runnable() {
                    @Override
                    public void run() {
                        // if(DataHolder.SIGNALR){
                        //Log.i("TAAG","fvbkdfb");
                        try {

                            JSONObject jsonMain = new JSONObject(json.toString());
                            String jsonData = jsonMain.getString("A");
                            JSONArray jsonArray = new JSONArray(jsonData);
                            final JSONObject key = jsonArray.getJSONObject(0);
                            //Log.i("TAG",json.toString());
                            back1 = key.getString("back1");
                            lay1 = key.getString("lay1");
                            backSize1 = key.getString("backSize1");
                            laySize1 = key.getString("laySize1");
                            runner = key.getString("runner");

                            for(index=0;index<MarketDataArray.size();index++) {
                                if (MarketDataArray.get(index).equalsIgnoreCase(runner)) {
                                    if(index<MarketDataArray.size()){
                                        //Log.i("TAG1234", MarketDataArray.get(index) + " " + runner +" "+runner+ " " +back1+ " " +lay1+ " " +backSize1+ " " +laySize1+ " " +bfId);
                                        MarketDataList.remove(index);
//                                        recycleViewMarketData.removeViewAt(index);
//                                        marketDataAdapter.notifyItemRemoved(index);
//                                        marketDataAdapter.notifyItemRemoved(index);
                                        MarketDataList.add(index,new MarketData(runner,back1,lay1,backSize1,laySize1));
                                        marketDataAdapter.notifyDataSetChanged();
//                                        recycleViewMarketData.setAdapter(marketDataAdapter);
                                    }
                                    else {
                                        index = 0;
                                        break;
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //}

                    }
                },2000);
            }
        });

        // Subscribe to the closed event
        _connection.closed(new Runnable() {

            @Override
            public void run() {
                System.out.println("DISCONNECTED");
            }
        });



    }*/