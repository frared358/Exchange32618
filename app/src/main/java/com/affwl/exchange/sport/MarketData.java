package com.affwl.exchange.sport;

/**
 * Created by user on 3/31/2018.
 */

public class MarketData {
    String RunnerName,Back,Lay,ChipsLay,ChipsBack,bfid;


    public MarketData(String RunnerName,String Back,String Lay,String ChipsBack, String ChipsLay,String bfid){
        this.RunnerName = RunnerName;
        this.Back = Back;
        this.Lay = Lay;
        this.ChipsBack = ChipsBack;
        this.ChipsLay = ChipsLay;
        this.bfid = bfid;
    }

}
