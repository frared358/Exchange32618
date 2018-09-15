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

public class IndieMarketDerivativeFragment extends Fragment implements View.OnClickListener {

    TextView txtV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indie_market_derivative, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtV =(TextView)view.findViewById(R.id.txtVTitle);
        txtV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVTitle:
                startActivity(new Intent(IndieMarketDerivativeFragment.this.getActivity(),MarketDerivativesActivity.class));
        }
    }
}
