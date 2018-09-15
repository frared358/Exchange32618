package com.affwl.exchange.news;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.List;

public class RssUrlAdapter extends BaseAdapter {

    Context context;
    List<RssUrlDetails> rssUrlDetailsList;
    RssUrlHolder rssUrlHolder;

    public RssUrlAdapter(Context context, List<RssUrlDetails> rssUrlDetailsList) {
        this.context = context;
        this.rssUrlDetailsList = rssUrlDetailsList;
    }

    @Override
    public int getCount() {
        return rssUrlDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return rssUrlDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rssUrlDetailsList.indexOf(getItem(position));
    }

    public class RssUrlHolder{

        TextView tv_rss_url;
        CheckBox rssFeedCheckBox;
        LinearLayout custom_rss_url_layout;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RssUrlHolder rssUrlHolder=null;

        LayoutInflater newsInflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView ==null)
        {
            rssUrlHolder=new RssUrlHolder();
            convertView=newsInflater.inflate(R.layout.rss_url_layout,null);

            rssUrlHolder.tv_rss_url=convertView.findViewById(R.id.tv_rss_url);
            rssUrlHolder.rssFeedCheckBox=convertView.findViewById(R.id.rssFeedCheckBox);
            rssUrlHolder.custom_rss_url_layout=convertView.findViewById(R.id.custom_rss_url_layout);

            final RssUrlDetails rssUrlDetails=rssUrlDetailsList.get(position);

            rssUrlHolder.tv_rss_url.setText(rssUrlDetails.getRssUrl());
            if(rssUrlDetails.getIntCheck()==1)
            {
                rssUrlHolder.rssFeedCheckBox.setTag(Integer.valueOf(position));
                rssUrlHolder.rssFeedCheckBox.setChecked(true);
            }
            else
            {
                rssUrlHolder.rssFeedCheckBox.setTag(Integer.valueOf(position));
                rssUrlHolder.rssFeedCheckBox.setChecked(false);
            }

            rssUrlHolder.rssFeedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {

                        rssUrlDetails.setIntCheck(1);
                    }
                    else
                    {
                        rssUrlDetails.setIntCheck(0);
                    }
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(rssUrlHolder);

        }
        else {
            rssUrlHolder=(RssUrlHolder) convertView.getTag();
        }
        return convertView;

    }
}
