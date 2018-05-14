package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class BookMakingAdapter extends RecyclerView.Adapter<BookMakingAdapter.MyViewHolder> {

    Context contextBook;
    private List<MarketData> dataList;
    ArrayList<String> arrayExposerName = new ArrayList<String>();
    ArrayList<String> arrayExposerValue = new ArrayList<String>();

    Handler handler = new Handler();

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtVRunnerName,txtVBackData,txtVBackChips,txtVLayData,txtVLayChips,txtVExposerData,txtVActiveResult;
        LinearLayout llBack,llLay,llMarketData;

        public MyViewHolder(View view) {
            super(view);
            txtVRunnerName = view.findViewById(R.id.txtVRunnerName);
            txtVBackData = view.findViewById(R.id.txtVBackData);
            txtVBackChips = view.findViewById(R.id.txtVBackChips);
            txtVLayData = view.findViewById(R.id.txtVLayData);
            txtVLayChips = view.findViewById(R.id.txtVLayChips);
            txtVExposerData = view.findViewById(R.id.txtVExposerData);
            txtVActiveResult = view.findViewById(R.id.txtVActiveResult);
            llBack = view.findViewById(R.id.llBack);
            llLay = view.findViewById(R.id.llLay);
            llMarketData = view.findViewById(R.id.llMarketData);

        }
    }

    public BookMakingAdapter(Context contextBook, List<MarketData> dataList){
        this.contextBook = contextBook;
        this.dataList = dataList;
    }

    String mRunnerName;
    int bookId,runnerId;
    double mBack,mChipsBack,mLay,mChipsLay;
