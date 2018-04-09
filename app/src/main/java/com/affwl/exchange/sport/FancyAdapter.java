package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 4/5/2018.
 */

public class FancyAdapter extends RecyclerView.Adapter<FancyAdapter.MyViewHolder> {

    Context contextFancy;
    private List<MarketData> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtVFancyName,txtVYesRate,txtVYesScore,txtVNoRate,txtVNoScore,txtVBookData,txtVFancyActiveResult,txtVFancyBook;
        LinearLayout llYes,llNo,llFancyMarketData;

        public MyViewHolder(View view) {
            super(view);
            txtVFancyName = view.findViewById(R.id.txtVFancyName);
            txtVYesRate = view.findViewById(R.id.txtVYesScore);
            txtVYesScore = view.findViewById(R.id.txtVYesRate);
            txtVNoRate = view.findViewById(R.id.txtVNoRate);
            txtVNoScore = view.findViewById(R.id.txtVNoScore);
            txtVBookData = view.findViewById(R.id.txtVBookData);
            txtVFancyActiveResult = view.findViewById(R.id.txtVFancyActiveResult);
            llYes = view.findViewById(R.id.llYes);
            llNo = view.findViewById(R.id.llNo);
            llFancyMarketData = view.findViewById(R.id.llFancyMarketData);
            txtVFancyBook = view.findViewById(R.id.txtVFancyBook);
            txtVFancyBook.setVisibility(View.VISIBLE);
        }
    }

    public FancyAdapter(Context contextFancy, List<MarketData> dataList){
        this.contextFancy = contextFancy;
        this.dataList = dataList;
    }

    int fancyId;
    String fancyScore,fancyRate,fancyRunnerName;
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MarketData fancy = dataList.get(position);

        holder.txtVFancyName.setText(fancy.RunnerName);
        holder.txtVYesRate.setText(fancy.yesRate);
        holder.txtVYesScore.setText(fancy.yesScore);
        holder.txtVNoRate.setText(fancy.noRate);
        holder.txtVNoScore.setText(fancy.noScore);
        fancyRunnerName = fancy.RunnerName;
        fancyId = fancy.fancyId;

        if (!fancy.book.equals("-")){
        try {
            int bookVal = Integer.parseInt(fancy.book);
            if(bookVal <0 ){
                holder.txtVBookData.setText(String.valueOf(bookVal));
                holder.txtVBookData.setTextColor(ContextCompat.getColor(contextFancy,R.color.colorRed));
            }
            else if (bookVal>0){
                holder.txtVBookData.setText(String.valueOf(bookVal));
                holder.txtVBookData.setTextColor(ContextCompat.getColor(contextFancy,R.color.colorGreen));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

        if(!fancy.ballStatus.equalsIgnoreCase("")){
            holder.txtVFancyActiveResult.setVisibility(View.VISIBLE);
            holder.llFancyMarketData.setVisibility(View.GONE);
            holder.txtVFancyActiveResult.setText(fancy.ballStatus);
        }
        holder.llYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = fancy.yesScore+"/"+fancy.yesRate;
                fancyRate = fancy.yesRate;
                fancyScore = fancy.yesScore;
                dialogBetPlace(fancy.RunnerName,R.color.colorBlueBet,score,"yes");
            }
        });

        holder.llNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = fancy.noScore+"/"+fancy.noRate;
                fancyRate = fancy.noRate;
                fancyScore = fancy.noScore;
                dialogBetPlace(fancy.RunnerName,R.color.colorRedBet,score,"no");
            }
        });

        holder.txtVFancyBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String matchId = DataHolder.getData(contextFancy,"Match_Id");
                new FancyBookAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/Fancybook?mtid="+matchId+"&fid="+fancyId);
            }
        });
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_fancy_data,parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void dialogFancyBook(){
        final Dialog dialog = new Dialog(contextFancy);
        dialog.setContentView(R.layout.dialog_fancy_book_list);
        dialog.setTitle(fancyRunnerName.toUpperCase());
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorGreay);
        Button btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog);
        LinearLayout llFAncyBook = dialog.findViewById(R.id.llFAncyBook);
        LinearLayout llFAncyBookKey = dialog.findViewById(R.id.llFAncyBookKey);
        LinearLayout llFAncyBookValue = dialog.findViewById(R.id.llFAncyBookValue);
        float TEN = contextFancy.getResources().getDimension(R.dimen.sp10);
        float ZERO = contextFancy.getResources().getDimension(R.dimen.sp10);
        try{
            for (int i = 0; i < KeyFancyBook.size() ; i++)
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,5,0,5);
                TextView tvScore = new TextView(contextFancy);
                TextView tvAmt = new TextView(contextFancy);
                tvScore.setTextSize(10);
                tvAmt.setTextSize(10);
                tvScore.setGravity(Gravity.CENTER);
                tvAmt.setGravity(Gravity.CENTER);
                tvAmt.setPadding(10,0,10,0);
                tvScore.setPadding(10,0,10,0);
                tvAmt.setLayoutParams(params);
                tvScore.setLayoutParams(params);
                tvScore.setText(KeyFancyBook.get(i));
                tvAmt.setText(ValueFancyBook.get(i));
                if(Integer.parseInt(ValueFancyBook.get(i))<0){
                    tvScore.setBackgroundColor(ContextCompat.getColor(contextFancy,R.color.colorRedBet));
                    tvAmt.setBackgroundColor(ContextCompat.getColor(contextFancy,R.color.colorRedBet));
                }else {
                    tvScore.setBackgroundColor(ContextCompat.getColor(contextFancy,R.color.colorBlueBet));
                    tvAmt.setBackgroundColor(ContextCompat.getColor(contextFancy,R.color.colorBlueBet));
                }

                llFAncyBookKey.addView(tvScore);
                llFAncyBookValue.addView(tvAmt);
                Log.i("ValueFancyBook.get(i)",ValueFancyBook.get(i));
                Log.i("KeyFancyBook.get(i)",KeyFancyBook.get(i));
            }
        }
        catch(Exception e){
            Log.i("CountryCount1",e.toString());
        }

        btnCancelDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    TextView txtVProfitValue,txtVOddIncrement,txtVOddDecrement,txtVStackDecrement,txtVStackIncrement,txtVOddScore;
    EditText editTxtStackValue,editTxtVOddValue;
    Button btnBetPlace;
    LinearLayout llDialogBetPlace;
    String STACKVALUE,PROFITVALUE;

    public void dialogBetPlace(final String RunnerTitle,int color, String scoreValue,final String YesNo){

        final String YES_NO;
        final Dialog dialog = new Dialog(contextFancy,R.style.Dialog);
        dialog.setContentView(R.layout.dialog_bet_place);
        dialog.setTitle(RunnerTitle);
        dialog.getWindow().setBackgroundDrawableResource(color);

        llDialogBetPlace = dialog.findViewById(R.id.llDialogBetPlace);
        editTxtStackValue = dialog.findViewById(R.id.editTxtStackValue);
        editTxtVOddValue = dialog.findViewById(R.id.editTxtVOddValue);
        txtVProfitValue = dialog.findViewById(R.id.txtVProfitValue);
        txtVOddIncrement = dialog.findViewById(R.id.txtVOddIncrement);
        txtVOddDecrement = dialog.findViewById(R.id.txtVOddDecrement);
        txtVStackDecrement = dialog.findViewById(R.id.txtVStackDecrement);
        txtVStackIncrement = dialog.findViewById(R.id.txtVStackIncrement);
        btnBetPlace = dialog.findViewById(R.id.btnBetPlace);
        txtVOddScore = dialog.findViewById(R.id.txtVOddScore);
        txtVOddScore.setText("SCORE");
        txtVOddDecrement.setVisibility(View.GONE);
        txtVOddIncrement.setVisibility(View.GONE);
        editTxtVOddValue.setEnabled(false);
        editTxtVOddValue.setText(scoreValue);
        //llDialogBetPlace.setBackgroundColor(ContextCompat.getColor(contextMarket, color));
        //txtVRunnerTitle.setText(RunnerTitle);
        STACKVALUE = String.valueOf(DataHolder.STACK_VALUE);
        editTxtStackValue.setText(STACKVALUE);

        if(YesNo.equalsIgnoreCase("yes")){
            YES_NO = "Profit";
            txtVProfitValue.setTextColor(ContextCompat.getColor(contextFancy,R.color.colorGreen));
        }else {
            YES_NO = "Liability";
            txtVProfitValue.setTextColor(ContextCompat.getColor(contextFancy,R.color.colorRed));
        }


        PROFITVALUE = String.format(YES_NO+" %.2f", DataHolder.STACK_VALUE);

        txtVProfitValue.setText(PROFITVALUE);

        txtVStackDecrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double stackVal = 0.0;
                try {
                    stackVal = Double.valueOf(STACKVALUE);
                } catch (NumberFormatException e) {
                    stackVal = 0.0;
                    e.printStackTrace();
                }
                double dec = DataHolder.decrement(stackVal);
                STACKVALUE = String.format("%.2f",dec);
                editTxtStackValue.setText(STACKVALUE);

                PROFITVALUE = String.format(YES_NO+" %.2f", Double.valueOf(STACKVALUE));
                txtVProfitValue.setText(PROFITVALUE);
            }
        });

        txtVStackIncrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double stackVal = 0.0;
                try {
                    stackVal = Double.valueOf(STACKVALUE);
                } catch (NumberFormatException e) {
                    stackVal = 0.0;
                    e.printStackTrace();
                }
                double inc = DataHolder.increment(stackVal);
                STACKVALUE = String.format("%.2f",inc);
                editTxtStackValue.setText(STACKVALUE);

                PROFITVALUE = String.format(YES_NO+" %.2f", Double.valueOf(STACKVALUE));
                txtVProfitValue.setText(PROFITVALUE);
            }
        });

        btnBetPlace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new BetPlaceFancyAsyncTask().execute(YesNo,STACKVALUE,RunnerTitle);
                dialog.cancel();
            }
        });


        editTxtStackValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0 && Double.valueOf(s.toString().trim()) <=100000 && s.length() < 10)  {
                    STACKVALUE = s.toString();
                    PROFITVALUE = String.format(YES_NO+" %.2f", Double.valueOf(STACKVALUE));
                    txtVProfitValue.setText(PROFITVALUE);
                }else  {
                    txtVProfitValue.setText("0.0");
                    editTxtStackValue.setText("0.0");
                }
            }
        });

        dialog.show();
    }

    public String  BetPlaceApi(String yesno,String stake,String runnerName){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://173.212.248.188/pclient/Prince.svc/Bets/PlaceFancyBet");

            Log.i("Check",yesno+" "+fancyId+" "+fancyRate+" "+fancyScore+" "+runnerName+" "+Integer.parseInt(DataHolder.getData(contextFancy,"Match_Id")));
            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("yesno",yesno);
            jsonObject.accumulate("info","Android");
            jsonObject.accumulate("fancyId",fancyId);
            jsonObject.accumulate("matchId",Integer.parseInt(DataHolder.getData(contextFancy,"Match_Id")));
            jsonObject.accumulate("rate",fancyRate);
            jsonObject.accumulate("score",fancyScore);
            jsonObject.accumulate("runnerName",runnerName);
            jsonObject.accumulate("source","Mobile");
            jsonObject.accumulate("stake",stake);


            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json");

            httpPost.setEntity(new StringEntity(json));

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Token", DataHolder.LOGIN_TOKEN);

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
        return result;
    }

    private class BetPlaceFancyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {
            return BetPlaceApi(urls[0],urls[1],urls[2]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String status = jsonObjMain.getString("status");
                String msg = jsonObjMain.getString("result");
                if(status.equalsIgnoreCase("Success")){
                    Snackbar snackbar = Snackbar.make(BetActivity.scrollBetActivity, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    Snackbar snackbar = Snackbar.make(BetActivity.scrollBetActivity, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        inputStream.close();
        return result;
    }

    public String  FancyBookApi(String url){
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


        } catch (Exception e) {
            Log.d("InputStream", ""+e);
        }
        return result;
    }

    ArrayList<String> KeyFancyBook = new ArrayList<>();
    ArrayList<String> ValueFancyBook = new ArrayList<>();

    private class FancyBookAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {
            return FancyBookApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            //Toast.makeText(contextFancy, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();
                for(int i=0; i<len; i++){
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String Key = obj.getString("Key");
                    String Value = obj.getString("Value");
                    KeyFancyBook.add(Key);
                    ValueFancyBook.add(Value);
                }

                dialogFancyBook();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
