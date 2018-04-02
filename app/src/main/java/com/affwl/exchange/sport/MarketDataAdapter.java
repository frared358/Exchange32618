package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import java.util.List;

/**
 * Created by user on 3/31/2018.
 */

public class MarketDataAdapter extends RecyclerView.Adapter<MarketDataAdapter.MyViewHolder> {

    Context contextMarket;
    private List<MarketData> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtVRunnerName,txtVBackData,txtVBackChips,txtVLayData,txtVLayChips;
        LinearLayout llBack,llLay;
        public MyViewHolder(View view) {
            super(view);
            txtVRunnerName = view.findViewById(R.id.txtVRunnerName);
            txtVBackData = view.findViewById(R.id.txtVBackData);
            txtVBackChips = view.findViewById(R.id.txtVBackChips);
            txtVLayData = view.findViewById(R.id.txtVLayData);
            txtVLayChips = view.findViewById(R.id.txtVLayChips);
            llBack = view.findViewById(R.id.llBack);
            llLay = view.findViewById(R.id.llLay);
        }
    }

    public MarketDataAdapter(Context contextMarket, List<MarketData> dataList){
        this.contextMarket = contextMarket;
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MarketData market = dataList.get(position);
        holder.txtVRunnerName.setText(market.RunnerName);
        holder.txtVBackData.setText(market.Back);
        holder.txtVBackChips.setText(market.ChipsBack);
        holder.txtVLayData.setText(market.Lay);
        holder.txtVLayChips.setText(market.ChipsLay);

        holder.llBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialogBetPlace(market.RunnerName,R.color.colorBlueBetTrasparent,Double.valueOf(market.Back),Double.valueOf(market.Back),Double.valueOf(market.Back));
            }
        });

        holder.llLay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialogBetPlace(market.RunnerName,R.color.colorRedBetTrasparent,Double.valueOf(market.Lay),Double.valueOf(market.Lay),Double.valueOf(market.Lay));
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_market_data,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    TextView txtVRunnerTitle,txtVStackValue,txtVOddValue,txtVProfitValue;
    LinearLayout llDialogBetPlace;
    public void dialogBetPlace(String RunnerTitle,int color,Double oddValue,Double stackValue,Double profit){
        final Dialog dialog = new Dialog(contextMarket,R.style.Dialog);
        dialog.setContentView(R.layout.dialog_bet_place);
        dialog.setTitle(RunnerTitle);
        dialog.getWindow().setBackgroundDrawableResource(color);

        //txtVRunnerTitle = dialog.findViewById(R.id.txtVRunnerTitle);
        llDialogBetPlace = dialog.findViewById(R.id.llDialogBetPlace);
        txtVStackValue = dialog.findViewById(R.id.txtVStackValue);
        txtVOddValue = dialog.findViewById(R.id.txtVOddValue);
        txtVProfitValue = dialog.findViewById(R.id.txtVProfitValue);


        //llDialogBetPlace.setBackgroundColor(ContextCompat.getColor(contextMarket, color));
        //txtVRunnerTitle.setText(RunnerTitle);
        txtVStackValue.setText(String.valueOf(DataHolder.STACK_VALUE));
        txtVOddValue.setText(String.valueOf(oddValue));
        profit = (oddValue-1)*DataHolder.STACK_VALUE;
        txtVProfitValue.setText(String.format("Profit CHIPS %.2f", profit));

        dialog.show();
    }
}
