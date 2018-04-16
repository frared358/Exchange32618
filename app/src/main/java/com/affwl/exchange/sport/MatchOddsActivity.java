package com.affwl.exchange.sport;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

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

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class MatchOddsActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    TextView txtVAllSportO,txtVSportNameO,txtVTournamentNameO,txtVMatchName;

    RecyclerView recycleViewMatchOdds;
    private List<SportsData> MatchOddList= new ArrayList<>();
    MatchOddAdapter matchOddAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_odds);

        txtVAllSportO = findViewById(R.id.txtVAllSportO);
        txtVSportNameO = findViewById(R.id.txtVSportNameO);
        txtVTournamentNameO = findViewById(R.id.txtVTournamentNameO);
        txtVMatchName = findViewById(R.id.txtVMatchName);

        txtVAllSportO.setOnClickListener(this);
        txtVSportNameO.setOnClickListener(this);
        txtVTournamentNameO.setOnClickListener(this);

//        txtVAllSportO.setOnTouchListener(this);
//        txtVSportNameO.setOnTouchListener(this);
//        txtVTournamentNameO.setOnTouchListener(this);

        txtVSportNameO.setText(DataHolder.SPORT_NAME+"> ");
        txtVTournamentNameO.setText(DataHolder.TOURNAMENT_NAME+"> ");
        txtVMatchName.setText(DataHolder.MATCH_NAME);
        Intent intent = getIntent();
        int matchId = intent.getIntExtra("matchId",0);

        recycleViewMatchOdds = findViewById(R.id.recycleViewMatchOdds);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycleViewMatchOdds.setLayoutManager(mLayoutManager);
        recycleViewMatchOdds.setItemAnimator(new DefaultItemAnimator());

        matchOddAdapter = new MatchOddAdapter(this,MatchOddList);
        new MatchOddsAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Navigation/MarketList?mtid="+matchId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVAllSportO:
                Intent intentAllSport = new Intent(this,SportActivity.class);
                intentAllSport.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentAllSport);
                break;
            case R.id.txtVSportNameO:
                Intent intentSportName = new Intent(this,TournamentActivity.class);
                intentSportName.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentSportName);
                break;
            case R.id.txtVTournamentNameO:
                finish();

                break;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.txtVAllSportO:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtVAllSportO.setTextColor(ContextCompat.getColor(this,R.color.colorYellow));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    txtVAllSportO.setTextColor(ContextCompat.getColor(this,R.color.colorBlack));
                }
                break;

            case R.id.txtVSportNameO:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtVSportNameO.setTextColor(ContextCompat.getColor(this,R.color.colorYellow));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    txtVSportNameO.setTextColor(ContextCompat.getColor(this,R.color.colorBlack));
                }
                break;

            case R.id.txtVTournamentNameO:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtVTournamentNameO.setTextColor(ContextCompat.getColor(this,R.color.colorYellow));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    txtVTournamentNameO.setTextColor(ContextCompat.getColor(this,R.color.colorBlack));
                }
                break;
        }
        return false;
    }

    private class MatchOddsAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                Log.i("TAG",""+strData);

                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String MatchOddBfId = key.getString("bfId");
                    String MatchOddName = key.getString("name");
                    int MarketOddId = key.getInt("id");
                    int MatchOddId = key.getInt("mtId");
                    int isMulti = key.getInt("isMulti");

                    MatchOddList.add(new SportsData(MatchOddId,MarketOddId,MatchOddBfId,MatchOddName,isMulti));

                    matchOddAdapter.notifyDataSetChanged();
                }
                recycleViewMatchOdds.setAdapter(matchOddAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(MatchOddsActivity.this,result);
            }
        }
    }
}
