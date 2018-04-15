package com.affwl.exchange.alerts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.affwl.exchange.R;

public class MessagePage extends AppCompatActivity implements View.OnClickListener {

    Toolbar maintoolbar, delettoolbar;
    ImageView save_delete;
    CheckBox checkBox;
    RelativeLayout relative_layout_main_message_page;
    LinearLayout linear_layout_message_text , linear_layout_message_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_page);

        relative_layout_main_message_page=findViewById(R.id.relative_layout_main_message_page);
        linear_layout_message_text=findViewById(R.id.linear_layout_message_text);
        linear_layout_message_date=findViewById(R.id.linear_layout_message_date);


        delettoolbar=findViewById(R.id.message_page_delete_toolbar);
        setSupportActionBar(delettoolbar);

        checkBox=findViewById(R.id.checkbox_message_delete);

        save_delete=findViewById(R.id.save_delete);
        save_delete.setOnClickListener(this);

        //working of toolbar
        maintoolbar=findViewById(R.id.message_page_toolbar);
        setSupportActionBar(maintoolbar);
        getSupportActionBar().setTitle("Chat");

    }

    //adding message_main items to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the message_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_page, menu);

        return true;
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.message_delete:
                deleteMessage();
                return true;
            case R.id.DTS_id:
                showId();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showId() {

        try{
        AlertDialog alertDialog = new AlertDialog.Builder(MessagePage.this).create();

        alertDialog.setTitle("DTS ID");
        alertDialog.setMessage("My ID:6F81B010 \n \nUse this ID to send messages to this device via notify service");
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Copy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                copyDtsid();
            }
        });
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();

    }
            catch(Exception e)
    {
        Log.d("log", "Show Dialog: "+e.getMessage());
    }

    }

    private void copyDtsid() {
        
    }

    private void deleteMessage() {

        checkBox.setVisibility(View.VISIBLE);
        maintoolbar.setVisibility(View.GONE);
        delettoolbar.setVisibility(View.VISIBLE);

        delettoolbar.inflateMenu(R.menu.message_delete);

        delettoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (item.getItemId()){
                    case R.id.select_all :

                    if(checkBox.isChecked()==false) {
                        checkBox.setChecked(true);
                        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_select_none); // The ID of your drawable.
                        item.setIcon(myDrawable);
                    }
                    else {
                        checkBox.setChecked(false);
                        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_select_all); // The ID of your drawable.
                        item.setIcon(myDrawable);
                    }
                    return true;
                }
                return false;
            }
        });
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        case R.id.save_delete:

        if(checkBox.isChecked()==true) {
            maintoolbar.setVisibility(View.VISIBLE);
            delettoolbar.setVisibility(View.GONE);
            linear_layout_message_text.setVisibility(View.GONE);
            linear_layout_message_date.setVisibility(View.GONE);
        }
        }
    }
}


