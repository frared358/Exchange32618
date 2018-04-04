package com.affwl.exchange.fx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.affwl.exchange.R;

public class Add_symbol extends AppCompatActivity {
LinearLayout ll_add_symbol;
AutoCompleteTextView autotv;
String[] Add_symbols;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symbol);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
       autotv =(AutoCompleteTextView) findViewById(R.id.autotv);
        Add_symbols=getResources().getStringArray(R.array.Add_symbols);
ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Add_symbols);

autotv.setAdapter(adapter);



        ll_add_symbol=(LinearLayout)findViewById(R.id.ll_add_symbol);
        ll_add_symbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Add_symbol_Forex.class);
                startActivity(i);
            }
        });

    }


}