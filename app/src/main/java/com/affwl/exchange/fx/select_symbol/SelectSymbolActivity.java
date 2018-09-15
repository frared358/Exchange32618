package com.affwl.exchange.fx.select_symbol;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
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
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;
import com.affwl.exchange.fx.Add_symbol;
import com.affwl.exchange.fx.CustomAdapter5_quotes;
import com.affwl.exchange.fx.DeleteNow;
import com.affwl.exchange.fx.RowItem5_quotes;
import com.affwl.exchange.fx.Selected_symbols;
import com.affwl.exchange.fx.select_symbol.listener.OnCustomerListChangedListener;
import com.affwl.exchange.fx.select_symbol.listener.OnStartDragListener;
import com.affwl.exchange.fx.select_symbol.utilities.SimpleItemTouchHelperCallback;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
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


        rowItems5 = new ArrayList<RowItem5_quotes>();

        member_names5 = getResources().getStringArray(R.array.Member_names5);

        profile_pics5 = getResources().obtainTypedArray(R.array.profile_pics5);

        statues5 = getResources().getStringArray(R.array.statues5);


        for (int i = 0; i < member_names5.length; i++) {
            RowItem5_quotes item = new RowItem5_quotes(member_names5[i], profile_pics5.getResourceId(i, -1), statues5[i]);
            rowItems5.add(item);
        }

        mylistview = (ListView) findViewById(R.id.list5);
        CustomAdapter5_quotes adapter = new CustomAdapter5_quotes(getApplicationContext(), rowItems5);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String member_name5 = rowItems5.get(position).getMember_name5();
                Toast.makeText(getApplicationContext(), "" + member_name5, Toast.LENGTH_SHORT).show();
            }
        });
        mylistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // view.startDrag();
                return false;
            }
        });

}

    @Override
    protected void onResume(){
        super.onResume();

        setupRecyclerView();
//        setupRecyclerView1();


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
//            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
//            for (int i = 0; i < member_names5.length; i++) {
//                RowItem5_quotes itemdel = new RowItem5_quotes(member_names5[i], profile_pics5.getResourceId(i, -1), statues5[i],0);
//                rowItems5.add(itemdel);
//            }
//
//            for(int i = (rowItems5.size() - 1); i >= 0; i--) {
//                if(rowItems5.get(i).getIntCheck()==1)
//                {
//                    rowItems5.remove(i);
//                }
//            }
//
//            mylistview.setVisibility(View.VISIBLE);
//            mRecyclerView.setVisibility(View.GONE);
//            DeleteNow adapter = new DeleteNow(getApplicationContext(), rowItems5);
//            mylistview.setAdapter(adapter);

//            for (int i=DataHolder.ArrayListCustomeName.size()-1;i>=0;i--){
//                Toast.makeText(this, DataHolder.ArrayListCustomeName.size()+" "+DataHolder.ArrayListCustomeName.get(i), Toast.LENGTH_SHORT).show();
//                Log.e("DIP",DataHolder.ArrayListCustomeName.size()+" "+DataHolder.ArrayListCustomeName.get(i));
//                mCustomers.remove(Integer.parseInt(DataHolder.ArrayListCustomeName.get(i)));
//                DataHolder.ArrayListCustomeName.remove(i);
//                mAdapter.notifyDataSetChanged();
//                if(DataHolder.ArrayListCustomeName.size()-1==i){
//                    DataHolder.ArrayListCustomeName.clear();
//                }
//            }

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
//    private void setupRecyclerView1(){
//
//        mRecyclerView =  findViewById(R.id.line_recycler_view);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator ());
//        mRecyclerView.setHasFixedSize(true);
//        mCustomers = SampleData.addSampleCustomers();
//
//        //setup the adapter with empty list
//        mAdapter = new CustomerListAdapter(mCustomers, this, this, this);
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback (mAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                .colorResId(R.color.colorPrimaryDark)
//                .size(2)
//                .build());
//        mRecyclerView.setAdapter(mAdapter);
//    }

    @Override
    public void onNoteListChanged(List<Customer> customers) {

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
