package com.affwl.exchange.alerts;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AlertListAdapter extends RecyclerView.Adapter<AlertListAdapter.AlertListHolder> {

    Context context;
    ArrayList<String> alertStrList;

    Dialog myDialog;
    Button close_alert_layout,cancel_alert_layout,save_alert_layout;
    TextView set_alert_layout,alert_heading;
    EditText editText_alert_value;
    LinearLayout edit_alert_layout,alert_option_layout;
    RadioGroup operator_radiogroup;
    RadioButton operator_radiobutton;

    AlertListHolder alertListHolder;


    public AlertListAdapter(Context context, ArrayList<String> alertStrList) {
        this.context = context;
        this.alertStrList = alertStrList;
    }

    @Override
    public AlertListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_reminder_item,parent, false);
        return new AlertListHolder(v);
    }

    @Override
    public void onBindViewHolder(AlertListHolder holder, final int position) {
        holder.txtDetails.setText(alertStrList.get(position));
        holder.txtEditAlert.setText("edit");
        holder.txtDeleteAlert.setText("delete");
    }

    @Override
    public int getItemCount() {
        return alertStrList.size();
    }

    public void deleteRow(int position) { //removes the row
        alertStrList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, alertStrList.size());

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        Set<String> set= new HashSet<String>();

        set.addAll(alertStrList);
        SharedPreferences.Editor saveEditor = sp.edit();
        saveEditor.putStringSet("key", set);
        saveEditor.commit();
    }

    public class AlertListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtDetails,txtDeleteAlert,txtEditAlert;

        public AlertListHolder(View view) {
            super(view);
            txtDetails=view.findViewById(R.id.txtDetails);
            txtEditAlert=view.findViewById(R.id.txtEditAlert);
            txtDeleteAlert=view.findViewById(R.id.txtDeleteAlert);

            txtDeleteAlert.setOnClickListener(this);
            txtEditAlert.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.txtDeleteAlert:
                    deleteAlert("Delete Alert","Are you sure. You want to delete ?",getAdapterPosition());
                    break;

                case R.id.txtEditAlert:
                    displayAlertOption(context,txtDetails.getText().toString(),getAdapterPosition());
                    Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    private void deleteAlert(String title, String message, final int adapterPos) {

        TextView alert_title,alertMessage;
        final ImageView close_alert;
        Button ok_alert,cancel_alert;
        final Dialog myAlertDialog = new Dialog(context);
        myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myAlertDialog.setCanceledOnTouchOutside(false);
        myAlertDialog.setContentView(R.layout.alert_message_dts);

        alert_title = myAlertDialog.findViewById(R.id.alert_title);
        alertMessage=myAlertDialog.findViewById(R.id.alertMessage);
        close_alert=myAlertDialog.findViewById(R.id.close_alert);
        ok_alert=myAlertDialog.findViewById(R.id.ok_alert);
        cancel_alert=myAlertDialog.findViewById(R.id.cancel_alert);

        alert_title.setText(title);
        alertMessage.setText(message);

        cancel_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();
            }
        });

        close_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();
            }
        });
        ok_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(adapterPos);
                myAlertDialog.dismiss();
            }
        });
        myAlertDialog.show();

    }


    private void displayAlertOption(final Context context, String stringValue, final int pos) {

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

        alert_option_layout.setVisibility(View.GONE);
        edit_alert_layout.setVisibility(View.VISIBLE);

        if(stringValue.contains(">"))
        {
            String substr = " > ";
            String before = stringValue.substring(0, stringValue.indexOf(substr));
            String after = stringValue.substring(stringValue.indexOf(substr) + substr.length());
            Log.i("Before"," "+before+" After"+after);
            int id=operator_radiogroup.getChildAt(1).getId();
            operator_radiobutton = myDialog.findViewById(id);
            if(operator_radiobutton.getText().toString().contains(">")) {
                operator_radiobutton.setChecked(true);
            }
            alert_heading.setText(before);
            editText_alert_value.setText(after);
        }
        else   if(stringValue.contains("<"))
        {
            String substr = " < ";
            String before = stringValue.substring(0, stringValue.indexOf(substr));
            String after = stringValue.substring(stringValue.indexOf(substr) + substr.length());
            Log.i("Before"," "+before+" After"+after);
            int id=operator_radiogroup.getChildAt(0).getId();
            operator_radiobutton = myDialog.findViewById(id);
            if(operator_radiobutton.getText().toString().contains("<")) {
                operator_radiobutton.setChecked(true);
            }
            alert_heading.setText(before);
            editText_alert_value.setText(after);

        }
        else  if(stringValue.contains("="))
        {
            String substr = " = ";
            String before = stringValue.substring(0, stringValue.indexOf(substr));
            String after = stringValue.substring(stringValue.indexOf(substr) + substr.length());
            Log.i("Before"," "+before+" After"+after);

            int id=operator_radiogroup.getChildAt(2).getId();
            operator_radiobutton = myDialog.findViewById(id);
            if(operator_radiobutton.getText().toString().contains("=")) {
                operator_radiobutton.setChecked(true);
            }
            alert_heading.setText(before);
            editText_alert_value.setText(after);

        }

        save_alert_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEdit=editText_alert_value.getText().toString();
                int selectedId = operator_radiogroup.getCheckedRadioButtonId();
                if(selectedId == -1) {
                    displayMessage("Error", "Select radio button first");
                }
                else {
                    operator_radiobutton = myDialog.findViewById(selectedId);
                    if (!strEdit.equalsIgnoreCase("") && strEdit != null) {
                        String valueArray = alert_heading.getText().toString() + " " + operator_radiobutton.getText().toString() + " " + strEdit;
                        alertStrList.set(pos, valueArray);
                        notifyDataSetChanged();

                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

                        Set<String> set = new HashSet<String>();

                        set.addAll(alertStrList);
                        SharedPreferences.Editor saveEditor = sp.edit();
                        saveEditor.putStringSet("key", set);
                        saveEditor.commit();

                        myDialog.dismiss();
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

        myDialog.show();
    }

    private void displayMessage(String title, String message) {

        TextView alert_title,alertMessage;
        final ImageView close_alert;
        Button ok_alert,cancel_alert;
        final Dialog myAlertDialog = new Dialog(context);
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
