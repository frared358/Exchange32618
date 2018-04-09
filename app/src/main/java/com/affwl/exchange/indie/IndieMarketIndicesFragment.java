package com.affwl.exchange.indie;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.affwl.exchange.news.RssFeedListActivity;


public class IndieMarketIndicesFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {


    TextView txtVTitle,txtVTime,txtVValue,txtVPercentage;
    LinearLayout indie_market_indices_layout;
    Dialog myDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indie_market_indices, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVTitle = (TextView)view.findViewById(R.id.txtVTitle);
        txtVTime = (TextView)view.findViewById(R.id.txtVTime);
        txtVValue = (TextView)view.findViewById(R.id.txtVValue);
        txtVPercentage = (TextView)view.findViewById(R.id.txtVPercentage);

        indie_market_indices_layout=view.findViewById(R.id.indie_market_indices_layout);

        indie_market_indices_layout.setOnLongClickListener(this);
        txtVTitle.setOnClickListener(this);
        txtVTime.setOnClickListener(this);
        txtVValue.setOnClickListener(this);
        txtVPercentage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtVTitle: case R.id.txtVTime:
            case R.id.txtVValue: case R.id.txtVPercentage:
                startActivity(new Intent(IndieMarketIndicesFragment.this.getActivity(),MarketIndicesActivity.class));
                break;

        }

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId())
        {
            case R.id.indie_market_indices_layout:
                displayAlertOption(v.getContext());
                break;
        }
        return false;
    }

    private void displayAlertOption(Context context) {
        Button close_alert_layout,cancel_alert_layout;
        TextView set_alert_layout;
        final LinearLayout edit_alert_layout,alert_option_layout;

        myDialog = new Dialog(context);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.indie_alert_option);

        close_alert_layout=myDialog.findViewById(R.id.close_alert_layout);
        set_alert_layout=myDialog.findViewById(R.id.set_alert_layout);
        edit_alert_layout=myDialog.findViewById(R.id.edit_alert_layout);
        alert_option_layout=myDialog.findViewById(R.id.alert_option_layout);
        cancel_alert_layout=myDialog.findViewById(R.id.cancel_alert_layout);

        cancel_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        close_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        set_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_option_layout.setVisibility(View.GONE);
                edit_alert_layout.setVisibility(View.VISIBLE);
            }
        });

        myDialog.show();
    }
}
