package com.affwl.exchange.fx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.affwl.exchange.R;

/**
 * Created by user on 3/26/2018.
 */

public class Fx_MarketStatistics_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fx_marketstatistics);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_search, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = "http://www.google.co.in";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}
   
