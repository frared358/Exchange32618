package com.affwl.exchange.crypto;


public class DataHolderBinance {

    public static boolean HOME = false;
    public static boolean MARKET = true;
    public static boolean TRADE = true;
    public static boolean FUND = true;
    public static boolean ACCOUNT = true;

    public static void bottomClick(boolean home,boolean market,boolean trade,boolean fund,boolean account){
        HOME = home;
        MARKET = market;
        TRADE = trade;
        FUND = fund;
        ACCOUNT = account;
    }
}
