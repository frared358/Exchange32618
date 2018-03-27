package com.affwl.exchange.news;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.affwl.exchange.R;

public class WebActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        progressDialog=new ProgressDialog(WebActivity.this);
        progressDialog.setTitle("Loading the content");
        progressDialog.setMessage("Please Wait ... ");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("urLink");

        WebView myWebView = findViewById(R.id.webDisplay);

//        myWebView.setBackgroundResource(R.drawable.ic_launcher_background);
        myWebView.setBackgroundColor(Color.TRANSPARENT);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);

        if(progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }
}
