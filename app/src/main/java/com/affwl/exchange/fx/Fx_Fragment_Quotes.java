package com.affwl.exchange.fx;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affwl.exchange.R;

/**  make sure select android.support.v4.app.Fragment  */
public class Fx_Fragment_Quotes extends BottomSheetDialogFragment {
    public Fx_Fragment_Quotes(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fx_quotes, container, false);
    }


}