//    String bmBallStatus,bmBook,bmName;
//    int bmId,bmBackPrice,bmBackSize,bmLayPrice,bmLaySize;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MarketData book = dataList.get(position);

        handler.post(new Runnable() {
            @Override
            public void run() {
                mRunnerName=book.bmName;
                mBack=book.bmBackPrice;
                mChipsBack=book.bmBackSize;
                mLay=book.bmLayPrice;
                mChipsLay=book.bmLaySize;
                bookId = book.bmId;
                runnerId = book.bmRunnerId;

                holder.txtVRunnerName.setText(mRunnerName);
                holder.txtVBackData.setText(String.valueOf(mBack));
                holder.txtVBackChips.setText(String.valueOf(mChipsBack));
                holder.txtVLayData.setText(String.valueOf(mLay));
                holder.txtVLayChips.setText(String.valueOf(mChipsLay));

                //Log.i("ROHITlk",mLay+" "+mChipsLay);
                if(book.bmBook != null){
                    try {
                        int bookVal = Integer.parseInt(book.bmBook);
                        if(bookVal <0 ){
                            holder.txtVExposerData.setText(String.valueOf(bookVal));
                            holder.txtVExposerData.setTextColor(ContextCompat.getColor(contextBook,R.color.colorRed));
                        }
                        else if (bookVal>0){
                            holder.txtVExposerData.setText(String.valueOf(bookVal));
                            holder.txtVExposerData.setTextColor(ContextCompat.getColor(contextBook,R.color.colorGreen));
                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }catch (NullPointerException n){
                        n.printStackTrace();
                    }
                }

                new getExposerAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/ExposureBook?mktid="+bookId);
            }
        });




        holder.llLay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /*if(DataHolder.getData(contextBook,"OneClickBet").equals("true")){
                        dialogOneClickBet(mRunnerName,R.color.colorRedBetTrasparent,mLay,"Lay");
                    }else {

                    }*/
                    dialogBetPlace(mRunnerName,R.color.colorRedBetTrasparent,Double.valueOf(book.bmLayPrice),"Lay");
                    //Log.i("TAG12356","Touchll");
                }
                return false;
            }
        });

        holder.llBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                /*if(DataHolder.getData(contextBook,"OneClickBet").equals("true")){
                    dialogOneClickBet(mRunnerName,R.color.colorBlueBetTrasparent,mBack,"Back");
                }else {

                }*/
                    dialogBetPlace(mRunnerName, R.color.colorBlueBetTrasparent, Double.valueOf(book.bmBackPrice), "Back");
                    //Log.i("TAG12356", "Touchll");
                }
                return false;
            }
        });


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_market_data,parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    TextView txtVProfitValue,txtVOddIncrement,txtVOddDecrement,txtVStackDecrement,txtVStackIncrement;
    EditText editTxtStackValue,editTxtVOddValue;
    Button btnBetPlace;
    LinearLayout llDialogBetPlace;
    String ODDVALUE,STACKVALUE,PROFITVALUE;

    public void dialogBetPlace(final String RunnerTitle,int color,final double oddValue,final String BackLay){

        final Dialog dialog = new Dialog(contextBook,R.style.Dialog);
        dialog.setContentView(R.layout.dialog_bet_place);
        dialog.setTitle(RunnerTitle);
        dialog.getWindow().setBackgroundDrawableResource(color);

        //txtVRunnerTitle = dialog.findViewById(R.id.txtVRunnerTitle);
        llDialogBetPlace = dialog.findViewById(R.id.llDialogBetPlace);
        editTxtStackValue = dialog.findViewById(R.id.editTxtStackValue);
        editTxtVOddValue = dialog.findViewById(R.id.editTxtVOddValue);
        txtVProfitValue = dialog.findViewById(R.id.txtVProfitValue);
        txtVOddIncrement = dialog.findViewById(R.id.txtVOddIncrement);
        txtVOddDecrement = dialog.findViewById(R.id.txtVOddDecrement);
        txtVStackDecrement = dialog.findViewById(R.id.txtVStackDecrement);
        txtVStackIncrement = dialog.findViewById(R.id.txtVStackIncrement);
        btnBetPlace = dialog.findViewById(R.id.btnBetPlace);
        txtVOddDecrement.setVisibility(View.GONE);
        txtVOddIncrement.setVisibility(View.GONE);
        editTxtVOddValue.setEnabled(false);
        //llDialogBetPlace.setBackgroundColor(ContextCompat.getColor(contextMarket, color));
        //txtVRunnerTitle.setText(RunnerTitle);
        STACKVALUE = String.valueOf(DataHolder.STACK_VALUE);
        editTxtStackValue.setText(STACKVALUE);
        ODDVALUE = String.valueOf(oddValue);
        editTxtVOddValue.setText(ODDVALUE);

        PROFITVALUE = String.format("Profit CHIPS %.2f", DataHolder.profit(oddValue,DataHolder.STACK_VALUE));
        txtVProfitValue.setText(PROFITVALUE);


        txtVOddIncrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double oddVal = 0.0;
                try {
                    oddVal = Double.valueOf(ODDVALUE);
                } catch (NumberFormatException e) {
                    oddVal = 0.0;
                    e.printStackTrace();
                }
                double inc = DataHolder.increment(oddVal);
                ODDVALUE = String.format("%.2f",inc);
                editTxtVOddValue.setText(ODDVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText(PROFITVALUE);
            }
        } );

        txtVOddDecrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double oddVal = 0.0;
                try {
                    oddVal = Double.valueOf(ODDVALUE);
                } catch (NumberFormatException e) {
                    oddVal = 0.0;
                    e.printStackTrace();
                }

                double dec = DataHolder.decrement(oddVal);
                ODDVALUE = String.format("%.2f",dec);
                editTxtVOddValue.setText(ODDVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText(PROFITVALUE);
            }
        });

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
                //Log.i("TAG",stackVal+"");
                double dec = DataHolder.decrement(stackVal);
                //Log.i("TAG",dec+"");
                STACKVALUE = String.format("%.2f",dec);
                editTxtStackValue.setText(STACKVALUE);

                PROFITVALUE = String.format("Profit CHIPS %.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
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

                PROFITVALUE = String.format("%.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                txtVProfitValue.setText("Profit CHIPS "+PROFITVALUE);
            }
        });

        btnBetPlace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DataHolder.showProgress(contextBook);
                new BetPlaceBookAsyncTask().execute(BackLay,ODDVALUE,STACKVALUE,RunnerTitle);
                dialog.cancel();
            }
        });

        editTxtVOddValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0 && s.length() <=5)  {
                    ODDVALUE = s.toString();
                    PROFITVALUE = String.format("Profit CHIPS %.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                    txtVProfitValue.setText(PROFITVALUE);
                }
                else {
                    txtVProfitValue.setText("0.0");
                    editTxtVOddValue.setText("0.0");
                }
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
                    PROFITVALUE = String.format("Profit CHIPS %.2f", DataHolder.profit(Double.valueOf(ODDVALUE),Double.valueOf(STACKVALUE)));
                    txtVProfitValue.setText(PROFITVALUE);
                }else  {
                    txtVProfitValue.setText("0.0");
                    editTxtStackValue.setText("0.0");
                }
            }
        });

        dialog.show();
    }

