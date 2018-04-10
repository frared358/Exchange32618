package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
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
 * Created by user on 4/9/2018.
 */

public class FavouriteDataAdapter extends RecyclerView.Adapter<FavouriteDataAdapter.MyViewHolder> {

    Context contextFav;
    private List<Data> dataListFav;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVFavMatchName,txtVFavMatchOddName;
        ImageView imgVFavCheck;
        LinearLayout llfavouriteDataList;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtVFavMatchName = itemView.findViewById(R.id.txtVFavMatchName);
            txtVFavMatchOddName = itemView.findViewById(R.id.txtVFavMatchOddName);
            imgVFavCheck = itemView.findViewById(R.id.imgVFavCheck);
            llfavouriteDataList = itemView.findViewById(R.id.llfavouriteDataList);
        }
    }

    public FavouriteDataAdapter(Context contextFav,List<Data> dataListFav){
        this.contextFav = contextFav;
        this.dataListFav = dataListFav;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_favourite_list,parent, false);
        return new MyViewHolder(v);
    }



    int POS;
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        final Data data = dataListFav.get(position);

        holder.txtVFavMatchName.setText(data.matchName);
        holder.txtVFavMatchOddName.setText(data.sportName);

        holder.imgVFavCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POS = position;
                holder.imgVFavCheck.setImageDrawable(ContextCompat.getDrawable(contextFav,R.drawable.star_small_white));
                new CancelAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/RemoveMultiMkt?id="+data.marketId);

            }
        });


        holder.llfavouriteDataList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataHolder.MATCH_NAME = data.matchName;
                Intent intent = new Intent(contextFav,BetActivity.class);
                intent.putExtra("matchName", DataHolder.MATCH_NAME);
                intent.putExtra("marketId",Integer.parseInt(data.marketId));
                intent.putExtra("matchDate",data.matchDate);
                intent.putExtra("matchId",Integer.parseInt(data.matchId));
                intent.putExtra("bfId",data.bfId);

                contextFav.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataListFav.size();
    }



    private class CancelAsyncTask extends AsyncTask<String, Void, String> {

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

                    dataListFav.remove(POS);
                    notifyItemRemoved(POS);
                    notifyItemRangeChanged(POS,dataListFav.size());

                    Toast.makeText(contextFav, ""+msg, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(contextFav, ""+msg, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}
