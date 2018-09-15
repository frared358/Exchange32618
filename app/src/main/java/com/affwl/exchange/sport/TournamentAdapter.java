package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        public MyViewHolder(View view) {
            super(view);
            txtVTournamentName = view.findViewById(R.id.txtVTournamentName);
            llTournamentData = view.findViewById(R.id.llTournamentData);
        }
    }

    public TournamentAdapter(Context contextT, List<SportsData> dataList) {
        this.contextT = contextT;
        this.dataListT = dataList;
    }

    @Override
    public void onBindViewHolder(TournamentAdapter.MyViewHolder holder, final int position) {
        final SportsData tournament = dataListT.get(position);
        holder.txtVTournamentName.setText(tournament.txtVTournamentName);

        holder.llTournamentData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(contextT,MatchListActivity.class);
                intent.putExtra("tournamentName",tournament.txtVTournamentName);
                intent.putExtra("tournamentId",tournament.txtVTournamentId);
                intent.putExtra("sportId",tournament.txtVSportId);
                contextT.startActivity(intent);
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
