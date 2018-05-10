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
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentFavouriteData extends Fragment {

    RecyclerView recycleViewFavouriteDataList;
    private List<Data> favList= new ArrayList<>();
    FavouriteDataAdapter favouritAdapter;
    TextView txtFavouriteNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_data, container, false);
        txtFavouriteNoData = view.findViewById(R.id.txtFavouriteNoData);
        recycleViewFavouriteDataList = view.findViewById(R.id.recycleViewFavouriteDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recycleViewFavouriteDataList.setLayoutManager(mLayoutManager);
        recycleViewFavouriteDataList.setItemAnimator(new DefaultItemAnimator());
        favouritAdapter = new FavouriteDataAdapter(getActivity(),favList);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        favList.clear();
        favouritAdapter.notifyDataSetChanged();
        recycleViewFavouriteDataList.setAdapter(favouritAdapter);
        new getFavouriteAsyncTask().execute("http://173.212.248.188/pclient/Prince.svc/Data/MultiMarkets");
    }

    private class getFavouriteAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return DataHolder.getApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);

            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                JSONArray dataArray = new JSONArray(jsonObjMain.getString("data"));
                int len = dataArray.length();

                if(len ==0){
                    txtFavouriteNoData.setVisibility(View.VISIBLE);
                }else {
                    txtFavouriteNoData.setVisibility(View.GONE);
                }


                for(int i=0;i<len;i++){
                    JSONObject key = dataArray.getJSONObject(i);
                    String marketId = key.getString("mktId");
                    String matchId = key.getString("matchId");
                    String matchName = key.getString("matchName");
                    String bfId = key.getString("bfId");
                    String matchDate = key.getString("matchDate");
                    String sportName = key.getString("sportName");
                    favList.add(new Data(marketId,matchId,matchName,bfId,matchDate,sportName));

                    favouritAdapter.notifyDataSetChanged();
                }
                recycleViewFavouriteDataList.setAdapter(favouritAdapter);
                DataHolder.cancelProgress();
            } catch (JSONException e) {
                e.printStackTrace();
                //DataHolder.unAuthorized(getActivity(),result);
            }
        }

    }
}
