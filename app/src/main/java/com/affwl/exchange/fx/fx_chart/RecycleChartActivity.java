package com.affwl.exchange.fx.fx_chart;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;
import com.affwl.exchange.fx.Add_symbol;
import com.affwl.exchange.fx.CustomAdapter5_quotes;
import com.affwl.exchange.fx.RowItem5_quotes;
import com.affwl.exchange.fx.select_symbol.Customer;
import com.affwl.exchange.fx.fx_chart.ChartListAdapter;
import com.affwl.exchange.fx.select_symbol.SampleData;
import com.affwl.exchange.fx.select_symbol.listener.OnCustomerListChangedListener;
import com.affwl.exchange.fx.select_symbol.listener.OnStartDragListener;
import com.affwl.exchange.fx.select_symbol.utilities.SimpleItemTouchHelperCallback;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RecycleChartActivity extends AppCompatActivity implements OnCustomerListChangedListener,OnStartDragListener {

    public RecyclerView mRecyclerView;
    private ChartListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private List<Customer> mCustomers;
    Toolbar toolbar;

    String[] member_names5;
    TypedArray profile_pics5;
    String[] statues5;


    List<RowItem5_quotes> rowItems5;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());

        setContentView (R.layout.select_symbol);


        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

}

    @Override
    protected void onResume(){
        super.onResume();

        setupRecyclerView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quotes_menu1, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.plus) {

            Intent i = new Intent(RecycleChartActivity.this, Add_symbol.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.delete)
        {


            for (int i=0;i<DataHolder.ListDeleted.size(); i++){
                //Toast.makeText(this, DataHolder.ArrayListCustomeName.size()+" "+DataHolder.ArrayListCustomeName.get(i), Toast.LENGTH_SHORT).show();
                Log.e("DIP",DataHolder.ListDeleted.size()+" "+DataHolder.ListDeleted.get(i));

                if(DataHolder.ListDeleted.get(i) == true)
                {
                    mCustomers.remove(i);
                    DataHolder.ListDeleted.remove(i);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }


        //checkbox
        return super.onOptionsItemSelected(item);
    }


    private void setupRecyclerView(){

        mRecyclerView =  findViewById(R.id.note_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator ());
        mRecyclerView.setHasFixedSize(true);
        mCustomers = SampleData.addSampleCustomers();

        //setup the adapter with empty list
        mAdapter = new ChartListAdapter(mCustomers, this, this, this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback (mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
               .colorResId(R.color.colorPrimaryDark)
                .size(2)
                .build());
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onNoteListChanged(List<Customer> customers) {

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
