package com.affwl.exchange.fx;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteFragment extends Fragment {
    String[] member_names1;
    TypedArray profile_pics1;
    String[] statues1;
    String[] contactType1;

    List<RowItem_news> rowItems;
    ListView mylistview;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_delete2, container, false);
        rowItems = new ArrayList<RowItem_news>();

        member_names1 = getResources().getStringArray(R.array.Member_names1);

        profile_pics1 = getResources().obtainTypedArray(R.array.profile_pics1);

        statues1 = getResources().getStringArray(R.array.statues1);

        contactType1 = getResources().getStringArray(R.array.contactType1);

        for (int i = 0; i < member_names1.length; i++) {
            RowItem_news item = new RowItem_news(member_names1[i], profile_pics1.getResourceId(i, -1), statues1[i], contactType1[i]);
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


