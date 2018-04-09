package com.affwl.exchange.sport;

/**
 * Created by user on 3/27/2018.
 */

public class SportsData {
    public String txtVMatchName,txtVMatchOdds;
    public String txtVSportName;
    public int txtVSportId;
    String matchDate,runner1Back,runner1Lay,runner1Name,runner2Back,runner2Lay,runner2Name,runner3Back,runner3Lay,runner3Name;


    public SportsData(String txtVMatchName){
        this.txtVMatchName = txtVMatchName;/*,String marketId,String matchDate,String matchId,String matchName,String runner1Back,String runner1Lay,String runner1Name,String runner2Back,String runner2Lay,String runner2Name
            ,String runner3Back,String runner3Lay,String runner3Name*/
    }

    //SPORT NAMES like CRICKET & Football
    public SportsData(String txtVSportName, int txtVSportId){
        this.txtVSportName = txtVSportName;
        this.txtVSportId = txtVSportId;
    }

    //Tournament NAMES like WorldCup & IPL
    String txtVTournamentName;
    int txtVTournamentId;
    public SportsData(String txtVTournamentName, int txtVTournamentId,int txtVSportId){
        this.txtVTournamentName = txtVTournamentName;
        this.txtVTournamentId = txtVTournamentId;
        this.txtVSportId = txtVSportId;
    }

    //Match NAMES like India vs Pakistan
    String matchName,bfid,date;
    int matchId;
    public SportsData(String matchName, String bfid,int matchId, String date){
        this.matchName = matchName;
        this.bfid = bfid;
        this.matchId = matchId;
        this.date = date;
    }


    String matchOddName,MatchOddBfId;
    int matchOddId,marketOddId;
    public SportsData(int matchOddId, int marketOddId,String MatchOddBfId,String matchOddName){
        this.matchOddId = matchOddId;
        this.matchOddName = matchOddName;
        this.MatchOddBfId = MatchOddBfId;
        this.marketOddId = marketOddId;
    }

    SportsData(int matchId){
        this.matchId = matchId;
    }



//    int matchId,marketId;
//    public SportsData(String txtVMatchName,String matchDate, String bfId, int matchId,int marketId){
//        this.txtVMatchName = txtVMatchName;
//        this.marketId = marketId;
//        this.matchId = matchId;
//        this.matchDate = matchDate;
//        this.bfId = bfId;
//    }

    public boolean selected;
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
