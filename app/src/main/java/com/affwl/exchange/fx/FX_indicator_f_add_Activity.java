package com.affwl.exchange.fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class FX_indicator_f_add_Activity extends AppCompatActivity {
TextView textView39;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fx_indicator_f_add_);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        textView39=(TextView)findViewById(R.id.textView39);
        textView39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FX_indicator_f1_add_Activity.class);
                startActivity(i);

            }
        });

    }
}

