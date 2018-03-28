package com.affwl.exchange.fx;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.ArrayList;

public class CustomSpinner extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spinner_menu, menu);

        return true;
    }



    public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    TextView output = null;
    CustomAdapter4_customspinner adapter;
    CustomSpinner activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        activity = this;

        Spinner SpinnerExample = (Spinner) findViewById(R.id.spinner);
        //output = (TextView) findViewById(R.id.output);

        setListData();

        Resources res = getResources();
        adapter = new CustomAdapter4_customspinner(activity, R.layout.spinner_rows, CustomListViewValuesArr, res);
        SpinnerExample.setAdapter(adapter);

        SpinnerExample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here

                String Company = ((TextView) v.findViewById(R.id.company)).getText().toString();
                String CompanyUrl = ((TextView) v.findViewById(R.id.sub)).getText().toString();

//                String OutputMsg = "\n\n" + Company ;
//                output.setText(OutputMsg);
//
               Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void setListData()
    {
        String coy[] = {"Instant Excecution", "Buy Limit", "Sell Limit", "Buy Stop", "Sell Stop", "Buy Stop Limit", "Sell Stop Limit"};
        for (int i = 0; i < coy.length; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setCompanyName(coy[i]);
            //sched.setImage("image"+i);
            //sched.setUrl("http:\\\\www."+coy[i]+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

    }
}
    /****** Function to set data in ArrayList *************/



