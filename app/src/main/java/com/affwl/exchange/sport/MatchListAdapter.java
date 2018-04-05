package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import java.util.List;

/**
 * Created by user on 4/4/2018.
 */

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.MyViewHolder> {

    Context contextM;
    private List<SportsData> dataListM;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVMatchName;
        LinearLayout llMatchData;


        public MyViewHolder(View view) {
            super(view);
            txtVMatchName = view.findViewById(R.id.txtVTournamentName);
            llMatchData = view.findViewById(R.id.llTournamentData);
        }
    }


    public MatchListAdapter(Context contextM, List<SportsData> dataListM) {
        this.contextM = contextM;
        this.dataListM = dataListM;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SportsData match = dataListM.get(position);
        holder.txtVMatchName.setText(match.matchName);

        holder.llMatchData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataHolder.MATCH_NAME = match.matchName;
                Intent intent = new Intent(new Intent(contextM,MatchOddsActivity.class));
                intent.putExtra("matchId",match.matchId);
                contextM.startActivity(intent);
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
        return dataListM.size();
    }
}
