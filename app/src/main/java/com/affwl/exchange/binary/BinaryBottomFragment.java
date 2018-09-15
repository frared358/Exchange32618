package com.affwl.exchange.binary;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;


public class BinaryBottomFragment extends Fragment implements View.OnClickListener {


    TextView txtVBProduct,txtVBAsset,txtVBTrade,txtVBHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_bottom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVBProduct =(TextView)view.findViewById(R.id.txtVBProduct);
        txtVBAsset =(TextView)view.findViewById(R.id.txtVBAsset);
        txtVBTrade =(TextView)view.findViewById(R.id.txtVBTrade);
        txtVBHome =(TextView)view.findViewById(R.id.txtVBHome);

        txtVBProduct.setOnClickListener(this);
        txtVBAsset.setOnClickListener(this);
        txtVBTrade.setOnClickListener(this);
        txtVBHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBProduct: startActivity(new Intent(this.getActivity(),Sec60Activity.class)); break;
            case R.id.txtVBAsset: startActivity(new Intent(this.getActivity(),AssetNameActivity.class)); break;
            case R.id.txtVBTrade: startActivity(new Intent(this.getActivity(),AssetListActivity.class)); break;
            case R.id.txtVBHome: startActivity(new Intent(this.getActivity(),MainActivity.class)); break;
        }

    }
}
