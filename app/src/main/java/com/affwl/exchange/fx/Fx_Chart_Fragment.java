package com.affwl.exchange.fx;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by user on 1/24/2018.
 */

public class Fx_Chart_Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    // private CandleStickChart mchart;
    private CandleStickChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;


    @Nullable
    @Override
    /** Right click - Generate - Override Method - slect onCreateView*/
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_fx_chart, null);


        tvX = (TextView) view.findViewById (R.id.tvXMax);
        tvY = (TextView) view.findViewById (R.id.tvYMax);

        mSeekBarX = (SeekBar) view.findViewById (R.id.seekBar1);
        mSeekBarX.setOnSeekBarChangeListener (this);

        mSeekBarY = (SeekBar) view.findViewById (R.id.seekBar2);
        mSeekBarY.setOnSeekBarChangeListener (this);

        mChart = (CandleStickChart) view.findViewById (R.id.chart1);
        mChart.setBackgroundColor (Color.WHITE);

        mChart.getDescription ().setEnabled (false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount (60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom (false);

        mChart.setDrawGridBackground (false);

        XAxis xAxis = mChart.getXAxis ();
        xAxis.setPosition (XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines (true);
        xAxis.enableGridDashedLine (10f, 10f, 2f);

        YAxis leftAxis = mChart.getAxisLeft ();
        leftAxis.setEnabled (false);
        leftAxis.setLabelCount (7, false);
        leftAxis.setDrawGridLines (false);
        leftAxis.setDrawAxisLine (false);

        leftAxis.setDrawGridLines (true);
        leftAxis.enableGridDashedLine (10f, 10f, 2f);


        YAxis rightAxis = mChart.getAxisRight ();
        rightAxis.setEnabled (true);
        rightAxis.setDrawGridLines (true);
        rightAxis.setDrawGridLines (true);
//        rightAxis.setStartAtZero(false);

        rightAxis.setDrawGridLines (true);
        rightAxis.enableGridDashedLine (10f, 10f, 2f);



        // setting data
        mSeekBarX.setProgress (50);
        mSeekBarY.setProgress (100);

        mChart.getLegend ().setEnabled (false);
        return view;
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
//        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor (Color.BLACK);
        set1.setShadowWidth (0.7f);
        set1.setDecreasingColor (Color.BLACK);
        set1.setDecreasingPaintStyle (Paint.Style.FILL);
//        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingColor (Color.BLACK);
        set1.setIncreasingPaintStyle (Paint.Style.STROKE);
        set1.setNeutralColor (Color.BLUE);
        set1.setHighLightColor (Color.BLACK);
        //set1.setHighlightLineWidth(1f);


        CandleData data = new CandleData (set1);

        mChart.setData (data);
        mChart.invalidate ();


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


}




