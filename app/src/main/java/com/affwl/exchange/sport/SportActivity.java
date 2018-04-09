
package com.affwl.exchange.sport;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

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

public class SportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayoutSport;
    ViewPager viewPagerSport;
    DrawerLayout drawerSport;
    int[] icon = {R.drawable.stop_watch_greay,R.drawable.trophy_greay,R.drawable.fav_contact_greay};
    int[] iconWhite = {R.drawable.stop_watch_white,R.drawable.trophy_white,R.drawable.fav_contact_white};
    Toolbar toolbar;
    TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerSport = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerSport, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerSport.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        userName = header.findViewById(R.id.tvUserName);

        tabLayoutSport = (TabLayout)findViewById(R.id.tabLayoutSport);
        viewPagerSport = (ViewPager)findViewById(R.id.viewPagerSport);


        for (int i = 0; i < 3; i++) {
            tabLayoutSport.addTab(tabLayoutSport.newTab().setIcon(icon[i]));
        }

        tabLayoutSport.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerSport.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerSport.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutSport));

        //change ViewPager page when tab selected
        tabLayoutSport.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position ==0) {

                    tabLayoutSport.getTabAt(1).setIcon(icon[1]);
                    tabLayoutSport.getTabAt(2).setIcon(icon[2]);
                    tabLayoutSport.getTabAt(0).setIcon(iconWhite[0]);

                } else if (position == 1) {

                    tabLayoutSport.getTabAt(0).setIcon(icon[0]);
                    tabLayoutSport.getTabAt(2).setIcon(icon[2]);
                    tabLayoutSport.getTabAt(1).setIcon(iconWhite[1]);

                } else {

                    tabLayoutSport.getTabAt(0).setIcon(icon[0]);
                    tabLayoutSport.getTabAt(1).setIcon(icon[1]);
                    tabLayoutSport.getTabAt(2).setIcon(iconWhite[2]);

                }
                viewPagerSport.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //Toast.makeText(this, ""+getIntent().getStringExtra("KEY_SELECT"), Toast.LENGTH_SHORT).show();
        Intent i = getIntent();
        if(i.getStringExtra("KEY_SELECT")!= null){
            if(i.getStringExtra("KEY_SELECT").toString().equals("true")){
                viewPagerSport.setCurrentItem(2);
            }else {
                tabLayoutSport.getTabAt(0).setIcon(iconWhite[0]);
            }
        }else {
            tabLayoutSport.getTabAt(0).setIcon(iconWhite[0]);
        }

        new HighlightsAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/UserDescription");
        new HighlightsAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Fund");

    }

    @Override
    public void onBackPressed() {

        if (drawerSport.isDrawerOpen(GravityCompat.START)) {
            drawerSport.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.myMarket) {
            dialogMyMarket();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            Intent i = new Intent(SportActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            dialogLogout();
        }


        drawerSport.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position ==0) {
                return new TimerFragment();
            } else if (position == 1) {
                return new FragmentAllSport();
            } else {
                return new FavFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public String  getApi(String url){
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

    private class HighlightsAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check123456",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                JSONObject jsonData = new JSONObject(strData);
                String name = jsonData.getString("name");

                Log.i("TAG159",""+userName );
                userName.setText(name);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jsonObject = new JSONObject(result.toString());
                String data = jsonObject.getString("data");
                JSONObject json = new JSONObject(data);
                String availBalance = json.getString("availBal");
                String exposure = json.getString("exposure");

                toolbar.setTitle("CHIPS: "+availBalance);
                toolbar.setSubtitle("EXP: "+exposure);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    void dialogLogout(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_logout);

        TextView txtVOK =  dialog.findViewById(R.id.txtVOK);
        TextView txtVCancel =  dialog.findViewById(R.id.txtVCancel);

        txtVOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        txtVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private class getMyMarketAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            Toast.makeText(SportActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String bookmakingData = jsonObjMain.getString("bookmakingData");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    void dialogMyMarket(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_fancy_book_list);
        dialog.setTitle("My Market");
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorGreay);
        dialog.show();
    }

    /*public void dialogFancyBook(){
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
    }*/

}
