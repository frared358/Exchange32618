package com.affwl.exchange.news;

/**
 * Created by user on 3/28/2018.
 */

public class NewsItemDetails {
    String RssHeadlines;
    String RssDateTime;
    String RssArticle;

    public NewsItemDetails(String rssHeadlines, String rssDateTime, String rssArticle) {
        RssHeadlines = rssHeadlines;
        RssDateTime = rssDateTime;
        RssArticle = rssArticle;
    }

    public String getRssHeadlines() {
        return RssHeadlines;
    }

    public void setRssHeadlines(String rssHeadlines) {
        RssHeadlines = rssHeadlines;
    }

    public String getRssDateTime() {
        return RssDateTime;
    }

    public void setRssDateTime(String rssDateTime) {
        RssDateTime = rssDateTime;
    }

    public String getRssArticle() {
        return RssArticle;
    }

    public void setRssArticle(String rssArticle) {
        RssArticle = rssArticle;
    }
}
