package com.affwl.exchange.sport;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class HighlightsActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llHighlightsCricket,llHighlightsTennis,llHighlightsFootball;
    RecyclerView recycleViewHighlightsCricket,recycleViewHighlightsTennis,recycleViewHighlightsFootball;
    TextView txtHighlightsNoData;

    LinearLayout llHighlightHome,llHighlightAccount;
    ImageView imgVHighlightHome,imgVHighlightAccount;
    TextView txtVHighlightHome,txtVHighlightAccount;

    private List<Data> dataListCricket = new ArrayList<>();
    private List<Data> dataListTennis = new ArrayList<>();
    private List<Data> dataListFootball = new ArrayList<>();

    InplayAdapter inplayAdapter1;
    InplayAdapter inplayAdapter2;
    InplayAdapter inplayAdapter3;

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlights);


        llHighlightHome = findViewById(R.id.llHighlightHome);
        imgVHighlightHome = findViewById(R.id.imgVHighlightHome);
        txtVHighlightHome = findViewById(R.id.txtVHighlightHome);

        llHighlightAccount = findViewById(R.id.llHighlightAccount);
        imgVHighlightAccount = findViewById(R.id.imgVHighlightAccount);
        txtVHighlightAccount = findViewById(R.id.txtVHighlightAccount);

        llHighlightHome.setOnClickListener(this);
        imgVHighlightHome.setOnClickListener(this);
        txtVHighlightHome.setOnClickListener(this);
        llHighlightAccount.setOnClickListener(this);
        imgVHighlightAccount.setOnClickListener(this);
        txtVHighlightAccount.setOnClickListener(this);


        txtHighlightsNoData = findViewById(R.id.txtHighlightsNoData);

        llHighlightsCricket = findViewById(R.id.llHighlightsCricket);
        llHighlightsTennis = findViewById(R.id.llHighlightsTennis);
        llHighlightsFootball = findViewById(R.id.llHighlightsFootball);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(this);

        recycleViewHighlightsCricket = findViewById(R.id.recycleViewHighlightsCricket);
        recycleViewHighlightsCricket.setLayoutManager(mLayoutManager1);
        recycleViewHighlightsCricket.setItemAnimator(new DefaultItemAnimator());
        inplayAdapter1 = new InplayAdapter(this,dataListCricket);

        recycleViewHighlightsTennis = findViewById(R.id.recycleViewHighlightsTennis);
        recycleViewHighlightsTennis.setLayoutManager(mLayoutManager2);
        recycleViewHighlightsTennis.setItemAnimator(new DefaultItemAnimator());
        inplayAdapter2 = new InplayAdapter(this,dataListTennis);

        recycleViewHighlightsFootball = findViewById(R.id.recycleViewHighlightsFootball);
        recycleViewHighlightsFootball.setLayoutManager(mLayoutManager3);
        recycleViewHighlightsFootball.setItemAnimator(new DefaultItemAnimator());
        inplayAdapter3 = new InplayAdapter(this,dataListFootball);

        new getHighlightsCricketAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Highlights?sid=4");
        new getHighlightsFootballAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Highlights?sid=1");
        new getHighlightsTennisAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Highlights?sid=2");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llHighlightHome: case R.id.txtVHighlightHome: case R.id.imgVHighlightHome:
                Intent intentHome = new Intent(this,SportActivity.class);
                intentHome.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentHome);
                break;

            case R.id.llHighlightAccount: case R.id.txtVHighlightAccount: case R.id.imgVHighlightAccount:
                startActivity(new Intent(this,SportAccountActivity.class));
                break;
        }
    }

    private class getHighlightsCricketAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check1",""+result);
            //Toast.makeText(HighlightsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();

                if(len>0){
                    llHighlightsCricket.setVisibility(View.VISIBLE);
                    count++;
                }

                for(int i=0; i<len;i++){

                        JSONObject in = jsonArray.getJSONObject(i);
                        String inBfId = in.getString("bfId");
                        String inMatchName = in.getString("matchName");
                        int inMarketId = in.getInt("marketId");
                        int inMatchId = in.getInt("matchId");
                        int isMulti = in.getInt("isMulti");
                        String matchDate = in.getString("matchDate");

                        dataListCricket.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        inplayAdapter1.notifyDataSetChanged();

                }

                recycleViewHighlightsCricket.setAdapter(inplayAdapter1);
            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }
    private class getHighlightsFootballAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check1",""+result);
            //Toast.makeText(HighlightsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();

                if(len>0){
                    llHighlightsFootball.setVisibility(View.VISIBLE);
                    count++;
                }

                for(int i=0; i<len;i++){

                    JSONObject in = jsonArray.getJSONObject(i);
                    String inBfId = in.getString("bfId");
                    String inMatchName = in.getString("matchName");
                    int inMarketId = in.getInt("marketId");
                    int inMatchId = in.getInt("matchId");
                    int isMulti = in.getInt("isMulti");
                    String matchDate = in.getString("matchDate");

                    dataListFootball.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                    inplayAdapter3.notifyDataSetChanged();

                }

                recycleViewHighlightsFootball.setAdapter(inplayAdapter3);
            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }
    private class getHighlightsTennisAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            //Toast.makeText(HighlightsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();

                if(len>0){
                    llHighlightsTennis.setVisibility(View.VISIBLE);
                    count++;
                }

                for(int i=0; i<len;i++){

                    JSONObject in = jsonArray.getJSONObject(i);
                    String inBfId = in.getString("bfId");
                    String inMatchName = in.getString("matchName");
                    int inMarketId = in.getInt("marketId");
                    int inMatchId = in.getInt("matchId");
                    int isMulti = in.getInt("isMulti");
                    String matchDate = in.getString("matchDate");

                    dataListTennis.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                    inplayAdapter2.notifyDataSetChanged();

                }

                recycleViewHighlightsTennis.setAdapter(inplayAdapter2);
            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }

            if (count==0){
                txtHighlightsNoData.setVisibility(View.VISIBLE);
            }
        }
    }

}









