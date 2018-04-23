package com.affwl.exchange.fx.select_symbol;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.affwl.exchange.fx.Add_symbol;
import com.affwl.exchange.fx.DeleteNow;
import com.affwl.exchange.fx.RowItem5_quotes;
import com.affwl.exchange.fx.Selected_symbols;
import com.affwl.exchange.fx.select_symbol.listener.OnCustomerListChangedListener;
import com.affwl.exchange.fx.select_symbol.listener.OnStartDragListener;
import com.affwl.exchange.fx.select_symbol.utilities.SimpleItemTouchHelperCallback;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectSymbolActivity extends AppCompatActivity implements OnCustomerListChangedListener,OnStartDragListener {

    public RecyclerView mRecyclerView;
    private CustomerListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private List<Customer> mCustomers;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());

        setContentView (R.layout.select_symbol);
        setupRecyclerView();

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

            Intent i = new Intent(SelectSymbolActivity.this, Add_symbol.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.delete)
        {
            // multiple delete
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

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
        mAdapter = new CustomerListAdapter(mCustomers, this, this, this);
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
