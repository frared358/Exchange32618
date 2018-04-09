package com.affwl.exchange.fx;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;

/**
 * Created by user on 4/5/2018.
 */

public class BottomClickSession {
    Context context;
    String data;
    SharedPreferences sharedPreferences;
    public BottomClickSession(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void setValue(String val)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("val",val);
        editor.commit();

    }

    public String getValue()
    {
        String val=sharedPreferences.getString("val","");
        return val;
    }
}
