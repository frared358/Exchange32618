package com.affwl.exchange.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.affwl.exchange.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayoutIndieNews;

    String title;
    String link;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    int timeNow;
    List headlines,newsDateTimes;
    List<NewsItemDetails> newsItemDetailsList;
    List links;
    ProgressDialog progressDialog;
    ImageView loading_news;

    ListView list_rss;
    ArrayList<String> myList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       initiateComponent();

    }

    private void initiateComponent() {
        list_rss=findViewById(R.id.list_rss);
        loading_news=findViewById(R.id.loading_news);

        list_rss.setOnItemClickListener(NewsActivity.this);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

            new MyAsyncTask().execute();

  /*      SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM selected_category WHERE user_id=?",  new String[] {String.valueOf(1234) });
        if (c.moveToFirst()) {

            do {
                Log.i("TagCategory",""+c.getInt(c.getColumnIndex("category_id")));
                Log.i("TagRefresh",""+c.getInt(c.getColumnIndex("refresh_time")));
                timeNow=c.getInt(c.getColumnIndex("refresh_time"));
//                reloadNews(timeNow);

            } while (c.moveToNext());
        }*/

    }

  /*  private void reloadNews(final int timeNow) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new MyAsyncTask().execute();
                reloadNews(timeNow);
            }
        }, TimeUnit.MINUTES.toMillis(timeNow));
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_category, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_news_category:
                Intent i = new Intent(this, RssFeedListActivity.class); //add CustomSpinner
                this.startActivity(i);
                return true;

            case R.id.news_reload:
                new MyAsyncTask().execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("urLink", links.get(position).toString());
        Intent i = new Intent(NewsActivity.this, WebActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    class MyAsyncTask extends AsyncTask<Object, Void, NewsAdapter> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

         /*   progressDialog=new ProgressDialog(NewsActivity.this);
            progressDialog.setTitle("Fetching the RSS");
            progressDialog.setMessage("Please Wait ... ");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();*/

            Animation startRotation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animate_progressbar);
            loading_news.startAnimation(startRotation);
        }

        @Override
        protected NewsAdapter doInBackground(Object[] params) {
            headlines = new ArrayList();
            newsDateTimes=new ArrayList();
            newsItemDetailsList=new ArrayList<>();
            myList.clear();

            links = new ArrayList();
            try {
                try {
                    SQLiteDatabase db = mDBHelper.getReadableDatabase();
                    Cursor c = db.rawQuery("SELECT * FROM category_url a INNER JOIN selected_category b ON a.selected_id=b.selected_id WHERE b.user_id=?",  new String[] { String.valueOf(1234) });

                    if (c.moveToFirst()) {

                        do {
                           /* Log.i("Tag",""+c.getInt(c.getColumnIndex("url_id")));
                            Log.i("Tag",""+c.getInt(c.getColumnIndex("selected_id")));
                            Log.i("Tag",""+c.getString(c.getColumnIndex("url_name")));*/
                            myList.add(c.getString(c.getColumnIndex("url_name")));
                        } while (c.moveToNext());
                    }
//                    Log.i("my db"," "+mDb);

                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }

                if(myList!=null) {
                    for (int i = 0; i < myList.size(); i++) {
                        URL url = new URL(myList.get(i));
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                        factory.setNamespaceAware(false);
                        XmlPullParser xpp = factory.newPullParser();

                        // We will get the XML from an input stream
                        xpp.setInput(getInputStream(url), "UTF_8");
                        boolean insideItem = false;

                        // Returns the type of current event: START_TAG, END_TAG, etc..
                        int eventType = xpp.getEventType();
//                        Log.i("Checking event"," "+eventType);


                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            if (eventType == XmlPullParser.START_TAG) {
                                if (xpp.getName().equalsIgnoreCase("item")) {
                                    insideItem = true;
                                } else if (xpp.getName().equalsIgnoreCase("title")) {
                                    if (insideItem)
                                        headlines.add(xpp.nextText());
                                    //extract the headline
                                } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                                    if (insideItem)
                                        newsDateTimes.add(xpp.nextText()); //extract the link of article
                                } else if (xpp.getName().equalsIgnoreCase("link")) {
                                    if (insideItem)
                                        links.add(xpp.nextText()); //extract the link of article
                                }
                            } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                                insideItem = false;
                            }
                            eventType = xpp.next(); //move to next element
                        }
                    }
                }

                    for(int i=0;i<headlines.size();i++) {
                        NewsItemDetails newsItem = new NewsItemDetails(headlines.get(i).toString(), newsDateTimes.get(i).toString(), "aritcle");
                        newsItemDetailsList.add(newsItem);
                    }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(NewsAdapter adapter) {
            adapter = new NewsAdapter(NewsActivity.this, newsItemDetailsList);
            list_rss.setAdapter(adapter);

           /* if(progressDialog!=null)
            {
                progressDialog.dismiss();
            }*/
           if(loading_news.getVisibility()==View.VISIBLE)
           {
               loading_news.setVisibility(View.GONE);
           }
        }
    }



    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }
}
