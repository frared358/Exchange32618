package com.affwl.exchange.news;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;
import com.affwl.exchange.fx.CustomSpinner;
import com.affwl.exchange.indie.IndieActivity;
import com.affwl.exchange.indie.LiveTipsActivity;
import com.affwl.exchange.indie.NewHiloActivity;
import com.affwl.exchange.indie.PivotActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayoutIndieNews;
    EditText edit_rssFeed;
    ImageView search_rssFeed;
    RecyclerView news_recycler_view;
    SwipeRefreshLayout news_swipe_layout;
    TextView tv_feedTitle,tv_feedDescription,tv_feedLink;
    String strFeedLink,strFeedTitle,strFeedDescription;
    XmlPullParserFactory xmlFactoryObject;

    String title,link,description,name;
    boolean isItem = false;
    private ArrayList<CheckBox> categoryCheckbox=new ArrayList<>();
    private CheckBox checkboxCategories;
    private ArrayList<String> selectedCategory=new ArrayList<>();


    List headlines;
    List links;
    ProgressDialog progressDialog;
    ListView list_rss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayoutIndieNews = findViewById(R.id.drawerLayoutIndieNews);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayoutIndieNews, toolbar,
                R.string.open, R.string.close);
        drawerLayoutIndieNews.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navViewIndieNews);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

       initiateComponent();

    }

    private void initiateComponent() {
        list_rss=findViewById(R.id.list_rss);

        list_rss.setOnItemClickListener(NewsActivity.this);

        new MyAsyncTask().execute();
    }

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.tabMarket) {
            DataHolder.navigationForTab = 0;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.tabMarketWatch) {
            DataHolder.navigationForTab = 1;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.tabMarketMovers) {
            DataHolder.navigationForTab = 2;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.tabPortfolio) {
            DataHolder.navigationForTab = 3;
            startActivity(new Intent(this,IndieActivity.class));
        } else if (id == R.id.home) {
            startActivity(new Intent(this,MainActivity.class));
        } else if (id == R.id.pivot) {
            startActivity(new Intent(this,PivotActivity.class));
        } else if (id == R.id.liveTips) {
            startActivity(new Intent(this,LiveTipsActivity.class));
        } else if (id == R.id.charts) {

        } else if (id == R.id.newHilo) {
            startActivity(new Intent(this,NewHiloActivity.class));
        } else if (id == R.id.scanner) {

        } else if (id == R.id.dataQuery) {

        } else if (id == R.id.logout) {
            finish();
        }

        drawerLayoutIndieNews.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawerLayoutIndieNews != null;
        if (drawerLayoutIndieNews.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutIndieNews.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

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

    class MyAsyncTask extends AsyncTask<Object, Void, ArrayAdapter> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=new ProgressDialog(NewsActivity.this);
            progressDialog.setTitle("Fetching the RSS");
            progressDialog.setMessage("Please Wait ... ");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected ArrayAdapter doInBackground(Object[] params) {
            headlines = new ArrayList();
            links = new ArrayList();
            try {
//             URL url=new URL("https://judeochristianclarion.com/feed/rss.xml");
//             URL url=new URL("https://economictimes.indiatimes.com/industry/auto/rssfeeds/13359412.cms");
//             URL url = new URL("http://cmhett.tk/rss.xml");

                ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("urlArray");
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
                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            if (eventType == XmlPullParser.START_TAG) {
                                if (xpp.getName().equalsIgnoreCase("item")) {
                                    insideItem = true;
                                } else if (xpp.getName().equalsIgnoreCase("title")) {
                                    if (insideItem)
                                        headlines.add(xpp.nextText()); //extract the headline
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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(ArrayAdapter adapter) {
            adapter = new ArrayAdapter(NewsActivity.this, android.R.layout.simple_list_item_1, headlines);
            list_rss.setAdapter(adapter);

            if(progressDialog!=null)
            {
                progressDialog.dismiss();
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
