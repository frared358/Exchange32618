package com.affwl.exchange.alerts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

public class MessageMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout layout_myroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_main);

        layout_myroot=findViewById(R.id.layout_my_root);

        //working of toolbar
        toolbar=findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat");

    }

    //adding message_main items to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the message_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_main, menu);

        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_message:
                newMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //method to create new message on runtime
    private void newMessage() {

        //creating imageview on runtime
        ImageView imageView = new ImageView(MessageMainActivity.this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.new_message));

        //creating textview on runtime
        TextView tv1 = new TextView(MessageMainActivity.this);
        tv1.setText("New message from Server");
        tv1.setBackgroundColor(Color.GRAY);
        tv1.setTextColor(Color.WHITE);
        tv1.setTextSize(30);
        tv1.setPadding(50, 0 ,20,0);


        //creating Linear Layout on runtime
        LinearLayout a = new LinearLayout(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(10,10,10,10);
        tv1.setLayoutParams(lparams);
        a.setOrientation(LinearLayout.HORIZONTAL);
        a.addView(imageView);
        a.addView(tv1);
        layout_myroot.addView(a);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MessageMainActivity.this, "inshallah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MessageMainActivity.this, MessagePage.class));
            }
        });

    }
}
