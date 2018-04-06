package com.affwl.exchange.sport;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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





    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVSportName;
        LinearLayout llMatchData;
        ImageView imgVSportIcon;


        public MyViewHolder(View view) {
            super(view);
            txtVSportName =  view.findViewById(R.id.txtVSportName);
            llMatchData = view.findViewById(R.id.llMatchData);
            imgVSportIcon = view.findViewById(R.id.imgVSportIcon);
        }


    }

    public SportsNameAdapter(Context context, List<SportsData> dataList) {
        this.context = context;
        this.dataList = dataList;
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

        Log.i("TAG1",highlights.txtVSportName+"");
        holder.txtVSportName.setText(highlights.txtVSportName);

        if (highlights.txtVSportName.equalsIgnoreCase("Cricket")){
            holder.imgVSportIcon.setImageResource(R.drawable.cricket);
        }else if (highlights.txtVSportName.equalsIgnoreCase("Soccer")){
            holder.imgVSportIcon.setImageResource(R.drawable.football);
        }else if (highlights.txtVSportName.equalsIgnoreCase("Tennis")){
            holder.imgVSportIcon.setImageResource(R.drawable.tennis);
        }

        holder.txtVSportName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,TournamentActivity.class);
                i.putExtra("SportId",highlights.txtVSportId);
                i.putExtra("SportName",highlights.txtVSportName);
                context.startActivity(i);
            }
        });



        holder.llMatchData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,TournamentActivity.class);
                i.putExtra("SportId",highlights.txtVSportId);
                i.putExtra("SportName",highlights.txtVSportName);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i("TAG",dataList.size()+"");
        return dataList.size();
    }


//    public String  TournamentApi(String url){
//        InputStream inputStream = null;
//        String result = "";
//        try {
//
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpGet Httpget = new HttpGet(url);
//
//            Httpget.setHeader("Accept", "application/json");
//            Httpget.setHeader("Content-type", "application/json");
//            Httpget.setHeader("Token", DataHolder.LOGIN_TOKEN);
//
//            HttpResponse httpResponse = httpclient.execute(Httpget);
//            inputStream = httpResponse.getEntity().getContent();
//
//            if(inputStream != null){
//                try {
//                    result = convertInputStreamToString(inputStream);
//                }
//                catch (Exception e){
//                    Log.e("Check",""+e);
//                }
//            }
//            else
//                result = "Did not work!";
//            Log.e("Check","how "+result);
//
//        } catch (Exception e) {
//            Log.d("InputStream", ""+e);
//        }
//        return result;
//    }
//
//    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null){
//            result += line;
//            Log.e("Line",result);
//        }
//
//        inputStream.close();
//        return result;
//    }
//
//    private class TournamentAsyncTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            return TournamentApi(urls[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.i("Check",""+result);
//            try {
//                JSONObject jsonObjMain = new JSONObject(result.toString());
//                String strData = jsonObjMain.getString("data");
//                Log.i("TAG",""+strData);
//
//                Toast.makeText(context, ""+strData, Toast.LENGTH_SHORT).show();
//
//                JSONArray arrayData = new JSONArray(strData);
//                int length = arrayData.length();
//
//                for(int i =0 ; i<length;i++){
//                    JSONObject key = arrayData.getJSONObject(i);
//                    String tournamentName = key.getString("name");
//                    int tournamentId = key.getInt("id");
//                    Log.i("TAG",""+tournamentName);
////                    TournamentList.add(new SportsData(tournamentName,tournamentId));
////
////                    tournamentAdapter.notifyDataSetChanged();
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
