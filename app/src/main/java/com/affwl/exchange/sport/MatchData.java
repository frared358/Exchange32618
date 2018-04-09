package com.affwl.exchange.sport;

/**
 * Created by user on 3/30/2018.
 */

public class MatchData {

    String runner,odd,stack,type,dateTime,marketName;

    MatchData(String runner,String odd,String stack,String type,String dateTime,String marketName){
        this.runner = runner;
        this.odd = odd;
        this.stack = stack;
        this.type = type;
        this.dateTime = dateTime;
        this.marketName = marketName;
    }
}
