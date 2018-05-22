package com.affwl.exchange.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.affwl.exchange.R;

public class FavoriteActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtVBackFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        txtVBackFavorite = findViewById(R.id.txtVBackFavorite);
        txtVBackFavorite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtVBackFavorite:
                onBackPressed();
                break;
        }
    }
}
