package com.affwl.exchange.trades;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.affwl.exchange.R;

public class TradesFxFragment extends Fragment implements View.OnClickListener {


    public TradesFxFragment() {
        // Required empty public constructor
    }

    LinearLayout ll_position_details,ll_position_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trades_fx, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_position_list=view.findViewById(R.id.ll_position_list);
        ll_position_details=view.findViewById(R.id.ll_position_details);

        ll_position_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ll_position_list:
                if(ll_position_details.getVisibility()==View.VISIBLE)
                {
                    ll_position_details.setVisibility(View.GONE);
                }
                else {
                    ll_position_details.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}

