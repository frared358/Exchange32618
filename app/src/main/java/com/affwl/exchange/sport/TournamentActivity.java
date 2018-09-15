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

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TournamentActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtVAllSportT,txtVSportNameT,txtVTournamentNoData;
    RecyclerView recycleViewTournament;
    private List<SportsData> TournamentList= new ArrayList<>();
    TournamentAdapter tournamentAdapter;
    int sportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        txtVAllSportT = findViewById(R.id.txtVAllSportT);
        txtVSportNameT = findViewById(R.id.txtVSportNameT);
        txtVTournamentNoData = findViewById(R.id.txtVTournamentNoData);

        txtVAllSportT.setOnClickListener(this);

        Intent intent = getIntent();
        txtVSportNameT.setText(intent.getStringExtra("SportName")+" 》");

        recycleViewTournament = findViewById(R.id.recycleViewTournament);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycleViewTournament.setLayoutManager(mLayoutManager);
        recycleViewTournament.setItemAnimator(new DefaultItemAnimator());

        tournamentAdapter = new TournamentAdapter(this,TournamentList);
        sportId = intent.getIntExtra("SportId",0);
        Log.i("TAG",""+sportId);

        DataHolder.SPORT_NAME = intent.getStringExtra("SportName");
        new TournamentAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Navigation/TournamentList?id="+sportId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVAllSportT:
                finish();
                break;
        }
    }
//
    private class TournamentAsyncTask extends AsyncTask<String, Void, String> {

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
                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();

                if(length == 0){
                    txtVTournamentNoData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){

                    JSONObject key = arrayData.getJSONObject(i);

                    String name = key.getString("name");
                    int Id = key.getInt("id");

                    TournamentList.add(new SportsData(name,Id,sportId));
                    tournamentAdapter.notifyDataSetChanged();

                }
                recycleViewTournament.setAdapter(tournamentAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(TournamentActivity.this,result);
            }
        }
    }
}
