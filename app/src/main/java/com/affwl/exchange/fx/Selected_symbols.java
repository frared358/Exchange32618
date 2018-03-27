package com.affwl.exchange.fx;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.affwl.exchange.fx.CustomAdapter5;
import com.affwl.exchange.fx.RowItem5;

import java.util.ArrayList;
import java.util.List;

public class Selected_symbols extends AppCompatActivity {

    String[] member_names5;
    TypedArray profile_pics5;
    String[] statues5;


    List<RowItem5> rowItems5;
    ListView mylistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_symbols);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        rowItems5 = new ArrayList<RowItem5>();

        member_names5 = getResources().getStringArray(R.array.Member_names5);

        profile_pics5 = getResources().obtainTypedArray(R.array.profile_pics5);

        statues5 = getResources().getStringArray(R.array.statues5);


        for (int i = 0; i < member_names5.length; i++) {
            RowItem5 item = new RowItem5(member_names5[i], profile_pics5.getResourceId(i, -1), statues5[i]);
            rowItems5.add(item);
        }

        mylistview = (ListView)findViewById(R.id.list5);
        CustomAdapter5 adapter = new CustomAdapter5(getApplicationContext(), rowItems5);
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
             //   view.startDrag();
                return false;
            }
        });
        mylistview.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                return false;
            }
        });


    }
















//        listView=(ListView)findViewById(R.id.listview);
//        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listSources);
//
//   listView.setAdapter(adapter);



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quotes_menu1, menu);
        return true;
    }
}
