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

public class IndieMarketCurrencyFragment extends Fragment implements View.OnClickListener{

    TextView txtVTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indie_market_currency, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVTitle = (TextView)view.findViewById(R.id.txtVTitle);
        txtVTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVTitle:
                startActivity(new Intent(IndieMarketCurrencyFragment.this.getActivity(),MarketCurrencyActivity.class));
        }
    }
}
