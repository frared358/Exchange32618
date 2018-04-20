package com.affwl.exchange.trades;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.affwl.exchange.R;

public class TradesSixtySecFragment extends Fragment implements View.OnClickListener {


    ImageView img_announce;
    LinearLayout ll_announce_details;

    public TradesSixtySecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trades_sixtysec, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_announce_details=view.findViewById(R.id.ll_announce_details);
        img_announce=view.findViewById(R.id.img_announce);
        img_announce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_announce:
                if(ll_announce_details.getVisibility()==View.VISIBLE)
                {
                    ll_announce_details.setVisibility(View.GONE);
                }
                else {
                    ll_announce_details.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}

