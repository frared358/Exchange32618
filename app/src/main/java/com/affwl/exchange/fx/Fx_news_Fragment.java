package com.affwl.exchange.fx;

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
 * Created by user on 1/24/2018.
 */


public class Fx_news_Fragment extends Fragment {

    String[] member_names2;
    String[] statues2;

    List<RowItem1> rowItems1;
    ListView mylistview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate (R.layout.fragment_fx_news,null);


        rowItems1 = new ArrayList<RowItem1>();

        member_names2 = getResources().getStringArray(R.array.Member_names2);

        statues2 = getResources().getStringArray(R.array.statues2);

        for (int i = 0; i < member_names2.length; i++) {
            RowItem1 item = new RowItem1(member_names2[i], statues2[i]);
            rowItems1.add(item);
        }

        mylistview = (ListView) v.findViewById(R.id.list2);
        CustomAdapter1 adapter = new com.affwl.exchange.fx.CustomAdapter1(getContext(), rowItems1);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String member_name2 = rowItems1.get(position).getMember_name2();
                Toast.makeText(getContext(), "" + member_name2, Toast.LENGTH_SHORT).show();
            }
        });

        return  v;
    }

}


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // TODO add your menu :
//        inflater.inflate(R.menu.news_menu, menu);
//        //TODO call super
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }
//
//
//    @Nullable
//    @Override   /** Right click - Generate - Override Method - slect onCreateView */
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate (R.layout.fragment_fx_news,null);
//    }
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


