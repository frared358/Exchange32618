<<<<<<< HEAD
package com.affwl.exchange.sport;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener{




    TextView txtVKeepBet,txtVNormal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtVKeepBet = view.findViewById(R.id.txtVKeepBet);
        txtVNormal = view.findViewById(R.id.txtVNormal);

        txtVKeepBet.setOnClickListener(SettingFragment.this);
        txtVNormal.setOnClickListener(SettingFragment.this);

        return view;
    }

    public SettingFragment() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVKeepBet:
                txtVNormal.setBackground(getResources().getDrawable(R.drawable.border_greay_small));
                txtVKeepBet.setBackgroundColor(Color.parseColor("#939090"));
                break;
            case R.id.txtVNormal:
                txtVKeepBet.setBackground(getResources().getDrawable(R.drawable.border_greay_small));
                txtVNormal.setBackgroundColor(Color.parseColor("#939090"));
                break;
        }
    }
}
=======
package com.affwl.exchange.sport;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener{




    TextView txtVKeepBet,txtVNormal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtVKeepBet = view.findViewById(R.id.txtVKeepBet);
        txtVNormal = view.findViewById(R.id.txtVNormal);

        txtVKeepBet.setOnClickListener(SettingFragment.this);
        txtVNormal.setOnClickListener(SettingFragment.this);

        return view;
    }

    public SettingFragment() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVKeepBet:
                txtVNormal.setBackground(getResources().getDrawable(R.drawable.border_greay_small));
                txtVKeepBet.setBackgroundColor(Color.parseColor("#939090"));
                break;
            case R.id.txtVNormal:
                txtVKeepBet.setBackground(getResources().getDrawable(R.drawable.border_greay_small));
                txtVNormal.setBackgroundColor(Color.parseColor("#939090"));
                break;
        }
    }
}
>>>>>>> app name to TEJAS
