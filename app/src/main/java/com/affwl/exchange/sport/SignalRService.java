package com.affwl.exchange.sport;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.ConnectionState;
import microsoft.aspnet.signalr.client.MessageReceivedHandler;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.StateChangedCallback;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;

import static com.affwl.exchange.DataHolder._connection;
import static com.affwl.exchange.DataHolder._hub;

/**
 * Created by user on 4/13/2018.
 */

public class SignalRService extends IntentService {

    SignalRFuture<Void> awaitConnection;
    public SignalRService() {
        super("MyServices");
    }

    String BfId,HubAddress;
    @Override
    protected void onHandleIntent(@Nullable Intent intentS) {
        try {
            if (intentS != null) {
                BfId = intentS.getStringExtra("BfId");
                HubAddress = intentS.getStringExtra("HubAddress");
                Log.i("TAGS",BfId+" "+HubAddress);
            }
            Platform.loadPlatformComponent( new AndroidPlatformComponent() );
            //_connection=new HubConnection("http://178.238.236.221:10800");
            //_hub=_connection.createHubProxy("BetAngelHub");
            _connection=new HubConnection(HubAddress);
            _hub=_connection.createHubProxy("RunnersHub");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            awaitConnection = _connection.start();
            awaitConnection.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        _hub.invoke("SubscribeMarket",BfId);

        try{
            _connection.received(new MessageReceivedHandler() {
                @Override
                public void onMessageReceived(final JsonElement json) {

                    try {
                        Thread.sleep(10);
                        Intent intentSignalR = new Intent(DataHolder.ACTION_SEND_ACTIVE);
                        intentSignalR.putExtra(DataHolder.keySIGNALR, json.toString());
                        sendBroadcast(intentSignalR);
                    } catch (InterruptedException e) {
                        Log.i("TGH",""+e);
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
