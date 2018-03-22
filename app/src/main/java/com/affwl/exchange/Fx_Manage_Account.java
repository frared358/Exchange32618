package com.affwl.exchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.affwl.exchange.R;


public class Fx_Manage_Account extends Fragment {
LinearLayout linearLayout;

      @Nullable
    @Override   /** Right click - Generate - Override Method - slect onCreateView */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v= inflater.inflate (R.layout.fragment_fx__manage__account,null);
          linearLayout=(LinearLayout) v.findViewById(R.id.l1);
          linearLayout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i = new Intent(v.getContext(), Login.class);
                  startActivity(i);
              }
          });
          return  v;
      }
    }







