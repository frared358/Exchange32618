package com.affwl.exchange.indie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.R;


public class IndieMarketIndicesFragment extends Fragment implements View.OnClickListener{


    TextView txtVTitle,txtVTime,txtVValue,txtVPercentage;
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
        }

    }
}
