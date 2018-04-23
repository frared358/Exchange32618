package com.affwl.exchange.trades;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

public class TradesFxFragment extends Fragment implements View.OnClickListener {


    public TradesFxFragment() {
        // Required empty public constructor
    }

    LinearLayout ll_position_details,ll_position_list;
    TextView tv4,tv_buy,tv_minus,tv_from_value,tv_to_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trades_fx, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Typeface yourfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoCondensed-Regular.ttf");

        ll_position_list=view.findViewById(R.id.ll_position_list);
        ll_position_details=view.findViewById(R.id.ll_position_details);

        tv4=view.findViewById(R.id.tv4);
        tv_buy=view.findViewById(R.id.tv_buy);
        tv_minus=view.findViewById(R.id.tv_minus);
        tv_from_value=view.findViewById(R.id.tv_from_value);
        tv_to_value=view.findViewById(R.id.tv_to_value);

        tv4.setTypeface(yourfont);
        tv_buy.setTypeface(yourfont);
        tv_minus.setTypeface(yourfont);
        tv_from_value.setTypeface(yourfont);
        tv_to_value.setTypeface(yourfont);

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

