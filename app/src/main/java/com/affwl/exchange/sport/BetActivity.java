
package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
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

import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

import static com.affwl.exchange.DataHolder._connection;
import static com.affwl.exchange.DataHolder._hub;
//Bet
public class BetActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    String matchName,matchDate,bfId;
    int marketId,matchId;
    ImageView imgVCheck,imgRightDrawer;
    DrawerLayout drawerBet;
    NavigationView navigationView1,navigationView2;
    TextView txtVChipsStake,txtVTitleMatchName;
    ImageView imgVFav;
    Handler handler;
    Runnable runnable;

    LinearLayout llBookMaking,llFancyBet,llMatchOddData;
    public static ScrollView scrollBetActivity;

    RecyclerView recycleViewMarketData,recycleViewBookMakingData,recycleViewFancyBet;
    private List<MarketData> MarketDataList = new ArrayList<>();
    private List<MarketData> BookMakingDataList = new ArrayList<>();
    private List<MarketData> FancyDataList = new ArrayList<>();
    MarketDataAdapter marketDataAdapter;
    BookMakingAdapter  bookMakingAdapter;
    FancyAdapter fancyAdapter;

    Handler handlerMarketData;

    SignalRFuture<Void> awaitConnection;

    public static ArrayList<String> MarketDataArray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

