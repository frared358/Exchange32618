package com.affwl.exchange;

import android.content.Context;
import android.content.SharedPreferences;

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

}
