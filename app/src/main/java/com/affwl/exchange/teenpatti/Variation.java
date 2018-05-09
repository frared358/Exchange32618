package com.affwl.exchange.teenpatti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


public class Variation extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    ImageView handle_right, backbtn,infobtn,infoclosebtn,profile,chatclosebtn,chatclosebtn2,themebtn,themeclosebtn,myplayerbtn,ustatusclosebtn,dealerbtn,dealerclsbtn,oplayerbtn,oustatusclosebtn,msgclosebtn,chngdealerclosebtn,pdealerbtn;
    TextView closebtn,tipsbtn,chngdbtn,canceltipbtn,plusbtn,minusbtn,nametext,backtolobby,code;
    PopupWindow popupWindow,infopopupWindow,chatpopupWindow,themepopupWindow,ustatuspopupWindow,dealerpopupWindow,oustatuspopupWindow,sendmsgpopupWindow,chngdpopupWindow,selectvariationpopupWindow;
    Button msgbtn,blockbtn;
    RelativeLayout relativeLayout2,relativeLayout3;
    DrawerLayout variationtble;
    NavigationView navigationView;
    public int counter=15;
    public int counter4=15;
    public int counter5=15;
    Session session;

    PercentRelativeLayout relativeLayout;

    CountDownTimer countDownTimer;

    int minteger = 0;
    RadioGroup radioGroup;
    RadioButton radiojokerbtn,radioakbtn,radioxbootbtn,radiohukumbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation);

        handle_right = findViewById(R.id.handle_right);
        handle_right.setOnClickListener(this);


        variationtble = (DrawerLayout) findViewById(R.id.variationtble);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, variationtble, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        variationtble.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.teen_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        relativeLayout= (PercentRelativeLayout) findViewById(R.id.variationrecycler);
        loadingPopup();



        //        implemention of user profile pic
        profile=findViewById(R.id.inner_player_img);
//        nametext=findViewById(R.id.nametext);

        code=findViewById(R.id.code);
        session=new Session(this);
        String encodedimage=session.getImage();
        if (!encodedimage.equalsIgnoreCase(""))
        {
            byte[] b= Base64.decode(encodedimage, Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(b,0,b.length);
            profile.setImageBitmap(bmp);
        }
//        String name=session.getName();
//        nametext.setText(name);

        //////////////// Popup for Backbutton ///////////////////


        backbtn=(ImageView) findViewById(R.id.back);
        variationtble = (DrawerLayout) findViewById(R.id.variationtble);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.backbutton_popup, null);

                closebtn = (TextView) customView.findViewById(R.id.close);
                backtolobby=customView.findViewById(R.id.backtolobby);
                //instantiate popup window
                popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                backtolobby.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Variation.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });



        //////////////// Popup for InfoButton ///////////////////

        infobtn=(ImageView) findViewById(R.id.info);
        variationtble = (DrawerLayout) findViewById(R.id.variationtble);

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.infogame_popup, null);

                infoclosebtn = (ImageView) customView.findViewById(R.id.infoclose);

                //instantiate popup window
                infopopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                infopopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                infoclosebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        infopopupWindow.dismiss();
                    }
                });
            }
        });




        //////////////// Popup for ChatButton ///////////////////

//        chatbtn=(ImageView) findViewById(R.id.chat);
//        variationtble = (DrawerLayout) findViewById(R.id.variationtble);
//
//        chatbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.chat_popup, null);
//
//                chatclosebtn = (ImageView) customView.findViewById(R.id.chatclose);
//                chatclosebtn2 = (ImageView) customView.findViewById(R.id.chatclose2);
//                //instantiate popup window
//                chatpopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                chatpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
//
//                //close the popup window on button click
//                chatclosebtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        chatpopupWindow.dismiss();
//                    }
//                });
//                chatclosebtn2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        chatpopupWindow.dismiss();
//                    }
//                });
//            }
//        });


        //////////////// Popup for Userstatus ///////////////////

