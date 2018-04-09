
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unmatch, container, false);
        recycleViewUnMatchData = view.findViewById(R.id.recycleViewUnMatchData);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recycleViewUnMatchData.setLayoutManager(mLayoutManager);
        recycleViewUnMatchData.setItemAnimator(new DefaultItemAnimator());
        unMatchAdapter = new MatchAdapter(UnmatchFragment.this.getActivity(),UnMatchList);
        new UnMatchAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetCurrentBets");

        return view;
    }

    public String  UnMatchApi(String url){
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

    private class UnMatchAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return UnMatchApi(urls[0]);
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
//
//                if(length == 0){
//                    txtVNoData.setVisibility(View.VISIBLE);
//                }
//
                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String selection = key.getString("selection");
                    String matchedStake = key.getString("matchedStake");
                    String odds = key.getString("odds");
                    String type = key.getString("type");
                    String placedDate = key.getString("placedDate");
                    String marketName = key.getString("marketName");

                    UnMatchList.add(new MatchData(selection,odds,matchedStake,type,placedDate,marketName));

                    unMatchAdapter.notifyDataSetChanged();

                }
                recycleViewUnMatchData.setAdapter(unMatchAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

