package com.affwl.exchange.news;

public class RssUrlDetails {
    String rssUrl;
    Integer intCheck;


    public RssUrlDetails(String rssUrl, Integer intCheck) {
        this.rssUrl = rssUrl;
        this.intCheck = intCheck;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public void setRssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public Integer getIntCheck() {
        return intCheck;
    }

    public void setIntCheck(Integer intCheck) {
        this.intCheck = intCheck;
    }
}
