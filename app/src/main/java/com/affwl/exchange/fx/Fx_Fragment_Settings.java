package com.affwl.exchange.fx;

import android.app.Dialog;
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

/**
 * Created by user on 1/25/2018.
 */

public class Fx_Fragment_Settings extends Fragment {

    @Nullable
    @Override
    /** Right click - Generate - Override Method - slect onCreateView */ public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fx_settings, null);
        return v;
    }
}