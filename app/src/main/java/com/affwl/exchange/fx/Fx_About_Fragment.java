package com.affwl.exchange.fx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.affwl.exchange.R;

/**
 * Created by user on 1/25/2018.
 */

public class Fx_About_Fragment extends Fragment {
    ImageView view;

    @Nullable
    @Override   /** Right click - Generate - Override Method - slect onCreateView */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fx_about, null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img = (ImageView) v.findViewById(R.id.imageView9);
                img.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.lifeclearance.lifec.lifec"));
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
    /** Right click - Generate - Override Method - slect onViewCreate */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        /** since it is a fragment we use view.findViewById */
//        view.findViewById (R.id.btn).setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText (getActivity (),"button clicked",Toast.LENGTH_SHORT).show ();
//            }
//        });
    }
}
