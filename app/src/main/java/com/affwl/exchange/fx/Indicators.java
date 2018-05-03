package com.affwl.exchange.fx;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.affwl.exchange.alerts.MessagePage;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.affwl.exchange.fx.Fx_Chart_Fragment.rlchartb;

public class Indicators extends AppCompatActivity implements OnClickListener{
    ImageView imageButton_fadd1,imageButton_fadd;
    CheckBox checkBox;
    LinearLayout llkuchb;
    int select=0;
    ImageView save_delete;
    Toolbar delettoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicators);

        delettoolbar=findViewById(R.id.message_page_delete_toolbar);
        setSupportActionBar(delettoolbar);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        llkuchb=findViewById(R.id.llkuchb);


        checkBox = (CheckBox) findViewById(R.id.cbindi);
        save_delete=findViewById(R.id.save_delete);
        save_delete.setOnClickListener(this);

        imageButton_fadd=findViewById(R.id.imageButton_fadd);
        imageButton_fadd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FX_indicator_f_add_Activity.class);
                startActivity(i);
            }
        });
        imageButton_fadd1 = findViewById(R.id.imageButton_fadd1);
        imageButton_fadd1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), FX_indicator_f_add_Activity.class);
                startActivity(i);
            }
        });

    }
    private void deletetxt() {

        checkBox.setVisibility(View.VISIBLE);

        delettoolbar.setVisibility(View.VISIBLE);
        delettoolbar.getMenu().clear();
        delettoolbar.inflateMenu(R.menu.fx_delete);

        delettoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (item.getItemId()){
                    case R.id.select_all :

                        if(checkBox.isChecked()==false) {
                            checkBox.setChecked(true);
                            Drawable myDrawable = getResources().getDrawable(R.drawable.ic_select_none); // The ID of your drawable.
                            item.setIcon(myDrawable);
                        }
                        else {
                            checkBox.setChecked(false);
                            Drawable myDrawable = getResources().getDrawable(R.drawable.ic_select_all); // The ID of your drawable.
                            item.setIcon(myDrawable);
                        }
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_delete:

                if(checkBox.isChecked()==true) {
//                    .setVisibility(View.VISIBLE);
                    delettoolbar.setVisibility(View.GONE);
                    llkuchb.setVisibility(View.GONE);

                }
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the message_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.indi_menu, menu);


        return true;
    }

@Override
public boolean onPrepareOptionsMenu(Menu menu) {
    return super.onPrepareOptionsMenu(menu);
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.indi_del:
                deletetxt();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.indi_del:
//                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
//                if (checkBox.getVisibility() == View.GONE ) {
//                    checkBox.setVisibility(View.VISIBLE);
//                    select=1;
//                }
//                if( select==1){
//                    if (checkBox.isChecked()) {
//                        llkuchb.setVisibility(View.GONE);
//
//                    }}
//
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//
//
//        }
//    }
}




