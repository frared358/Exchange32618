package com.affwl.exchange.fx;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.affwl.exchange.R;

public class new_Acc_open_a_real_acc_Activity extends AppCompatActivity implements View.OnClickListener {
    Dialog myDialog;
    Button button6;
    RelativeLayout RL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__acc_open_a_real_acc_);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        RL = (RelativeLayout)findViewById(R.id.RL);
        RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), New_acc_Open_a_real_acc_pI.class);
                startActivity(i);
            }
        });

        myDialog = new Dialog(this);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);

    }
    public void ShowPopup() {
        TextView txtclose;
        Button btnFollow;

        myDialog.setContentView(R.layout.new_acc_broker_popup);
        txtclose = myDialog.findViewById(R.id.btnfollow);

        txtclose.setText("OK");

        btnFollow =  myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        // myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6:
                ShowPopup();
                break;
        }

    }}