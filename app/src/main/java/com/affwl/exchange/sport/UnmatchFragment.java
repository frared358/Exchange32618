
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
import android.widget.LinearLayout;
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


public class UnmatchFragment extends Fragment {

    RecyclerView recycleViewUnMatchData;
    private List<MatchData> UnMatchList = new ArrayList<>();
    MatchAdapter unMatchAdapter;
    public static LinearLayout llUnMatchPage;
    TextView txtUnmatchNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unmatch, container, false);


        txtUnmatchNoData = view.findViewById(R.id.txtUnmatchNoData);
        recycleViewUnMatchData = view.findViewById(R.id.recycleViewUnMatchData);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        llUnMatchPage = view.findViewById(R.id.llUnMatchPage);
        recycleViewUnMatchData.setLayoutManager(mLayoutManager);
        recycleViewUnMatchData.setItemAnimator(new DefaultItemAnimator());
        unMatchAdapter = new MatchAdapter(UnmatchFragment.this.getActivity(),UnMatchList);

        String s = getArguments().getString("unMatchedbets");
        new UnMatchDataAsyncTask().execute(s);

        //new UnMatchAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetCurrentBets");

        return view;
    }


    private class UnMatchAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            //Toast.makeText(UnmatchFragment.this.getContext(), ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("unMatchedbets");

                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();

                if(length == 0){
                    txtUnmatchNoData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String selection = key.getString("selection");
                    String matchedStake = key.getString("matchedStake");
                    String odds = key.getString("odds");
                    String type = key.getString("type");
                    String placedDate = key.getString("placedDate");
                    String marketName = key.getString("marketName");
                    String  betId = key.getString("betId");
                    boolean CHECKCANCEL = true;


                    UnMatchList.add(new MatchData(selection,odds,matchedStake,type,placedDate,marketName,betId,CHECKCANCEL));

                    unMatchAdapter.notifyDataSetChanged();

                }
                recycleViewUnMatchData.setAdapter(unMatchAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }

    private class UnMatchDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return urls[0];
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            Toast.makeText(UnmatchFragment.this.getContext(), ""+result, Toast.LENGTH_SHORT).show();
            try {

                JSONArray arrayData = new JSONArray(result);
                int length = arrayData.length();

                if(length == 0){
                    txtUnmatchNoData.setVisibility(View.VISIBLE);
                }

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String selection = key.getString("selection");
                    String matchedStake = key.getString("matchedStake");
                    String odds = key.getString("odds");
                    String type = key.getString("type");
                    String placedDate = key.getString("placedDate");
                    String marketName = key.getString("marketName");
                    String  betId = key.getString("betId");
                    boolean CHECKCANCEL = true;


                    UnMatchList.add(new MatchData(selection,odds,matchedStake,type,placedDate,marketName,betId,CHECKCANCEL));

                    unMatchAdapter.notifyDataSetChanged();

                }
                recycleViewUnMatchData.setAdapter(unMatchAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }
}

