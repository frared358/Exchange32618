package com.affwl.exchange.fx;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.affwl.exchange.R;

/**
 * Created by user on 1/25/2018.
 */

public class Fx_Fragment_Settings extends Fragment implements View.OnClickListener {
    TextView tx;
    TextView tv1;
    Dialog myDialog,myDialog1;
TextView tv3;

    @Nullable
    @Override
    /** Right click - Generate - Override Method - slect onCreateView */ public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fx_settings, null);
        myDialog = new Dialog(getContext());
        myDialog1 = new Dialog(getContext());
        tv1 = v.findViewById(R.id.tv1);

        tv1.setOnClickListener(this);

        tv3 = v.findViewById(R.id.tv3);

        tv3.setOnClickListener(this);


        tx = (TextView) v.findViewById(R.id.tv11);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), OneClickTrading.class);
                startActivity(i);
            }
        });
        return v;
    }

    public void ShowPopup() {
        TextView txtclose;
        Button btnFollow;
        Button btnFollow1;

        myDialog.setContentView(R.layout.custompopup1_msg_dts_id);


        txtclose = myDialog.findViewById(R.id.btnfollow);
        txtclose = myDialog.findViewById(R.id.btnfollow1);


        txtclose.setText("Copy");
        txtclose.setText("OK");

        btnFollow = myDialog.findViewById(R.id.btnfollow);
        btnFollow = myDialog.findViewById(R.id.btnfollow1);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        // myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPopup1() {
        TextView txtclose;
        Button btnFollow;
        Button btnFollow1;

        myDialog1.setContentView(R.layout.setting_custompopup2);



        txtclose = myDialog1.findViewById(R.id.btnfollow1);

        txtclose.setText("Cancel");

        btnFollow = myDialog1.findViewById(R.id.btnfollow1);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog1.dismiss();
            }
        });

        // myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog1.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                ShowPopup();
                break;
            case R.id.tv3:
                ShowPopup1();
                break;

        }

    }
}