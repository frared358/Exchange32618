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


public class IndieMarketMoversEquityFragment extends Fragment implements View.OnClickListener {

    TextView txtv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indie_market_movers_equity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtv = (TextView)view.findViewById(R.id.txtVEquityData);
        txtv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVEquityData:
                startActivity(new Intent(IndieMarketMoversEquityFragment.this.getActivity(),MarketMoversEquityActivity.class));
        }
    }
}
