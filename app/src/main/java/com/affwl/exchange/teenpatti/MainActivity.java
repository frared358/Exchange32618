package com.affwl.exchange.teenpatti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import com.affwl.exchange.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView showPopupBtn, closeRateus, closeHelpBtn, closeTrophyBtn,profile,orangechipsbtn,close312help,closesixpattihelp,short321info,tourney_shortinfo_closebtn,shortsixpattiinfo,bluechipsbtn,cyanchipsbtn,shortinfo_tourney,tourney_join_closebtn,ygreenchipsbtn,closebtn_create_table,mainlimegchipsbtn,variation_closebtn,facebook,whatsapp,general;
    PopupWindow RateuspopupWindow, HelpUspopupWindow, TrophypopupWindow, tounpopupWindow,howto321popup,sixpattipopup,howtosixpattipopup,join_tourney_popupWindow,shortinfo_tourney_popupwindow,create_table_private_popupwindow,join_table_popupwindow;
    RelativeLayout RelativeLayoutloader,relativelayout321,relativeLayoutsixpatti,relativeLayout_tourney,yellowchiplayout,orangechipslayout,limechipslayout,darkbluechiplayout,blackchipslayout,cyanchipslayout,ygreenchipslayout;
    TextView loaderbuychips,joinnowbtn,howtoplay321btn,howtoplaysixpattibtn,joinnowsixpattibtn,join_tourneybtn,create_table_btn,join_variation_btn,nametext,code;
    Session session;
    LinearLayout jokerlayout_btn,jokerinfo_layout,ak47_layout_btn,ak47info_layout,xboot_layout_btn,xboot_info_layout,
            hukum_layout_btn, hukum_info_layout, muflis_layout_btn, muflis_info_layout, faceoff_layout_btn, faceoff_info_layout,
            ljoker_layout_btn, ljoker_info_layout, nnnine_layout_btn, nnnine_info_layout;

    ImageView joker_img,ak_img,xboot_img, hukum_img, muflis_img, faceoff_img, ljoker_img, nnnine_img;
    ImageView mainychips,mainlimegchips,blackchips;
    Animation animatechips1,animatechips2,animatechips3,animatechips4,animatechips5,animatechips6,animatechips7;


    int value=0;
    int value1=0;
    int value2=0;
    int value3=0;
    int value4=0;
    int value5=0;
    int value6=0;
    int value7=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teenpatti_activity_main);


//        ListView listView = (ListView) findViewById(R.id.ll);
//        listView.setAdapter(adapter);

        profile=findViewById(R.id.profile);
        nametext=findViewById(R.id.nametext);

        final Animation Animchipsright = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_chips_right);

        mainychips=findViewById(R.id.mainychips);
        mainychips.setOnClickListener(this);

        mainlimegchips=findViewById(R.id.mainlimegchips);
        mainlimegchips.setOnClickListener(this);

        blackchips=findViewById(R.id.blackchips);
        blackchips.setOnClickListener(this);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);

        code=findViewById(R.id.code);
        session=new Session(this);
        String encodedimage=session.getImage();
        if (!encodedimage.equalsIgnoreCase(""))
        {
            byte[] b = Base64.decode(encodedimage, Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(b,0,b.length);
            profile.setImageBitmap(bmp);
        }
        String name=session.getName();
        nametext.setText(name);


        //Animations for chips click
//        final Animation Animchipsright = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_chips_right);
        final Animation Animchipsleft = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_chips_left);

//        animatechips1 = AnimationUtils.loadAnimation(this, R.anim.translate_chips_right);
//        animatechips2 = AnimationUtils.loadAnimation(this, R.anim.translate_chips_right);
//        animatechips3 = AnimationUtils.loadAnimation(this, R.anim.translate_chips_right);
//        //animatechips4 = AnimationUtils.loadAnimation(this, R.anim.translate_center);
//        animatechips5 = AnimationUtils.loadAnimation(this, R.anim.translate_chips_left);
//        animatechips6 = AnimationUtils.loadAnimation(this, R.anim.translate_chips_left);
//        animatechips7 = AnimationUtils.loadAnimation(this, R.anim.translate_chips_left);

//        yellowchiplayout = findViewById(R.id.yellowchiplayout);
//        yellowchiplayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                yellowchiplayout.startAnimation(Animchipsright);
//            }
//        });
        orangechipslayout = findViewById(R.id.orangechipslayout);
        orangechipslayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                orangechipslayout.clearAnimation();
                orangechipslayout.startAnimation(Animchipsright);
            }
        });
