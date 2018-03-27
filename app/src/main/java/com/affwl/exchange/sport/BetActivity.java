
package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.google.gson.JsonElement;

import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

//Bet
public class BetActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    ImageView imgVCheck,imgRightDrawer;
    DrawerLayout drawerBet;
    NavigationView navigationView1,navigationView2;
    LinearLayout pink1,pink2,pink3,blue1,blue2,blue3;
    TextView txtVChipsStake;
    ImageView imgVFav;
    Handler handler;
    int vchscv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgVCheck = findViewById(R.id.imgVCheck);
        imgVCheck.setOnClickListener(this);
        imgRightDrawer = findViewById(R.id.imgRightDrawer);
        imgRightDrawer.setOnClickListener(this);
        pink1 = findViewById(R.id.pink1);
        pink1.setOnClickListener(this);
        pink2 = findViewById(R.id.pink2);
        pink2.setOnClickListener(this);
        pink3 = findViewById(R.id.pink3);
        pink3.setOnClickListener(this);

        blue1 = findViewById(R.id.blue1);
        blue1.setOnClickListener(this);
        blue2 = findViewById(R.id.blue2);
        blue2.setOnClickListener(this);
        blue3 = findViewById(R.id.blue3);
        blue3.setOnClickListener(this);

        txtVChipsStake = findViewById(R.id.txtVChipsStake);
        txtVChipsStake.setOnClickListener(this);

        imgVFav = findViewById(R.id.imgVFav);
        imgVFav.setOnClickListener(this);

        drawerBet = (DrawerLayout) findViewById(R.id.drawerBet);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerBet, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerBet.addDrawerListener(toggle);
        toggle.syncState();

        navigationView1 = (NavigationView) findViewById(R.id.nav_view1);
        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);

        handler = new Handler();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgVCheck:
                startActivity(new Intent(this,UnmatchMatchTabActivity.class));
                break;
            case R.id.imgRightDrawer:
                drawerBet.openDrawer(navigationView1);
                break;
            case R.id.blue1: case R.id.blue2: case R.id.blue3: case R.id.pink1: case R.id.pink2: case R.id.pink3:
                drawerBet.openDrawer(navigationView2);
                break;
            case R.id.txtVChipsStake:
                dialogStack();
                break;
            case R.id.imgVFav:
                Intent intent = new Intent(this,SportActivity.class);
                intent.putExtra("KEY_SELECT","true");
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void onBackPressed() {

        if (drawerBet.isDrawerOpen(GravityCompat.START)) {
            drawerBet.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    void dialogStack(){
// custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_stack);

        TextView txtVOK =  dialog.findViewById(R.id.txtVOK);
        TextView txtVCancel =  dialog.findViewById(R.id.txtVCancel);
        TextView txtVStackEdit = dialog.findViewById(R.id.txtVStackEdit);

        txtVOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txtVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txtVStackEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(BetActivity.this,StackActivity.class));
            }
        });

        dialog.show();
    }

    HubConnection _connection;
    HubProxy _hub;
    SignalRFuture<Void> awaitConnection;

    public void displaySignalRData(final String call){

        Platform.loadPlatformComponent( new AndroidPlatformComponent() );
        _connection=new HubConnection("http://185.2.101.78:9094");
        _hub=_connection.createHubProxy("FOREXHub");

        try {
            awaitConnection = _connection.start();
            awaitConnection.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //Log.i("graph","InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            //Log.i("graph", "ExecutionException");
        }

        _hub.invoke("SubscribeSymbol",call);

        try{
            _connection.received(new MessageReceivedHandler() {
                @Override
                public void onMessageReceived(JsonElement json) {

                    Log.i("Signalr",""+json);
                    String[] arrToken = json.toString().replaceAll(",",":").split(":");
//                    final Double Ask = Double.valueOf(arrToken[8]);
//                    final Double Bid = Double.valueOf(arrToken[12]);
//                    final Double calc =  (Ask+Bid)/2;



                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },1000);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            //Log.i("graph",e.toString());}
        }
    }
}
