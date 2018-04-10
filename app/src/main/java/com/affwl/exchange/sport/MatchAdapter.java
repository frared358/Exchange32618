package com.affwl.exchange.sport;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by user on 3/30/2018.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder>{

    Context contextMatch;
    private List<MatchData> dataList;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtVRunner,txtVOdds,txtVStack,txtVProfitLiability,txtVDateTime;
        LinearLayout llMatchList;
        ImageView imgVCancel;

        public MyViewHolder(View view) {
            super(view);
            txtVRunner = view.findViewById(R.id.txtVRunner);
            txtVOdds = view.findViewById(R.id.txtVOdds);
            txtVStack = view.findViewById(R.id.txtVStack);
            txtVProfitLiability = view.findViewById(R.id.txtVProfitLiability);
            txtVDateTime = view.findViewById(R.id.txtVDateTime);
            llMatchList = view.findViewById(R.id.llMatchList);
            imgVCancel = view.findViewById(R.id.imgVCancel);



        }
    }

    public MatchAdapter(Context contextMatch, List<MatchData> dataList) {
        this.contextMatch = contextMatch;
        this.dataList = dataList;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MatchData matchData = dataList.get(position);
        Double val;
        Log.i("CHECK "+position,matchData.runner+""+matchData.odd+""+matchData.stack+""+matchData.type+""+matchData.dateTime);

        holder.txtVStack.setText(matchData.stack);
        if (matchData.marketName.equalsIgnoreCase("BookMaking") || matchData.marketName.equalsIgnoreCase("Match Odds")) {
            Double stackValue = Double.parseDouble(matchData.stack.trim());
            Double oddValue = Double.parseDouble(matchData.odd.trim());
            val = (oddValue-1.00)*stackValue;
            holder.txtVOdds.setText(matchData.odd);
            holder.txtVProfitLiability.setText(String.format("%.2f", val));
            holder.txtVRunner.setText(matchData.runner);
        }else {
            String[] split = matchData.odd.split("@");
            holder.txtVOdds.setText(split[0]);
            holder.txtVProfitLiability.setText(split[1]);
            holder.txtVRunner.setText(matchData.runner.replaceFirst(" ","\n"));
        }


        holder.txtVDateTime.setText(matchData.dateTime.replaceFirst(" ","\n"));
        if(matchData.type.equalsIgnoreCase("lay")){
            holder.llMatchList.setBackgroundColor(ContextCompat.getColor(contextMatch, R.color.colorRedBet));
        }
        else {
            holder.llMatchList.setBackgroundColor(ContextCompat.getColor(contextMatch, R.color.colorBlueBet));
        }

        if(matchData.bol){
            holder.imgVCancel.setVisibility(View.VISIBLE);
        }else {
            holder.txtVOdds.setGravity(Gravity.CENTER);
        }

        holder.imgVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CancelAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/CancelBet?id="+matchData.betId);
                removeItem(position);
            }
        });
    }

    private void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,dataList.size());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_match_unmatch,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    public String  CancelApi(String url){

        InputStream inputStream = null;

        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Token", DataHolder.LOGIN_TOKEN);
            Log.e("Check","rtuyty");

            HttpResponse httpResponse = httpclient.execute(httpPost);
            Log.e("Check","tjhttjj");

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                try {
                    result = convertInputStreamToString(inputStream);
                    Log.e("Check","hey");

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

        Log.e("result",result+"");
        //Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
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

    private class CancelAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return CancelApi(urls[0]);


        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("Check","francis"+result);

            try {
                JSONObject  jsonObjMain = new JSONObject(result.toString());
                String msg = jsonObjMain.getString("result");
                String status = jsonObjMain.getString("status");
                if (status.equalsIgnoreCase("Success")){
                    Snackbar snackbar = Snackbar.make(UnmatchFragment.llUnMatchPage, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    Snackbar snackbar = Snackbar.make(UnmatchFragment.llUnMatchPage, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