/*private class getHighlightsAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check1",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();
                Log.i("Check1","len "+len);
                if(len==0){
                    txtHighlightsNoData.setVisibility(View.VISIBLE);
                }

                for(int i=0; i<len;i++){
                    JSONObject key = jsonArray.getJSONObject(i);
                    String name = key.getString("name");
                    String inplay = key.getString("inplayData");

                    JSONArray inplayArray = new JSONArray(key.getString("inplayData"));
                    int lenHighlights = inplayArray.length();
                    Log.i("Highlights",lenHighlights+" "+inplay.toString());
                    for (int j=0;j<lenHighlights;j++){
                        JSONObject in = inplayArray.getJSONObject(j);
                        String inBfId = in.getString("bfId");
                        String inMatchName = in.getString("matchName");
                        int inMarketId = in.getInt("marketId");
                        int inMatchId = in.getInt("matchId");
                        int isMulti = in.getInt("isMulti");
                        String matchDate = in.getString("matchDate");


                        if(name.equalsIgnoreCase("Cricket")){
                            inplayAdapter1.notifyDataSetChanged();
                            dataListCricket.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        }else if(name.equalsIgnoreCase("Tennis")){
                            inplayAdapter2.notifyDataSetChanged();
                            dataListTennis.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        }else if(name.equalsIgnoreCase("Football")){
                            inplayAdapter3.notifyDataSetChanged();
                            dataListFootball.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        }
                    }

                    if(name.equalsIgnoreCase("Cricket")){
                        recycleViewHighlightsCricket.setAdapter(inplayAdapter1);
                        llHighlightsCricket.setVisibility(View.VISIBLE);
                    }else if(name.equalsIgnoreCase("Tennis")){
                        recycleViewHighlightsTennis.setAdapter(inplayAdapter2);
                        llHighlightsTennis.setVisibility(View.VISIBLE);
                    }else if(name.equalsIgnoreCase("Football")){
                        recycleViewHighlightsFootball.setAdapter(inplayAdapter3);
                        llHighlightsFootball.setVisibility(View.VISIBLE);
                    }
                }

                DataHolder.cancelProgress();

            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }*/