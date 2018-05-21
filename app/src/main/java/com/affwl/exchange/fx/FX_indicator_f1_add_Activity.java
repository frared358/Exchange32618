package com.affwl.exchange.fx;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.affwl.exchange.R;
import com.guna.libcolorpicker.ColorPickerOval;
import com.guna.libcolorpicker.OnColorChangedListener;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FX_indicator_f1_add_Activity extends AppCompatActivity implements OnColorChangedListener {

Button btncolorpicker;

RelativeLayout mylayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_fx_indicator_f1_add);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void openPicker1(View view) {
        new ColorPickerOval(this, this, "", Color.WHITE).show();
    }



    @Override
    public void colorChanged(String key, int color) {
        this.findViewById(android.R.id.content)
                .setBackgroundColor(color);
    }

    @Override
    public void colorChanging(int color) {

    }


//    @Override
//    public void onClick(View v) {
//        openColorPicker();
//    }
}
