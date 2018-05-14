
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


public class MatchFragment extends Fragment {

    RecyclerView recycleViewMatchData;
    private List<MatchData> MatchList = new ArrayList<>();
    MatchAdapter matchAdapter;
    TextView txtMatchNoData;
    String matchedbets;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        txtMatchNoData = view.findViewById(R.id.txtMatchNoData);
        recycleViewMatchData = view.findViewById(R.id.recycleViewMatchData);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recycleViewMatchData.setLayoutManager(mLayoutManager);
        recycleViewMatchData.setItemAnimator(new DefaultItemAnimator());
        matchAdapter = new MatchAdapter(MatchFragment.this.getActivity(),MatchList);

        String s = getArguments().getString("matchedbets");
        new MatchDataAsyncTask().execute(s);

        //new MatchAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetCurrentBets");

        return view;
    }


    private class MatchAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check123",""+result);
            //Toast.makeText(MatchFragment.this.getContext(), ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("matchedbets");

                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();

                if(length == 0){
                    txtMatchNoData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String selection = key.getString("selection");
                    String matchedStake = key.getString("matchedStake");
                    String odds = key.getString("odds");
                    String type = key.getString("type");
                    String placedDate = key.getString("placedDate");
                    String marketName = key.getString("marketName");
                    boolean CHECKCANCEL = false;

                    MatchList.add(new MatchData(selection,odds,matchedStake,type,placedDate,marketName,CHECKCANCEL));

                    matchAdapter.notifyDataSetChanged();

                }
                recycleViewMatchData.setAdapter(matchAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }

    private class MatchDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return urls[0];
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check123",""+result);
            //Toast.makeText(MatchFragment.this.getContext(), ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONArray arrayData = new JSONArray(result);
                int length = arrayData.length();

                if(length == 0){
                    txtMatchNoData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String selection = key.getString("selection");
                    String matchedStake = key.getString("matchedStake");
                    String odds = key.getString("odds");
                    String type = key.getString("type");
                    String placedDate = key.getString("placedDate");
                    String marketName = key.getString("marketName");
                    boolean CHECKCANCEL = false;

                    MatchList.add(new MatchData(selection,odds,matchedStake,type,placedDate,marketName,CHECKCANCEL));

                    matchAdapter.notifyDataSetChanged();

                }
                recycleViewMatchData.setAdapter(matchAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }


}
