package com.affwl.exchange.fx;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 1/24/2018.
 */

public class Fx_Chart_Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener  {
    // private CandleStickChart mchart;
    private CandleStickChart mChart, m2Chart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    BottomClickSession bcs;
    static RelativeLayout  rlchartb;
    public View viewChart;

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

        creatingChart();

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
//                    if(rlchartb.getVisibility()==View.GONE) {
                        rlchartb.setVisibility(View.VISIBLE);
//                    }
                   /* else {
                        rlchartb.setVisibility(View.GONE);
                    }*/
    }
}




