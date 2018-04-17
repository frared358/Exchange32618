package com.affwl.exchange.sport;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.google.gson.JsonElement;

import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;

import static com.affwl.exchange.DataHolder._connection;
import static com.affwl.exchange.DataHolder._hub;

/**
 * Created by user on 4/13/2018.
 */

public class FancyBookMakingService extends IntentService {


    public FancyBookMakingService() {
        super("MyFancyBookMakingServices");
    }

    String matchId;
    @Override
    protected void onHandleIntent(@Nullable Intent intentS) {
        try {

                matchId = intentS.getStringExtra("matchId");

                Toast.makeText(this, ""+matchId, Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(1000);

            Intent intentFBM = new Intent(DataHolder.ACTION_SEND_FANCY_BOOKMAKING);
            String result = DataHolder.getApi("http://173.212.248.188/pclient/Prince.svc/Data/FancyData?mtid="+matchId);
            intentFBM.putExtra(DataHolder.keyFANCY_BOOKMAKING, result);
            Log.i("TAAG2",intentFBM+"fvbkdfb2"+result);
            sendBroadcast(intentFBM);

        } catch (InterruptedException e) {
            Log.i("TGH",""+e);
            e.printStackTrace();
        }
    }
}
