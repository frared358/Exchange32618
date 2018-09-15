package com.affwl.exchange.sport;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class SportAccountActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTxtBetHistoryFrom,editTxtBetHistoryTo,editTxtBetHistoryFromtime,editTxtBetHistoryTotime;
    RecyclerView recyclerViewBetHistory;
    BetHistoryAdapter betHistoryAdapter;
    TextView txtVBetHistoryNoData;
    Button btnBetHistory;
    RadioGroup radioGroupBetStatus;
    int filterBetHistory;
    String from,to,time;
    Spinner spinnerBetHistoryDate;
    Date dateFrom,dateTo,dateCurrent;
    static Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
    private List<Data> dataList = new ArrayList<>();

    LinearLayout llSportAccountHome,llSportAccountHighlight;
    ImageView imgVSportAccountHome,imgVSportAccountHighlight;
    TextView txtVSportAccountHome,txtVSportAccountHighlight;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_account);

        llSportAccountHome = findViewById(R.id.llSportAccountHome);
        llSportAccountHighlight = findViewById(R.id.llSportAccountHighlight);
        imgVSportAccountHome = findViewById(R.id.imgVSportAccountHome);
        imgVSportAccountHighlight = findViewById(R.id.imgVSportAccountHighlight);
        txtVSportAccountHome = findViewById(R.id.txtVSportAccountHome);
        txtVSportAccountHighlight = findViewById(R.id.txtVSportAccountHighlight);

        llSportAccountHome.setOnClickListener(this);
        llSportAccountHighlight.setOnClickListener(this);
        imgVSportAccountHome.setOnClickListener(this);
        imgVSportAccountHighlight.setOnClickListener(this);
        txtVSportAccountHome.setOnClickListener(this);
        txtVSportAccountHighlight.setOnClickListener(this);

        editTxtBetHistoryFrom = findViewById(R.id.editTxtBetHistoryFrom);
        editTxtBetHistoryTo = findViewById(R.id.editTxtBetHistoryTo);
        editTxtBetHistoryFromtime = findViewById(R.id.editTxtBetHistoryFromtime);
        editTxtBetHistoryTotime = findViewById(R.id.editTxtBetHistoryTotime);

        editTxtBetHistoryFrom.setOnClickListener(this);
        editTxtBetHistoryTo.setOnClickListener(this);
        editTxtBetHistoryFromtime.setOnClickListener(this);
        editTxtBetHistoryTotime.setOnClickListener(this);

        recyclerViewBetHistory = findViewById(R.id.recyclerViewBetHistory);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewBetHistory.setLayoutManager(mLayoutManager);
        recyclerViewBetHistory.setItemAnimator(new DefaultItemAnimator());
        betHistoryAdapter = new BetHistoryAdapter(this,dataList);

        txtVBetHistoryNoData = findViewById(R.id.txtVBetHistoryNoData);

        btnBetHistory = findViewById(R.id.btnBetHistory);
        btnBetHistory.setOnClickListener(this);

        radioGroupBetStatus = findViewById(R.id.radioGroupBetStatus);

        spinnerBetHistoryDate = findViewById(R.id.spinnerBetHistoryDate);
        spinnerBetHistoryDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                to = df.format(c.getTime());

                if (position == 0){
                    from = calender(0);
                }
                else if (position == 1){
                    from = calender(1);
                }
                else if (position == 2){
                    from =calender(7);
                }
                else if (position == 3){
                    from = calender(30);
                }
                else if (position == 4){
                    from = calender(60);
                }
                else if (position == 5){
                    from = calender(90);
                }

                new getBetHistoryAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetBetHistory?from="+from+"%2000:00:00&to="+to+"%20"+time+"&f="+filter());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        from = df.format(c.getTime());
        to = df.format(c.getTime());

        try {
            dateFrom = df.parse(from);
            dateTo = df.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        time = dfTime.format(c.getTime());
        editTxtBetHistoryFrom.setText(from);
        editTxtBetHistoryTo.setText(to);
        new getBetHistoryAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetBetHistory?from="+from+"%2000:00:00&to="+to+"%20"+time+"&f=4");

    }


    RadioButton radio;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editTxtBetHistoryFrom:
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialogFrom = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        from =  year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth ;
                        editTxtBetHistoryFrom.setText(from);
                        try {
                            dateFrom = df.parse(from);
                            if(new Date().before(df.parse(from))){
                                from =  df.format(c.getTime()) ;
                                editTxtBetHistoryFrom.setText(from);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, year, month, day);

                datePickerDialogFrom.show();
                break;

            case R.id.editTxtBetHistoryTo:
                int tYear = c.get(Calendar.YEAR);
                int tMonth = c.get(Calendar.MONTH);
                int tDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialogTo = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        to = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        editTxtBetHistoryTo.setText(to);
                        try {
                            dateTo = df.parse(to);
                            if(dateTo.before(dateFrom) || new Date().before(df.parse(to))){
                                to =  df.format(c.getTime()) ;
                                editTxtBetHistoryTo.setText(to);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, tYear, tMonth, tDay);

                datePickerDialogTo.show();
                break;
            case R.id.editTxtBetHistoryFromtime:
                time(editTxtBetHistoryFromtime);
                break;

            case R.id.editTxtBetHistoryTotime:
                time(editTxtBetHistoryTotime);
                break;

            case R.id.btnBetHistory:
                time = dfTime.format(c.getTime());
                new getBetHistoryAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetBetHistory?from="+from+"%2000:00:00&to="+to+"%20"+time+"&f="+filter());
                break;

            case R.id.llSportAccountHome:case R.id.imgVSportAccountHome:case R.id.txtVSportAccountHome:
                Intent intentHome = new Intent(this,SportActivity.class);
                intentHome.addFlags(FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentHome);
                break;

            case R.id.llSportAccountHighlight:case R.id.imgVSportAccountHighlight:case R.id.txtVSportAccountHighlight:
                startActivity(new Intent(this,HighlightsActivity.class));
                break;
        }
    }

    private String calender(int min){

        Calendar cal = Calendar.getInstance();
        String wantedDate;
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        try {
            cal.setTime(df.parse(year+"-"+(month+1)+"-"+day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE,-min);
        wantedDate = df.format(cal.getTime());
        return wantedDate;
    }

    private int filter(){

        int selectedId=radioGroupBetStatus.getCheckedRadioButtonId();
        radio=(RadioButton)findViewById(selectedId);
        if (radio.getText().toString().equalsIgnoreCase("Settled"))
        {
            return 4;
        }
        else
        {
            return 3;
        }
    }


    public void time(final EditText editTxt){



        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        editTxt.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();

        timePickerDialog.show();
    }


    private class getBetHistoryAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            dataList.clear();
            betHistoryAdapter.notifyDataSetChanged();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();

                if (len == 0){
                    txtVBetHistoryNoData.setVisibility(View.VISIBLE);
                }

                for (int i=0; i<len;i++){
                    JSONObject key = jsonArray.getJSONObject(i);

                    String selection = key.getString("selection");
                    String type = key.getString("type");
                    String avgOdds = key.getString("avgOdds");
                    String stake = key.getString("stake");
                    String pnl = key.getString("pnl");

                    dataList.add(new Data(selection,type,avgOdds,stake,pnl));
                    betHistoryAdapter.notifyDataSetChanged();

                    txtVBetHistoryNoData.setVisibility(View.GONE);
                }

                recyclerViewBetHistory.setAdapter(betHistoryAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(BetActivity.this,result);
            }
        }
    }
}


/*    public void date(final EditText editTxt){

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }*/