package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by user on 4/10/2018.
 */

public class InplayAdapter extends RecyclerView.Adapter<InplayAdapter.MyViewHolder> {

    Context contextInplay;
    private List<Data> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVMatchName,txtVAnchor;
        LinearLayout llMatchData;
        ImageView imgVFavourit;
        int Multi;
        public MyViewHolder(View itemView) {
            super(itemView);

            txtVMatchName = itemView.findViewById(R.id.txtVTournamentName);
            llMatchData = itemView.findViewById(R.id.llTournamentData);
            imgVFavourit = itemView.findViewById(R.id.imgVFavourit);
            txtVAnchor = itemView.findViewById(R.id.txtVAnchor);
            imgVFavourit.setVisibility(View.VISIBLE);
            txtVAnchor.setVisibility(View.GONE);
            txtVMatchName.setTextSize(15);
            txtVMatchName.setTextColor(ContextCompat.getColor(contextInplay,R.color.colorGreen));
        }
    }

    public InplayAdapter(Context contextInplay,List<Data> dataList){
        this.contextInplay = contextInplay;
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Data data = dataList.get(position);
        holder.Multi= data.isMulti;
        if(holder.Multi == 1){
            holder.imgVFavourit.setImageDrawable(contextInplay.getResources().getDrawable(R.drawable.star_small_gold));
        }else {
            holder.imgVFavourit.setImageDrawable(contextInplay.getResources().getDrawable(R.drawable.star_small_white));
        }
        Log.i("INPLAYName",""+data.inMatchName);
        holder.txtVMatchName.setText(data.inMatchName);

        holder.llMatchData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataHolder.MATCH_NAME = data.inMatchName;
                Intent intent = new Intent(contextInplay,BetActivity.class);
                intent.putExtra("matchName", DataHolder.MATCH_NAME);
                intent.putExtra("marketId",data.inMarketId);
                intent.putExtra("matchDate",data.inMatchDate);
                intent.putExtra("matchId",data.inMatchId);
                intent.putExtra("bfId",data.inBfId);

                contextInplay.startActivity(intent);
            }
        });

        holder.imgVFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.Multi == 1){
                    new removeFavouriteAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/RemoveMultiMkt?id="+data.inMarketId);
                    holder.imgVFavourit.setImageDrawable(contextInplay.getResources().getDrawable(R.drawable.star_small_white));
                    holder.Multi = 0;
                }else {
                    new setFavouriteAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/AddMultiMkt?id="+data.inMarketId);
                    holder.imgVFavourit.setImageDrawable(contextInplay.getResources().getDrawable(R.drawable.star_small_gold));
                    holder.Multi = 1;
                }
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_tournament_name,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        Log.i("INPLAYSIZE",""+dataList.size());
        return dataList.size();
    }

    private class setFavouriteAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.setApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            Toast.makeText(contextInplay, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String msg = jsonObjMain.getString("result");
                String status = jsonObjMain.getString("status");
                if (status.equalsIgnoreCase("Success")){
                    Toast.makeText(contextInplay, ""+msg.toUpperCase(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(contextInplay, ""+msg.toUpperCase(), Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(contextInplay, ""+msg, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(contextInplay, ""+msg, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
