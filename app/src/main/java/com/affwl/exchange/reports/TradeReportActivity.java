package com.affwl.exchange.reports;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TradeReportActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar trade_report_toolbar;
    LinearLayout to_date_layout, from_date_layout;

    TextView display_from_date,display_to_date;
    Button btn_get_trade;
    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;
    static final int TO_DIALOG_ID = 99;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_report);

        trade_report_toolbar=findViewById(R.id.trade_report_toolbar);
        setSupportActionBar(trade_report_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        display_from_date=findViewById(R.id.display_from_date);
        display_to_date=findViewById(R.id.display_to_date);

        btn_get_trade=findViewById(R.id.btn_get_trade);
        to_date_layout=findViewById(R.id.to_date_layout);
        from_date_layout=findViewById(R.id.from_date_layout);

        to_date_layout.setOnClickListener(this);
        from_date_layout.setOnClickListener(this);
        btn_get_trade.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.from_date_layout:
                Toast.makeText(this, "Its working", Toast.LENGTH_SHORT).show();
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.to_date_layout:
                showDialog(TO_DIALOG_ID);
                Toast.makeText(this, "Its working", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_get_trade:
                try {
                    Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(display_from_date.getText().toString());
                    Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(display_to_date.getText().toString());

                    if(toDate.after(fromDate)||toDate.equals(fromDate)) {
                        Toast.makeText(this, "Valid Date", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        displayWarning("Alert","Invalid date selection ! ");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void displayWarning(String title, String message) {

            TextView alert_title,alertMessage;
            final ImageView close_alert;
            Button ok_alert,cancel_alert;
            final Dialog myAlertDialog = new Dialog(TradeReportActivity.this);
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

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog fromdatePicker=new DatePickerDialog(this, datePickerListener, mYear, mMonth,mDay);
                fromdatePicker.updateDate(mYear, mMonth, mDay);

                return fromdatePicker;
            case TO_DIALOG_ID:

                DatePickerDialog todatePicker=new DatePickerDialog(this, datePickerListener1, mYear, mMonth,mDay);
                todatePicker.updateDate(mYear, mMonth, mDay);

                return todatePicker;

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            display_from_date.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year)
                    .append(" "));

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            display_to_date.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year)
                    .append(" "));

        }
    };
}
