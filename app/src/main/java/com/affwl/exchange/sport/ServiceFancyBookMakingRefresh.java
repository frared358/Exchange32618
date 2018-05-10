package com.affwl.exchange.sport;

import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;

import java.util.Timer;
import java.util.TimerTask;


public class ServiceFancyBookMakingRefresh extends Service {

    public static final int notify = 5000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new
        mTimer.scheduleAtFixedRate(new TimeDisplay(), 0, notify);   //Schedule task
    }

    int matchId;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        matchId = intent.getIntExtra("matchId",0);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();    //For Cancel Timer
    }


    //class TimeDisplay for handling task
    class TimeDisplay extends TimerTask {
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    new getFBMAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/FancyData?mtid="+matchId);
                }
            });
        }
    }

    private class getFBMAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            //result = DataHolder.getApi("http://173.212.248.188/pclient/Prince.svc/Data/FancyData?mtid="+matchId);
            Intent intentFBM = new Intent(DataHolder.ACTION_SEND_FANCY_BOOKMAKING);
            intentFBM.putExtra(DataHolder.keyFANCY_BOOKMAKING, result);
            sendBroadcast(intentFBM);
        }
    }
}
