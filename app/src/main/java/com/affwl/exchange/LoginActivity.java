package com.affwl.exchange;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin;
    EditText editEmailId,editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        editEmailId = findViewById(R.id.editEmailId);
        editPassword = findViewById(R.id.editPassword);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                new HttpAsyncTask().execute("http://5.189.140.198/Prince99/Prince.svc/Login");
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    public String  loginApi(String url){

        InputStream inputStream = null;

        String result = "";
        try {
            Log.e("Check","Setgs");

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost HttpPost = new HttpPost(url);
            Log.e("Check","aew4hgwreeth");

            String json = "";
            Log.e("Check","aewg");

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("context","mobile");
            jsonObject.accumulate("pwd","123456");
            jsonObject.accumulate("username","test");


            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);
            se.setContentType("application/json");

            HttpPost.setEntity(new StringEntity(json));

            HttpPost.setHeader("Accept", "application/json");
            HttpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(HttpPost);
            Log.e("Check","tjhttjj");

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                try {
                    result = convertInputStreamToString(inputStream);
                    Log.e("Check","hey");

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
        //Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();
            Log.i("Check",""+result);
        }
    }

//    static String check,token;
//    public static String POST(String url){
//        InputStream inputStream = null;
//
//        String result = "";
//
//        /*if(!isConnected()){
//            Toast.makeText(context.getApplicationContext(),"You are not conncted",Toast.LENGTH_LONG).show();
//            Log.i("connect","You are not conncted");
//            DataHolder.hideProgressDialog();
//        }*/
//
//        try {
//
//            HttpClient httpclient = new DefaultHttpClient();
//
//            HttpPost httpPost = new HttpPost(url);
//
//            String json = "";
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("context","mobile");
//            jsonObject.accumulate("pwd","123456");
//            jsonObject.accumulate("username","test");
//            json = jsonObject.toString();
//            check = "json : "+json+"\n";
//
//            StringEntity se = new StringEntity(json);
//            se.setContentType("application/json");
//            check = check +"se : "+ se+"\n";
//            httpPost.setEntity(se);
//            check = check +"httpPost : "+ httpPost+"\n";
//
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//
//            check = check +"httpPostGetContentLength : "+ httpPost.getEntity().getContentLength()+"\n";
//
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//            check = check +"httpResponse : "+ httpResponse+"\n";
//            check = check +"httpResponseGetContentLength : "+ httpResponse.getEntity().getContentLength()+"\n";
//
//            inputStream = httpResponse.getEntity().getContent();
//            check = check +"inputStream : "+ inputStream+"\n";
//
//            if(inputStream != null){
//                try{
//                    result = convertInputStreamToString(inputStream);
//                    check = check +"result : "+ result+"\n";
//                }
//                catch (Exception e){
//                    Log.e("Check",""+e);
//                    check = check+"error : "+ e+"\n";
//                }
//            }
//            else
//                result = "Did not work!";
//
//        } catch (Exception e) {
//            Log.d("InputStream", e.toString());
//            check = check+"error : "+ e+"\n";
//        }
//
//        Log.e("result",result+"");
//        check = check+"result : "+ result+"\n";
//        Log.e("check1234",check+"");
//        token = result;
//        return result;
//    }
}
