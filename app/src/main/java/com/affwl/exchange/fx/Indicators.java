package com.affwl.exchange.fx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.affwl.exchange.fx.Fx_Chart_Fragment.rlchartb;

public class Indicators extends AppCompatActivity {
    ImageView imageButton_fadd;
    CheckBox cbindi;

    LinearLayout llmainchart, llindichart;
    int select=0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicators);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        llmainchart=findViewById(R.id.llmainchart);
        llindichart=findViewById(R.id.llindichart);

        cbindi=findViewById(R.id.cbindi);


        imageButton_fadd = (ImageView) findViewById(R.id.imageButton_fadd);
        imageButton_fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), FX_indicator_f_add_Activity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.indi_menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.indi_del:
//                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
//                if (checkBox.getVisibility() == View.GONE || checkBox2.getVisibility()==View.GONE) {
//                    checkBox.setVisibility(View.VISIBLE);
//                    checkBox2.setVisibility(View.VISIBLE);
//                    select=1;
//                }
//                    if (checkBox.isChecked()) {
//                        textView17.setVisibility(View.INVISIBLE);
//                    }
//                    else if (checkBox2.isChecked()) {
//                        textView19.setVisibility(View.INVISIBLE);
//                    }
//                    else {
//                        textView17.setVisibility(View.VISIBLE);
//                        textView19.setVisibility(View.VISIBLE);
//
//                    }
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
////       else if(checkBox.isChecked())
////        {
////            textView17.setVisibility(View.GONE);
////
////        }
////        if(checkBox2.isChecked())
////        {
////            textView19.setVisibility(View.GONE);
////        }
////
//        }
//    }
}




