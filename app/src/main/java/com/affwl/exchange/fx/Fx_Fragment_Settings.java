package com.affwl.exchange.fx;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.affwl.exchange.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by user on 1/25/2018.
 */

public class Fx_Fragment_Settings extends Fragment {

    @Nullable
    @Override
    /** Right click - Generate - Override Method - slect onCreateView */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_fx_settings, null);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Oswald-Stencbab.ttf")
                .setFontAttrId(R.attr.fontPath).build());

        return v;
    }

/*
    @Override
    protected void attachBaseContext(Context newBase) {
        View.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/
}

