
package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Intent;
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

//Bet
public class BetActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    String matchName,matchDate,bfId;
    int marketId,matchId;
    ImageView imgVCheck,imgRightDrawer;
    DrawerLayout drawerBet;
    NavigationView navigationView1,navigationView2;
    //LinearLayout pink1,pink2,pink3,blue1,blue2,blue3;
    TextView txtVChipsStake;
    ImageView imgVFav;
    Handler handler;

    RecyclerView recycleViewMarketData;
    private List<MarketData> MarketDataList = new ArrayList<>();
    MarketDataAdapter marketDataAdapter;

    Handler handlerMarketData;
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

        Log.i("TAG456",matchName+" "+marketId+" "+matchId);

        handler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new getMartketDataAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/MktData?mtid="+matchId+"&mktid="+marketId);
        new getStackAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Settings/GetBetStakeSetting");
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

        txtVStackValue1.setText("CHIPS "+StackValue1);
        txtVStackValue2.setText("CHIPS "+StackValue2);
        txtVStackValue3.setText("CHIPS "+StackValue3);

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

    HubConnection _connection;
    HubProxy _hub;
    SignalRFuture<Void> awaitConnection;


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

                    handler.postDelayed(new Runnable() {
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

                            for(index=0;index<DataHolder.MarketDataArray.size();index++) {

                                if (DataHolder.MarketDataArray.get(index).equalsIgnoreCase(runner)) {

                                    if(index<DataHolder.MarketDataArray.size()){

                                        Log.i("TAG1234", DataHolder.MarketDataArray.get(index) + " " + runner +" "+runner+ " " +back1+ " " +lay1+ " " +backSize1+ " " +laySize1+ " " +bfId);

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
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        _connection.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _hub = null;
        _connection.stop();
        DataHolder.MarketDataArray.clear();
    }

    public String  getStackApi(String url){
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

    String StackValue1,StackValue2,StackValue3;

    private class getStackAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getStackApi(urls[0]);
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    private class getMartketDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {


            return getMartketDataApi(urls[0]);
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

                    DataHolder.MarketDataArray.add(key.getString("runnerName"));

                    MarketDataList.add(new MarketData(runnerName,back1,lay1,backSize1,laySize1,bfId,matchId,marketId));

                    marketDataAdapter.notifyDataSetChanged();

                }
                recycleViewMarketData.setAdapter(marketDataAdapter);

                displaySignalRData(bfId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