//    public void dialogOneClickBet(final String RunnerTitle,int color,final double oddValue,final String BackLay){
//
//        final Dialog dialog = new Dialog(contextBook,R.style.Dialog);
//        dialog.setContentView(R.layout.dialog_one_click_bet);
//        dialog.setTitle("Please Confirm Your Bet");
//        dialog.getWindow().setBackgroundDrawableResource(color);
//
//        //txtVRunnerTitle = dialog.findViewById(R.id.txtVRunnerTitle);
//        LinearLayout llOneClickBet = dialog.findViewById(R.id.llOneClickBet);
//        TextView txtVOneClickTitle = dialog.findViewById(R.id.txtVOneClickTitle);
//        TextView txtVOddOneClickValue = dialog.findViewById(R.id.txtVOddOneClickValue);
//        TextView txtVStackOneClickValue = dialog.findViewById(R.id.txtVStackOneClickValue);
//        TextView txtVProfitOneClickValue = dialog.findViewById(R.id.txtVProfitOneClickValue);
//        Button btnOneClickCancel = dialog.findViewById(R.id.btnOneClickCancel);
//        Button btnOneClickConfirm = dialog.findViewById(R.id.btnOneClickConfirm);
//
//        txtVOneClickTitle.setText(RunnerTitle);
//
//        STACKVALUE = String.valueOf(DataHolder.STACK_VALUE);
//        txtVStackOneClickValue.setText(STACKVALUE);
//
//        ODDVALUE = String.valueOf(oddValue);
//        txtVOddOneClickValue.setText(ODDVALUE);
//
//
//        PROFITVALUE = String.format("%.2f", DataHolder.profit(oddValue,DataHolder.STACK_VALUE));
//        txtVProfitOneClickValue.setText(PROFITVALUE);
//
//
//        btnOneClickConfirm.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                new BetPlaceBookAsyncTask().execute(BackLay,ODDVALUE,STACKVALUE,RunnerTitle);
//                dialog.cancel();
//            }
//        });
//
//        btnOneClickCancel.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//
//        dialog.show();
//    }

    public String  BetPlaceApi(String backlay,String odds,String stake,String runnerName){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://173.212.248.188/pclient/Prince.svc/Bets/PlaceBMBet");

            String json = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("backlay",backlay);
            jsonObject.accumulate("info","Android");
            jsonObject.accumulate("bookId",bookId);
            jsonObject.accumulate("eventId",Integer.parseInt(DataHolder.getData(contextBook,"Match_Id")));
            jsonObject.accumulate("odds",odds);
            jsonObject.accumulate("runnerName",runnerName);
            jsonObject.accumulate("runnerId",runnerId);
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
                    Log.e("ERROR ",""+e);
                }
            }
            else
                result = "Did not work!";
            //Log.e("Check","how "+result);

        } catch (Exception e) {
            Log.d("ERROR ", ""+e);
        }
        return result;
    }

    private class BetPlaceBookAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(final String... urls) {
            return BetPlaceApi(urls[0],urls[1],urls[2],urls[3]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check",""+result);

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
            DataHolder.cancelProgress();
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

    public String  getBookExposerApi(String url){
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
                    Log.e("ERROR ",""+e);
                }
            }
            else
                result = "Did not work!";


        } catch (Exception e) {
            Log.d("ERROR ", ""+e);
        }
        return result;
    }

    private class getExposerAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getBookExposerApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.i("Check",""+result);
            //Toast.makeText(contextBook, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();

                for(int i=0; i<len; i++){
                    JSONObject key = jsonArray.getJSONObject(i);
                    arrayExposerName.add(key.getString("Key"));
                    arrayExposerValue.add(key.getString("Value"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
