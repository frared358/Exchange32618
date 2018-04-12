package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MatchOddAdapter extends RecyclerView.Adapter<MatchOddAdapter.MyViewHolder>{

    Context contextO;
    private List<SportsData> dataListO;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVMatchOddName;
        LinearLayout llMatchOddData;
        ImageView imgVFavourit;
        int Multi;
        public MyViewHolder(View itemView) {
            super(itemView);

            txtVMatchOddName = itemView.findViewById(R.id.txtVTournamentName);
            llMatchOddData = itemView.findViewById(R.id.llTournamentData);
            imgVFavourit = itemView.findViewById(R.id.imgVFavourit);
            imgVFavourit.setVisibility(View.VISIBLE);

        }
    }

    public MatchOddAdapter(Context contextO,List<SportsData> dataListO){
        this.contextO = contextO;
        this.dataListO = dataListO;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_tournament_name,parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SportsData odd = dataListO.get(position);
        holder.txtVMatchOddName.setText(odd.matchOddName);
        holder.Multi= odd.isMulti;

        if(holder.Multi == 1){
            holder.imgVFavourit.setImageDrawable(contextO.getResources().getDrawable(R.drawable.star_small_gold));
        }else {
            holder.imgVFavourit.setImageDrawable(contextO.getResources().getDrawable(R.drawable.star_small_white));
        }

        holder.txtVMatchOddName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contextO,BetActivity.class);
                intent.putExtra("matchName", DataHolder.MATCH_NAME);
                intent.putExtra("marketId",odd.marketOddId);
                intent.putExtra("matchDate",odd.matchDate);
                intent.putExtra("matchId",odd.matchOddId);
                intent.putExtra("bfId",odd.MatchOddBfId);
                Log.i("TAG4567",DataHolder.MATCH_NAME+" "+odd.matchOddId+" "+odd.marketOddId);
                contextO.startActivity(intent);
            }
        });


        holder.imgVFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.Multi == 1){
                    new removeFavouriteAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/RemoveMultiMkt?id="+odd.marketOddId);
                    holder.imgVFavourit.setImageDrawable(contextO.getResources().getDrawable(R.drawable.star_small_white));
                    holder.Multi = 0;
                }else {
                    new setFavouriteAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/AddMultiMkt?id="+odd.marketOddId);
                    holder.imgVFavourit.setImageDrawable(contextO.getResources().getDrawable(R.drawable.star_small_gold));
                    holder.Multi = 1;
                }

            }
        });

        //new CancelAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/RemoveMultiMkt?id="+data.marketId);
    }

    @Override
    public int getItemCount() {
        return dataListO.size();
    }

    private class setFavouriteAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return setFavouriteApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            Toast.makeText(contextO, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject  jsonObjMain = new JSONObject(result.toString());
                String msg = jsonObjMain.getString("result");
                String status = jsonObjMain.getString("status");
                if (status.equalsIgnoreCase("Success")){
                    Toast.makeText(contextO, ""+msg.toUpperCase(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(contextO, ""+msg.toUpperCase(), Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class removeFavouriteAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.setApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("Check","francis"+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String msg = jsonObjMain.getString("result");
                String status = jsonObjMain.getString("status");
                if (status.equalsIgnoreCase("Success")){

                    Toast.makeText(contextO, ""+msg, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(contextO, ""+msg, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    public String  setFavouriteApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost Httppost = new HttpPost(url);

            /*String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("stake1",Integer.parseInt(editTxtStackValue1.getText().toString()));
            jsonObject.accumulate("stake2",Integer.parseInt(editTxtStackValue2.getText().toString()));
            jsonObject.accumulate("stake3",Integer.parseInt(editTxtStackValue3.getText().toString()));

            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json");

            Httppost.setEntity(new StringEntity(json));*/

            Httppost.setHeader("Accept", "application/json");
            Httppost.setHeader("Content-type", "application/json");
            Httppost.setHeader("Token", DataHolder.LOGIN_TOKEN);

            HttpResponse httpResponse = httpclient.execute(Httppost);
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                try {
                    result = DataHolder.convertInputStreamToString(inputStream);
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


}
