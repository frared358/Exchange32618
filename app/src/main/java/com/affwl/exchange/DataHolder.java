package com.affwl.exchange;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.LinearLayout;

import java.util.ArrayList;

import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by Admin on 10/24/2017.
 */

public class DataHolder {
    public static int navigationForTab=0;
    public static String LOGIN_TOKEN;
    public static Double STACK_VALUE;

    public static HubConnection _connection;
    public static HubProxy _hub;

    public static String SPORT_NAME;
    public static String TOURNAMENT_NAME;
    public static String MATCH_NAME;

//  Stack Share Prefrences
    private static SharedPreferences getPrefSTACK(Context context) {
        return context.getSharedPreferences("PREF_STACK", Context.MODE_PRIVATE);
    }

    public static String getSTACK(Context context,String Key) {
        return getPrefSTACK(context).getString(Key, "");
    }

    public static void setSTACK(Context context,String Key, String input) {
        SharedPreferences.Editor editor = getPrefSTACK(context).edit();
        editor.putString(Key, input);
        editor.commit();
    }

    //Data Share Prefrences
    private static SharedPreferences getPrefData(Context context) {
        return context.getSharedPreferences("PREF_DATA", Context.MODE_PRIVATE);
    }

    public static String getData(Context context,String Key) {
        return getPrefData(context).getString(Key, "");
    }

    public static void setData(Context context,String Key, String input) {
        SharedPreferences.Editor editor = getPrefData(context).edit();
        editor.putString(Key, input);
        editor.commit();
    }

    public  static boolean SIGNALR=false;

    public static double increment(double val){

        if(val >=0.00 && val <1.00){
            val = val+0.02;
        }
        else if(val >=1.0 && val <10.0){
            val = val+0.5;
        }
        else if(val >=10.0 && val <100.0) {
            val = val+5.0;
        }
        else if(val >=100.0 && val <1000.0) {
            val = val+20.0;
        }
        else if(val >=1000.0 ) {
            val = 0;
        }
        return val;
    }

    public static double decrement(double val){

        if(val >0.05 && val <1.00){
            val = val-0.02;
        }
        else if(val >=1.0 && val <10.0){
            val = val-0.5;
        }
        else if(val >=10.0 && val <100.0) {
            val = val-5.0;
        }
        else if(val >=10.0 && val <1000.0) {
            val = val-5.0;
        }
        else if(val <= 0.05) {
            val = 0;
        }
        return val;
    }

    public static double profit(double odd,double stack){
        return (odd-1)*stack;
    }

}
