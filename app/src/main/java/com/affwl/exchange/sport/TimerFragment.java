
package com.affwl.exchange.sport;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class TimerFragment extends Fragment {

    TabLayout tabLayoutTime;
    ViewPager viewPagerTime;
    private int[] icon = {R.drawable.football,R.drawable.horse_head,R.drawable.cricket};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutTime = (TabLayout)view.findViewById(R.id.tabLayoutTime);
        viewPagerTime = (ViewPager)view.findViewById(R.id.viewPagerTime);

        for (int i = 0; i < 3; i++) {
            tabLayoutTime.addTab(tabLayoutTime.newTab().setIcon(icon[i]));
        }

        tabLayoutTime.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerTime.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPagerTime.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutTime));

        //change ViewPager page when tab selected
        tabLayoutTime.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerTime.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position ==0) {
                return new FragmentBlankSport();
            }else if(position ==1) {
                return new FragmentBlankSport();
            }
            else {
                return new FragmentCricketInplay();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    ArrayList<String> SPORTNAME = new ArrayList<>();
    private class getInplayAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();
                for(int i=0; i<len;i++){
                    JSONObject key = jsonArray.getJSONObject(i);
                    String name = key.getString("name");
                    String Inplay = key.getString("inplayData");
                    SPORTNAME.add(name);

                    /*JSONArray inplayArray = new JSONArray();
                    int lenInplay = inplayArray.length();
                    for (int j=0;j<lenInplay;j++){

                    }*/
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
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

}