//        limechipslayout = findViewById(R.id.limechipslayout);
//        limechipslayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                limechipslayout.startAnimation(animatechips3);
//            }
//        });
//        darkbluechiplayout = findViewById(R.id.darkbluechiplayout);
//        yellowchiplayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                yellowchiplayout.startAnimation(animatechips1);
//            }
//        });
//        blackchipslayout = findViewById(R.id.blackchipslayout);
//        blackchipslayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blackchipslayout.startAnimation(animatechips5);
//            }
//        });
//        cyanchipslayout = findViewById(R.id.cyanchipslayout);
//        cyanchipslayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cyanchipslayout.startAnimation(animatechips6);
//            }
//        });
//        ygreenchipslayout = findViewById(R.id.ygreenchipslayout);
//        ygreenchipslayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ygreenchipslayout.startAnimation(animatechips7);
//            }
//        });

        // Popup for Help
        showPopupBtn = findViewById(R.id.help_btn_loader);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);

        showPopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.help_popup,null);

                closeHelpBtn = customView.findViewById(R.id.close_helpus);

                //instantiate popup window
                HelpUspopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                //display the popup window
                HelpUspopupWindow.showAtLocation(RelativeLayoutloader, Gravity.TOP, 0, 0);

                //close the popup window on button click
                closeHelpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HelpUspopupWindow.dismiss();
                    }
                });

            }
        });



        //////////////// Popup for 321 tournament ////////////////

        orangechipsbtn = findViewById(R.id.mainorgchips);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);
        relativelayout321 = findViewById(R.id.relativelayout321);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                final Intent intent = new Intent(MainActivity.this, ThreetwooneTournament.class);
//                startActivity(intent);
//            }
//        }, 5000);

        orangechipsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreetwooneTournament.class);
                startActivity(intent);
            }
        });

        //////////////// Popup for six patti ////////////////
        bluechipsbtn = findViewById(R.id.darkbluechips);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);
        relativeLayoutsixpatti = findViewById(R.id.relativelayoutsixpatti);


        bluechipsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoadingScreen_sixpatti.class);
                startActivity(intent);
                finish();

//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.activity_how_to_play_sixpatti,null);
//                shortsixpattiinfo = findViewById(R.id.shortsixpattiinfo);
//
//                howtoplaysixpattibtn = customView.findViewById(R.id.howtoplaysixpattibtn);
//
//
//
//                // onclick event
//                howtoplaysixpattibtn.setOnClickListener(new View.OnClickListener(){
//                    public void onClick(View v) {
//                        LayoutInflater layoutInflater1 = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        View customView1 = layoutInflater1.inflate(R.layout.sixpatti_how_to_play_info,null);
//                        closesixpattihelp = customView1.findViewById(R.id.closesixpattihelp);
//
//                        //Instantiate the popup
//                        howtosixpattipopup = new PopupWindow(customView1, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                        //display the popup window
//                        howtosixpattipopup.showAtLocation(RelativeLayoutloader, Gravity.TOP, 0, 0);
//
//                        //closing the popup
//                        closesixpattihelp.setOnClickListener(new View.OnClickListener(){
//
//                            @Override
//                            public void onClick(View v) {
//                                howtosixpattipopup.dismiss();
//                            }
//                        });
//                        sixpattipopup.dismiss();
//                    }
//                });
//
//                //instantiate popup window
//                sixpattipopup = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                sixpattipopup.showAtLocation(RelativeLayoutloader, Gravity.CENTER, 0, 0);
//
//                //join now the popup window on button click
//                joinnowsixpattibtn = customView.findViewById(R.id.joinnowsixpatti);
//
//                joinnowsixpattibtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivity.this, LoadingScreen_sixpatti.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
            }
        });

        //////////////// Popup for Tourney ////////////////
        cyanchipsbtn = findViewById(R.id.cyanchips);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);
        relativeLayout_tourney = findViewById(R.id.relativelayout_tourney);


        cyanchipsbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, LoadingScreen_tourney.class);
                startActivity(intent);
                finish();

