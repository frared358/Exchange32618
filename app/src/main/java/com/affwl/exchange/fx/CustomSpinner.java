package com.affwl.exchange.fx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tictactec.ta.lib.meta.TaGrpService;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CustomSpinner extends AppCompatActivity implements  OnChartValueSelectedListener,View.OnClickListener {

    
    public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    protected Typeface mTfLight;

    int DeviationValue=0;
    int redvar1;
    int greenvar1,devhigh1,devlow1;

    EditText displayInteger,displayDeviation,displayInteger1;
    ImageView imgVIncrementRed,decGreen,incGreen,ivDevInc;

    EditText redValue,greenValue;

    TextView tvLowValue,tvHighValue;
    TextView output = null;
    CustomAdapter4_customspinner adapter;
    CustomSpinner activity = null;
    private LineChart mChart;
    ImageView locButton10;
    private PopupWindow mDropdown = null;
    LayoutInflater mInflater;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spinner_menu, menu);
        locButton10 = (ImageView) menu.findItem(R.id.dolllar1).getActionView();
        locButton10.setImageDrawable(getResources().getDrawable(R.drawable.ic_symbol));
        locButton10.setPadding(2,2,0,2);
     locButton10.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v){
             initiatePopupWindow();
         }
     });

        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        /** fro line chart  */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView (R.layout.activity_custom_spinner);


        /** for  toolbar  */
        android.support.v7.widget.Toolbar toolbar = findViewById (R.id.toolbar2);
        setSupportActionBar (toolbar);
        /** for  toolbar backpress */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        activity = this;
        
        redValue=(EditText)findViewById (R.id.redValue);
        String var= redValue.getText ().toString ();
        redvar1=Integer.parseInt (var);

        greenValue=(EditText)findViewById (R.id.greenValue);
        String greenvar=greenValue.getText ().toString ();
        greenvar1=Integer.parseInt (greenvar);



        tvHighValue=(TextView)findViewById (R.id.tvHighValue);
        String devhigh=tvHighValue.getText ().toString ();
        devhigh1=Integer.parseInt (devhigh);

        tvLowValue=(TextView)findViewById (R.id.tvLowValue);
        String devlow=tvLowValue.getText ().toString ();
        devlow1=Integer.parseInt (devlow);





        Spinner SpinnerExample = (Spinner) findViewById (R.id.spinner);
        //output = (TextView) findViewById(R.id.output);


        ivDevInc=findViewById (R.id.ivDevInc);
        ivDevInc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){

            incrementDeviation();

        }
    });


        decGreen=findViewById (R.id.decGreen);
        incGreen=findViewById (R.id.incGreen);
        displayInteger1 =  findViewById (R.id.greenValue);


        incGreen.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                incrementRed1 ();
            }
        });


        displayInteger =  findViewById (R.id.redValue);
        displayDeviation = findViewById(R.id.etDevValue);

        imgVIncrementRed = findViewById (R.id.imgVIncrementRed);
        imgVIncrementRed.setOnClickListener (new View.OnClickListener (){
            @Override
            public void onClick(View v) {

                incrementRed();
            }
        });

        setListData ();

        Resources res = getResources ();
        adapter = new CustomAdapter4_customspinner (activity, R.layout.spinner_rows, CustomListViewValuesArr, res);
        SpinnerExample.setAdapter (adapter);

        SpinnerExample.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here

                String Company = ((TextView) v.findViewById (R.id.company)).getText ().toString ();
                String CompanyUrl = ((TextView) v.findViewById (R.id.sub)).getText ().toString ();

//                String OutputMsg = "\n\n" + Company ;
//                output.setText(OutputMsg);
//
                Toast.makeText (getApplicationContext (), "Clicked", Toast.LENGTH_LONG).show ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        /** Line chart Start  */
        mChart = (LineChart) findViewById(R.id.chartNewOrder);
        mChart.setOnChartValueSelectedListener(this);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        //mChart.setBackgroundColor(Color.LTGRAY);

        // add data
        setData(20, 30);

        mChart.animateX(2500);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.GRAY);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

