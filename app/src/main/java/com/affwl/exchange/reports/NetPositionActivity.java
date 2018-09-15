package com.affwl.exchange.reports;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.affwl.exchange.R;

public class NetPositionActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar net_position_toolbar;
    Button btn_sq_off_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_position);

        net_position_toolbar=findViewById(R.id.net_position_toolbar);
        setSupportActionBar(net_position_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_sq_off_all=findViewById(R.id.btn_sq_off_all);
        btn_sq_off_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_sq_off_all:
                displayAlert("Square Off All", "Are you sure to square of all? ");
                break;
        }
    }

    private void displayAlert(String title, String message) {

            TextView alert_title,alertMessage;
            final ImageView close_alert;
            Button ok_alert,cancel_alert;
            final Dialog myAlertDialog = new Dialog(NetPositionActivity.this);
            myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myAlertDialog.setCanceledOnTouchOutside(false);
            myAlertDialog.setContentView(R.layout.alert_message_dts);

            alert_title = myAlertDialog.findViewById(R.id.alert_title);
            alertMessage=myAlertDialog.findViewById(R.id.alertMessage);
            close_alert=myAlertDialog.findViewById(R.id.close_alert);
            ok_alert=myAlertDialog.findViewById(R.id.ok_alert);
            cancel_alert=myAlertDialog.findViewById(R.id.cancel_alert);

            close_alert.setVisibility(View.GONE);
            ok_alert.setText("OK");
            alert_title.setText(title);
            alertMessage.setText(message);

        cancel_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialog.dismiss();
            }
        });

            ok_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myAlertDialog.dismiss();
                }
            });
            myAlertDialog.show();
        }

}
