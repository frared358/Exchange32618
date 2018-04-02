package com.affwl.exchange.sport;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.List;

/**
 * Created by user on 3/29/2018.
 */

public class TournamentAdapter extends RecyclerView.Adapter<TournamentAdapter.MyViewHolder> {


    Context contextT;
    private List<SportsData> dataListT;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVTournamentName;
        LinearLayout llTournamentData;
        ImageView imgVTournamentIcon;

        public MyViewHolder(View view) {
            super(view);
            txtVTournamentName = view.findViewById(R.id.txtVTournamentName);
            llTournamentData = view.findViewById(R.id.llTournamentData);
            imgVTournamentIcon = view.findViewById(R.id.imgVTournamentIcon);
        }
    }

    public TournamentAdapter(Context context, List<SportsData> dataList) {
        this.contextT = context;
        this.dataListT = dataList;
    }

    @Override
    public void onBindViewHolder(TournamentAdapter.MyViewHolder holder, final int position) {
        final SportsData tournament = dataListT.get(position);
        holder.txtVTournamentName.setText(tournament.txtVSportName);
        holder.llTournamentData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(contextT, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public TournamentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_tournament_name,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return dataListT.size();
    }
}
