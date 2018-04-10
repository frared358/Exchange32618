
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
        new getMyMarketAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Bets/MyMarket");
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

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONArray array = new JSONArray(jsonObjMain.getString("data"));
                int len = array.length();
                for(int i=0;i<len;i++){
                    JSONObject key = array.getJSONObject(i);
                    String liability = key.getString("liability");
                    String matchName = key.getString("matchName");
                    arrayExposer.add(liability);
                    arrayMatchName.add(matchName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<String> arrayMatchName = new ArrayList<>();
    ArrayList<String> arrayExposer = new ArrayList<>();
    void dialogMyMarket(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_fancy_book_list);
        dialog.setTitle("My Market");
        //dialog.getWindow().setBackgroundDrawableResource(R.color.colorGreay);
        Button btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog);

        LinearLayout llMatchName = dialog.findViewById(R.id.llFAncyBookKey);
        LinearLayout llExposer = dialog.findViewById(R.id.llFAncyBookValue);
        llExposer.setVisibility(View.GONE);
        TextView txtVHeaderScoreMatchName = dialog.findViewById(R.id.txtVHeaderScoreMatchName);
        TextView txtVHeaderAmtExposer = dialog.findViewById(R.id.txtVHeaderAmtExposer);
        txtVHeaderAmtExposer.setVisibility(View.GONE);
        txtVHeaderScoreMatchName.setText("My Market");

        for (int i = 0; i < arrayMatchName.size() ; i++)
        {
            LinearLayout.LayoutParams paramsMN = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
            LinearLayout.LayoutParams paramsExp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0f);

            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.setBackgroundColor(ContextCompat.getColor(this,R.color.colorGreay));
            layoutParams.setMargins(5,5,5,5);
            ll.setLayoutParams(layoutParams);

            TextView tvMatchName = new TextView(this);
            TextView tvExposer = new TextView(this);

            tvExposer.setLayoutParams(paramsExp);
            tvMatchName.setLayoutParams(paramsMN);

            tvMatchName.setTextSize(15);
            tvExposer.setTextSize(15);
            tvMatchName.setGravity(Gravity.LEFT);
            tvExposer.setGravity(Gravity.RIGHT);
            tvExposer.setPadding(10,0,10,0);
            tvMatchName.setPadding(10,0,10,0);

            tvMatchName.setText(arrayMatchName.get(i));
            tvExposer.setText(arrayExposer.get(i));
            if(Double.valueOf(arrayExposer.get(i))<0){
                tvExposer.setTextColor(ContextCompat.getColor(this,R.color.colorRed));
            }else {
                tvExposer.setTextColor(ContextCompat.getColor(this,R.color.colorGreen));
            }

            ll.addView(tvMatchName);
            ll.addView(tvExposer);

            llMatchName.addView(ll);
            Log.i("ValueFancyBook.get(i)",arrayMatchName.get(i));
            Log.i("KeyFancyBook.get(i)",arrayExposer.get(i));
        }

        btnCancelDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }



}
