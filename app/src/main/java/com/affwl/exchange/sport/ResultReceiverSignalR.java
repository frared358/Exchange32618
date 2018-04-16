package com.affwl.exchange.sport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by user on 4/14/2018.
 */

public class ResultReceiverSignalR extends ResultReceiver {
    private static Receiver mReceiver;

    public ResultReceiverSignalR(Handler handler) {
        super(handler);
    }


    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);

    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

}
