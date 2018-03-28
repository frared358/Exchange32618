package com.affwl.exchange.news;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.ArrayList;

public class RssFeedUrlActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText edit_add_url;
    ImageView img_add_url;
    ListView display_url_list;
    Button submit_urlList,cancel_urlList;
    ArrayList<String> urlArray=new ArrayList<String>();
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> myList=new ArrayList<String>();

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

       myList = (ArrayList<String>) getIntent().getSerializableExtra("urlArray");
        if(myList!=null) {
            itemsAdapter = new ArrayAdapter<String>(RssFeedUrlActivity.this, android.R.layout.simple_list_item_1, myList);
            display_url_list.setAdapter(itemsAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_add_url:
                String url = edit_add_url.getText().toString();
                if(!url.equalsIgnoreCase("") && url!=null) {
                    if (urlArray.size() < 5) {

                        urlArray.add(url);
                        itemsAdapter = new ArrayAdapter<String>(RssFeedUrlActivity.this, android.R.layout.simple_list_item_1, urlArray);
                        display_url_list.setAdapter(itemsAdapter);
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


            case R.id.submit_urlList:

                Intent intent=new Intent(RssFeedUrlActivity.this,NewsActivity.class);
                intent.putExtra("urlArray",urlArray);

               /* Intent selfIntent=new Intent(RssFeedUrlActivity.this,NewsActivity.class);
                selfIntent.putExtra("urlArray",urlArray);*/

                startActivity(intent);
                break;

            case R.id.cancel_urlList:
                finish();
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        ((TextView)view).setText("Change now");

        showInputBox(urlArray.get(position),position);
    }

    private void showInputBox(String oldItem, final int index) {
        final Dialog dialog=new Dialog(RssFeedUrlActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.news_options);
        TextView txtMessage=dialog.findViewById(R.id.txtMessage);
        txtMessage.setText("Update item");
        txtMessage.setTextColor(Color.parseColor("#fff000"));
        final EditText edit_url_entered=dialog.findViewById(R.id.edit_url_entered);
        edit_url_entered.setText(oldItem);
        Button btn_edit_url=dialog.findViewById(R.id.btn_edit_url);
        Button btn_delete_url=dialog.findViewById(R.id.btn_delete_url);

        LinearLayout news_option_layout=dialog.findViewById(R.id.news_option_layout);
        LinearLayout minutes_layout=dialog.findViewById(R.id.minutes_layout);
        LinearLayout edit_url_layout=dialog.findViewById(R.id.edit_url_layout);
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
                urlArray.remove(index);
                itemsAdapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        btn_edit_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlArray.set(index,edit_url_entered.getText().toString());
                itemsAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
