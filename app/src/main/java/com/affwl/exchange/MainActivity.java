package com.affwl.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.binary.Sec60Activity;
import com.affwl.exchange.fx.FxActivity;
import com.affwl.exchange.indie.IndieActivity;
import com.affwl.exchange.news.NewsActivity;
import com.affwl.exchange.sport.SportActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtVIndie,txtV60Sec,txtVsports,fx;
    ImageView imgVIndie,imgV60Sec,imgVsports,imgVsportsCup,imgfx;
    LinearLayout news_layout;


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

        news_layout=findViewById(R.id.news_layout);




        txtVIndie.setOnClickListener(this);
        imgVIndie.setOnClickListener(this);
        txtV60Sec.setOnClickListener(this);
        imgV60Sec.setOnClickListener(this);
        txtVsports.setOnClickListener(this);
        imgVsports.setOnClickListener(this);
        fx.setOnClickListener(this);
        imgfx.setOnClickListener(this);

        news_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVIndie:case R.id.imgVIndie:
                startActivity(new Intent(this,IndieActivity.class)); break;
            case R.id.txtV60Sec:case R.id.imgV60Sec:
                startActivity(new Intent(this,Sec60Activity.class)); break;
            case R.id.txtVsports:case R.id.imgVsports:
                startActivity(new Intent(this,SportActivity.class)); break;
            case R.id.fx:case R.id.imgfx:
                startActivity(new Intent(this,FxActivity.class)); break;

            case R.id.news_layout:
                startActivity(new Intent(this,NewsActivity.class));
                break;

        }
    }
}
