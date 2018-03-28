
package com.affwl.exchange.sport;


import android.content.Intent;
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
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.entity.StringEntity;
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
    private List<HighlightsData> HighlightsList= new ArrayList<>();
    HighlightsAdapter highlightsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_sport, container, false);

        recycleViewHighlights = view.findViewById(R.id.recycleViewHighlights);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recycleViewHighlights.setLayoutManager(mLayoutManager);
        recycleViewHighlights.setItemAnimator(new DefaultItemAnimator());

        highlightsAdapter = new HighlightsAdapter(FragmentAllSport.this.getActivity(),HighlightsList);

        new HighlightsAsyncTask().execute("http://5.189.140.198/Prince99/Prince.svc/Data/Highlights?sid=4");
        return view;
    }



    public String  HighligthsApi(String url){
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

    private class HighlightsAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return HighligthsApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();
                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String matchName = key.getString("matchName");
                    Log.i("TAG",""+matchName);
                    HighlightsList.add(new HighlightsData(matchName));
                    highlightsAdapter.notifyDataSetChanged();
                }
                recycleViewHighlights.setAdapter(highlightsAdapter);
//                int length = jsonObj.length();
//                String data = jsonObjMain.getString("data");
//                String fancyList = jsonObjMain.getString("fancyList");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
