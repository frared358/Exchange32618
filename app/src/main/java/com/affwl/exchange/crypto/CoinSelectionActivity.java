package com.affwl.exchange.crypto;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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


public class CoinSelectionActivity extends Activity implements View.OnClickListener, SearchAdapter.ContactsAdapterListener{

    TextView txtVCoinSelctionCancel;

    private static final String TAG = CoinSelectionActivity.class.getSimpleName();
    private RecyclerView recyclerViewCoinSelection;
    private List<SearchItem> searchItemList;
    private SearchAdapter mAdapter;
    private SearchView searchViewCoinSelction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_selection);
        txtVCoinSelctionCancel=findViewById(R.id.txtVCoinSelctionCancel);
        txtVCoinSelctionCancel.setOnClickListener(this);

        //editTxtCoinSearch = findViewById(R.id.editTxtCoinSearch);

        recyclerViewCoinSelection = findViewById(R.id.recyclerViewCoinSelection);
        searchItemList = new ArrayList<>();
        mAdapter = new SearchAdapter(this, searchItemList, this);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCoinSelection.setLayoutManager(mLayoutManager);
        recyclerViewCoinSelection.setItemAnimator(new DefaultItemAnimator());

        recyclerViewCoinSelection.setAdapter(mAdapter);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchViewCoinSelction = (SearchView) findViewById(R.id.searchViewCoinSelction);
        searchViewCoinSelction.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchViewCoinSelction.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchViewCoinSelction.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        new SearchAsyncTask().execute("https://api.androidhive.info/json/contacts.json");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtVCoinSelctionCancel:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchViewCoinSelction.isIconified()) {
            searchViewCoinSelction.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onContactSelected(SearchItem searchItem) {
        Toast.makeText(getApplicationContext(), "Selected: " + searchItem.getName() + ", " + searchItem.getPhone(), Toast.LENGTH_LONG).show();
    }

    public class SearchAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //Toast.makeText(CoinSelectionActivity.this, ""+response, Toast.LENGTH_SHORT).show();
            if (response == null) {
                Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                return;
            }

            List<SearchItem> items = new Gson().fromJson(response.toString(), new TypeToken<List<SearchItem>>() {
            }.getType());

            Log.i("items",""+items);
            // adding searchItems to searchItems list
            searchItemList.clear();
            searchItemList.addAll(items);

            // refreshing recycler view
            mAdapter.notifyDataSetChanged();

        }
    }

    public static String  getApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet Httpget = new HttpGet(url);

            Httpget.setHeader("Accept", "application/json");
            Httpget.setHeader("Content-type", "application/json");
            //Httpget.setHeader("Token", DataHolder.LOGIN_TOKEN);

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

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
            //Log.e("Line",result);
        }

        inputStream.close();
        return result;
    }

}





















































/*
public class CoinSelectionActivity extends Activity implements View.OnClickListener {

    TextView txtVCoinSelctionCancel;
    EditText editTxtCoinSearch;
    RecyclerView recyclerViewCoinSelection;
    private List<SearchItem> searchList;
    private SearchAdapter mAdapter;
    private SearchView searchViewCoinSelction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_selection);
        txtVCoinSelctionCancel=findViewById(R.id.txtVCoinSelctionCancel);
        txtVCoinSelctionCancel.setOnClickListener(this);

        //editTxtCoinSearch = findViewById(R.id.editTxtCoinSearch);

        recyclerViewCoinSelection = findViewById(R.id.recyclerViewCoinSelection);
        searchList = new ArrayList<>();
        //mAdapter = new SearchAdapter(this, searchList,  this);
        mAdapter = new SearchAdapter(this, searchList);
        // white background notification bar
        //whiteNotificationBar(recyclerViewCoinSelection);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCoinSelection.setLayoutManager(mLayoutManager);
        recyclerViewCoinSelection.setItemAnimator(new DefaultItemAnimator());
        //recyclerViewCoinSelection.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerViewCoinSelection.setAdapter(mAdapter);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchViewCoinSelction = findViewById(R.id.searchViewCoinSelction);
        searchViewCoinSelction.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchViewCoinSelction.setMaxWidth(Integer.MAX_VALUE);

         //listening to search query text change
        searchViewCoinSelction.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        new SearchAsyncTask().execute("https://api.androidhive.info/json/contacts.json");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtVCoinSelctionCancel:
                onBackPressed();
                break;
        }
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

   public class SearchAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //Toast.makeText(CoinSelectionActivity.this, ""+response, Toast.LENGTH_SHORT).show();
            try {
                JSONArray arrayData = new JSONArray(response);
                int length = arrayData.length();
                for(int i =0 ; i<length;i++){

                    JSONObject key = arrayData.getJSONObject(i);
                    String name = key.getString("name");
                    String symbol = key.getString("phone");
                    String imgV = key.getString("image");

                    Log.i("TAG",name+""+symbol+""+imgV);
                    searchList.add(new SearchItem(name,imgV,symbol));
                    mAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerViewCoinSelection.setAdapter(mAdapter);
//            if (response == null) {
//                Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
//                return;
//            }
//
//            List<SearchItem> items = new Gson().fromJson(response.toString(), new TypeToken<List<SearchItem>>() {
//            }.getType());

            // adding contacts to contacts list
//            searchList.clear();
//            searchList.addAll(items);
//
//            // refreshing recycler view
//            mAdapter.notifyDataSetChanged();
        }
    }

    public static String  getApi(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet Httpget = new HttpGet(url);

            Httpget.setHeader("Accept", "application/json");
            Httpget.setHeader("Content-type", "application/json");
            //Httpget.setHeader("Token", DataHolder.LOGIN_TOKEN);

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

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
            //Log.e("Line",result);
        }

        inputStream.close();
        return result;
    }

}
* */