//        myplayerbtn=(ImageView) findViewById(R.id.myplayer);
//        variationtble = (DrawerLayout) findViewById(R.id.variationtble);
//
//        myplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.player_status_popup, null);
//
//                ustatusclosebtn = (ImageView) customView.findViewById(R.id.userstatusclose);
//
//                //instantiate popup window
//                ustatuspopupWindow = new PopupWindow(customView,RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                ustatuspopupWindow.showAtLocation(relativeLayout, Gravity.CENTER_HORIZONTAL, 0, 0);
//
//                //close the popup window on button click
//                ustatusclosebtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ustatuspopupWindow.dismiss();
//                    }
//                });
//            }
//        });


        // Onclick on playerdealer button

        pdealerbtn=(ImageView)findViewById(R.id.playerdealervar);
        variationtble = (DrawerLayout) findViewById(R.id.variationtble);
        pdealerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View customView = layoutInflater.inflate(R.layout.select_variation, null);
                //instantiate popup window
                selectvariationpopupWindow = new PopupWindow(customView,PercentRelativeLayout.LayoutParams.WRAP_CONTENT, PercentRelativeLayout.LayoutParams.WRAP_CONTENT);

                //display the popup window
                selectvariationpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
                // countdown timer for selectvariation
                final TextView txtsecondvar=customView.findViewById(R.id.timecountervar);

                long millisInFuture = 15000; //25 seconds
                long countDownInterval = 1000; //1 second

                countDownTimer=new CountDownTimer(millisInFuture, countDownInterval){
                    public void onTick(long millisUntilFinished){
//                        txtsecondvar.setText(String.valueOf(counter2));
                        txtsecondvar.setText(" "+millisUntilFinished/1000);
//                        counter2--;
                    }
                    public  void onFinish(){
                        selectvariationpopupWindow.dismiss();
                    }
                };
                countDownTimer.start();

                // Implementing onclick on radio button to select variation
//                final RelativeLayout variation1 = customView.findViewById(R.id.variation);
//                final RelativeLayout relativeLayout2 = customView.findViewById(R.id.svariationpopup);
                final TextView txtsecondtimer=customView.findViewById(R.id.timecountervar2);
                radioGroup = (RadioGroup)customView.findViewById(R.id.radiogrp);
                int id= radioGroup.getCheckedRadioButtonId();
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(final RadioGroup group, final int checkedId) {
                        group.check(checkedId);
                        // countdown for radio button to dismiss popup
                        long millisInFuture = 3000; //3 seconds
                        long countDownInterval = 1000; //1 second
                        new CountDownTimer(millisInFuture, countDownInterval){
                            public void onTick(long millisUntilFinished){
                                txtsecondtimer.setText(" "+millisUntilFinished/1000);
//                                counter3--;
                            }
                            public  void onFinish(){
                                selectvariationpopupWindow.dismiss();



//                                if (variation1.getVisibility() == View.VISIBLE) {
//                                    variation1.setVisibility(View.INVISIBLE);
//                                }
//                                if (relativeLayout2.getVisibility() == View.INVISIBLE) {
//                                    relativeLayout2.setVisibility(View.VISIBLE);
//                                }
                            }
                        }.start();

                    }
                });
            }
        });


        //////////////// Popup for Otheruserstatus ///////////////////
//        oplayerbtn=(ImageView) findViewById(R.id.playerbg2);

//
//        oplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.other_player_status, null);
//
//                msgbtn=customView.findViewById(R.id.msg);
//
//                // onclick event for message button
//                msgbtn.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        View customView1 = layoutInflater.inflate(R.layout.send_message_popup, null);
//                        msgclosebtn=customView1.findViewById(R.id.msgclose);
//                        //instantiate popup window
//                        sendmsgpopupWindow = new PopupWindow(customView1,RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//                        //display the popup window
//                        sendmsgpopupWindow.showAtLocation(relativeLayout, Gravity.TOP, 0, 0);
//
//                        //close the popup window on button click
//                        msgclosebtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                sendmsgpopupWindow.dismiss();
//                            }
//                        });
//
//                        oustatuspopupWindow.dismiss();
//                    }
//
//                });
//
//                oustatusclosebtn = (ImageView) customView.findViewById(R.id.ouserstatusclose);
//                //instantiate popup window
//                oustatuspopupWindow = new PopupWindow(customView,RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                oustatuspopupWindow.showAtLocation(relativeLayout, Gravity.CENTER_HORIZONTAL, 0, 0);
//
//                //close the popup window on button click
//                oustatusclosebtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        oustatuspopupWindow.dismiss();
//                    }
//                });
//            }
//        });

        //////////////// Popup for Dealer ///////////////////

        dealerbtn=(ImageView) findViewById(R.id.dealer);
        variationtble = (DrawerLayout) findViewById(R.id.variationtble);
        dealerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.dealer_popup, null);

                relativeLayout2=customView.findViewById(R.id.inctip_layout);
                relativeLayout3=customView.findViewById(R.id.bottombtns);
                tipsbtn=customView.findViewById(R.id.tipbtn);
                canceltipbtn=customView.findViewById(R.id.canceltip);
                chngdbtn=customView.findViewById(R.id.chngdealer);
                // onclick event for tip button to hide and show three_two_one_leaderboard
                tipsbtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (relativeLayout2.getVisibility()==View.INVISIBLE) {
                            relativeLayout2.setVisibility(View.VISIBLE);
                        }
                        if (relativeLayout3.getVisibility()==View.VISIBLE)
                        {
                            relativeLayout3.setVisibility(View.INVISIBLE);
                        }
                    }

                });
                // onclick event for change dealer button
                chngdbtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View customView2 = layoutInflater.inflate(R.layout.change_dealer, null);
                        chngdealerclosebtn=customView2.findViewById(R.id.chngdealerclose);
                        //instantiate popup window
                        chngdpopupWindow = new PopupWindow(customView2,RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                        //display the popup window
                        chngdpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                        //close the popup window on button click
                        chngdealerclosebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chngdpopupWindow.dismiss();
                            }
                        });
                        dealerpopupWindow.dismiss();
                    }

                });
                // onclick event for cancel button of tip
                canceltipbtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (relativeLayout2.getVisibility()==View.VISIBLE) {
                            relativeLayout2.setVisibility(View.INVISIBLE);
                        }
                        if (relativeLayout3.getVisibility()==View.INVISIBLE)
                        {
                            relativeLayout3.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //Implementation of increament button
                final TextView displayInteger = customView.findViewById(R.id.tipamount);
                plusbtn=customView.findViewById(R.id.plus);
                plusbtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        String sub= displayInteger.getText().toString().substring(1);
                        minteger = Integer.parseInt(sub)*2;
                        displayInteger.setText("₹" + minteger);

                    }

                });

                //Implementation of decreament
                minusbtn=customView.findViewById(R.id.minus);
                minusbtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        String sub= displayInteger.getText().toString().substring(1);
                        minteger =Integer.parseInt(sub)/2;
                        displayInteger.setText("₹" + minteger);
                    }
                });
                dealerclsbtn = (ImageView) customView.findViewById(R.id.dealerclose);
                //instantiate popup window
                dealerpopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                dealerpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                //close the popup window on button click
                dealerclsbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dealerpopupWindow.dismiss();
                    }
                });
            }
        });
    }

    /////////// Slider /////////////
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.handle_right:
                variationtble.openDrawer(navigationView);
                break;
        }
    }


    @Override
    public void onBackPressed() {

        LayoutInflater layoutInflater = (LayoutInflater) Variation.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.backbutton_popup, null);

        closebtn = (TextView) customView.findViewById(R.id.close);
        backtolobby=customView.findViewById(R.id.backtolobby);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        //display the popup window
        popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        backtolobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Variation.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }



