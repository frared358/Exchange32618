package com.affwl.exchange;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.alerts.AlertsActivity;
import com.affwl.exchange.binary.Sec60Activity;
import com.affwl.exchange.crypto.BinancesActivity;
import com.affwl.exchange.fx.FxActivity;
import com.affwl.exchange.fx.Fx_Fragment_Quotes;
import com.affwl.exchange.indie.IndieActivity;
import com.affwl.exchange.news.NewsActivity;
import com.affwl.exchange.reports.ReportsActivity;
import com.affwl.exchange.settings.SettingsMainActivity;
import com.affwl.exchange.sport.SportActivity;
import com.affwl.exchange.teenpatti.SplashActivity;
import com.affwl.exchange.trades.TradesActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtVIndie,txtV60Sec,txtVsports,fx;
    ImageView imgVIndie,imgV60Sec,imgVsports,imgVsportsCup,imgfx;
    LinearLayout news_layout,settings_layout,alerts_layout,trade_layout,reports_layout,teenpatti_layout,crypto_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVIndie = (TextView)findViewById(R.id.txtVIndie);
        imgVIndie = (ImageView)findViewById(R.id.imgVIndie);

        txtV60Sec = (TextView)findViewById(R.id.txtV60Sec);
        imgV60Sec = (ImageView)findViewById(R.id.imgV60Sec);

        txtVsports=(TextView)findViewById(R.id.txtVsports);
        imgVsports=(ImageView)findViewById(R.id.imgVsports);


        fx=(TextView)findViewById(R.id.fx);
        imgfx=(ImageView)findViewById(R.id.imgfx);

        teenpatti_layout=findViewById(R.id.teenpatti_layout);
        news_layout=findViewById(R.id.news_layout);
        trade_layout=findViewById(R.id.trade_layout);
        reports_layout=findViewById(R.id.reports_layout);
        settings_layout=findViewById(R.id.settings_layout);
        alerts_layout=findViewById(R.id.alerts_layout);
        crypto_layout=findViewById(R.id.crypto_layout);

        txtVIndie.setOnClickListener(this);
        imgVIndie.setOnClickListener(this);
        txtV60Sec.setOnClickListener(this);
        imgV60Sec.setOnClickListener(this);
        txtVsports.setOnClickListener(this);
        imgVsports.setOnClickListener(this);
        fx.setOnClickListener(this);
        imgfx.setOnClickListener(this);

        news_layout.setOnClickListener(this);
        trade_layout.setOnClickListener(this);
        reports_layout.setOnClickListener(this);
        settings_layout.setOnClickListener(this);
        alerts_layout.setOnClickListener(this);
        teenpatti_layout.setOnClickListener(this);
        crypto_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVIndie:case R.id.imgVIndie:
                startActivity(new Intent(this,IndieActivity.class));
                break;
            case R.id.txtV60Sec:case R.id.imgV60Sec:
                startActivity(new Intent(this,Sec60Activity.class));
                break;
            case R.id.txtVsports:case R.id.imgVsports:
                startActivity(new Intent(this,SportActivity.class));
                break;
            case R.id.fx:case R.id.imgfx:
               startActivity(new Intent(this,FxActivity.class));
                break;
//                Fragment currentFragment = new Fragment ();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction ft = fragmentManager.beginTransaction();
//                ft.replace(R.id.xzz, currentFragment);    //content_fx
              //  ft.commit();

            case R.id.news_layout:
                startActivity(new Intent(this,NewsActivity.class));
                break;

            case R.id.teenpatti_layout:
                startActivity(new Intent(this, SplashActivity.class));
                break;

            case R.id.trade_layout:
                startActivity(new Intent(this, TradesActivity.class));
                break;

            case R.id.reports_layout:
                startActivity(new Intent(this, ReportsActivity.class));
                break;

            case R.id.alerts_layout:
                startActivity(new Intent(this, AlertsActivity.class));
                break;

            case R.id.crypto_layout:
                startActivity(new Intent(this, BinancesActivity.class));
                break;

            case R.id.settings_layout:
                startActivity(new Intent(this, SettingsMainActivity.class));
                break;

        }
    }

    @Override
    public void onBackPressed() {
        dialogLogout();
    }

    void dialogLogout(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_logout);


        TextView txtVOK =  dialog.findViewById(R.id.txtVOK);
        TextView txtVCancel =  dialog.findViewById(R.id.txtVCancel);

        txtVOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        txtVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
