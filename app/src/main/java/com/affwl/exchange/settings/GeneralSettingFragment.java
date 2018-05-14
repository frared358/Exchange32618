package com.affwl.exchange.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.affwl.exchange.LoginActivity;
import com.affwl.exchange.R;

public class GeneralSettingFragment extends Fragment implements View.OnClickListener {

    Switch switch_crypto , switch_fx , switch_indie , switch_sixtysec , switch_sports , switch_teen_patti;
    LinearLayout dropdown_language,layout_push_notification_options , layout_language_options,accout_logout,dropdown_push_notification;
    TextView tv_logout;
    ImageView dropdown_language_arrow, dropdown_push_notification_arrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_setting, container, false);

        //Do your main coding of this page betweem this

        switch_crypto=(Switch) view.findViewById(R.id.crypto_push_notify);
        switch_fx=(Switch) view.findViewById(R.id.fx_push_notify);
        switch_indie=(Switch) view.findViewById(R.id.indie_push_notify);
        switch_sixtysec=(Switch) view.findViewById(R.id.sixtysec_push_notify);
        switch_sports=(Switch) view.findViewById(R.id.sports_push_notify);
        switch_teen_patti=(Switch) view.findViewById(R.id.teen_patti_push_notify);

        layout_push_notification_options = view.findViewById(R.id.layout_push_notification_options);
        layout_language_options= view.findViewById(R.id.layout_language_options);

        layout_language_options.setOnClickListener(this);
        layout_push_notification_options.setOnClickListener(this);

        dropdown_language=view.findViewById(R.id.dropdown_language);
        dropdown_push_notification=view.findViewById(R.id.dropdown_push_notification);

        dropdown_language_arrow=view.findViewById(R.id.dropdown_language_arrow);
        dropdown_push_notification_arrow=view.findViewById(R.id.dropdown_push_notification_arrow);

        tv_logout=view.findViewById(R.id.tv_logout);

        dropdown_push_notification.setOnClickListener(this);
        dropdown_language.setOnClickListener(this);

        tv_logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_logout:
                Intent i = new Intent(getContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;

  /*          case R.id.crypto_push_notify:
                changeCryptoswitch();
                break;

            case R.id.fx_push_notify:
                changeFXswitch();
                break;

            case R.id.indie_push_notify:
                changeIndieswitch();
                break;

            case R.id.sixtysec_push_notify:
                changeSixtysecswitch();
                break;

            case R.id.sports_push_notify:
                changeSportsswitch();
                break;

            case R.id.teen_patti_push_notify:
                changeTeenPattiswitch();
                break;*/

            case R.id.dropdown_language:
                dropdownLanguage();
                break;

            case R.id.dropdown_push_notification:
                dropdownPushNotification();
                break;
        }
    }

    private void dropdownLanguage(){
        if(layout_language_options.getVisibility() == View.GONE){

            layout_language_options.setVisibility(View.VISIBLE);
            dropdown_language_arrow.setImageDrawable(getResources().getDrawable(R.drawable.arrowup_general));

        } else{

            layout_language_options.setVisibility(View.GONE);
            dropdown_language_arrow.setImageDrawable(getResources().getDrawable(R.drawable.drop_down_general));

        }
    }

    private void dropdownPushNotification(){
        if(layout_push_notification_options.getVisibility() == View.GONE){

            layout_push_notification_options.setVisibility(View.VISIBLE);
            dropdown_push_notification_arrow.setImageDrawable(getResources().getDrawable(R.drawable.arrowup_general));

        } else{

            layout_push_notification_options.setVisibility(View.GONE);
            dropdown_push_notification_arrow.setImageDrawable(getResources().getDrawable(R.drawable.drop_down_general));

        }

    }

    private void changeCryptoswitch() {
        if(!switch_crypto.isChecked()){
            switch_crypto.setChecked(true);
        }
        else {
            switch_crypto.setChecked(false);
        }
    }

    private void changeFXswitch() {
        if(!switch_fx.isChecked()){
            switch_fx.setChecked(true);
        }
        else {
            switch_fx.setChecked(false);
        }
    }

    private void changeIndieswitch() {
        if(!switch_indie.isChecked()){
            switch_indie.setChecked(true);
        }
        else {
            switch_indie.setChecked(false);
        }
    }

    private void changeSixtysecswitch(){
        if(!switch_sixtysec.isChecked()){
            switch_sixtysec.setChecked(true);
        }
        else {
            switch_sixtysec.setChecked(false);
        }
    }

    private void changeSportsswitch(){
        if(!switch_sports.isChecked()){
            switch_sports.setChecked(true);
        }
        else {
            switch_sports.setChecked(false);
        }
    }

    private void changeTeenPattiswitch(){
        if(!switch_teen_patti.isChecked()){
            switch_teen_patti.setChecked(true);
        }
        else {
            switch_teen_patti.setChecked(false);
        }
    }
}
