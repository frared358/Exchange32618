package com.affwl.exchange.teenpatti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.Collections;


public class TourneyActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    ImageView handle_right, backbtn,infobtn,infoclosebtn,profile,chatclosebtn,chatclosebtn2,closebtn_tourney_leadboard,leaderboard_tourneybtn,myplayerbtn,ustatusclosebtn,dealerbtn,dealerclsbtn,oplayerbtn,oustatusclosebtn,msgclosebtn,chngdealerclosebtn;
    TextView closebtn,tipsbtn,chngdbtn,canceltipbtn,plusbtn,minusbtn,backtolobby,nametext,code;
    PopupWindow popupWindow,infopopupWindow,chatpopupWindow,ustatuspopupWindow,dealerpopupWindow,oustatuspopupWindow,sendmsgpopupWindow,chngdpopupWindow,tourney_leadboardpopupWindow;
    Button msgbtn,blockbtn;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3;
    DrawerLayout tourneytble;
    Session session;
    int minteger = 0;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourney);

        handle_right = findViewById(R.id.handle_right);
        handle_right.setOnClickListener(this);

//        Toggle drawer
        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, tourneytble, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        tourneytble.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.teen_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        relativeLayout= (RelativeLayout) findViewById(R.id.tourneyrecycler);

        //        implemention of user profile pic
        profile=findViewById(R.id.inner_player_img);
        nametext=findViewById(R.id.nametext);

        code=findViewById(R.id.code);
        session=new Session(this);
        String encodedimage=session.getImage();
        if (!encodedimage.equalsIgnoreCase(""))
        {
            byte[] b= Base64.decode(encodedimage, Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(b,0,b.length);
            profile.setImageBitmap(bmp);
        }
        String name=session.getName();
        nametext.setText(name);

        //////////////// Popup for Backbutton ///////////////////

        backbtn=(ImageView) findViewById(R.id.back);
        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        Intent intent = new Intent(TourneyActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        //////////////// Popup for InfoButton ///////////////////

        infobtn=(ImageView) findViewById(R.id.info);
        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.tourney_info_game_popup, null);

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
//        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);
//
//        chatbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//
//        myplayerbtn=(ImageView) findViewById(R.id.myplayer);
//        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);
//
//        myplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.player_status_popup, null);
//
//                ustatusclosebtn = (ImageView) customView.findViewById(R.id.userstatusclose);
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



        //////////////// Popup for Otheruserstatus ///////////////////

//        oplayerbtn=(ImageView) findViewById(R.id.playerbg2);
//
//
//        oplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.other_player_status, null);
//
//                msgbtn = customView.findViewById(R.id.msg);
//
//                // onclick event for message button
//                msgbtn.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);

        dealerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.dealer_popup, null);

                relativeLayout2=customView.findViewById(R.id.inctip_layout);
                relativeLayout3=customView.findViewById(R.id.bottombtns);
                tipsbtn=customView.findViewById(R.id.tipbtn);
                canceltipbtn=customView.findViewById(R.id.canceltip);
                chngdbtn=customView.findViewById(R.id.chngdealer);
                // onclick event for tip button to hide and show layout
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
                        LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


        //////////////// Popup for Leaderboard ///////////////////

        leaderboard_tourneybtn=(ImageView) findViewById(R.id.leaderboardtourney);
        tourneytble = (DrawerLayout) findViewById(R.id.tourneytble);

        leaderboard_tourneybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.tourney_leaderboard, null);

                closebtn_tourney_leadboard = (ImageView) customView.findViewById(R.id.closeleadtourney);

                //instantiate popup window
                tourney_leadboardpopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                tourney_leadboardpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                closebtn_tourney_leadboard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tourney_leadboardpopupWindow.dismiss();
                    }
                });
            }
        });
    }

    /////////// Slider /////////////
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.handle_right:
                tourneytble.openDrawer(navigationView);
                break;
        }
    }


    @Override
    public void onBackPressed() {

        LayoutInflater layoutInflater = (LayoutInflater) TourneyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                Intent intent = new Intent(TourneyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }





    /////////// Onclick for Backtolobby /////////////

    public void backtolobby(View view)
    {
        Intent intent = new Intent(TourneyActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}


