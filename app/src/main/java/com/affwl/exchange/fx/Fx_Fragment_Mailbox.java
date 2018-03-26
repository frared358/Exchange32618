package com.affwl.exchange.fx;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.affwl.exchange.RowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/24/2018.
 */

public class Fx_Fragment_Mailbox extends Fragment{

    String[] member_names1;
    TypedArray profile_pics1;
    String[] statues1;
    String[] contactType1;

    List<RowItem> rowItems;
    ListView mylistview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate (R.layout.fragment_fx_mailbox,null);

//        ((Fx_Fragment_Mailbox) getActivity())
//                .setActionBarTitle("Your title");


        rowItems = new ArrayList<RowItem>();

        member_names1 = getResources().getStringArray(R.array.Member_names1);

        profile_pics1 = getResources().obtainTypedArray(R.array.profile_pics1);

        statues1 = getResources().getStringArray(R.array.statues1);

        contactType1 = getResources().getStringArray(R.array.contactType1);

        for (int i = 0; i < member_names1.length; i++) {
            RowItem item = new RowItem(member_names1[i], profile_pics1.getResourceId(i, -1), statues1[i], contactType1[i]);
            rowItems.add(item);
        }

        mylistview = (ListView) v.findViewById(R.id.list1);
        CustomAdapter adapter = new CustomAdapter(getContext(), rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String member_name = rowItems.get(position).getMember_name();
                Toast.makeText(getContext(), "" + member_name, Toast.LENGTH_SHORT).show();
            }
        });

        return  v;
    }

}