//        pink1 = findViewById(R.id.pink1);
//        pink1.setOnClickListener(this);
//        pink2 = findViewById(R.id.pink2);
//        pink2.setOnClickListener(this);
//        pink3 = findViewById(R.id.pink3);
//        pink3.setOnClickListener(this);
//
//        blue1 = findViewById(R.id.blue1);
//        blue1.setOnClickListener(this);
//        blue2 = findViewById(R.id.blue2);
//        blue2.setOnClickListener(this);
//        blue3 = findViewById(R.id.blue3);
//        blue3.setOnClickListener(this);
        txtVChipsStake = findViewById(R.id.txtVChipsStake);
        txtVChipsStake.setOnClickListener(this);

        imgVFav = findViewById(R.id.imgVFav);
        imgVFav.setOnClickListener(this);

        //DataHolder.setLayout();
        scrollBetActivity = findViewById(R.id.scrollBetActivity);


        drawerBet = (DrawerLayout) findViewById(R.id.drawerBet);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerBet, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerBet.addDrawerListener(toggle);
        toggle.syncState();

        navigationView1 = (NavigationView) findViewById(R.id.nav_view1);
        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);

        Intent i = getIntent();
        marketId = getIntent().getIntExtra("marketId",0);
        matchName = getIntent().getStringExtra("matchName");
        matchDate = getIntent().getStringExtra("matchDate");
        matchId = getIntent().getIntExtra("matchId",0);
        bfId = getIntent().getStringExtra("bfId");

        /*DataHolder.setData(this,"keyMarketId",String.valueOf(marketId));*/
        DataHolder.setData(this,"Match_Id",String.valueOf(matchId));

        Log.i("TAG4561",matchName+" "+marketId+" "+matchId);
        txtVTitleMatchName = findViewById(R.id.txtVTitleMatchName);
        txtVTitleMatchName.setText(matchName);

        llBookMaking = findViewById(R.id.llBookMaking);
        llFancyBet = findViewById(R.id.llFancyBet);
        llMatchOddData = findViewById(R.id.llMatchOddData);

        handler = new Handler();
        DataHolder.SIGNALR = true;
        new getStackAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Settings/GetBetStakeSetting");
        new getBookMakingAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/FancyData?mtid="+matchId);
        new getMartketDataAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/MktData?mtid="+matchId+"&mktid="+marketId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtVChipsStake.setText("CHIPS "+DataHolder.getSTACK(this,"StackValue1"));
        try{
            DataHolder.STACK_VALUE = Double.valueOf(DataHolder.getSTACK(this,"StackValue1"));
        }catch(NumberFormatException e){
            
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgVCheck:
                startActivity(new Intent(this,UnmatchMatchTabActivity.class));
                break;
            case R.id.imgRightDrawer:
                drawerBet.openDrawer(navigationView1);
                break;
//            case R.id.blue1: case R.id.blue2: case R.id.blue3: case R.id.pink1: case R.id.pink2: case R.id.pink3:
//                drawerBet.openDrawer(navigationView2);
//                break;
            case R.id.txtVChipsStake:
                dialogStack();
                break;
            case R.id.imgVFav:
                Intent intent = new Intent(this,SportActivity.class);
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
    @Override
    public void onBackPressed() {

        if (drawerBet.isDrawerOpen(GravityCompat.START)) {
            drawerBet.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        txtVOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
    int count=0;
    public void displaySignalRData(final String bfid){

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
                public void onMessageReceived(final JsonElement json) {

                    //Log.i("Signalr",""+json);

                    //Toast.makeText(BetActivity.this, ""+json, Toast.LENGTH_SHORT).show();
                    //Log.i("TAG11","cbc");
                    if(DataHolder.SIGNALR){
                        handler.postDelayed(runnable = new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    JSONObject jsonMain = new JSONObject(json.toString());
                                    String jsonData = jsonMain.getString("A");
                                    JSONArray jsonArray = new JSONArray(jsonData);
                                    final JSONObject key = jsonArray.getJSONObject(0);
                                    Log.i("TAG",json.toString());
                                    back1 = key.getString("back1");
                                    lay1 = key.getString("lay1");
                                    backSize1 = key.getString("backSize1");
                                    laySize1 = key.getString("laySize1");
                                    runner = key.getString("runner");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                for(index=0;index<MarketDataArray.size();index++) {

                                    if (MarketDataArray.get(index).equalsIgnoreCase(runner)) {

                                        if(index<MarketDataArray.size()){

                                            Log.i("TAG1234", MarketDataArray.get(index) + " " + runner +" "+runner+ " " +back1+ " " +lay1+ " " +backSize1+ " " +laySize1+ " " +bfId);

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
                            }


                        },2000);
                    }


                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //_connection.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MarketDataArray.clear();
        DataHolder.SIGNALR = false;
        //handler.removeCallbacks(runnable);
        //handler.removeCallbacksAndMessages(null);

        try {
            if (runnable != null ) {
                Log.i("TAG14552",runnable.toString());
                //_connection.closed(runnable);
                //_hub = null;
                //_connection.disconnect();
                //_hub.invoke("Unsubscirbe",sub);
                _connection.stop();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String  getApi(String url){
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
            Log.e("Check","how "+result);

        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }
        return result;
    }

    String StackValue1,StackValue2,StackValue3;

    private class getStackAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                Log.i("TAG",""+strData);

                JSONObject jsonObj = new JSONObject(jsonObjMain.getString("data"));

                StackValue1 = jsonObj.getString("stake1");
                StackValue2 = jsonObj.getString("stake2");
                StackValue3 = jsonObj.getString("stake3");
                txtVChipsStake.setText("CHIPS "+StackValue1);
                DataHolder.STACK_VALUE = Double.valueOf(StackValue1);

                DataHolder.setSTACK(BetActivity.this,"StackValue1",StackValue1);
                DataHolder.setSTACK(BetActivity.this,"StackValue2",StackValue2);
                DataHolder.setSTACK(BetActivity.this,"StackValue3",StackValue3);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class getMartketDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {


            return getApi(urls[0]);
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

                if(length >0){
                    llMatchOddData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String back1 = key.getString("back1");
                    String lay1 = key.getString("lay1");
                    String backSize1 = key.getString("backSize1");
                    String laySize1 = key.getString("laySize1");
                    String runnerName = key.getString("runnerName");
                    MarketDataArray.add(key.getString("runnerName"));
                    MarketDataList.add(new MarketData(runnerName,back1,lay1,backSize1,laySize1,bfId,matchId,marketId));
                    marketDataAdapter.notifyDataSetChanged();
                }
                recycleViewMarketData.setAdapter(marketDataAdapter);

                //displaySignalRData(bfId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class getBookMakingAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);

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


                        BookMakingDataList.add(new MarketData(BookId,runnerId,back,lay,backSize,laySize,runnerName,ballStatus,book));
                        bookMakingAdapter.notifyDataSetChanged();
                    }
                    recycleViewBookMakingData.setAdapter(bookMakingAdapter);
                }

                String fancyData = jsonObjMain.getString("data");
                JSONArray fancyArrayData = new JSONArray(fancyData);
                int lengthFancy = fancyArrayData.length();
                Log.i("TAG456",fancyData);

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

                        FancyDataList.add(new MarketData(yesRate,yesScore,noRate,noScore,runnerName,ballStatus,book,fancyId));
                        fancyAdapter.notifyDataSetChanged();
                    }
                    recycleViewFancyBet.setAdapter(fancyAdapter);
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
            Log.e("Line",result);
        }

        inputStream.close();
        return result;
    }

    public String  getMartketDataApi(String url){
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
            Log.e("Check","how "+result);

        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }
        return result;
    }

    public String  getBookMakingApi(String url){
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
            Log.e("Check","how "+result);

        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }
        return result;
    }
}
