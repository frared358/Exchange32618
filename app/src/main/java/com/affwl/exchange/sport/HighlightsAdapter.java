package com.affwl.exchange.sport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.List;

/**
 * Created by user on 3/27/2018.
 */

public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.MyViewHolder> {

    private List<HighlightsData> dataList;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVMatchName;

        public MyViewHolder(View view) {
            super(view);
            txtVMatchName =  view.findViewById(R.id.txtVMatchName);
        }
    }

    public HighlightsAdapter(Context context,List<HighlightsData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_match_name,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HighlightsData highlights = dataList.get(position);
        Log.i("TAG1",highlights.txtVMatchName+"");
        holder.txtVMatchName.setText(highlights.txtVMatchName);
        holder.txtVMatchName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,BetActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("TAG",dataList.size()+"");
        return dataList.size();
    }
}
