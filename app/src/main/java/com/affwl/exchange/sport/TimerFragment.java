
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
    private int[] icon = {R.drawable.football,R.drawable.tennis,R.drawable.cricket};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new getInplayAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Inplay");
        tabLayoutTime = (TabLayout)view.findViewById(R.id.tabLayoutTime);
        viewPagerTime = (ViewPager)view.findViewById(R.id.viewPagerTime);

        /*for (int i = 0; i < 3; i++) {
            tabLayoutTime.addTab(tabLayoutTime.newTab().setIcon(icon[i]));
        }*/

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
            /*Bundle bundle = new Bundle();
            bundle.putString("edttext", "data From Activity");
            // set Fragmentclass Arguments
            FragmentBlankSport fragobj = new FragmentBlankSport();
            fragobj.setArguments(bundle);*/

            /*for(int i=0;i< InplayData.size()-1;i++){
                if(position == i){
                    Bundle bundle = new Bundle();
                    bundle.putString("inplay", InplayData.get(i));

                    FragmentBlankSport obj = new FragmentBlankSport();
                    obj.setArguments(bundle);
                    return obj;
                }else {

                }
            }*/
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

    ArrayList<String> InplayData = new ArrayList<>();
    private class getInplayAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
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
                    String inplay = key.getString("inplayData");
                    Log.i("INPLAY",""+inplay.toString());
                    InplayData.add(inplay);
                    if(name.equalsIgnoreCase("Cricket")){
                        tabLayoutTime.addTab(tabLayoutTime.newTab().setIcon(R.drawable.cricket));
                    }else if(name.equalsIgnoreCase("Tennis")){
                        tabLayoutTime.addTab(tabLayoutTime.newTab().setIcon(R.drawable.tennis));
                    }else if(name.equalsIgnoreCase("Football")){
                        tabLayoutTime.addTab(tabLayoutTime.newTab().setIcon(R.drawable.football));
                    }

                    /*JSONArray inplayArray = new JSONArray(key.getString("inplayData"));
                    int lenInplay = inplayArray.length();
                    SPORTNAME.add(name);
                    for (int j=0;j>lenInplay;j++){
                        JSONObject in = inplayArray.getJSONObject(j);
                    }*/


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

}
