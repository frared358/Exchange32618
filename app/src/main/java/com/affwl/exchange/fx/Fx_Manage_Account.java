package com.affwl.exchange.fx;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.R;


public class Fx_Manage_Account extends Fragment implements View.OnClickListener{
    Dialog myDialog,myDialog1;
LinearLayout linearLayout;
ImageView btnShowPopupAccount,btnShowPopupAccount1;





      @Nullable
    @Override   /** Right click - Generate - Override Method - slect onCreateView */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v= inflater.inflate (R.layout.fragment_fx__manage__account,null);
      myDialog =new Dialog(getContext());
          myDialog1 =new Dialog(getContext());

          btnShowPopupAccount = v.findViewById(R.id.btnShowPopupAccount);
          btnShowPopupAccount1 = v.findViewById(R.id.btnShowPopupAccount);

          btnShowPopupAccount.setOnClickListener(this);
          btnShowPopupAccount1.setOnClickListener(this);

          linearLayout=(LinearLayout) v.findViewById(R.id.l1);
          linearLayout.setOnClickListener(this);

          return  v;
      }
    public void ShowPopup() {
        TextView txtclose;
        Button btnFollow;

        myDialog.setContentView(R.layout.custompopup_acc);
        txtclose = myDialog.findViewById(R.id.btnfollow);

        txtclose.setText("OK");

        btnFollow =  myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

       // myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void ShowPopup1() {
        TextView txtclose;
        Button btnFollow;

        myDialog.setContentView(R.layout.custompopup_acc);
        txtclose = myDialog.findViewById(R.id.btnfollow);

        txtclose.setText("OK");

        btnFollow =  myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        // myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowPopupAccount:
                ShowPopup();
                break;
            case R.id.btnShowPopupAccount1 :
                ShowPopup();
                break;
            case R.id.l1:
                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                break;
        }
    }
}








