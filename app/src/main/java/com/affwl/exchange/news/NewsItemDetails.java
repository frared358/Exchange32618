package com.affwl.exchange.news;

import java.util.Date;

/**
 * Created by user on 3/28/2018.
 */

public class NewsItemDetails implements Comparable<NewsItemDetails>  {
    String RssHeadlines;
    Date RssDateTime;
    String RssArticle;
    String RssLinks;


    public NewsItemDetails(String rssHeadlines, Date rssDateTime, String rssLinks, String rssArticle) {
        RssHeadlines = rssHeadlines;
        RssDateTime = rssDateTime;
        RssArticle = rssArticle;
        RssLinks = rssLinks;
    }

    public NewsItemDetails() {
        RssLinks=null;
        RssHeadlines=null;
        RssArticle=null;
        RssDateTime=null;
    }

    public String getRssHeadlines() {
        return RssHeadlines;
    }

    public void setRssHeadlines(String rssHeadlines) {
        RssHeadlines = rssHeadlines;
    }

    public Date getRssDateTime() {
        return RssDateTime;
    }

    public void setRssDateTime(Date rssDateTime) {
        RssDateTime = rssDateTime;
    }

    public String getRssArticle() {
        return RssArticle;
    }

    public void setRssArticle(String rssArticle) {
        RssArticle = rssArticle;
    }


    public String getRssLinks() {
        return RssLinks;
    }

    public void setRssLinks(String rssLinks) {
        RssLinks = rssLinks;
    }

    @Override
    public int compareTo(NewsItemDetails o) {
        return getRssDateTime().compareTo(o.getRssDateTime());
    }
}
