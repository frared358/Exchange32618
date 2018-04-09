package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import java.util.List;

/**
 * Created by user on 4/4/2018.
 */

public class MatchOddAdapter extends RecyclerView.Adapter<MatchOddAdapter.MyViewHolder>{

    Context contextO;
    private List<SportsData> dataListO;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVMatchOddName;
        LinearLayout llMatchOddData;
        ImageView imgVFavourit;

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
                holder.imgVFavourit.setImageDrawable(contextO.getResources().getDrawable(R.drawable.star_small_gold));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataListO.size();
    }
}
