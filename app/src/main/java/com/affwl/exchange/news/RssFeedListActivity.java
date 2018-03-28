package com.affwl.exchange.news;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssFeedListActivity  extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    CheckBox sportsCheckBox,cryptoCheckBox,indieCheckBox,binaryCheckBox,fxCheckBox;
    LinearLayout news_checkbox_layout,sports_checkbox_layout,crypto_checkbox_layout,indie_checkbox_layout,binary_checkbox_layout,fx_checkbox_layout;
    private ArrayList<String> myList;

    private int integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed_list);

        initNewsComponent();
    }

    private void initNewsComponent() {

        sportsCheckBox=findViewById(R.id.sportsCheckBox);
        cryptoCheckBox=findViewById(R.id.cryptoCheckBox);
        indieCheckBox=findViewById(R.id.indieCheckBox);
        binaryCheckBox=findViewById(R.id.binaryCheckBox);
        fxCheckBox=findViewById(R.id.fxCheckBox);


        sports_checkbox_layout=findViewById(R.id.sports_checkbox_layout);
        crypto_checkbox_layout=findViewById(R.id.crypto_checkbox_layout);
        indie_checkbox_layout=findViewById(R.id.indie_checkbox_layout);
        binary_checkbox_layout=findViewById(R.id.binary_checkbox_layout);
        fx_checkbox_layout=findViewById(R.id.fx_checkbox_layout);


        sportsCheckBox.setOnClickListener(this);
        cryptoCheckBox.setOnClickListener(this);
        indieCheckBox.setOnClickListener(this);
        binaryCheckBox.setOnClickListener(this);
        fxCheckBox.setOnClickListener(this);

        sports_checkbox_layout.setOnLongClickListener(this);
        crypto_checkbox_layout.setOnLongClickListener(this);
        indie_checkbox_layout.setOnLongClickListener(this);
        binary_checkbox_layout.setOnLongClickListener(this);
        fx_checkbox_layout.setOnLongClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sportsCheckBox:
                if (sportsCheckBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "selected Sports", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.cryptoCheckBox:
                if(cryptoCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected Crypto", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.indieCheckBox:
                if(indieCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected Indie", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.binaryCheckBox:
                if(binaryCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected Binary", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.fxCheckBox:
                if(fxCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected FX", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId())
        {
            case R.id.sports_checkbox_layout: case R.id.crypto_checkbox_layout:  case R.id.indie_checkbox_layout:  case R.id.binary_checkbox_layout: case R.id.fx_checkbox_layout:
            displayPopup();
            break;
        }
        return false;
    }

    private void displayPopup() {
        TextView setMinutes,pasteUrl,deleteUrl;
        final LinearLayout news_option_layout,minutes_layout,new_url_layout;
        final ImageView img_minus_minutes,img_plus_minutes,close_news_option;
        final TextView text_minutes;

        final Dialog myDialog = new Dialog(RssFeedListActivity.this);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.setContentView(R.layout.news_options);
        news_option_layout=myDialog.findViewById(R.id.news_option_layout);
        minutes_layout=myDialog.findViewById(R.id.minutes_layout);

        setMinutes = myDialog.findViewById(R.id.setMinutes);
        pasteUrl=myDialog.findViewById(R.id.pasteUrl);
        deleteUrl=myDialog.findViewById(R.id.deleteUrl);
        text_minutes=myDialog.findViewById(R.id.text_minutes);
        close_news_option=myDialog.findViewById(R.id.close_news_option);


        img_plus_minutes=myDialog.findViewById(R.id.img_plus_minutes);
        img_minus_minutes=myDialog.findViewById(R.id.img_minus_minutes);

        close_news_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        setMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news_option_layout.setVisibility(View.GONE);
                minutes_layout.setVisibility(View.VISIBLE);
            }
        });

        pasteUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RssFeedListActivity.this,RssFeedUrlActivity.class));
            }
        });

        deleteUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   myDialog.dismiss();
                   finish();
            }
        });

        img_plus_minutes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String value = text_minutes.getText().toString();
                Log.i("value",""+value);
                integer = Integer.parseInt(value);
                Log.i("integer",""+integer);
                if (integer >= 5) {
                    integer = integer + 1;
                    String str = String.valueOf(integer);
                    text_minutes.setText(str);
                }
            }

        });

        img_minus_minutes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String value = text_minutes.getText().toString();
                Log.i("value",""+value);
                integer = Integer.parseInt(value);
                Log.i("integer",""+integer);
                if (integer > 5) {
                    integer = integer - 1;
                    String str = String.valueOf(integer);
                    text_minutes.setText(str);
                }
            }

        });

        myDialog.show();
    }
}