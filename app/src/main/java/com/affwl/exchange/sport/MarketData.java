package com.affwl.exchange.sport;

/**
 * Created by user on 3/31/2018.
 */

public class MarketData {
    String RunnerName,Back,Lay,ChipsLay,ChipsBack,bfid;
    int MatchId,MarketId;
    String Exposer;

    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay,String bfid,int MatchId,int MarketId){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
        this.bfid = bfid;
        this.MatchId= MatchId;
        this.MarketId = MarketId;
    }

    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
    }


    //Fancy BET
    String yesRate,yesScore,noRate,noScore,ballStatus,book;
    int fancyId;
    public MarketData(String yesRate,String yesScore,String noRate,String noScore,String RunnerName,String ballStatus,String book,int fancyId){
        this.yesRate = yesRate;
        this.yesScore = yesScore;
        this.noRate = noRate;
        this.noScore = noScore;
        this.RunnerName = RunnerName;
        this.ballStatus = ballStatus;
        this.book = book;
        this.fancyId = fancyId;
    }


    // Book Making
    String bmBallStatus,bmBook,bmName;
    int bmId,bmBackPrice,bmBackSize,bmLayPrice;
    int bmLaySize,bmRunnerId;
    public MarketData(int bmId,int bmRunnerId,int bmBackPrice,int bmBackSize,int bmLayPrice,int bmLaySize,String bmName,String bmBallStatus,String bmBook){
        this.bmBackPrice = bmBackPrice;
        this.bmBackSize = bmBackSize;
        this.bmLayPrice = bmLayPrice;
        this.bmLaySize = bmLaySize;
        this.bmName = bmName;
        this.bmBallStatus = bmBallStatus;
        this.bmBook = bmBook;
        this.bmId = bmId;
        this.bmRunnerId = bmRunnerId;
    }


}
