package com.affwl.exchange.sport;

import android.util.Log;

/**
 * Created by user on 3/31/2018.
 */

public class MarketData {
    String RunnerName,Back,Lay,ChipsLay,ChipsBack,bfid,Back2,Lay2,Back3,Lay3;
    String ChipsLay2,ChipsBack2,ChipsLay3,ChipsBack3;

    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay,String bfid){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
        this.bfid = bfid;
    }

    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay,String bfid,String Back2,String Lay2,String Back3,String Lay3,String ChipsBack2, String ChipsLay2,String ChipsBack3, String ChipsLay3){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.Back2 = Back2;
        this.Lay2 = Lay2;
        this.Back3 = Back3;
        this.Lay3 = Lay3;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
        this.ChipsBack2 = ChipsBack2;
        this.ChipsLay2 = ChipsLay2;
        this.ChipsBack3 = ChipsBack3;
        this.ChipsLay3 = ChipsLay3;
        this.bfid = bfid;
    }

    /*
    int MatchId,MarketId;
    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay,String bfid,int MatchId,int MarketId){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
        this.bfid = bfid;
        this.MatchId= MatchId;
        this.MarketId = MarketId;
    }*/

    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay,String Back2,String Lay2,String Back3,String Lay3,String ChipsBack2, String ChipsLay2,String ChipsBack3, String ChipsLay3){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.Back2 = Back2;
        this.Lay2 = Lay2;
        this.Back3 = Back3;
        this.Lay3 = Lay3;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
        this.ChipsBack2 = ChipsBack2;
        this.ChipsLay2 = ChipsLay2;
        this.ChipsBack3 = ChipsBack3;
        this.ChipsLay3 = ChipsLay3;
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
    int fancyId,matchId;
    public MarketData(String yesRate,String yesScore,String noRate,String noScore,String RunnerName,String ballStatus,String book,int fancyId,int matchId){
        this.yesRate = yesRate;
        this.yesScore = yesScore;
        this.noRate = noRate;
        this.noScore = noScore;
        this.RunnerName = RunnerName;
        this.ballStatus = ballStatus;
        this.book = book;
        this.fancyId = fancyId;
        this.matchId = matchId;
    }


    // Book Making
    String bmBallStatus,bmBook,bmName;
    String bmLaySize,bmBackPrice,bmBackSize,bmLayPrice;
    int bmRunnerId,bmId;
    public MarketData(int bmId,int bmRunnerId,String bmBackPrice,String bmLayPrice,String bmBackSize,String bmLaySize,String bmName,String bmBallStatus,String bmBook){

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
