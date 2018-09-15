package com.affwl.exchange.sport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.List;

public class BetHistoryAdapter extends RecyclerView.Adapter<BetHistoryAdapter.MyViewHolder> {

    Context contextBH;
    private List<Data> dataList;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtVBHSelection,txtVBHType,txtVBHOdds,txtVBHStake,txtVBHP_L;
        public MyViewHolder(View v) {
            super(v);
            txtVBHSelection = v.findViewById(R.id.txtVBHSelection);
            txtVBHType = v.findViewById(R.id.txtVBHType);
            txtVBHOdds = v.findViewById(R.id.txtVBHOdds);
            txtVBHStake = v.findViewById(R.id.txtVBHStake);
            txtVBHP_L = v.findViewById(R.id.txtVBHP_L);
        }
    }

    public BetHistoryAdapter(Context contextBH,List<Data> dataList){
        this.contextBH = contextBH;
        this.dataList = dataList;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.txtVBHSelection.setText(data.betSelection);
        holder.txtVBHType.setText(data.betType);
        holder.txtVBHOdds.setText(data.betOdd);
        holder.txtVBHOdds.setTextColor(ContextCompat.getColor(contextBH,R.color.colorBlueBet));
        holder.txtVBHStake.setText(data.betStake);
        holder.txtVBHP_L.setText(data.betPL);

        if (data.betType.equalsIgnoreCase("lay") || data.betType.equalsIgnoreCase("no")){
            holder.txtVBHType.setTextColor(ContextCompat.getColor(contextBH,R.color.colorRedBet2));
        }else {
            holder.txtVBHType.setTextColor(ContextCompat.getColor(contextBH,R.color.colorBlueBet2));
        }

        if (Integer.parseInt(data.betPL)> 0){
            holder.txtVBHP_L.setTextColor(ContextCompat.getColor(contextBH,R.color.colorGreen));
        }else if (Integer.parseInt(data.betPL)< 0) {
            holder.txtVBHP_L.setTextColor(ContextCompat.getColor(contextBH,R.color.colorRed));
        }else  {
            holder.txtVBHP_L.setTextColor(ContextCompat.getColor(contextBH,R.color.colorBlack));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_bet_history,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
