package com.affwl.exchange.fx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affwl.exchange.R;

/**
 * Created by user on 1/24/2018.
 */

public class Fx_Trade_Fragment extends Fragment {



    @Nullable
    @Override   /** Right click - Generate - Override Method - slect onCreateView */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_fx_trade,null);


    }
    /** Right click - Generate - Override Method - slect onViewCreate */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        /** since it is a fragment we use view.findViewById */
//        view.findViewById (R.id.btn).setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText (getActivity (),"button clicked",Toast.LENGTH_SHORT).show ();
//            }
     //   });
    }

}
