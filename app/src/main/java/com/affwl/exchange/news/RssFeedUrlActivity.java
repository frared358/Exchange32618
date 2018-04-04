package com.affwl.exchange.news;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;

public class RssFeedUrlActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    EditText edit_add_url;
    ImageView img_add_url,delete_url_img;
    ListView display_url_list;
    Button submit_urlList,cancel_urlList;
    List<RssUrlDetails> rssUrlDetailsList=new ArrayList<>();
    RssUrlAdapter rssUrlAdapter;
    ArrayAdapter itemsAdapter;
    ArrayList<String> myList=new ArrayList<String>();
    private EditText edit_url_entered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed_url);

        edit_add_url=findViewById(R.id.edit_add_url);
        img_add_url=findViewById(R.id.img_add_url);
        delete_url_img=findViewById(R.id.delete_url_img);
        display_url_list=findViewById(R.id.display_url_list);
        submit_urlList=findViewById(R.id.submit_urlList);
        cancel_urlList=findViewById(R.id.cancel_urlList);

        submit_urlList.setOnClickListener(this);
        cancel_urlList.setOnClickListener(this);
        img_add_url.setOnClickListener(this);
        delete_url_img.setOnClickListener(this);

        display_url_list.setOnItemClickListener(this);
        display_url_list.setOnItemLongClickListener(this);




 /*      myList = (ArrayList<String>) getIntent().getSerializableExtra("urlArray");
        if(myList!=null) {
            itemsAdapter = new ArrayAdapter<String>(RssFeedUrlActivity.this, android.R.layout.simple_list_item_1, myList);
            display_url_list.setAdapter(itemsAdapter);
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_add_url:
                String url = edit_add_url.getText().toString();
                if(!url.equalsIgnoreCase("") && url!=null) {
                    if (rssUrlDetailsList.size() < 5) {

                        RssUrlDetails rssUrl=new RssUrlDetails(url, 0);
                        rssUrlDetailsList.add(rssUrl);

                        rssUrlAdapter = new RssUrlAdapter(RssFeedUrlActivity.this,rssUrlDetailsList);
                        display_url_list.setAdapter(rssUrlAdapter);

                        edit_add_url.setError(null);
                        edit_add_url.setText("");
                        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                    } else {
                        edit_add_url.setError("Not more than 5 Url allowed");
                    }
                }
                else {
                    edit_add_url.setError("Enter Value First");
                }
                break;

            case R.id.delete_url_img:
                        diplayAlert();
                break;


            case R.id.submit_urlList:

                for(int i=0;i<rssUrlDetailsList.size();i++)
                {
                    myList.add(rssUrlDetailsList.get(i).getRssUrl());
                }
                Intent intent=new Intent(RssFeedUrlActivity.this,NewsActivity.class);
                intent.putExtra("urlArray", myList);

                startActivity(intent);
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

        String stringUrl = "";
        String extension = "";

        for(int i=0;i<rssUrlDetailsList.size();i++){
            if (rssUrlDetailsList.get(i).getIntCheck() == 1) {
                myList.add(rssUrlDetailsList.get(i).getRssUrl());
            }


            if(myList.size()>1) {
                alertMessage.append(myList.size()+"");
                Log.i("Alert check"," "+alertMessage.getText().toString());
            }
           else {
                String url=myList.get(i);
                int leng=url.length();

                    if (leng > 20) {
                        stringUrl = url.substring(0, leng / 2) + "...";
                        extension = url.substring(url.lastIndexOf(".") + 1);
//                        alertMessage.append(stringUrl + extension + " , ");
                        Log.i("Alert check"," "+alertMessage.getText().toString());

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
                        rssUrlDetailsList.remove(i);

                    }
                }
                rssUrlAdapter = new RssUrlAdapter(RssFeedUrlActivity.this,rssUrlDetailsList);
                display_url_list.setAdapter(rssUrlAdapter);

                myAlertDialog.dismiss();
            }
        });
            myAlertDialog.show();
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        showInputBox(rssUrlDetailsList.get(position).getRssUrl(),position);
    }

    private void showInputBox(String oldItem, final int index) {
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
                rssUrlDetailsList.remove(index);

                rssUrlAdapter = new RssUrlAdapter(RssFeedUrlActivity.this,rssUrlDetailsList);
                display_url_list.setAdapter(rssUrlAdapter);

                dialog.dismiss();

            }
        });

        btn_edit_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rssFeedStr= edit_url_entered.getText().toString();
                RssUrlDetails rssUrl=new RssUrlDetails(rssFeedStr,0);
                rssUrlDetailsList.set(index,rssUrl);

                rssUrlAdapter = new RssUrlAdapter(RssFeedUrlActivity.this,rssUrlDetailsList);
                display_url_list.setAdapter(rssUrlAdapter);

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, " "+rssUrlDetailsList.get(position).getRssUrl(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
