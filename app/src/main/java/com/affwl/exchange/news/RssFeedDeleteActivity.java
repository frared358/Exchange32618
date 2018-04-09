package com.affwl.exchange.news;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RssFeedDeleteActivity extends AppCompatActivity {

    EditText edit_add_url;
    ImageView img_add_url,delete_url_img;
    ListView display_url_list;
    Button submit_urlList,cancel_urlList;
    List<RssUrlDetails> rssUrlDetailsList=new ArrayList<>();
    RssUrlAdapter rssUrlAdapter;
    ArrayAdapter itemsAdapter;
    ArrayList<String> myList=new ArrayList<String>();
    private EditText edit_url_entered;
    int categoryValue,selected_value;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_rss_feed);

        Toolbar toolbar = findViewById(R.id.delete_toolbar);
        setSupportActionBar(toolbar);

        display_url_list=findViewById(R.id.delete_list_rss);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        categoryValue = getIntent().getIntExtra("category",0);
        try {
            SQLiteDatabase db = mDBHelper.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM category_url a INNER JOIN selected_category b ON a.selected_id=b.selected_id WHERE b.category_id=?",  new String[] { String.valueOf(categoryValue) });

            if (c.moveToFirst()) {

                do {
                  /*  Log.i("Tag",""+c.getInt(c.getColumnIndex("url_id")));
                    Log.i("Tag",""+c.getInt(c.getColumnIndex("selected_id")));
                    Log.i("Tag",""+c.getString(c.getColumnIndex("url_name")));*/
                    myList.add(c.getString(c.getColumnIndex("url_name")));
                } while (c.moveToNext());
            }
//            Log.i("my db"," "+mDb);

        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        for(int i=0;i<myList.size();i++) {
            RssUrlDetails rssUrl = new RssUrlDetails(myList.get(i), 0);
            rssUrlDetailsList.add(rssUrl);
        }
        rssUrlAdapter = new RssUrlAdapter(RssFeedDeleteActivity.this,rssUrlDetailsList);
        display_url_list.setAdapter(rssUrlAdapter);



 /*      myList = (ArrayList<String>) getIntent().getSerializableExtra("urlArray");
        if(myList!=null) {
            itemsAdapter = new ArrayAdapter<String>(RssFeedUrlActivity.this, android.R.layout.simple_list_item_1, myList);
            display_url_list.setAdapter(itemsAdapter);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete_news, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.news_delete:
                diplayAlert();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void diplayAlert() {
        
            TextView alert_title,alertMessage;
            final ImageView close_alert;
            Button ok_alert,cancel_alert;

            final Dialog myAlertDialog = new Dialog(RssFeedDeleteActivity.this);
            myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myAlertDialog.setCanceledOnTouchOutside(false);
            myAlertDialog.setContentView(R.layout.alert_message_dts);

        alert_title = myAlertDialog.findViewById(R.id.alert_title);
        alertMessage=myAlertDialog.findViewById(R.id.alertMessage);
        close_alert=myAlertDialog.findViewById(R.id.close_alert);
        ok_alert=myAlertDialog.findViewById(R.id.ok_alert);
        cancel_alert=myAlertDialog.findViewById(R.id.cancel_alert);

        String stringUrl = "";
        String extension = "";

        for(int i=0;i<rssUrlDetailsList.size();i++){
            if (rssUrlDetailsList.get(i).getIntCheck() == 1) {
                myList.add(rssUrlDetailsList.get(i).getRssUrl());
            }


            if(myList.size()>1) {
                alertMessage.append(myList.size()+"");
//                Log.i("Alert check"," "+alertMessage.getText().toString());
            }
           else {
                String url=myList.get(i);
                int leng=url.length();

                    if (leng > 20) {
                        stringUrl = url.substring(0, leng / 2) + "...";
                        extension = url.substring(url.lastIndexOf(".") + 1);
//                        alertMessage.append(stringUrl + extension + " , ");
//                        Log.i("Alert check"," "+alertMessage.getText().toString());

                    }
                }
                }
                myList.clear();


        cancel_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myAlertDialog.dismiss();
                }
            });

        close_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();
            }
        });


        ok_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = (rssUrlDetailsList.size() - 1); i >= 0; i--) {
                    if(rssUrlDetailsList.get(i).getIntCheck()==1)
                    {
                        SQLiteDatabase db = mDBHelper.getReadableDatabase();
                        Cursor c = db.rawQuery("SELECT * FROM selected_category WHERE category_id=? AND user_id=?",  new String[] { String.valueOf(categoryValue),String.valueOf(1234) });
                        if (c.moveToFirst()) {

                            do {
//                                Log.i("Tag",""+c.getInt(c.getColumnIndex("selected_id")));
                                selected_value=c.getInt(c.getColumnIndex("selected_id"));
                            } while (c.moveToNext());
                        }

                        SQLiteDatabase deldb = mDBHelper.getWritableDatabase();
                        deldb.delete("category_url", "url_name = ? AND selected_id = ?", new String[] { rssUrlDetailsList.get(i).getRssUrl(), String.valueOf(selected_value)});

                        rssUrlDetailsList.remove(i);
                    }
                }
                rssUrlAdapter = new RssUrlAdapter(RssFeedDeleteActivity.this,rssUrlDetailsList);
                display_url_list.setAdapter(rssUrlAdapter);

                myAlertDialog.dismiss();
            }
        });
            myAlertDialog.show();
        
    }

}
