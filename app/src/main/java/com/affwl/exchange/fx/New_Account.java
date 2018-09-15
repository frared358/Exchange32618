package com.affwl.exchange.fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.affwl.exchange.R;

public class New_Account extends AppCompatActivity {
RelativeLayout relativeLayout;
    RelativeLayout   relativeLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__account);
        relativeLayout=(RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(v.getContext(), New_acc_start_without_regi.class);
                startActivity(i);*/
            }
        });

        relativeLayout1=(RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), new_Acc_open_a_real_acc_Activity.class);
                startActivity(i);
            }
        });

    }

}
