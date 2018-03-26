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
//    @Nullable
//    @Override   /** Right click - Generate - Override Method - slect onCreateView */
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate (R.layout.fragment_fx_quotes,null);
//    }
//    /** Right click - Generate - Override Method - slect onViewCreate */
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated (view, savedInstanceState);
//
//        /** since it is a fragment we use view.findViewById */
//        view.findViewById (R.id.btn).setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText (getActivity (),"button clicked",Toast.LENGTH_SHORT).show ();
//            }
//        });
//    }


}
