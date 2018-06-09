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

    //InPLay Data
    String inMatchName,inBfId,inMatchDate;
    int inMatchId,inMarketId,isMulti;
    public Data(int inMatchId, int inMarketId,String inBfId,String inMatchName,int isMulti,String inMatchDate){
        this.inMatchId = inMatchId;
        this.inMatchName = inMatchName;
        this.inBfId = inBfId;
        this.inMarketId = inMarketId;
        this.isMulti = isMulti;
        this.inMatchDate = inMatchDate;
    }

    //Bet History
    String betSelection,betOdd,betStake,betType,betPL;
    public Data(String betSelection, String betType, String betOdd,String betStake,String betPL){
        this.betSelection = betSelection;
        this.betType = betType;
        this.betOdd = betOdd;
        this.betStake = betStake;
        this.betPL = betPL;
    }
}
