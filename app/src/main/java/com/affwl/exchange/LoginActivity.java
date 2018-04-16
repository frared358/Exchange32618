package com.affwl.exchange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
                        //Toast.makeText(this, ""+editUser+" "+editPass, Toast.LENGTH_SHORT).show();
                        saveLoginDetails(editUser,editPass);
                    }

                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                else {
                    new HttpAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Login");
                    //Toast.makeText(this, "Please check User name & Password", Toast.LENGTH_SHORT).show();
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

    public String  loginApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("context","mobile");
            jsonObject.accumulate("pwd",editPass);
            jsonObject.accumulate("username",editUser);

            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json");

            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                try {
                    result = convertInputStreamToString(inputStream);
                }
                catch (Exception e){
                    Log.e("Check",""+e);
                }
            }
            else
                result = "Did not work!";
            Log.e("Check","how "+result);

        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }

        Log.e("result",result+"");
        //Toast.makeText(SelectSymbolActivity.this, ""+result, Toast.LENGTH_SHORT).show();
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
            Log.e("Line",result);
        }

        inputStream.close();
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return loginApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                String description = jsonObjMain.getString("description");
                String response = jsonObjMain.getString("response");

                JSONObject jsonObjRes = new JSONObject(response.toString());
                JSONObject jsonObjDes = new JSONObject(description.toString());
                DataHolder.LOGIN_TOKEN = jsonObjRes.getString("AuthToken");
                String status = jsonObjDes.getString("result");

                if(status.equals("Login Successful")){
                    Toast.makeText(getApplicationContext(), ""+status, Toast.LENGTH_SHORT).show();
                    if(rememberCheckBox.isChecked())
                    {
                        saveLoginDetails(editUser,editPass);
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                Log.i("result","result "+DataHolder.LOGIN_TOKEN+" Status "+status);

            } catch (JSONException e) {
                e.printStackTrace();
                JSONObject jsonObjMain = null;
                try {
                    jsonObjMain = new JSONObject(result.toString());
                    JSONObject des  = new JSONObject(jsonObjMain.getString("description"));
                    String res = des.getString("result");
                    if (res.equals("Invalid username")){
                        Toast.makeText(LoginActivity.this,"Invalid Username",Toast.LENGTH_SHORT).show();

                    }else if (res.equals("Invalid password")){
                        Toast.makeText(LoginActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
