package com.affwl.exchange.fx;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.affwl.exchange.fx.fx_chart.RecycleChartActivity;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 1/24/2018.
 */

public class Fx_Chart_Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener,OnChartValueSelectedListener {
    // private CandleStickChart mchart;
    private CandleStickChart mChart, m2Chart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    BottomClickSession bcs;
    static RelativeLayout  rlchartb;
    public View viewChart;
    protected Typeface mTfLight;
    private LineChart mLineChart;
    static LinearLayout llcharverti;

    Button checkDemo;

    @Nullable
    @Override
    /** Right click - Generate - Override Method - slect onCreateView*/
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewChart = inflater.inflate (R.layout.fragment_fx_chart, null);

        bcs=new BottomClickSession(getContext());
//        String chartval=bcs.getChart();
//        if (chartval != "1") {
//            Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
        //}
           // RelativeLayout relativeLayout=view.findViewById(R.id.rlchartb);
//
//            if (relativeLayout.getVisibility()== View.GONE)
//            relativeLayout.setVisibility(View.VISIBLE);
   //     }
        tvX = (TextView) viewChart.findViewById (R.id.tvXMax);
        tvY = (TextView) viewChart.findViewById (R.id.tvYMax);

        mSeekBarX = (SeekBar) viewChart.findViewById (R.id.seekBar1);
        mSeekBarX.setOnSeekBarChangeListener (this);

        mSeekBarY = (SeekBar) viewChart.findViewById (R.id.seekBar2);
        mSeekBarY.setOnSeekBarChangeListener (this);


        mChart = (CandleStickChart) viewChart.findViewById (R.id.chart1);
        mChart.setBackgroundColor (Color.WHITE);
//
        m2Chart = (CandleStickChart) viewChart.findViewById (R.id.chart15);
        m2Chart.setBackgroundColor (Color.WHITE);

        rlchartb = (RelativeLayout) viewChart.findViewById(R.id.rlchartb);
        llcharverti=(LinearLayout)viewChart.findViewById(R.id.llcharverti);


     /*   checkDemo=viewChart.findViewById(R.id.checkDemo);
        checkDemo.setOnClickListener(this);*/
        creatingChart();






        /** Line chart Start  */
     mLineChart=viewChart.findViewById(R.id.chartNewOrder);
        mLineChart.setOnChartValueSelectedListener(this);

        // no description text
        mLineChart.getDescription().setEnabled(false);

        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        mLineChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        // set an alternative background color
        //mLineChart.setBackgroundColor(Color.LTGRAY);

        // add data
        setData(20, 30);

        mLineChart.animateX(2500);

        // get the legend (only possible after setting data)
        Legend l = mLineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.GRAY);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setTypeface(mTfLight);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(-200);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

        /** Line chart End  */
        return viewChart;
    }


    private void creatingChart(){
        mChart.getDescription ().setEnabled (false);
        m2Chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount (60);
        m2Chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom (false);
        m2Chart.setPinchZoom(false);

        mChart.setDrawGridBackground (false);
        m2Chart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis ();
        xAxis.setPosition (XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines (true);
        xAxis.enableGridDashedLine (10f, 10f, 2f);
        /**Add date time on chart start*/
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm");
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date (millis));
            }
        });

        XAxis xAxis2 = m2Chart.getXAxis ();
        xAxis2.setPosition (XAxis.XAxisPosition.BOTTOM);
        xAxis2.setDrawGridLines (true);
        xAxis2.enableGridDashedLine (10f, 10f, 2f);
        /**Add date time on chart start*/
        xAxis2.setCenterAxisLabels(true);
        xAxis2.setGranularity(1f); // one hour
        xAxis2.setValueFormatter(new IAxisValueFormatter() {

            private SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm");
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date (millis));
            }
        });
        /**Add date time on chart end*/

        YAxis leftAxis = mChart.getAxisLeft ();
        leftAxis.setEnabled (false);
        leftAxis.setLabelCount (7, false);
        leftAxis.setDrawGridLines (false);
        leftAxis.setDrawAxisLine (false);

        leftAxis.setDrawGridLines (true);
        leftAxis.enableGridDashedLine (10f, 10f, 2f);



        YAxis leftAxis2 = m2Chart.getAxisLeft ();
        leftAxis2.setEnabled (false);
        leftAxis2.setLabelCount (7, false);
        leftAxis2.setDrawGridLines (false);
        leftAxis2.setDrawAxisLine (false);

        leftAxis2.setDrawGridLines (true);
        leftAxis2.enableGridDashedLine (10f, 10f, 2f);
        //leftAxis.setDrawValues(false);

