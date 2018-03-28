package com.affwl.exchange.fx;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user    on 1/25/2018.
 */

public class Fx_Messages_Fragment extends Fragment {

    Button btn_regi;
    Button btn_si;

    String[] member_names;
    TypedArray profile_pics;
    String[] statues;
    String[] contactType;

    List<RowItem_news> rowItems;
    ListView mylistview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fx_messages, null);



        btn_regi=(Button)v.findViewById(R.id.btn_regi);
        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Fx_msg_chat_Regi_activity.class);
                startActivity(i);

            }
        });
        btn_si=(Button)v.findViewById(R.id.btn_si);
        btn_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), activity_fx_msg_chat__login_activity.class);
                startActivity(i);

            }
        });
        rowItems = new ArrayList<RowItem_news>();

        member_names = getResources().getStringArray(R.array.Member_names);

        profile_pics = getResources().obtainTypedArray(R.array.profile_pics);

        statues = getResources().getStringArray(R.array.statues);

        contactType = getResources().getStringArray(R.array.contactType);

        for (int i = 0; i < member_names.length; i++) {
            RowItem_news item = new RowItem_news(member_names[i], profile_pics.getResourceId(i, -1), statues[i], contactType[i]);
            rowItems.add(item);
        }

        mylistview = (ListView) v.findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(getContext(), rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String member_name = rowItems.get(position).getMember_name();
                Toast.makeText(getContext(), "" + member_name, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}



