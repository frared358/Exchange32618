package com.affwl.exchange.sport;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentInplay extends Fragment {

    LinearLayout llInplayCricket,llInplayTennis,llInplayFootball;
    RecyclerView recycleViewInplayCricket,recycleViewInplayTennis,recycleViewInplayFootball;
    TextView txtInplayNoData;

    private List<Data> dataListCricket = new ArrayList<>();
    private List<Data> dataListTennis = new ArrayList<>();
    private List<Data> dataListFootball = new ArrayList<>();

    InplayAdapter inplayAdapter1;
    InplayAdapter inplayAdapter2;
    InplayAdapter inplayAdapter3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inplay, container, false);

        txtInplayNoData = v.findViewById(R.id.txtInplayNoData);

        llInplayCricket = v.findViewById(R.id.llInplayCricket);
        llInplayTennis = v.findViewById(R.id.llInplayTennis);
        llInplayFootball = v.findViewById(R.id.llInplayFootball);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity());

        recycleViewInplayCricket = v.findViewById(R.id.recycleViewInplayCricket);
        recycleViewInplayCricket.setLayoutManager(mLayoutManager1);
        recycleViewInplayCricket.setItemAnimator(new DefaultItemAnimator());
        inplayAdapter1 = new InplayAdapter(getActivity(),dataListCricket);

        recycleViewInplayTennis = v.findViewById(R.id.recycleViewInplayTennis);
        recycleViewInplayTennis.setLayoutManager(mLayoutManager2);
        recycleViewInplayTennis.setItemAnimator(new DefaultItemAnimator());
        inplayAdapter2 = new InplayAdapter(getActivity(),dataListTennis);

        recycleViewInplayFootball = v.findViewById(R.id.recycleViewInplayFootball);
        recycleViewInplayFootball.setLayoutManager(mLayoutManager3);
        recycleViewInplayFootball.setItemAnimator(new DefaultItemAnimator());
        inplayAdapter3 = new InplayAdapter(getActivity(),dataListFootball);

        new getInplayAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/Inplay");
        return v;
    }

    private class getInplayAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check1",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());

                JSONArray jsonArray = new JSONArray(jsonObjMain.getString("data"));
                int len = jsonArray.length();
                Log.i("Check1","len "+len);
                if(len==0){
                    txtInplayNoData.setVisibility(View.VISIBLE);
                }

                for(int i=0; i<len;i++){
                    JSONObject key = jsonArray.getJSONObject(i);
                    String name = key.getString("name");
                    String inplay = key.getString("inplayData");

                    JSONArray inplayArray = new JSONArray(key.getString("inplayData"));
                    int lenInplay = inplayArray.length();
                    Log.i("INPLAY",lenInplay+" "+inplay.toString());
                    for (int j=0;j<lenInplay;j++){
                        JSONObject in = inplayArray.getJSONObject(j);
                        String inBfId = in.getString("bfId");
                        String inMatchName = in.getString("matchName");
                        int inMarketId = in.getInt("marketId");
                        int inMatchId = in.getInt("matchId");
                        int isMulti = in.getInt("isMulti");
                        String matchDate = in.getString("matchDate");


                        if(name.equalsIgnoreCase("Cricket")){
                            inplayAdapter1.notifyDataSetChanged();
                            dataListCricket.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        }else if(name.equalsIgnoreCase("Tennis")){
                            inplayAdapter2.notifyDataSetChanged();
                            dataListTennis.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        }else if(name.equalsIgnoreCase("Football")){
                            inplayAdapter3.notifyDataSetChanged();
                            dataListFootball.add(new Data(inMatchId,inMarketId,inBfId,inMatchName,isMulti,matchDate));
                        }
                    }

                    if(name.equalsIgnoreCase("Cricket")){
                        recycleViewInplayCricket.setAdapter(inplayAdapter1);
                        llInplayCricket.setVisibility(View.VISIBLE);
                    }else if(name.equalsIgnoreCase("Tennis")){
                        recycleViewInplayTennis.setAdapter(inplayAdapter2);
                        llInplayTennis.setVisibility(View.VISIBLE);
                    }else if(name.equalsIgnoreCase("Football")){
                        recycleViewInplayFootball.setAdapter(inplayAdapter3);
                        llInplayFootball.setVisibility(View.VISIBLE);
                    }
                }

                DataHolder.cancelProgress();

            } catch (JSONException e) {
                e.printStackTrace();
                DataHolder.unAuthorized(getActivity(),result);
            }
        }
    }

}
