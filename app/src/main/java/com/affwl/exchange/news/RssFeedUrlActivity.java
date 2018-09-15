package com.affwl.exchange.news;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssFeedUrlActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    EditText edit_add_url;
    ImageView img_add_url;
    ListView display_url_list;
    Button submit_urlList,cancel_urlList;
    ArrayAdapter itemsAdapter;
    ArrayList<String> myList=new ArrayList<String>();
    private EditText edit_url_entered;
    int categoryValue,selected_value;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed_url);

        edit_add_url=findViewById(R.id.edit_add_url);
        img_add_url=findViewById(R.id.img_add_url);
        display_url_list=findViewById(R.id.display_url_list);
        submit_urlList=findViewById(R.id.submit_urlList);
        cancel_urlList=findViewById(R.id.cancel_urlList);

        submit_urlList.setOnClickListener(this);
        cancel_urlList.setOnClickListener(this);
        img_add_url.setOnClickListener(this);

        display_url_list.setOnItemClickListener(this);
        display_url_list.setOnItemLongClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
                   /* Log.i("Tag",""+c.getInt(c.getColumnIndex("url_id")));
                    Log.i("Tag",""+c.getInt(c.getColumnIndex("selected_id")));
                    Log.i("Tag",""+c.getString(c.getColumnIndex("url_name")));*/
                    myList.add(c.getString(c.getColumnIndex("url_name")));
                } while (c.moveToNext());
            }
//            Log.i("my db"," "+mDb);

        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        itemsAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,myList);
        display_url_list.setAdapter(itemsAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_add_url:
                String url = edit_add_url.getText().toString();
                if(!url.equalsIgnoreCase("") && url!=null) {
                    try {
                        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                        Document doc = builder.parse(url); // url is your url for testing
                        if (doc.getDocumentElement().getNodeName().equalsIgnoreCase("rss"))
                        {
                            if (myList.size() < 5) {

                                myList.add(url);
                                itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
                                display_url_list.setAdapter(itemsAdapter);

                                edit_add_url.setError(null);
                                edit_add_url.setText("");
                                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                in.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                                try {

                                    SQLiteDatabase db = mDBHelper.getReadableDatabase();
                                    Cursor c = db.rawQuery("SELECT * FROM selected_category WHERE category_id=?", new String[]{String.valueOf(categoryValue)});
                                    if (c.moveToFirst()) {

                                        do {
//                                            Log.i("Tag", "" + c.getInt(c.getColumnIndex("selected_id")));
                                            selected_value = c.getInt(c.getColumnIndex("selected_id"));
                                        } while (c.moveToNext());
                                    }

                                    mDb = mDBHelper.getWritableDatabase();
                                    ContentValues values = new ContentValues();
                                    values.put("selected_id", selected_value);
                                    values.put("url_name", url);

                                    mDb.insert("category_url", null, values);
//                                    Log.i("my db", " " + mDb);

                                } catch (SQLException mSQLException) {
                                    throw mSQLException;
                                }
                            } else {
                                edit_add_url.setError("Not more than 5 Url allowed");
                            }
                    }
                    else {
                            diplayAlert();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        diplayAlert();
                    } catch (IOException e) {
                        e.printStackTrace();
                        diplayAlert();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    edit_add_url.setError("Enter Value First");
                }
                break;

            case R.id.submit_urlList:

                startActivity(new Intent(RssFeedUrlActivity.this,NewsActivity.class));
                break;

            case R.id.cancel_urlList:
                finish();
                break;

        }

    }

    private void diplayAlert() {

            TextView alert_title,alertMessage;
            final ImageView close_alert;
            Button ok_alert,cancel_alert;

            final Dialog myAlertDialog = new Dialog(RssFeedUrlActivity.this);
            myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myAlertDialog.setCanceledOnTouchOutside(false);
            myAlertDialog.setContentView(R.layout.alert_message_dts);

            alert_title = myAlertDialog.findViewById(R.id.alert_title);
            alertMessage=myAlertDialog.findViewById(R.id.alertMessage);
            close_alert=myAlertDialog.findViewById(R.id.close_alert);
            ok_alert=myAlertDialog.findViewById(R.id.ok_alert);
            cancel_alert=myAlertDialog.findViewById(R.id.cancel_alert);

            close_alert.setVisibility(View.GONE);
        cancel_alert.setVisibility(View.GONE);
        ok_alert.setText("OK");
        alert_title.setText("Alert");
        alertMessage.setText("Enter a valid URL");

            ok_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myAlertDialog.dismiss();
                }
            });
            myAlertDialog.show();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        showInputBox(myList.get(position),position);
    }

    private void showInputBox(final String oldItem, final int index) {
        final Dialog dialog=new Dialog(RssFeedUrlActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.news_options);
        TextView txtMessage=dialog.findViewById(R.id.txtMessage);
        txtMessage.setText("Update item");
        TextView text_view_option=dialog.findViewById(R.id.text_view_option);
        text_view_option.setText("Input Box");
        edit_url_entered=dialog.findViewById(R.id.edit_url_entered);
        edit_url_entered.setText(oldItem);
        Button btn_edit_url=dialog.findViewById(R.id.btn_edit_url);
        Button btn_delete_url=dialog.findViewById(R.id.btn_delete_url);

        LinearLayout news_option_layout=dialog.findViewById(R.id.news_option_layout);
        LinearLayout minutes_layout=dialog.findViewById(R.id.minutes_layout);
        final LinearLayout edit_url_layout=dialog.findViewById(R.id.edit_url_layout);
        ImageView close_news_option=dialog.findViewById(R.id.close_news_option);

        news_option_layout.setVisibility(View.GONE);
        minutes_layout.setVisibility(View.GONE);
        edit_url_layout.setVisibility(View.VISIBLE);

        close_news_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_delete_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.remove(index);

                itemsAdapter.notifyDataSetChanged();

                SQLiteDatabase db = mDBHelper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM selected_category WHERE category_id=? AND user_id=?",  new String[] { String.valueOf(categoryValue),String.valueOf(1234) });
                if (c.moveToFirst()) {

                    do {
//                        Log.i("Tag",""+c.getInt(c.getColumnIndex("selected_id")));
                        selected_value=c.getInt(c.getColumnIndex("selected_id"));
                    } while (c.moveToNext());
                }

                SQLiteDatabase deldb = mDBHelper.getWritableDatabase();
                deldb.delete("category_url", "url_name = ? AND selected_id = ?", new String[] { oldItem, String.valueOf(selected_value)});
                dialog.dismiss();

            }
        });

        btn_edit_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rssFeedStr= edit_url_entered.getText().toString();

                myList.set(index,rssFeedStr);
                itemsAdapter.notifyDataSetChanged();

                SQLiteDatabase db = mDBHelper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM selected_category WHERE category_id=? AND user_id=?",  new String[] { String.valueOf(categoryValue),String.valueOf(1234) });
                if (c.moveToFirst()) {

                    do {
//                        Log.i("Tag",""+c.getInt(c.getColumnIndex("selected_id")));
                        selected_value=c.getInt(c.getColumnIndex("selected_id"));
                    } while (c.moveToNext());
                }

                SQLiteDatabase sqldb = mDBHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("url_name", rssFeedStr);

                // updating row
                sqldb.update("category_url", values, "url_name = ?", new String[] { oldItem });

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, " "+myList.get(position), Toast.LENGTH_SHORT).show();
        return true;
    }
}
