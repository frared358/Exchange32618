package com.affwl.exchange.sport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class FragmentAllSport extends Fragment{

    RecyclerView recycleViewHighlights;
    private List<SportsData> HighlightsList= new ArrayList<>();
    SportsNameAdapter sportsNameAdapter;
    TextView txtVNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_sport, container, false);

        txtVNoData = view.findViewById(R.id.txtVNoData);

        recycleViewHighlights = view.findViewById(R.id.recycleViewHighlights);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recycleViewHighlights.setLayoutManager(mLayoutManager);
        recycleViewHighlights.setItemAnimator(new DefaultItemAnimator());

        sportsNameAdapter = new SportsNameAdapter(FragmentAllSport.this.getActivity(),HighlightsList);

        new SportListAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Navigation/SportsList");
        //new HighlightsAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Highlights?sid=4");

        return view;
    }

    private class SportListAsyncTask extends AsyncTask<String, Void, String> {

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
                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();
                if(length == 0){
                    txtVNoData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);

                    String name = key.getString("name");
                    int matchId = key.getInt("id");

                    HighlightsList.add(new SportsData(name,matchId));
                    sportsNameAdapter.notifyDataSetChanged();

                }
                recycleViewHighlights.setAdapter(sportsNameAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }
}
