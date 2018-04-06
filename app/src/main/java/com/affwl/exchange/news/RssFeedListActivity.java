package com.affwl.exchange.news;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

    int categoryValue,selected_value,timeNow;
    private int integer;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;


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

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT  * FROM selected_category WHERE user_id = 1234", null);

            if (c.moveToFirst()) {

                do {
                    Log.i("Tag",""+c.getInt(c.getColumnIndex("category_id")));
                    Log.i("Tag",""+c.getInt(c.getColumnIndex("user_name")));
                    Log.i("Tag",""+c.getString(c.getColumnIndex("refresh_time")));
                    if(c.getInt(c.getColumnIndex("category_id"))==1)
                    {
                        sportsCheckBox.setChecked(true);
                    }
                    else if(c.getInt(c.getColumnIndex("category_id"))==2)
                    {
                        cryptoCheckBox.setChecked(true);
                    }
                    else if(c.getInt(c.getColumnIndex("category_id"))==3)
                    {
                        indieCheckBox.setChecked(true);
                    }
                    else if(c.getInt(c.getColumnIndex("category_id"))==4)
                    {
                        binaryCheckBox.setChecked(true);
                    }
                    else if(c.getInt(c.getColumnIndex("category_id"))==5)
                    {
                        fxCheckBox.setChecked(true);
                    }
                } while (c.moveToNext());
            }
            Log.i("my db"," "+mDb);

        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sportsCheckBox:
                if (sportsCheckBox.isChecked()) {
                    try {
                        mDb = mDBHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("category_id",1);
                        values.put("user_id",1234);
                        values.put("user_name","kavari");
                        values.put("refresh_time",5);

                        mDb.insert("selected_category",null,values);
                        Log.i("my db"," "+mDb);

                    } catch (SQLException mSQLException) {
                        throw mSQLException;
                    }
                    Toast.makeText(getApplicationContext(), "selected Sports", Toast.LENGTH_LONG).show();
                }
                else if(!sportsCheckBox.isChecked()){
                    //delete now
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    db.delete("selected_category", "user_name = ? AND category_id = ? ",
                            new String[] { "kavari", String.valueOf(1)});
                }
                break;

            case R.id.cryptoCheckBox:
                if(cryptoCheckBox.isChecked())
                {
                    try {
                        mDb = mDBHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("category_id",2);
                        values.put("user_id",1234);
                        values.put("user_name","kavari");
                        values.put("refresh_time",5);

                        mDb.insert("selected_category",null,values);
                        Log.i("my db"," "+mDb);

                    } catch (SQLException mSQLException) {
                        throw mSQLException;
                    }
                    Toast.makeText(this, "Selected Crypto", Toast.LENGTH_SHORT).show();
                }
                else if(!sportsCheckBox.isChecked()){
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    db.delete("selected_category", "user_name = ? AND category_id = ? ",
                            new String[] { "kavari", String.valueOf(2)});
                }
                break;

            case R.id.indieCheckBox:
                if(indieCheckBox.isChecked())
                {
                    try {
                        mDb = mDBHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("category_id",3);
                        values.put("user_id",1234);
                        values.put("user_name","kavari");
                        values.put("refresh_time",5);

                        mDb.insert("selected_category",null,values);
                        Log.i("my db"," "+mDb);

                    } catch (SQLException mSQLException) {
                        throw mSQLException;
                    }
                    Toast.makeText(this, "Selected Indie", Toast.LENGTH_SHORT).show();
                }
                else if(!sportsCheckBox.isChecked()){
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    db.delete("selected_category", "user_name = ? AND category_id = ? ",
                            new String[] { "kavari", String.valueOf(3)});
                }
                break;

            case R.id.binaryCheckBox:
                if(binaryCheckBox.isChecked())
                {
                    try {
                        mDb = mDBHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("category_id",4);
                        values.put("user_id",1234);
                        values.put("user_name","kavari");
                        values.put("refresh_time",5);

                        mDb.insert("selected_category",null,values);
                        Log.i("my db"," "+mDb);

                    } catch (SQLException mSQLException) {
                        throw mSQLException;
                    }
                    Toast.makeText(this, "Selected Binary", Toast.LENGTH_SHORT).show();
                }
                else if(!sportsCheckBox.isChecked()){
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    db.delete("selected_category", "user_name = ? AND category_id = ? ",
                            new String[] { "kavari", String.valueOf(4)});
                }
                break;

            case R.id.fxCheckBox:
                if(fxCheckBox.isChecked())
                {
                    try {
                        mDb = mDBHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("category_id",5);
                        values.put("user_id",1234);
                        values.put("user_name","kavari");
                        values.put("refresh_time",5);

                        mDb.insert("selected_category",null,values);
                        Log.i("my db"," "+mDb);

                    } catch (SQLException mSQLException) {
                        throw mSQLException;
                    }
                    Toast.makeText(this, "Selected FX", Toast.LENGTH_SHORT).show();
                }
                else if(!sportsCheckBox.isChecked()){
                    SQLiteDatabase db = mDBHelper.getWritableDatabase();
                    db.delete("selected_category", "user_name = ? AND category_id = ? ",
                            new String[] { "kavari", String.valueOf(5)});
                }
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId())
        {
            case R.id.sports_checkbox_layout:
            displayPopup(1);
            break;

            case R.id.crypto_checkbox_layout:
                displayPopup(2);
                break;
            case R.id.indie_checkbox_layout:
                displayPopup(3);
                break;

            case R.id.binary_checkbox_layout:
                displayPopup(4);
                break;

            case R.id.fx_checkbox_layout:
                displayPopup(5);
                break;
        }
        return false;
    }

    private void displayPopup(final int categoryValue) {
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

               SQLiteDatabase db = mDBHelper.getReadableDatabase();
                    Cursor c = db.rawQuery("SELECT * FROM selected_category WHERE category_id=? AND user_id=?",  new String[] { String.valueOf(categoryValue),String.valueOf(1234) });
                    if (c.moveToFirst()) {

                        do {
                            Log.i("TagCategory",""+c.getInt(c.getColumnIndex("category_id")));
                            Log.i("TagRefresh",""+c.getInt(c.getColumnIndex("refresh_time")));
                            timeNow=c.getInt(c.getColumnIndex("refresh_time"));
                            String strTime= String.valueOf(timeNow);
                            text_minutes.setText(strTime);
                        } while (c.moveToNext());
                    }


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
                Intent i=new Intent(RssFeedListActivity.this,RssFeedUrlActivity.class);
                i.putExtra("category",categoryValue);
                startActivity(i);
            }
        });

        deleteUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RssFeedListActivity.this,RssFeedDeleteActivity.class);
                i.putExtra("category",categoryValue);
                startActivity(i);
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

                    SQLiteDatabase sqldb = mDBHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put("refresh_time", integer);

                    // updating row
                    sqldb.update("selected_category", values, "user_id = ? AND category_id = ?", new String[] {String.valueOf(1234), String.valueOf(categoryValue)});
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

                    SQLiteDatabase sqldb = mDBHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put("refresh_time", integer);

                    // updating row
                    sqldb.update("selected_category", values, "user_id = ? AND category_id = ?", new String[] {String.valueOf(1234), String.valueOf(categoryValue)});

                }

            }

        });

        myDialog.show();
    }
}