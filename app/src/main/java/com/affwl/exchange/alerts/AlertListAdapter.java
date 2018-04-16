package com.affwl.exchange.alerts;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.ArrayList;

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
                    deleteRow(getAdapterPosition());
                    break;

                case R.id.txtEditAlert:
                    displayAlertOption(context,txtDetails.getText().toString(),getAdapterPosition());
                    Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
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
                operator_radiobutton = myDialog.findViewById(selectedId);

                String valueArray=alert_heading.getText().toString()+" "+operator_radiobutton.getText().toString()+" "+strEdit;
                alertStrList.set(pos,valueArray);
                notifyDataSetChanged();
                myDialog.dismiss();
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
}
