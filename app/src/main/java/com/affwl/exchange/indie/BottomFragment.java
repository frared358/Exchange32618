package com.affwl.exchange.indie;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class BottomFragment extends Fragment implements View.OnClickListener{

    TextView txtVHome,txtVTradeQuote,txtVMarketWatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVHome= (TextView)view.findViewById(R.id.txtVHome);
        txtVTradeQuote= (TextView)view.findViewById(R.id.txtVTradeQuote);
//        txtVNews= (TextView)view.findViewById(R.id.txtVNews);
        txtVMarketWatch= (TextView)view.findViewById(R.id.txtVMarketWatch);

        txtVHome.setOnClickListener(this);
        txtVTradeQuote.setOnClickListener(this);
//        txtVNews.setOnClickListener(this);
        txtVMarketWatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtVHome :
                startActivity(new Intent(BottomFragment.this.getActivity(), MainActivity.class)); break;
            case R.id.txtVTradeQuote :startActivity(new Intent(BottomFragment.this.getActivity(), ScripSearchActivity.class)); break;
          /*  case R.id.txtVNews :
                startActivity(new Intent(BottomFragment.this.getActivity(), NewsActivity.class));
                break;*/
            case R.id.txtVMarketWatch :
                DataHolder.navigationForTab = 1;
                startActivity(new Intent(BottomFragment.this.getActivity(), IndieActivity.class)); break;
        }
    }
}
