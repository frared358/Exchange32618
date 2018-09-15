package com.affwl.exchange.indie;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.affwl.exchange.news.RssFeedListActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class IndieMarketIndicesFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {


    TextView txtVTitle,txtVTime,txtVValue,txtVPercentage;
    LinearLayout indie_market_indices_layout;
    Dialog myDialog;
    Button close_alert_layout,cancel_alert_layout,save_alert_layout;
    TextView set_alert_layout,alert_heading;
    EditText editText_alert_value;
     LinearLayout edit_alert_layout,alert_option_layout;
     RadioGroup operator_radiogroup;
    RadioButton operator_radiobutton;
    ArrayList<String> alert_array_list=new ArrayList<>();
    Set<String> set;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indie_market_indices, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVTitle = (TextView)view.findViewById(R.id.txtVTitle);
        txtVTime = (TextView)view.findViewById(R.id.txtVTime);
        txtVValue = (TextView)view.findViewById(R.id.txtVValue);
        txtVPercentage = (TextView)view.findViewById(R.id.txtVPercentage);

        indie_market_indices_layout=view.findViewById(R.id.indie_market_indices_layout);

        indie_market_indices_layout.setOnLongClickListener(this);
        txtVTitle.setOnClickListener(this);
        txtVTime.setOnClickListener(this);
        txtVValue.setOnClickListener(this);
        txtVPercentage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtVTitle: case R.id.txtVTime:
            case R.id.txtVValue: case R.id.txtVPercentage:
                startActivity(new Intent(IndieMarketIndicesFragment.this.getActivity(),MarketIndicesActivity.class));
                break;

        }

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId())
        {
            case R.id.indie_market_indices_layout:
                String strValue=txtVValue.getText().toString();
                String strTitle=txtVTitle.getText().toString();
                if(strValue!=null ||!strTitle.equalsIgnoreCase("")||!strValue.equalsIgnoreCase("")||strTitle!=null) {
                    displayAlertOption(v.getContext(), strTitle, strValue);
                }
                break;
        }
        return false;
    }

    private void displayAlertOption(final Context context, String strTitle, String strValue) {

        myDialog = new Dialog(context);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.indie_alert_option);

        alert_heading=myDialog.findViewById(R.id.alert_heading);
        operator_radiogroup=myDialog.findViewById(R.id.operator_radiogroup);
        close_alert_layout=myDialog.findViewById(R.id.close_alert_layout);
        set_alert_layout=myDialog.findViewById(R.id.set_alert_layout);
        edit_alert_layout=myDialog.findViewById(R.id.edit_alert_layout);
        alert_option_layout=myDialog.findViewById(R.id.alert_option_layout);
        cancel_alert_layout=myDialog.findViewById(R.id.cancel_alert_layout);
        save_alert_layout=myDialog.findViewById(R.id.save_alert_layout);
        editText_alert_value=myDialog.findViewById(R.id.editText_alert_value);

        alert_heading.setText(strTitle);
        editText_alert_value.setText(strValue);

        save_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEdit = editText_alert_value.getText().toString();
                Toast.makeText(context, "Checking text : "+strEdit, Toast.LENGTH_SHORT).show();
                int selectedId = operator_radiogroup.getCheckedRadioButtonId();
                    Log.i("Selected Id", " " + selectedId);
                if(selectedId == -1) {
                    displayMessage("Error", "Select radio button first");
                }
                    else  {
                        operator_radiobutton = myDialog.findViewById(selectedId);
//                        operator_radiobutton.setBackground(getResources().getDrawable(R.drawable.selected_radio_circle));
                        String selectedString=operator_radiobutton.getText().toString();
                            Log.i("String Edit",strEdit);
                            if(!strEdit.equalsIgnoreCase("")&& strEdit!=null) {
                                String valueArray = alert_heading.getText().toString() + " " + selectedString + " " + strEdit;
                                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
                                if (sp.getStringSet("key", null) != null) {
                                    set = sp.getStringSet("key", null);
                                } else {
                                    set = new HashSet<String>();
                                }

                                set.add(valueArray);
                                SharedPreferences.Editor saveEditor = sp.edit();
                                saveEditor.putStringSet("key", set);
                                saveEditor.commit();
                                myDialog.dismiss();
                                displayMessage("New Alert", "Alert Set Successfully !");
                            }
                            else {
                                displayMessage("Error", "Please enter a value");
                            }
                    }

            }
        });

        cancel_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        close_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        set_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_option_layout.setVisibility(View.GONE);
                edit_alert_layout.setVisibility(View.VISIBLE);
            }
        });

        myDialog.show();
    }

    private void displayMessage(String title, String message) {

            TextView alert_title,alertMessage;
            final ImageView close_alert;
            Button ok_alert,cancel_alert;

            final Dialog myAlertDialog = new Dialog(getContext());
            myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myAlertDialog.setCanceledOnTouchOutside(false);
            myAlertDialog.setContentView(R.layout.alert_message_dts);

            alert_title = myAlertDialog.findViewById(R.id.alert_title);
            alertMessage=myAlertDialog.findViewById(R.id.alertMessage);
            close_alert=myAlertDialog.findViewById(R.id.close_alert);
            ok_alert=myAlertDialog.findViewById(R.id.ok_alert);
            cancel_alert=myAlertDialog.findViewById(R.id.cancel_alert);

            close_alert.setVisibility(View.GONE);
            cancel_alert.setVisibility(View.GONE);
            ok_alert.setText("OK");
            alert_title.setText(title);
            alertMessage.setText(message);

            ok_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myAlertDialog.dismiss();
                }
            });
            myAlertDialog.show();

    }
}
