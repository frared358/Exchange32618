package com.affwl.exchange.news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.List;

/**
 * Created by user on 3/28/2018.
 */

public class NewsAdapter extends BaseAdapter{

    Context context;
    List<NewsItemDetails> newsItemDetailsList;

    public NewsAdapter(Context context, List<NewsItemDetails> newsItemDetailsList) {
        this.context = context;
        this.newsItemDetailsList = newsItemDetailsList;
    }

    @Override
    public int getCount() {
        return newsItemDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItemDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return newsItemDetailsList.indexOf(getItem(position));
    }

    private class NewsHolder{

        TextView tv_rss_headlines,tv_rss_dateTime,tv_rss_article;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder newsHolder=null;

        LayoutInflater newsInflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView ==null)
        {
            convertView=newsInflater.inflate(R.layout.news_item_layout,null);
            newsHolder=new NewsHolder();

            newsHolder.tv_rss_headlines=convertView.findViewById(R.id.tv_rss_headlines);
            newsHolder.tv_rss_dateTime=convertView.findViewById(R.id.tv_rss_dateTime);
            newsHolder.tv_rss_article=convertView.findViewById(R.id.tv_rss_article);

            NewsItemDetails newsItemDetails=newsItemDetailsList.get(position);

            newsHolder.tv_rss_headlines.setText(newsItemDetails.getRssHeadlines());
            newsHolder.tv_rss_article.setText(newsItemDetails.getRssArticle());
            newsHolder.tv_rss_dateTime.setText(newsItemDetails.getRssDateTime());

            convertView.setTag(newsHolder);

        }
        else {
            newsHolder=(NewsHolder) convertView.getTag();

        }
        return convertView;

    }
}