//    Implementing startup popup selecting variation

    public void loadingPopup() {
        final LayoutInflater inflater = this.getLayoutInflater();
        final View layout = inflater.inflate(R.layout.select_variation_popup, null);
        final PopupWindow windows = new PopupWindow(layout , RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        windows.setFocusable(false);
        windows.setTouchable(true);
        windows.setOutsideTouchable(true);
        layout.post(new Runnable() {
            public void run() {
                windows.showAtLocation(layout,Gravity.CENTER, 0, 0);
            }
        });

        ImageView closeselectv=layout.findViewById(R.id.closeselectv);
        closeselectv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                windows.dismiss();
                final RelativeLayout variation1 = layout.findViewById(R.id.variation);
                final RelativeLayout relativeLayout1 = layout.findViewById(R.id.svariationpopup);
                if (variation1.getVisibility() == View.VISIBLE) {
                    variation1.setVisibility(View.INVISIBLE);
                }
                if (relativeLayout1.getVisibility() == View.INVISIBLE) {
                    relativeLayout1.setVisibility(View.VISIBLE);
                    final TextView txtsecond=layout.findViewById(R.id.countdowntimer);
                    new CountDownTimer(2000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            txtsecond.setText(String.valueOf(counter5));
                            counter5--;
                        }

                        @Override
                        public void onFinish() {
                            relativeLayout1.setVisibility(View.INVISIBLE);
                        }
                    }.start();
                }
            }
        });


        final TextView txtsecond=layout.findViewById(R.id.countdowntimer);
        new CountDownTimer(16000, 1000){
            public void onTick(long millisUntilFinished){
                txtsecond.setText(String.valueOf(counter));
                counter--;
            }
            public  void onFinish() {
                //windows.dismiss();
                final RelativeLayout variation = layout.findViewById(R.id.variation);
                final RelativeLayout relativeLayout = layout.findViewById(R.id.svariationpopup);
                if (variation.getVisibility() == View.VISIBLE) {
                    variation.setVisibility(View.INVISIBLE);
                }
                if (relativeLayout.getVisibility() == View.INVISIBLE) {
                    relativeLayout.setVisibility(View.VISIBLE);
                    new CountDownTimer(2000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            txtsecond.setText(String.valueOf(counter4));
                            counter4--;
                        }

                        @Override
                        public void onFinish() {
                            relativeLayout.setVisibility(View.INVISIBLE);
                        }
                    }.start();
                }
            }
        }.start();
//        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.zoom_in);
//        RelativeLayout vrelative=three_two_one_leaderboard.findViewById(R.id.variation);
//        vrelative.setVisibility(View.VISIBLE);
//        vrelative.startAnimation(animZoomIn);
//
//
//        Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.zoom_out);
//        vrelative.setVisibility(View.VISIBLE);
//        vrelative.startAnimation(animZoomOut);
//        windows.dismiss();
    }
}