package com.affwl.exchange.sport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UnmatchMatchTabActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    String[] txt ={"Match","Unmatch"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unmatch_match_tab);

        DataHolder.showProgress(this);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        for (int i = 0; i < 2; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(txt[i]));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        new Match_UnMatchAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Reports/GetCurrentBets");

    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();

            if (position ==0) {
                bundle.putString("matchedbets", matchedbets);
                MatchFragment matchFragment = new MatchFragment();
                matchFragment.setArguments(bundle);
                return matchFragment;
            } else {
                bundle.putString("unMatchedbets", unMatchedbets);
                UnmatchFragment unmatchFragment = new UnmatchFragment();
                unmatchFragment.setArguments(bundle);
                return unmatchFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void gui(){

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    String unMatchedbets,matchedbets;
    private class Match_UnMatchAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                unMatchedbets = jsonObjMain.getString("unMatchedbets");
                matchedbets = jsonObjMain.getString("matchedbets");

                gui();
            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(UnmatchMatchTabActivity.this,result);
            }

            DataHolder.cancelProgress();
        }
    }
}