//        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
//        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
//        leftAxis.setAxisMaximum(200f);
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTypeface(mTfLight);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(-200);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

        /** Line chart End  */
    }
    private PopupWindow initiatePopupWindow() {

        try {
            mInflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.activity_chart_dollar_activity, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itemA = (TextView) layout.findViewById(R.id.ItemA);

            final TextView itemB = (TextView) layout.findViewById(R.id.ItemB);

            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            mDropdown = new PopupWindow(layout, FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,true);
            Drawable background = getResources().getDrawable(android.R.drawable.alert_light_frame);
            mDropdown.setBackgroundDrawable(background);
            mDropdown.showAsDropDown(locButton10 , -10, -115);
//            mDropdown.showAtLocation(view, Gravity.LEFT, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdown;
    }

    /** for  toolbar backpress */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

        /**  Line chart   */
        private void setData(int count, float range) {

            ArrayList<Entry> yVals1 = new ArrayList<Entry>();

            for (int i = 0; i < count; i++) {
                float mult = range / 2f;
                float val = (float) (Math.random() * mult) + 50;
                yVals1.add(new Entry(i, val));
            }

            ArrayList<Entry> yVals2 = new ArrayList<Entry>();

            for (int i = 0; i < count-1; i++) {
                float mult = range;
                float val = (float) (Math.random() * mult) + 450;
                yVals2.add(new Entry(i, val));
//            if(i == 10) {
//                yVals2.add(new Entry(i, val + 50));
//            }
            }

//            ArrayList<Entry> yVals3 = new ArrayList<Entry>();
//
//            for (int i = 0; i < count; i++) {
//                float mult = range;
//                float val = (float) (Math.random() * mult) + 500;
//                yVals3.add(new Entry(i, val));
//            }

            LineDataSet set1, set2, set3;

            if (mChart.getData() != null &&
                    mChart.getData().getDataSetCount() > 0) {
                set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
                set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
               //set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
                set1.setValues(yVals1);
                set2.setValues(yVals2);
               // set3.setValues(yVals3);
                mChart.getData().notifyDataChanged();
                mChart.notifyDataSetChanged();

                /**   remove circle on lineChart   */
                set1.setDrawCircles (false);
                set2.setDrawCircles (false);
               // set3.setDrawCircles (false);

                /**   remove curve on lineChart   */
                set1.setMode (LineDataSet.Mode.CUBIC_BEZIER);
                set2.setMode (LineDataSet.Mode.CUBIC_BEZIER);
                //set3.setMode (LineDataSet.Mode.CUBIC_BEZIER);

                /**   remove VALUES on lineChart   */
                set1.setDrawValues(false);
                set2.setDrawValues(false);
                //set3.setDrawValues(false);

            } else {
                // create a dataset and give it a type
                set1 = new LineDataSet(yVals1, "TP");

                set1.setAxisDependency(AxisDependency.LEFT);
                set1.setColor(ColorTemplate.getHoloBlue());
                set1.setCircleColor(Color.WHITE);
                set1.setLineWidth(2f);
//                set1.setCircleRadius(3f);
//                set1.setFillAlpha(65);
                set1.setFillColor(ColorTemplate.getHoloBlue());
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setDrawCircleHole(false);
                set1.setDrawCircles (false);
                set1.setMode (LineDataSet.Mode.CUBIC_BEZIER);
                set1.setDrawValues(false);
                //set1.setFillFormatter(new MyFillFormatter(0f));
                //set1.setDrawHorizontalHighlightIndicator(false);
                //set1.setVisible(false);
                //set1.setCircleHoleColor(Color.WHITE);

                // create a dataset and give it a type
                set2 = new LineDataSet(yVals2, "SL");
                set2.setAxisDependency(AxisDependency.RIGHT);
                set2.setColor(Color.RED);
                set2.setCircleColor(Color.WHITE);
                set2.setLineWidth(2f);
                //set2.setCircleRadius(3f);
                //set2.setFillAlpha(65);
                set2.setFillColor(Color.RED);
                set2.setDrawCircleHole(false);
                set2.setDrawCircles (false);
                set2.setMode (LineDataSet.Mode.CUBIC_BEZIER);
                set2.setDrawValues(false);
                set2.setHighLightColor(Color.rgb(244, 117, 117));
                //set2.setFillFormatter(new MyFillFormatter(900f));

//                set3 = new LineDataSet(yVals3, "DataSet 3");
//                set3.setAxisDependency(AxisDependency.RIGHT);
//                set3.setColor(Color.YELLOW);
//                set3.setCircleColor(Color.WHITE);
//                set3.setLineWidth(2f);
//                set3.setCircleRadius(3f);
//                set3.setFillAlpha(65);
//                set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
//                set3.setDrawCircleHole(false);
//                set3.setDrawCircles (false);
//                set3.setMode (LineDataSet.Mode.CUBIC_BEZIER);
//                set3.setDrawValues(false);
//                set3.setHighLightColor(Color.rgb(244, 117, 117));

                // create a data object with the datasets
                LineData data = new LineData(set1, set2);
                data.setValueTextColor(Color.WHITE);
                data.setValueTextSize(9f);

                // set data
                mChart.setData(data);
            }
        }

        @Override
        public void onValueSelected(Entry e, Highlight h) {
            Log.i("Entry selected", e.toString());

            mChart.centerViewToAnimated(e.getX(), e.getY(), mChart.getData().getDataSetByIndex(h.getDataSetIndex())
                    .getAxisDependency(), 500);
            //mChart.zoomAndCenterAnimated(2.5f, 2.5f, e.getX(), e.getY(), mChart.getData().getDataSetByIndex(dataSetIndex)
            // .getAxisDependency(), 1000);
            //mChart.zoomAndCenterAnimated(1.8f, 1.8f, e.getX(), e.getY(), mChart.getData().getDataSetByIndex(dataSetIndex)
            // .getAxisDependency(), 1000);
        }

        @Override
        public void onNothingSelected() {
            Log.i("Nothing selected", "Nothing selected.");
        }
        /**  Line chart   */



    public void setListData()
    {
        String coy[] = {"Instant Excecution","Request Execution", "Buy Limit", "Sell Limit", "Buy Stop", "Sell Stop", "Buy Stop Limit", "Sell Stop Limit"};
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



   /** Increment/Decrement  for Red value */
   public  void incrementRed()
   {
       redvar1 = redvar1 + 1;
       display(redvar1);

   }


    public  void decrementRed(View view)
    {
        redvar1 = redvar1 - 1;
        display(redvar1);

    }

//Increment/Decrement  for green value *
    public  void incrementRed1()
    {
        greenvar1 = greenvar1 + 1;

        display1 (greenvar1);
    }


    public  void decrementRed1(View view)
    {
        greenvar1 = greenvar1 - 1;

        display1 (greenvar1);
    }

    /** Increment/Decrement  for Deviation */
    public  void incrementDeviation()
    {
        DeviationValue = DeviationValue + 1;
        displayDiviation(DeviationValue);

    }
    public  void decrementDeviation(View view)
    {
        DeviationValue = DeviationValue - 1;
        displayDiviation(DeviationValue);

    }




    private void  display(int number)
    {
        Log.i ("TAGf",""+number);
        try {
            displayInteger.setText(String.valueOf (number));
        } catch (Exception e) {
            e.printStackTrace ();
            Log.i("TAGf",""+e);
        }
    }


    private void  display1(int number)
    {
        Log.i ("TAGf",""+number);
        try {
            displayInteger1.setText(String.valueOf (number));
        } catch (Exception e) {
            e.printStackTrace ();
            Log.i("TAGf",""+e);
        }
    }

    private void  displayDiviation(int number)
    {
        Log.i ("TAGf",""+number);
        try {
            displayDeviation.setText(String.valueOf (number));
        } catch (Exception e) {
            e.printStackTrace ();
            Log.i("TAGf",""+e);
        }
    }

    @Override
    public void onClick(View v) {

    }
}