//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.tourney_join_tournament_popup,null);
//                shortinfo_tourney =customView.findViewById(R.id.short_tourney_info);
//
//                tourney_join_closebtn = customView.findViewById(R.id.join_tourney_close);
//
//
//
//                // onclick event
//                shortinfo_tourney.setOnClickListener(new View.OnClickListener(){
//                    public void onClick(View v) {
//                        LayoutInflater layoutInflater1 = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        View customView1 = layoutInflater1.inflate(R.layout.tourney_info,null);
//                        tourney_shortinfo_closebtn = customView1.findViewById(R.id.close_tourney_info);
//
//                        //Instantiate the popup
//                        shortinfo_tourney_popupwindow = new PopupWindow(customView1, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                        //display the popup window
//                        shortinfo_tourney_popupwindow.showAtLocation(RelativeLayoutloader, Gravity.TOP, 0, 0);
//
//                        //closing the popup
//                        tourney_shortinfo_closebtn.setOnClickListener(new View.OnClickListener(){
//
//                            @Override
//                            public void onClick(View v) {
//                                shortinfo_tourney_popupwindow.dismiss();
//                            }
//                        });
//                        sixpattipopup.dismiss();
//                    }
//                });
//
//                //instantiate popup window
//                join_tourney_popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                join_tourney_popupWindow.showAtLocation(RelativeLayoutloader, Gravity.CENTER, 0, 0);
//
//                //join now the popup window on button click
//                join_tourneybtn = customView.findViewById(R.id.joinnow_tourney);
//
//                join_tourneybtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivity.this, LoadingScreen_tourney.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
            }
        });




        //////////////// Popup for Tourney2 ////////////////
//        cyanchipsbtn = findViewById(R.id.cyanchips);
//        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);
//        relativeLayout_tourney = findViewById(R.id.relativelayout_tourney);
//
//
//        cyanchipsbtn.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongViewCast")
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, LoadingScreen_tourney.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });




        //////////////// Popup for Private ////////////////
        ygreenchipsbtn = findViewById(R.id.ygreenchips);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);

        ygreenchipsbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, LoadingScreen_private.class);
                startActivity(intent);


//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.private_create_table_popup,null);
//                closebtn_create_table =customView.findViewById(R.id.close_create_table);
//                //instantiate popup window
//                create_table_private_popupwindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                create_table_private_popupwindow.showAtLocation(RelativeLayoutloader, Gravity.CENTER, 0, 0);
//
//                //join now the popup window on button click
//                create_table_btn = customView.findViewById(R.id.create_table);
//
//                closebtn_create_table.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        create_table_private_popupwindow.dismiss();
//                    }
//                });
//                create_table_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(MainActivity.this, LoadingScreen_private.class);
//                        startActivity(intent);
//                    }
//                });
            }
        });




        //////////////// Popup for Variation ////////////////
        mainlimegchipsbtn = findViewById(R.id.mainlimegchips);
        RelativeLayoutloader = findViewById(R.id.linearLayoutloader);

        mainlimegchipsbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.variation_join_table,null);

                variation_closebtn =customView.findViewById(R.id.close_var_popup);

                jokerlayout_btn=customView.findViewById(R.id.joker_layout);
                jokerinfo_layout=customView.findViewById(R.id.jokerinfo);
                joker_img=customView.findViewById(R.id.joker_img);

                ak47_layout_btn=customView.findViewById(R.id.ak47_layout);
                ak47info_layout=customView.findViewById(R.id.ak47info);
                ak_img=customView.findViewById(R.id.ak_img);

                xboot_layout_btn=customView.findViewById(R.id.xboot_layout);
                xboot_info_layout=customView.findViewById(R.id.xboot_info);
                xboot_img=customView.findViewById(R.id.xboot_img);

                hukum_layout_btn=customView.findViewById(R.id.hukum_layout);
                hukum_info_layout=customView.findViewById(R.id.hukum_info);
                hukum_img=customView.findViewById(R.id.hukum_img);

                muflis_layout_btn=customView.findViewById(R.id.muflis_layout);
                muflis_info_layout=customView.findViewById(R.id.muflis_info);
                muflis_img=customView.findViewById(R.id.muflis_img);

                faceoff_layout_btn=customView.findViewById(R.id.faceoff_layout);
                faceoff_info_layout=customView.findViewById(R.id.faceoff_info);
                faceoff_img=customView.findViewById(R.id.faceoff_img);

                ljoker_layout_btn=customView.findViewById(R.id.ljoker_layout);
                ljoker_info_layout=customView.findViewById(R.id.ljoker_info);
                ljoker_img=customView.findViewById(R.id.ljoker_img);

                nnnine_layout_btn=customView.findViewById(R.id.nnnine_layout);
                nnnine_info_layout=customView.findViewById(R.id.nnnine_info);
                nnnine_img=customView.findViewById(R.id.nnnine_img);


                final Animation Animleft = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_translate);
                final Animation Animright = AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_translate);


                //instantiate popup window
                join_table_popupwindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                join_table_popupwindow.showAtLocation(RelativeLayoutloader, Gravity.CENTER, 0, 0);

                variation_closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        join_table_popupwindow.dismiss();
                    }
                });


                // joker variation on click

                jokerlayout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(value==0) {
                            ak47info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();

                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

//                           ak_img.setVisibility(View.VISIBLE);
                            jokerinfo_layout.setVisibility(View.VISIBLE);
                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));
                            Toast.makeText(MainActivity.this, "joker out", Toast.LENGTH_SHORT).show();
                            jokerinfo_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value=1;
                            value1=0;
                            value2=0;
                            value3=0;
                            value4=0;
                            value5=0;
                            value6=0;
                            value7=0;

                            return;
                        }
                       else if(value==1){
                            ak47info_layout.clearAnimation();
                            jokerinfo_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            xboot_info_layout.setVisibility(View.INVISIBLE);

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            Toast.makeText(MainActivity.this, "joker in", Toast.LENGTH_SHORT).show();

                            jokerinfo_layout.startAnimation(Animright);
                            Animright.setFillAfter(true);
                            value=0;
                            return;
                        }
                    }
                });

            // AK47 variation on click

                ak47_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value1==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

