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
        this.bfid = bfid;
    }

}
