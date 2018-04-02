package com.affwl.exchange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText editUserEmail,editPassDTS;
    Button loginNow;
    CheckBox rememberCheckBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String editUser,editPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponent();
    }

    private void initComponent() {

        editUserEmail=findViewById(R.id.editUserEmail);
        editPassDTS=findViewById(R.id.editPassDTS);
        loginNow=findViewById(R.id.loginNow);
        rememberCheckBox=findViewById(R.id.rememberCheckBox);

        loginNow.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("Email")) {
            editUserEmail.setText(sharedPreferences.getString("Email", ""));
        }
        if (sharedPreferences.contains("Password")) {
            editPassDTS.setText(sharedPreferences.getString("Password", ""));
            rememberCheckBox.setChecked(true);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginNow:
                editUser=editUserEmail.getText().toString();
                editPass=editPassDTS.getText().toString();
                if(editUser.equalsIgnoreCase("")||editUser==null){
                    editUserEmail.setError("Enter User Name");
                }
                else if(editPass.equalsIgnoreCase("")||editPass==null){
                    editPassDTS.setError("Enter Password");
                }
                else if(editUser.equalsIgnoreCase("admin")&& editPass.equals("admin"))
                {
                    if(rememberCheckBox.isChecked())
                    {
                        Toast.makeText(this, ""+editUser+" "+editPass, Toast.LENGTH_SHORT).show();
                        saveLoginDetails(editUser,editPass);
                    }
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(this, "Please check User name & Password", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public void saveLoginDetails(String email, String password) {
        sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }
}
