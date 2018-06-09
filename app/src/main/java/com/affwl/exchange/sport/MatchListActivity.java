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
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class MatchListActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtVAllSportM,txtVSportNameM,txtVTournamentNameM;

    RecyclerView recycleViewMatchList;
    private List<SportsData> MatchList= new ArrayList<>();
    MatchListAdapter matchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        txtVAllSportM = findViewById(R.id.txtVAllSportM);
        txtVSportNameM = findViewById(R.id.txtVSportNameM);
        txtVTournamentNameM = findViewById(R.id.txtVTournamentNameM);

        txtVAllSportM.setOnClickListener(this);
        txtVSportNameM.setOnClickListener(this);

        Intent intent = getIntent();

        int tournamentId = intent.getIntExtra("tournamentId",0);
        int sportId = intent.getIntExtra("sportId",0);
        //Toast.makeText(this, tournamentId+" "+sportId, Toast.LENGTH_SHORT).show();

        txtVSportNameM.setText(DataHolder.SPORT_NAME+" 》");
        txtVTournamentNameM.setText(intent.getStringExtra("tournamentName")+" 》");
        DataHolder.TOURNAMENT_NAME = intent.getStringExtra("tournamentName");

        recycleViewMatchList = findViewById(R.id.recycleViewMatchList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycleViewMatchList.setLayoutManager(mLayoutManager);
        recycleViewMatchList.setItemAnimator(new DefaultItemAnimator());

        matchListAdapter = new MatchListAdapter(this,MatchList);

        new MatchListAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Navigation/MatchList?id="+sportId+"&tourid="+tournamentId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVAllSportM:
                Intent intentAllSport = new Intent(this,SportActivity.class);
                intentAllSport.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentAllSport);
                break;
            case R.id.txtVSportNameM:
                finish();

//                Intent intentSportName = new Intent(this,TournamentActivity.class);
//                intentSportName.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intentSportName);
                break;
        }
    }

    private class MatchListAsyncTask extends AsyncTask<String, Void, String> {

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
                    String MatchName = key.getString("name");
                    String MatchBfId = key.getString("bfId");
                    String MatchDate = key.getString("startDate");
                    int MatchId = key.getInt("id");
                    Log.i("TAG",""+MatchName);
                    MatchList.add(new SportsData(MatchName,MatchBfId,MatchId,MatchDate));

                   matchListAdapter.notifyDataSetChanged();
                }
                recycleViewMatchList.setAdapter(matchListAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(MatchListActivity.this,result);
            }
        }
    }
}
