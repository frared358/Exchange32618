package com.affwl.exchange.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class RssFeedListActivity  extends AppCompatActivity implements AdapterView.OnItemClickListener {
    List headlines;
    List links;
    ProgressDialog progressDialog;
    ListView list_rss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feed_list);

        list_rss=findViewById(R.id.list_rss);

        list_rss.setOnItemClickListener(this);

        new MyAsyncTask().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Bundle bundle = new Bundle();
            bundle.putString("urLink", links.get(position).toString());
            Intent i = new Intent(RssFeedListActivity.this, WebActivity.class);
            i.putExtras(bundle);
            startActivity(i);
        }

    class MyAsyncTask extends AsyncTask<Object, Void, ArrayAdapter> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=new ProgressDialog(RssFeedListActivity.this);
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
                for(int i=0;i<myList.size();i++) {
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
            adapter = new ArrayAdapter(RssFeedListActivity.this, android.R.layout.simple_list_item_1, headlines);
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