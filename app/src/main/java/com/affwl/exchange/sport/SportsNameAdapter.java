package com.affwl.exchange.sport;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.BlankFragment;
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
import java.util.List;

/**
 * Created by user on 3/27/2018.
 */

public class SportsNameAdapter extends RecyclerView.Adapter<SportsNameAdapter.MyViewHolder> {

    private List<SportsData> dataList;
    private Context context;
    private static List mFeedsList;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;


    private List<SportsData> TournamentList= new ArrayList<>();
    TournamentAdapter tournamentAdapter;

    public void swap(List list){
        if (mFeedsList != null) {
            mFeedsList.clear();
            mFeedsList.addAll(list);
        }
        else {
            mFeedsList = list;
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtVSportName;
        LinearLayout llMatchData;
        ImageView imgVSportIcon;
        RecyclerView recycleViewTournament;
        SparseBooleanArray selectedItems = new SparseBooleanArray();
        public MyViewHolder(View view) {
            super(view);
            txtVSportName =  view.findViewById(R.id.txtVSportName);
            llMatchData = view.findViewById(R.id.llMatchData);
            imgVSportIcon = view.findViewById(R.id.imgVSportIcon);



            recycleViewTournament = view.findViewById(R.id.recycleViewTournament);
            final LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recycleViewTournament.setLayoutManager(mLayoutManager);
            recycleViewTournament.setItemAnimator(new DefaultItemAnimator());

            tournamentAdapter = new TournamentAdapter(context,TournamentList);
        }

        @Override
        public void onClick(View v) {
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                v.setSelected(false);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                v.setSelected(true);
            }
        }
    }

    public SportsNameAdapter(Context context, List<SportsData> dataList) {
        this.context = context;
        this.dataList = dataList;
        mPref = context.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_sports_name,parent, false);
        return new MyViewHolder(v);
    }

    int row_index;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SportsData highlights = dataList.get(position);

        Log.i("TAG1",highlights.txtVMatchName+"");
        holder.txtVSportName.setText(highlights.txtVMatchName);

        holder.imgVSportIcon.setImageResource(R.drawable.cricket);
        /*if (highlights.txtVSportName.equalsIgnoreCase("Cricket")){
            holder.imgVSportIcon.setImageResource(R.drawable.cricket);
        }else if (highlights.txtVSportName.equalsIgnoreCase("Soccer")){
            holder.imgVSportIcon.setImageResource(R.drawable.football);
        }else if (highlights.txtVSportName.equalsIgnoreCase("Tennis")){
            holder.imgVSportIcon.setImageResource(R.drawable.tennis);
        }*/

//        holder.txtVSportName.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, ""+highlights.txtVSportId, Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context,BetActivity.class));
//            }
//        });

//        if (highlights.isSelected()) {
//            holder.recycleViewTournament.setVisibility(View.VISIBLE);
//            TournamentList.clear();
//            tournamentAdapter.notifyDataSetChanged();
//            new TournamentAsyncTask().execute("http://5.189.140.198/Prince99/Prince.svc/Navigation/TournamentList?id="+highlights.txtVSportId);
//            holder.recycleViewTournament.setAdapter(tournamentAdapter);
//            context.startActivity(new Intent(context,BetActivity.class));
//        } else {
//            holder.recycleViewTournament.setVisibility(View.GONE);
//        }

        holder.llMatchData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                row_index=position;
                TournamentList.clear();

                tournamentAdapter.notifyDataSetChanged();

                if(row_index==position){
                    holder.recycleViewTournament.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.recycleViewTournament.setVisibility(View.GONE);
                }

                for(int i=0;i<dataList.size();i++){
                    final SportsData checkList = dataList.get(i);
                    if(position == i){
                        holder.recycleViewTournament.setVisibility(View.VISIBLE);
                    }
                    else {
                        holder.recycleViewTournament.setVisibility(View.GONE);
                    }
                }

                Toast.makeText(context, ""+highlights.txtVSportId, Toast.LENGTH_SHORT).show();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//
//
//                BlankFragment fragment = BlankFragment.newInstance("fragment1");
//                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
//                int containerId = holder.mediaContainer.getId();
//                Fragment oldFragment = fragmentManager.findFragmentById(containerId);
//                if(oldFragment != null) {
//                    // Delete fragmet from ui, do not forget commit() otherwise no action
//                    // is going to be observed
//                    ragmentManager.beginTransaction().remove(oldFragment).commit();
//                }


//                bundle.putInt(POSITION, (position + 10));
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.llViewPagerFragmentHolder, new BlankFragment(),"TAG");
//                fragmentTransaction.commit();

                Intent intent = new Intent(context,BetActivity.class);
                intent.putExtra("matchName",highlights.txtVMatchName);
                intent.putExtra("marketId",highlights.marketId);
                intent.putExtra("matchDate",highlights.matchDate);
                intent.putExtra("matchId",highlights.matchId);
                intent.putExtra("bfId",highlights.bfId);
                Log.i("TAG",highlights.txtVMatchName+" "+highlights.marketId+" "+highlights.matchId);
//                intent.putExtra("runner1Back","");
//                intent.putExtra("runner1Lay","");
//                intent.putExtra("runner1Name","");
//                intent.putExtra("runner2Back","");
//                intent.putExtra("runner2Lay","");
//                intent.putExtra("runner2Name","");
//                intent.putExtra("runner3Back","");
//                intent.putExtra("runner3Lay","");
//                intent.putExtra("runner3Name","");
                context.startActivity(intent);

            }
        });
    }

    public void setSelected(int pos) {
        try {

            if (dataList.size() >= 1) {
                dataList.get(mPref.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            dataList.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        Log.i("TAG",dataList.size()+"");
        return dataList.size();
    }


    public String  TournamentApi(String url){
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

    private class TournamentAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return TournamentApi(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("Check",""+result);
            try {
                JSONObject jsonObjMain = new JSONObject(result.toString());
                String strData = jsonObjMain.getString("data");
                Log.i("TAG",""+strData);

                //Toast.makeText(context, ""+strData, Toast.LENGTH_SHORT).show();

                JSONArray arrayData = new JSONArray(strData);
                int length = arrayData.length();

                for(int i =0 ; i<length;i++){
                    JSONObject key = arrayData.getJSONObject(i);
                    String tournamentName = key.getString("name");
                    int tournamentId = key.getInt("id");
                    Log.i("TAG",""+tournamentName);
                    TournamentList.add(new SportsData(tournamentName,tournamentId));

                    tournamentAdapter.notifyDataSetChanged();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
