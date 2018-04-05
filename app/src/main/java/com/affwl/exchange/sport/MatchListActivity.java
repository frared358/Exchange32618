package com.affwl.exchange.sport;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MatchListActivity extends AppCompatActivity {

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

        Intent intent = getIntent();

        int tournamentId = intent.getIntExtra("tournamentId",0);
        int sportId = intent.getIntExtra("sportId",0);
        Toast.makeText(this, tournamentId+" "+sportId, Toast.LENGTH_SHORT).show();

        txtVSportNameM.setText(DataHolder.SPORT_NAME+" > ");
        txtVTournamentNameM.setText(intent.getStringExtra("tournamentName")+" > ");
        DataHolder.TOURNAMENT_NAME = intent.getStringExtra("tournamentName");

        recycleViewMatchList = findViewById(R.id.recycleViewMatchList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycleViewMatchList.setLayoutManager(mLayoutManager);
        recycleViewMatchList.setItemAnimator(new DefaultItemAnimator());

        matchListAdapter = new MatchListAdapter(this,MatchList);

        new MatchListAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Navigation/MatchList?id="+sportId+"&tourid="+tournamentId);
    }

    public String  MatchListApi(String url){
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

    private class MatchListAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return MatchListApi(urls[0]);
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
            }
        }
    }
}
