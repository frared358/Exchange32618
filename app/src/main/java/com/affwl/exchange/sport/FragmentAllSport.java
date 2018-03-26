package com.affwl.exchange.sport;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;


public class FragmentAllSport extends Fragment implements View.OnClickListener{


    TextView txtVMatchName;
    LinearLayout llMatchData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_sport, container, false);
        txtVMatchName = view.findViewById(R.id.txtVMatchName);
        llMatchData = view.findViewById(R.id.llMatchData);
        txtVMatchName.setOnClickListener(FragmentAllSport.this);
        llMatchData.setOnClickListener(FragmentAllSport.this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVMatchName: case R.id.llMatchData:
                startActivity(new Intent(FragmentAllSport.this.getActivity(),BetActivity.class));
                break;

        }
    }
}
