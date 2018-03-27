package com.affwl.exchange.news;

import android.app.Dialog;
import android.content.Intent;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.DataHolder;
import com.affwl.exchange.MainActivity;
import com.affwl.exchange.R;
import com.affwl.exchange.indie.IndieActivity;
import com.affwl.exchange.indie.LiveTipsActivity;
import com.affwl.exchange.indie.NewHiloActivity;
import com.affwl.exchange.indie.PivotActivity;

import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, View.OnLongClickListener {

    private DrawerLayout drawerLayoutIndieNews;
    EditText edit_rssFeed;
    ImageView search_rssFeed;
    RecyclerView news_recycler_view;
    SwipeRefreshLayout news_swipe_layout;
    TextView tv_feedTitle,tv_feedDescription,tv_feedLink;
    String strFeedLink,strFeedTitle,strFeedDescription;
    XmlPullParserFactory xmlFactoryObject;

    LinearLayout news_checkbox_layout,sports_checkbox_layout,crypto_checkbox_layout,indie_checkbox_layout,binary_checkbox_layout,fx_checkbox_layout;
    String title,link,description,name;
    boolean isItem = false;
    private ArrayList<CheckBox> categoryCheckbox=new ArrayList<>();
    private CheckBox checkboxCategories;
    private ArrayList<String> selectedCategory=new ArrayList<>();

    CheckBox sportsCheckBox,cryptoCheckBox,indieCheckBox,binaryCheckBox,fxCheckBox;
    private int integer;
    private ArrayList<String> myList;

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

        initNewsComponent();
    }

    private void initNewsComponent() {

    /*    edit_rssFeed=findViewById(R.id.edit_rssFeed);
        search_rssFeed=findViewById(R.id.search_rssFeed);
        news_recycler_view=findViewById(R.id.news_recycler_view);
        news_swipe_layout=findViewById(R.id.news_swipe_layout);



        tv_feedTitle=findViewById(R.id.tv_feedTitle);
        tv_feedDescription=findViewById(R.id.tv_feedDescription);
        tv_feedLink=findViewById(R.id.tv_feedLink);*/

       /* search_rssFeed.setOnClickListener(this);
        news_swipe_layout.setOnRefreshListener(this);*/

       sportsCheckBox=findViewById(R.id.sportsCheckBox);
       cryptoCheckBox=findViewById(R.id.cryptoCheckBox);
       indieCheckBox=findViewById(R.id.indieCheckBox);
       binaryCheckBox=findViewById(R.id.binaryCheckBox);
       fxCheckBox=findViewById(R.id.fxCheckBox);


       sports_checkbox_layout=findViewById(R.id.sports_checkbox_layout);
       crypto_checkbox_layout=findViewById(R.id.crypto_checkbox_layout);
       indie_checkbox_layout=findViewById(R.id.indie_checkbox_layout);
       binary_checkbox_layout=findViewById(R.id.binary_checkbox_layout);
       fx_checkbox_layout=findViewById(R.id.fx_checkbox_layout);


       sportsCheckBox.setOnClickListener(this);
       cryptoCheckBox.setOnClickListener(this);
       indieCheckBox.setOnClickListener(this);
       binaryCheckBox.setOnClickListener(this);
       fxCheckBox.setOnClickListener(this);

       sports_checkbox_layout.setOnLongClickListener(this);
       crypto_checkbox_layout.setOnLongClickListener(this);
       indie_checkbox_layout.setOnLongClickListener(this);
       binary_checkbox_layout.setOnLongClickListener(this);
       fx_checkbox_layout.setOnLongClickListener(this);

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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sportsCheckBox:
                if (sportsCheckBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "selected Sports", Toast.LENGTH_LONG).show();
                }
            break;

            case R.id.cryptoCheckBox:
                if(cryptoCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected Crypto", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.indieCheckBox:
                if(indieCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected Indie", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.binaryCheckBox:
                if(binaryCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected Binary", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.fxCheckBox:
                if(fxCheckBox.isChecked())
                {
                    Toast.makeText(this, "Selected FX", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId())
        {
            case R.id.sports_checkbox_layout: case R.id.crypto_checkbox_layout:  case R.id.indie_checkbox_layout:  case R.id.binary_checkbox_layout: case R.id.fx_checkbox_layout:
                    displayPopup();
                break;
        }
        return false;
    }

    private void displayPopup() {
            TextView setMinutes,pasteUrl,deleteUrl;
            final LinearLayout news_option_layout,minutes_layout,new_url_layout;
            final ImageView img_minus_minutes,img_plus_minutes;
            final TextView text_minutes;

            final Dialog myDialog = new Dialog(NewsActivity.this);
            myDialog.setContentView(R.layout.news_options);
        news_option_layout=myDialog.findViewById(R.id.news_option_layout);
        minutes_layout=myDialog.findViewById(R.id.minutes_layout);

        setMinutes = myDialog.findViewById(R.id.setMinutes);
        pasteUrl=myDialog.findViewById(R.id.pasteUrl);
        deleteUrl=myDialog.findViewById(R.id.deleteUrl);
        text_minutes=myDialog.findViewById(R.id.text_minutes);


        img_plus_minutes=myDialog.findViewById(R.id.img_plus_minutes);
        img_minus_minutes=myDialog.findViewById(R.id.img_minus_minutes);


        setMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news_option_layout.setVisibility(View.GONE);
                minutes_layout.setVisibility(View.VISIBLE);
            }
        });

        pasteUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(NewsActivity.this,RssFeedUrlActivity.class));
            }
        });

        deleteUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = (ArrayList<String>) getIntent().getSerializableExtra("urlArray");
                if(myList!=null) {
                    Intent intent=new Intent(NewsActivity.this, RssFeedUrlActivity.class);
                    intent.putExtra("urlArray",myList);
                    startActivity(intent);
                }
            }
        });

        img_plus_minutes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String value = text_minutes.getText().toString();
                Log.i("value",""+value);
                    integer = Integer.parseInt(value);
                    Log.i("integer",""+integer);
                    if (integer >= 5) {
                        integer = integer + 1;
                        String str = String.valueOf(integer);
                        text_minutes.setText(str);
                    }
                }

        });

        img_minus_minutes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String value = text_minutes.getText().toString();
                Log.i("value",""+value);
                    integer = Integer.parseInt(value);
                    Log.i("integer",""+integer);
                    if (integer > 5) {
                        integer = integer - 1;
                        String str = String.valueOf(integer);
                        text_minutes.setText(str);
                    }
                }

        });

            myDialog.show();
        }
}
