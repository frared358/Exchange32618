package com.affwl.exchange.fx.fx_chart;

/**
 * Created by user on 4/15/2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.affwl.exchange.fx.BottomClickSession;
import com.affwl.exchange.fx.select_symbol.Customer;
import com.affwl.exchange.fx.select_symbol.listener.OnCustomerListChangedListener;
import com.affwl.exchange.fx.select_symbol.listener.OnStartDragListener;
import com.affwl.exchange.fx.select_symbol.utilities.ItemTouchHelperAdapter;
import com.affwl.exchange.fx.select_symbol.utilities.ItemTouchHelperViewHolder;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


class ChartListAdapter extends RecyclerView.Adapter<ChartListAdapter.ItemViewHolderdemo> implements ItemTouchHelperAdapter {
    private CandleStickChart mChart, m2Chart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    BottomClickSession bcs;
    static RelativeLayout rlchartb;
    public View holder;

    Button checkDemo;

    private List<Customer> mCustomers;
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnCustomerListChangedListener mListChangedListener;

    public ChartListAdapter(List<Customer> customers, Context context, OnStartDragListener dragLlistener, OnCustomerListChangedListener listChangedListener){
        mCustomers = customers;
        mContext = context;
        mDragStartListener = dragLlistener;
        mListChangedListener = listChangedListener;
    }


    @Override
    public ItemViewHolderdemo onCreateViewHolder(ViewGroup parent, int viewType){
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_fx_chart, parent, false);
        ItemViewHolderdemo viewHolder = new ItemViewHolderdemo(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolderdemo holder, int position){

        holder.rlrecycledemo.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
        holder.rlrecyclelinedemo.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

        holder.mChart.setBackgroundColor(Color.WHITE);
       holder.mChart.setMaxVisibleValueCount (60);
        holder.mChart.setPinchZoom (false);
        holder.mChart.setDrawGridBackground (false);

        holder.mChart.getDescription().setEnabled(false);


        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        holder.mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        holder.mChart.setPinchZoom(false);

        holder.mChart.setDrawGridBackground(false);




        XAxis xAxis = holder.mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.enableGridDashedLine(10f, 10f, 2f);
        /**Add date time on chart start*/
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm");

            @Override
            public String getFormattedValue(float value, AxisBase axis){
                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date(millis));
            }
        });


        /**Add date time on chart end*/

        YAxis leftAxis = holder.mChart.getAxisLeft();
        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        leftAxis.setDrawGridLines(true);
        leftAxis.enableGridDashedLine(10f, 10f, 2f);


       holder.mChart.getLegend().setEnabled(false);
    holder.mSeekBarX.setProgress (40);
       holder.mSeekBarY.setProgress (100);


    }

    @Override
    public int getItemCount(){
        return mCustomers.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition){
        Collections.swap(mCustomers, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mCustomers);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position){

    }

    class ItemViewHolderdemo extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        RelativeLayout rlrecycledemo;
        RelativeLayout rlrecyclelinedemo;

        TextView tvX, tvY;
        CandleStickChart mChart;
        private SeekBar mSeekBarX, mSeekBarY;



        public ItemViewHolderdemo(View itemView){
            super(itemView);

            rlrecycledemo = (RelativeLayout) itemView.findViewById(R.id.rlrecycledemo);
            rlrecyclelinedemo = (RelativeLayout) itemView.findViewById(R.id.rlrecyclelinedemo);


            tvX = (TextView) itemView.findViewById(R.id.tvXMax);
            tvY = (TextView) itemView.findViewById(R.id.tvYMax);

          mChart = (CandleStickChart) itemView.findViewById(R.id.chart1);
            mSeekBarX = (SeekBar) itemView.findViewById (R.id.seekBar1);
            mSeekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

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
                                even ? val - close : val + close

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



                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar){

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar){

                }
            });

            mSeekBarY = (SeekBar) itemView.findViewById (R.id.seekBar2);
            mSeekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar){

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar){

                }
            });

        }

        @Override
        public void onItemSelected(){
            itemView.getId();       //changed
        }

        @Override
        public void onItemClear(){
            itemView.setBackgroundColor(0);
        }
    }
}