//        YAxis rightAxis = m2Chart.getAxisRight ();
//        rightAxis.setEnabled (true);
//        rightAxis.setDrawGridLines (true);
//        rightAxis.setDrawGridLines (true);
//        rightAxis.setStartAtZero(false);

//        rightAxis.setDrawGridLines (true);
//        rightAxis.enableGridDashedLine (10f, 10f, 2f);

        // setting data
        mSeekBarX.setProgress (40);
        mSeekBarY.setProgress (100);

        mChart.getLegend ().setEnabled (false);
        m2Chart.getLegend ().setEnabled (false);

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

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mLineChart.getData().getDataSetByIndex(1);
            //set3 = (LineDataSet) mLineChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            // set3.setValues(yVals3);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();

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

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
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
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
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
            mLineChart.setData(data);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int prog = (mSeekBarX.getProgress () + 1);

        tvX.setText ("" + prog);
        tvY.setText ("" + (mSeekBarY.getProgress ()));

        mChart.resetTracking ();

        ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry> ();

        for (int i = 0; i < prog; i++) {
            float mult = (mSeekBarY.getProgress () + 1);
            float val = (float) (Math.random () * 40) + mult;

            float high = (float) (Math.random () * 9) + 8f;
            float low = (float) (Math.random () * 9) + 8f;

            float open = (float) (Math.random () * 6) + 1f;
            float close = (float) (Math.random () * 6) + 1f;

            boolean even = i % 2 == 0;

            yVals1.add (new CandleEntry (
                    i, val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close,
                    getResources ().getDrawable (R.drawable.main)
            ));
        }

        CandleDataSet set1 = new CandleDataSet (yVals1, "Data Set");

        set1.setDrawIcons (false);
        set1.setAxisDependency (YAxis.AxisDependency.LEFT);
        set1.setShadowColor (Color.BLACK);
        set1.setShadowWidth (0.7f);
        set1.setDecreasingColor (Color.BLACK);
        set1.setDecreasingPaintStyle (Paint.Style.FILL);
        set1.setIncreasingColor (Color.BLACK);
        set1.setIncreasingPaintStyle (Paint.Style.STROKE);
        set1.setNeutralColor (Color.BLUE);
        set1.setHighLightColor (Color.BLACK);
        set1.setDrawValues(false);              /**remove Data values */
        //set1.setHighlightLineWidth(1f);
        CandleData data = new CandleData (set1);

        mChart.setData (data);
        mChart.invalidate ();
        m2Chart.setData (data);
        m2Chart.invalidate ();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

/*    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.checkDemo:
                startActivity(new Intent(getContext(), RecycleChartActivity.class));
                break;
        }
    }*/

    @Override
    public void onValueSelected(Entry e, Highlight h){

    }

    @Override
    public void onNothingSelected(){

    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return null;
        }
    }

    /**
     * Right click - Generate - Override Method - slect onViewCreate
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        /** since it is a fragment we use view.findViewById */
//        view.findViewById (R.id.btn).setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText (getActivity (),"button clicked",Toast.LENGTH_SHORT).show ();
//            }
//        });
    }

    /**
     * Toggle on + icon on chart
     */
    public void showHighLight() {
        if (mChart.getData () != null) {
            mChart.getData ().setHighlightEnabled (!mChart.getData ().isHighlightEnabled ());
            mChart.invalidate ();
        }
    }

    public static void DisplayChart(){
                        rlchartb.setVisibility(View.VISIBLE);
    }

    public static void displayHorizontal(){
        llcharverti.setOrientation(LinearLayout.HORIZONTAL);
    }

    public static void displayVertical(){
        llcharverti.setOrientation(LinearLayout.VERTICAL);
    }
}




