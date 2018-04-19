package com.affwl.exchange.fx;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.affwl.exchange.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**  make sure select android.support.v4.app.Fragment   */
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

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.activity_fx   , container, false);
        RecyclerView programingList = (RecyclerView) v.findViewById(R.id.programingList);
        // RecyclerView programingList1=(RecyclerView) findViewById(R.id.programingList1);
        //how to position items in PecyclerView
        programingList.setLayoutManager(new LinearLayoutManager(getContext()));
        //programingList1.setLayoutManager(new LinearLayoutManager(this));
        //set adapter
        String[] currency = {"INR", "USD", "URO", "AFN", "EUR", "AOA", "XCD", "ARS", "AMD", "SHP", "ARS", "AMD", "SHP", "ARS", "AMD", "SHP", "ARS", "AMD", "SHP"};
        // String[] rates={"1.98787","1.37867","1.98989","11.9878","87.0988","6.98789","55.4376","76.3388","2.37636","77.9988"};
        //String[] rates2={"2.98787","1.37867","1.98989","11.9878","87.0988","6.98789","55.4376","76.3388","2.37636","77.9988"};
        // String[] rates2={"2","1","1","11","87","6","55","88","2","77"};
        programingList.setAdapter(new Fx_ProgramingAdapter(currency, getContext())); /**context this for rcyclerview click*/
        // programingList1.setAdapter(new ProgramingAdapter(rates));
        //programingList.setAdapter(new ProgramingAdapter(currency));
        programingList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                Toast.makeText (getContext (),"dd",Toast.LENGTH_SHORT).show ();
//                return  true;
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                RecyclerView.Adapter adapter = rv.getAdapter();
                adapter.getItemCount();
                Toast.makeText(getContext(), adapter.getItemCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return  v;

    }


}
