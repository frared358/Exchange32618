package com.affwl.exchange.fx;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Selected_symbols extends AppCompatActivity {

    MenuItem delete;
    CheckBox checkBox;
    String[] member_names5;
    TypedArray profile_pics5;
    String[] statues5;


    List<RowItem5_quotes> rowItems5;
    ListView mylistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_symbols);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


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
        mylistview.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Toast.makeText(Selected_symbols.this, "Dragging", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


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

            Intent i = new Intent(Selected_symbols.this, Add_symbol.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.delete)
        {
            // multiple delete
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < member_names5.length; i++) {
        RowItem5_quotes itemdel = new RowItem5_quotes(member_names5[i], profile_pics5.getResourceId(i, -1), statues5[i],0);
        rowItems5.add(itemdel);
        }

            for(int i = (rowItems5.size() - 1); i >= 0; i--) {
                if(rowItems5.get(i).getIntCheck()==1)
                {
                    rowItems5.remove(i);
                }
            }

        DeleteNow adapter = new DeleteNow(getApplicationContext(), rowItems5);
        mylistview.setAdapter(adapter);
        }
        //checkbox
        return super.onOptionsItemSelected(item);
    }
}

