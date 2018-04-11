package com.affwl.exchange.sport;

/**
 * Created by user on 4/9/2018.
 */

public class Data {
    String marketId,matchId,matchName,bfId,matchDate,sportName;
    //Favourite Data List
    public Data(String marketId,String matchId,String matchName,String bfId,String matchDate,String sportName){
        this.marketId = marketId;
        this.matchId = matchId;
        this.matchName = matchName;
        this.bfId = bfId;
        this.matchDate = matchDate;
        this.sportName = sportName;
    }
}
