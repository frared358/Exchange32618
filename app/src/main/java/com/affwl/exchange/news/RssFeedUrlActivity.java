package com.affwl.exchange.news;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    ArrayList<String> urlArray=new ArrayList<String>();
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
                    if (urlArray.size() < 5) {

                        RssUrlDetails rssUrl=new RssUrlDetails(url, 0);
                        rssUrlDetailsList.add(rssUrl);

                       urlArray.add(url);
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

                            for(int i = (rssUrlDetailsList.size() - 1); i >= 0; i--) {
                            Log.i("Size",""+rssUrlDetailsList.size());
                            Log.i("List"," "+rssUrlDetailsList.get(i).getRssUrl()+" "+rssUrlDetailsList.get(i).getIntCheck());
                            if(rssUrlDetailsList.get(i).getIntCheck()==1)
                            {
                                Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
                                rssUrlDetailsList.remove(i);

                            }
                        }
                rssUrlAdapter = new RssUrlAdapter(RssFeedUrlActivity.this,rssUrlDetailsList);
                display_url_list.setAdapter(rssUrlAdapter);
                break;


            case R.id.submit_urlList:

                Intent intent=new Intent(RssFeedUrlActivity.this,NewsActivity.class);
                intent.putExtra("urlArray",urlArray);

                startActivity(intent);
                break;

            case R.id.cancel_urlList:
                finish();
                break;

        }

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
                rssUrlAdapter.notifyDataSetChanged();
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

        Toast.makeText(this, " "+urlArray.get(position), Toast.LENGTH_SHORT).show();
        return true;
    }
}
