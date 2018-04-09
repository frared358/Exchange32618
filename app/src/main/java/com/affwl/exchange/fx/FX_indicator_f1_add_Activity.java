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

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;
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
        setContentView(R.layout.activity_fx_indicator_f1_add_);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void openPicker1(View view) {
        new ColorPickerOval(this, this, "", Color.WHITE).show();
    }

//        btncolorpicker=(Button)findViewById(R.id.btncolorpicker);
//
//
//        mylayout=(RelativeLayout)findViewById(R.id.mylayout);
//
//


//    public void openColorPicker()
//    {
//        final ColorPicker ColorPicker =new ColorPicker(this);
//        ArrayList<String> colors= new ArrayList<>();
//        colors.add("#258174");
//        colors.add("#3C8D2F");
//        colors.add("#20724F");
//        colors.add("#6a3ab2");
//        colors.add("#323299");
//        colors.add("#000000");
//        colors.add("#808000");
//        colors.add("#966d37");
//        colors.add("#b77231");
//        colors.add("#258174");
//
//        ColorPicker.setColors(colors)
//                .setColumns(5)
//                .setRoundColorButton(true)
//        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
//            @Override
//            public void onChooseColor(int position, int color) {
//                mylayout.setBackgroundColor(color);
//            }

//            @Override
//            public void onCancel() {
//
//            }
//        })
//                .show();
//    }

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