//                            xboot_info_layout.clearAnimation();
//                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            ak47info_layout.setVisibility(View.VISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "ak out", Toast.LENGTH_SHORT).show();

                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));
                            ak47info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value1=1;
                            value2=0;
                            value3=0;
                            value=0;
                            value4=0;
                            value5=0;
                            value6=0;
                            value7=0;

                            return;
                        }
                        else if(value1==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "ak in", Toast.LENGTH_SHORT).show();

                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            ak47info_layout.startAnimation(Animright);
                            Animright.setFillAfter(true);
                            value1=0;
                            return;
                        }
                    }
                });


                // 4X boot variation on click

                xboot_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value2==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.setVisibility(View.VISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "xboot out", Toast.LENGTH_SHORT).show();

                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));

                            xboot_info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value2=1;
                            value1=0;
                            value=0;
                            value3=0;
                            value4=0;
                            value5=0;
                            value6=0;
                            value7=0;

                            return;
                        }
                        if (value2==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "xboot in", Toast.LENGTH_SHORT).show();

                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.startAnimation(Animright);
                            value2 = 0;
                            Animright.setFillAfter(true);
                            return;
                        }
                    }
                });

                // hukum variation on click

                hukum_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value3==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.VISIBLE);

                            Toast.makeText(MainActivity.this, "hukum out", Toast.LENGTH_SHORT).show();

                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));

                            hukum_info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value3=1;
                            value1=0;
                            value=0;
                            value2=0;
                            value4=0;
                            value5=0;
                            value6=0;
                            value7=0;

                            return;
                        }
                        if (value3==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "hukum in", Toast.LENGTH_SHORT).show();

                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            hukum_info_layout.startAnimation(Animright);
                            value3 = 0;
                            Animright.setFillAfter(true);
                            return;
                        }
                    }
                });


                // muflis variation on click

                muflis_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value4==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.VISIBLE);

                            Toast.makeText(MainActivity.this, "muflis out", Toast.LENGTH_SHORT).show();

                            muflis_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));

                            muflis_info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value4=1;
                            value1=0;
                            value=0;
                            value2=0;
                            value3=0;
                            value5=0;
                            value6=0;
                            value7=0;

                            return;
                        }
                        if (value4==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "muflis in", Toast.LENGTH_SHORT).show();

                            muflis_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            muflis_info_layout.startAnimation(Animright);
                            value4 = 0;
                            Animright.setFillAfter(true);
                            return;
                        }
                    }
                });


                //  faceoff variation on click

                faceoff_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value5==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            muflis_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);
                            faceoff_info_layout.setVisibility(View.VISIBLE);

                            Toast.makeText(MainActivity.this, "faceoff out", Toast.LENGTH_SHORT).show();

                            faceoff_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));

                            faceoff_info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value5=1;
                            value1=0;
                            value=0;
                            value2=0;
                            value3=0;
                            value4=0;
                            value6=0;
                            value7=0;

                            return;
                        }
                        if (value5==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();
                            faceoff_info_layout.clearAnimation();

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);
                            faceoff_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "faceoff in", Toast.LENGTH_SHORT).show();

                            faceoff_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            faceoff_info_layout.startAnimation(Animright);
                            value5 = 0;
                            Animright.setFillAfter(true);
                            return;
                        }
                    }
                });


                //  ljoker variation on click

                ljoker_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value6==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();
                            faceoff_info_layout.clearAnimation();


                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            muflis_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            faceoff_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            nnnine_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);
                            faceoff_info_layout.setVisibility(View.INVISIBLE);
                            ljoker_info_layout.setVisibility(View.VISIBLE);

                            Toast.makeText(MainActivity.this, "ljoker out", Toast.LENGTH_SHORT).show();

                            ljoker_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));

                            ljoker_info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value6=1;
                            value1=0;
                            value=0;
                            value2=0;
                            value3=0;
                            value4=0;
                            value5=0;
                            value7=0;

                            return;
                        }
                        if (value6==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();
                            faceoff_info_layout.clearAnimation();
                            ljoker_info_layout.clearAnimation();

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);
                            faceoff_info_layout.setVisibility(View.INVISIBLE);
                            ljoker_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "ljoker in", Toast.LENGTH_SHORT).show();

                            ljoker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            ljoker_info_layout.startAnimation(Animright);
                            value6 = 0;
                            Animright.setFillAfter(true);
                            return;
                        }
                    }
                });


                //  nnnine variation on click

                nnnine_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value7==0) {

                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();
                            faceoff_info_layout.clearAnimation();
                            ljoker_info_layout.clearAnimation();

                            joker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ak_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            xboot_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            hukum_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            muflis_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            faceoff_img.setImageDrawable(getResources().getDrawable(R.drawable.q));
                            ljoker_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);
                            faceoff_info_layout.setVisibility(View.INVISIBLE);
                            ljoker_info_layout.setVisibility(View.INVISIBLE);
                            nnnine_info_layout.setVisibility(View.VISIBLE);


                            Toast.makeText(MainActivity.this, "nnnine out", Toast.LENGTH_SHORT).show();

                            nnnine_img.setImageDrawable(getResources().getDrawable(R.drawable.circle_arrow));

                            nnnine_info_layout.startAnimation(Animleft);
                            Animleft.setFillAfter(true);
                            value7=1;
                            value1=0;
                            value=0;
                            value2=0;
                            value3=0;
                            value4=0;
                            value5=0;
                            value6=0;

                            return;
                        }
                        if (value7==1){
                            jokerinfo_layout.clearAnimation();
                            ak47info_layout.clearAnimation();
                            xboot_info_layout.clearAnimation();
                            hukum_info_layout.clearAnimation();
                            muflis_info_layout.clearAnimation();
                            faceoff_info_layout.clearAnimation();
                            ljoker_info_layout.clearAnimation();
                            nnnine_info_layout.clearAnimation();

                            xboot_info_layout.setVisibility(View.INVISIBLE);
                            hukum_info_layout.setVisibility(View.INVISIBLE);
                            ak47info_layout.setVisibility(View.INVISIBLE);
                            jokerinfo_layout.setVisibility(View.INVISIBLE);
                            muflis_info_layout.setVisibility(View.INVISIBLE);
                            faceoff_info_layout.setVisibility(View.INVISIBLE);
                            ljoker_info_layout.setVisibility(View.INVISIBLE);
                            nnnine_info_layout.setVisibility(View.INVISIBLE);

                            Toast.makeText(MainActivity.this, "nnnine in", Toast.LENGTH_SHORT).show();

                            nnnine_img.setImageDrawable(getResources().getDrawable(R.drawable.q));

                            nnnine_info_layout.startAnimation(Animright);
                            value7 = 0;
                            Animright.setFillAfter(true);
                            return;
                        }
                    }
                });

                //join now the popup window on button click
                join_variation_btn = customView.findViewById(R.id.variation_jointble);

                join_variation_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, LoadingScreen_variation.class);
                        startActivity(intent);
                    }
                });
            }
        });



