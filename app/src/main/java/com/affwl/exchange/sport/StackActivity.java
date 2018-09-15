
package com.affwl.exchange.sport;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StackActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTxtStackValue1,editTxtStackValue2,editTxtStackValue3;
    Button btnStackSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        editTxtStackValue1 = findViewById(R.id.editTxtStackValue1);
        editTxtStackValue2 = findViewById(R.id.editTxtStackValue2);
        editTxtStackValue3 = findViewById(R.id.editTxtStackValue3);
        btnStackSave = findViewById(R.id.btnStackSave);
        btnStackSave.setOnClickListener(this);

        //new getStackAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Settings/GetBetStakeSetting");
        /*SharedPreferences prefs = getSharedPreferences("PREF_STACK", MODE_PRIVATE);
        String StackValue1 = prefs.getString("StackValue1", "0");
        String StackValue2 = prefs.getString("StackValue2", "0");
        String StackValue3 = prefs.getString("StackValue3", "0");*/

        editTxtStackValue1.setText(DataHolder.getSTACK(StackActivity.this,"StackValue1"));
        editTxtStackValue2.setText(DataHolder.getSTACK(StackActivity.this,"StackValue2"));
        editTxtStackValue3.setText(DataHolder.getSTACK(StackActivity.this,"StackValue3"));
    }

    public String  setStackApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost Httppost = new HttpPost(url);

            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("stake1",Integer.parseInt(editTxtStackValue1.getText().toString()));
            jsonObject.accumulate("stake2",Integer.parseInt(editTxtStackValue2.getText().toString()));
            jsonObject.accumulate("stake3",Integer.parseInt(editTxtStackValue3.getText().toString()));

            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json");

            Httppost.setEntity(new StringEntity(json));

            Httppost.setHeader("Accept", "application/json");
            Httppost.setHeader("Content-type", "application/json");
            Httppost.setHeader("Token", DataHolder.LOGIN_TOKEN);

            HttpResponse httpResponse = httpclient.execute(Httppost);
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

    private class setStackAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return setStackApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("status");
                Log.i("TAG",""+strData);
                if (strData.trim().equalsIgnoreCase("Success")){
                    DataHolder.setSTACK(StackActivity.this,"StackValue1",editTxtStackValue1.getText().toString());
                    DataHolder.setSTACK(StackActivity.this,"StackValue2",editTxtStackValue2.getText().toString());
                    DataHolder.setSTACK(StackActivity.this,"StackValue3",editTxtStackValue3.getText().toString());

                    DataHolder.setSTACK(StackActivity.this,"ChipsValue",editTxtStackValue1.getText().toString());
                    DataHolder.STACK_VALUE = Double.valueOf(editTxtStackValue1.getText().toString());
                }
                Toast.makeText(StackActivity.this, ""+strData, Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(StackActivity.this,result);
            }
            DataHolder.cancelProgress();
        }
    }

    public String  getStackApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet Httpget = new HttpGet(url);

            Httpget.setHeader("Accept", "application/json");
            Httpget.setHeader("Content-type", "application/json");
            Httpget.setHeader("Token", DataHolder.LOGIN_TOKEN);

            HttpResponse httpResponse = httpclient.execute(Httpget);
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
        return result;
    }

    private class getStackAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getStackApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                Log.i("TAG",""+strData);

                //Toast.makeText(StackActivity.this, ""+strData, Toast.LENGTH_SHORT).show();

                JSONObject jsonObj = new JSONObject(jsonObjMain.getString("data"));

                editTxtStackValue1.setText(jsonObj.getString("stake1"));
                editTxtStackValue2.setText(jsonObj.getString("stake2"));
                editTxtStackValue3.setText(jsonObj.getString("stake3"));

            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(StackActivity.this,result);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStackSave:

                if(editTxtStackValue1.getText().toString().equals("") || editTxtStackValue1.getText().toString() == null){
                    editTxtStackValue1.setError("Enter Stack Value");
                }else if(editTxtStackValue2.getText().toString().equals("") || editTxtStackValue2.getText().toString() == null){
                    editTxtStackValue2.setError("Enter Stack Value");
                }else if(editTxtStackValue3.getText().toString().equals("") || editTxtStackValue3.getText().toString() == null){
                    editTxtStackValue3.setError("Enter Stack Value");
                }else {
                    DataHolder.showProgress(StackActivity.this);
                    new setStackAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Settings/SaveBetStakeSetting");
                }

                break;
        }
    }
}
