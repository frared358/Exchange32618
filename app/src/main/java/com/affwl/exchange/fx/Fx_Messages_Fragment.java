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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user    on 1/25/2018.
 */

public class Fx_Messages_Fragment extends Fragment {

    String[] member_names;
    TypedArray profile_pics;
    String[] statues;
    String[] contactType;

    List<RowItem> rowItems;
    ListView mylistview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fx_messages, null);


        rowItems = new ArrayList<RowItem>();

        member_names = getResources().getStringArray(R.array.Member_names);

        profile_pics = getResources().obtainTypedArray(R.array.profile_pics);

        statues = getResources().getStringArray(R.array.statues);

        contactType = getResources().getStringArray(R.array.contactType);

        for (int i = 0; i < member_names.length; i++) {
            RowItem item = new RowItem(member_names[i], profile_pics.getResourceId(i, -1), statues[i], contactType[i]);
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

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        String member_name = rowItems.get(position).getMember_name();
//        Toast.makeText(getApplicationContext(), "" + member_name, Toast.LENGTH_SHORT).show();
//    }



//    @Nullable
//    @Override   /** Right click - Generate - Override Method - slect onCreateView */
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate (R.layout.fragment_fx_messages,null);
//
//
//    }
//
//    private void findViewId() {
//    }
//
//    /** Right click - Generate - Override Method - slect onViewCreate */
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated (view, savedInstanceState);
//
//        /** since it is a fragment we use view.findViewById */
////        view.findViewById (R.id.btn).setOnClickListener (new View.OnClickListener () {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText (getActivity (),"button clicked",Toast.LENGTH_SHORT).show ();
////            }
////        });
//    }