//        Implementation of share

//        share_loader.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
//                LayoutInflater inflater=getLayoutInflater();
//                View view= inflater.inflate(R.layout.share_dialog,null);
//                builder.setView(view);
//                facebook=view.findViewById(R.id.facebook);
//                whatsapp=view.findViewById(R.id.whatsapp);
//                general=view.findViewById(R.id.general);
//                facebook.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                        sharingIntent.setType("text/plain");
//                        sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.facebook.com");
//                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
//                    }
//                });
//                whatsapp.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//                        whatsappIntent.setType("text/plain");
//                        whatsappIntent.setPackage("com.whatsapp");
//                        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
//                        try {
//                            startActivity(whatsappIntent);
//                        } catch (android.content.ActivityNotFoundException ex) {
//                            Toast.makeText(MainActivity.this, "whatsapp not installed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                // general click
//                general.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //Uri pictureUri = Uri.parse("https://lifeclearance.com/androidImages/0.png");
//                        Intent shareIntent = new Intent();
//                        shareIntent.setAction(Intent.ACTION_SEND);
//                        shareIntent.putExtra(Intent.EXTRA_STREAM, "hi");
//                        shareIntent.setType("text/plain");
//                        //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        startActivity(Intent.createChooser(shareIntent, "Share images..."));
//                    }
//                });
//                AlertDialog alert= builder.create();
//                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(alert.getWindow().getAttributes());
//                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//                alert.show();
//                alert.getWindow().setAttributes(lp);
//
//            }
//        });



        // Animation of chips on main page

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mainactivity_chips_rotate);
        findViewById(R.id.mainychips).startAnimation(animation);
        findViewById(R.id.mainlimegchips).startAnimation(animation);
        findViewById(R.id.mainorgchips).startAnimation(animation);
        findViewById(R.id.darkbluechips).startAnimation(animation);
        findViewById(R.id.cyanchips).startAnimation(animation);
        findViewById(R.id.blackchips).startAnimation(animation);
        findViewById(R.id.ygreenchips).startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Animation antianimation = AnimationUtils.loadAnimation(this, R.anim.mainactivity_chips_rotate_anticlockwise);
        findViewById(R.id.innerlime).startAnimation(antianimation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //////////// Onclick method for teenpatti table /////////////




    @Override
    public void onBackPressed() {
            displayExitAlert("Alert","Do you want to Exit?");
        }

    private void displayExitAlert(String title, String message) {

            TextView tv_alert_ok,tv_alert_title,tv_alert_message,tv_alert_cancel;
            ImageView alert_box_close;

            final Dialog myAlertDialog = new Dialog(this);
            myAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myAlertDialog.setCanceledOnTouchOutside(false);
            myAlertDialog.setContentView(R.layout.alert_box);

            tv_alert_ok = myAlertDialog.findViewById(R.id.tv_alert_ok);
            tv_alert_cancel=myAlertDialog.findViewById(R.id.tv_alert_cancel);
            alert_box_close=myAlertDialog.findViewById(R.id.alert_box_close);
            tv_alert_title=myAlertDialog.findViewById(R.id.tv_alert_title);
            tv_alert_message=myAlertDialog.findViewById(R.id.tv_alert_message);

            tv_alert_title.setText(title);
            tv_alert_message.setText(message);
            tv_alert_ok.setText("Yes");
            tv_alert_cancel.setText("No");

            alert_box_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myAlertDialog.dismiss();
                }
            });

            tv_alert_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(0);
                }
            });

            tv_alert_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myAlertDialog.dismiss();
                }
            });
            myAlertDialog.show();

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainychips:
                startActivity(new Intent(MainActivity.this, LoadingScreen_teenpatti.class));
                finish();
                break;

            case R.id.mainlimegchips:
                startActivity(new Intent(MainActivity.this, Variation.class));
                finish();
                break;

            case R.id.blackchips:
                startActivity(new Intent(MainActivity.this, NewVariationActivity.class));
                finish();
                break;
        }
    }


    //////////// Onclick method for new variation table /////////////1
/*    public void sendNewVariationPage(View view){
        Intent intent = new Intent(MainActivity.this, LoadingScreen_new_variation.class);
        startActivity(intent);

    }*/
}

