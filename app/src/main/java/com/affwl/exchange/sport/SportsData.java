package com.affwl.exchange.sport;

/**
 * Created by user on 3/27/2018.
 */

public class SportsData {
    public String txtVMatchName,txtVMatchOdds;
    public String txtVSportName;
    public int txtVSportId;
    String matchDate,bfId,matchName,runner1Back,runner1Lay,runner1Name,runner2Back,runner2Lay,runner2Name,runner3Back,runner3Lay,runner3Name;

    public SportsData(String txtVMatchName){
        this.txtVMatchName = txtVMatchName;/*,String marketId,String matchDate,String matchId,String matchName,String runner1Back,String runner1Lay,String runner1Name,String runner2Back,String runner2Lay,String runner2Name
            ,String runner3Back,String runner3Lay,String runner3Name*/
    }

    public SportsData(String txtVSportName, int txtVSportId){
        this.txtVSportName = txtVSportName;
        this.txtVSportId = txtVSportId;
    }

    public void TournamentData(String txtVSportName, int txtVSportId){
        this.txtVSportName = txtVSportName;
        this.txtVSportId = txtVSportId;
    }

    int matchId,marketId;
    public SportsData(String txtVMatchName,String matchDate, String bfId, int matchId,int marketId){
        this.txtVMatchName = txtVMatchName;
        this.marketId = marketId;
        this.matchId = matchId;
        this.matchDate = matchDate;
        this.bfId = bfId;
    }

    public boolean selected;
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
