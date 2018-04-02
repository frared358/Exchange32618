package com.affwl.exchange.sport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

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
        public MyViewHolder(View view) {
            super(view);
            txtVRunner = view.findViewById(R.id.txtVRunner);
            txtVOdds = view.findViewById(R.id.txtVOdds);
            txtVStack = view.findViewById(R.id.txtVStack);
            txtVProfitLiability = view.findViewById(R.id.txtVProfitLiability);
            txtVDateTime = view.findViewById(R.id.txtVDateTime);
            llMatchList = view.findViewById(R.id.llMatchList);
        }
    }

    public MatchAdapter(Context contextMatch, List<MatchData> dataList) {
        this.contextMatch = contextMatch;
        this.dataList = dataList;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MatchData matchData = dataList.get(position);
        Log.i("CHECK "+position,matchData.runner+""+matchData.odd+""+matchData.stack+""+matchData.type+""+matchData.dateTime);
        holder.txtVRunner.setText(matchData.runner);
        holder.txtVOdds.setText(matchData.odd);
        holder.txtVStack.setText(matchData.stack);
        Double stackValue = Double.parseDouble(matchData.stack.trim());
        Double oddValue = Double.parseDouble(matchData.odd.trim());
        Double val = (oddValue-1.00)*stackValue;

        holder.txtVProfitLiability.setText(String.format("%.2f", val));
        holder.txtVDateTime.setText(matchData.dateTime.replaceFirst(" ","\n"));
        if(matchData.type.equalsIgnoreCase("lay")){
            holder.llMatchList.setBackgroundColor(ContextCompat.getColor(contextMatch, R.color.colorRedBet));
        }
        else {
            holder.llMatchList.setBackgroundColor(ContextCompat.getColor(contextMatch, R.color.colorBlueBet));
        }
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

}
