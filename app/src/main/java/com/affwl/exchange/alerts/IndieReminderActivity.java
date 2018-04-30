package com.affwl.exchange.alerts;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.Set;


public class IndieReminderActivity extends AppCompatActivity {

    Toolbar toolbarReminder;
    AlertListAdapter alertListAdapter;
    RecyclerView list_alerts;
    ArrayList<String> alert_array_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_reminder);
        toolbarReminder = (Toolbar)findViewById(R.id.toolbarReminder);
        setSupportActionBar(toolbarReminder);

        list_alerts=findViewById(R.id.list_alerts);
        list_alerts.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_alerts.setLayoutManager(mLayoutManager);
       list_alerts.setItemAnimator(new DefaultItemAnimator());

        SharedPreferences myScores = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> set = myScores.getStringSet("key", null);
        Log.i("Set",""+set);
        if(set!=null) {
            alert_array_list = new ArrayList<String>(set);
        }
        else {
            alert_array_list=new ArrayList<>();
//            alert_array_list.add("No data to display");
        }
        alertListAdapter = new AlertListAdapter(this,alert_array_list);
        list_alerts.setAdapter(alertListAdapter);
        }

}
