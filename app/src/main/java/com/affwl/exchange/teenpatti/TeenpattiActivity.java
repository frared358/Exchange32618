package com.affwl.exchange.teenpatti;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


public class TeenpattiActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    ImageView handle_right, backbtn,infobtn,infoclosebtn,chatbtn,chatclosebtn,chatclosebtn2,themebtn,themeclosebtn,myplayerbtn,ustatusclosebtn,dealerbtn,dealerclsbtn,oplayerbtn,oustatusclosebtn,msgclosebtn,chngdealerclosebtn;;
    TextView closebtn,tipsbtn,chngdbtn,canceltipbtn,plusbtn,minusbtn,backtolobby;
    PopupWindow popupWindow,infopopupWindow,chatpopupWindow,themepopupWindow,ustatuspopupWindow,dealerpopupWindow,oustatuspopupWindow,sendmsgpopupWindow,chngdpopupWindow;
    Button msgbtn,blockbtn;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3;
    DrawerLayout teenpattitble;
    NavigationView navigationView;

    TextView btn_see_cards;

    ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15;

    TranslateAnimation animatecard1, animatecard2, animatecard3, animatecard4, animatecard5, animatecard6, animatecard7, animatecard8,
            animatecard9, animatecard10, animatecard11, animatecard12, animatecard13, animatecard14, animatecard15;

    int minteger = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teenpatti);

        handle_right = findViewById(R.id.handle_right);
        handle_right.setOnClickListener(this);

        teenpattitble = (DrawerLayout) findViewById(R.id.teenpattitble);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, teenpattitble, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        teenpattitble.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.teen_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        animatecard1 = new TranslateAnimation(0.0f, -600.0f, 0.0f, -150.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animatecard1.setDuration(100);// animation duration
        animatecard1.setFillAfter(true);

        animatecard2 = new TranslateAnimation(0.0f, -600.0f, 0.0f, 250.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animatecard2.setDuration(100);  // animation duration
        animatecard2.setStartOffset(200);
        animatecard2.setFillAfter(true);

        animatecard3 = new TranslateAnimation(0.0f, -50.0f, 0.0f, 300.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animatecard3.setDuration(100);  // animation duration
        animatecard3.setStartOffset(400);
        animatecard3.setFillAfter(true);

        animatecard4 = new TranslateAnimation(0.0f, 550.0f,0.0f, 250.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animatecard4.setDuration(100);  // animation duration
        animatecard4.setStartOffset(600);
        animatecard4.setFillAfter(true);

        animatecard5 = new TranslateAnimation(0.0f, 550.0f,0.0f, -150.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animatecard5.setDuration(100);  // animation duration
        animatecard5.setStartOffset(800);
        animatecard5.setFillAfter(true);

        animatecard6 = new TranslateAnimation(0.0f, -600.0f, 0.0f, -150.0f);
        animatecard6.setDuration(100);
        animatecard6.setStartOffset(1000);
        animatecard6.setFillAfter(true);

        animatecard7 = new TranslateAnimation(0.0f, -600.0f, 0.0f, 250.0f);
        animatecard7.setDuration(100);
        animatecard7.setStartOffset(1200);
        animatecard7.setFillAfter(true);

        animatecard8 = new TranslateAnimation(0.0f, -50.0f, 0.0f, 300.0f);
        animatecard8.setDuration(100);
        animatecard8.setStartOffset(1400);
        animatecard8.setFillAfter(true);

        animatecard9= new TranslateAnimation(0.0f, 550.0f,0.0f, 250.0f);
        animatecard9.setDuration(100);
        animatecard9.setStartOffset(1600);
        animatecard9.setFillAfter(true);

        animatecard10 = new TranslateAnimation(0.0f, 550.0f,0.0f, -150.0f);
        animatecard10.setDuration(100);
        animatecard10.setStartOffset(1800);
        animatecard10.setFillAfter(true);

        animatecard11 = new TranslateAnimation(0.0f, -600.0f, 0.0f, -150.0f);
        animatecard11.setDuration(100);
        animatecard11.setStartOffset(2000);
        animatecard11.setFillAfter(true);

        animatecard12 = new TranslateAnimation(0.0f, -600.0f, 0.0f, 250.0f);
        animatecard12.setDuration(100);
        animatecard12.setStartOffset(2200);
        animatecard12.setFillAfter(true);

        animatecard13 = new TranslateAnimation(0.0f, -50.0f, 0.0f, 300.0f);
        animatecard13.setDuration(100);
        animatecard13.setStartOffset(2400);
        animatecard13.setFillAfter(true);

        animatecard14= new TranslateAnimation(0.0f, 550.0f,0.0f, 250.0f);
        animatecard14.setDuration(100);
        animatecard14.setStartOffset(2600);
        animatecard14.setFillAfter(true);

        animatecard15 = new TranslateAnimation(0.0f, 550.0f,0.0f, -150.0f);
        animatecard15.setDuration(100);
        animatecard15.setStartOffset(2800);
        animatecard15.setFillAfter(true);

        //////////////// Popup for Backbutton ///////////////////


        backbtn=(ImageView) findViewById(R.id.back);

        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        card4=findViewById(R.id.card4);
        card5=findViewById(R.id.card5);
        card6=findViewById(R.id.card6);
        card7=findViewById(R.id.card7);
        card8=findViewById(R.id.card8);
        card9=findViewById(R.id.card9);
        card10=findViewById(R.id.card10);
        card11=findViewById(R.id.card11);
        card12=findViewById(R.id.card12);
        card13=findViewById(R.id.card13);
        card14=findViewById(R.id.card14);
        card15=findViewById(R.id.card15);

        btn_see_cards=findViewById(R.id.btn_see_cards);

        card3.bringToFront();
        card8.bringToFront();
        card13.bringToFront();

        card1.startAnimation(animatecard1);
        card2.startAnimation(animatecard2);
        card3.startAnimation(animatecard3);
        card4.startAnimation(animatecard4);
        card5.startAnimation(animatecard5);
        card6.startAnimation(animatecard6);
        card7.startAnimation(animatecard7);
        card8.startAnimation(animatecard8);
        card9.startAnimation(animatecard9);
        card10.startAnimation(animatecard10);
        card11.startAnimation(animatecard11);
        card12.startAnimation(animatecard12);
        card13.startAnimation(animatecard13);
        card14.startAnimation(animatecard14);
        card15.startAnimation(animatecard15);

        animatecard15.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Pass the Intent to switch to other Activity

                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) card1.getLayoutParams();
//        params1.setMargins(-25, 70, 0, 0);
                params1.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params1.width=getResources().getDimensionPixelSize(R.dimen.card_width);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        params.addRule(RelativeLayout.ALIGN_TOP, R.id.table);
//        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
//        params1.addRule(RelativeLayout.RIGHT_OF, R.id.playerbg1);
                card1.setLayoutParams(params1);
                card1.setRotation(-30.0f);

                RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) card2.getLayoutParams();
                params2.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params2.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params2.setMargins(-100, 350, 0, 0);
                card2.setLayoutParams(params2);
                card2.setRotation(-30.0f);

                RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) card3.getLayoutParams();
                params3.height=getResources().getDimensionPixelSize(R.dimen.my_card_height);
                params3.width=getResources().getDimensionPixelSize(R.dimen.my_card_width);
                params3.setMargins(-100, 350, 0, 0);
                params3.addRule(RelativeLayout.RIGHT_OF,R.id.rl_myplayer);
                card3.setLayoutParams(params3);
                card3.setRotation(-30.0f);

                RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) card4.getLayoutParams();
                params4.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params4.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                card4.setLayoutParams(params4);
                card4.setRotation(-30.0f);

                RelativeLayout.LayoutParams params5 = (RelativeLayout.LayoutParams) card5.getLayoutParams();
                params5.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params5.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                card5.setLayoutParams(params5);
                card5.setRotation(-30.0f);

                RelativeLayout.LayoutParams params6 = (RelativeLayout.LayoutParams) card6.getLayoutParams();
                params6.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params6.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params6.setMargins(-80, 350, 0, 0);
                params6.addRule(RelativeLayout.RIGHT_OF,R.id.card1);
                card6.setLayoutParams(params6);
                card6.setRotation(-10.0f);

                RelativeLayout.LayoutParams params7 = (RelativeLayout.LayoutParams) card7.getLayoutParams();
                params7.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params7.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params7.addRule(RelativeLayout.RIGHT_OF,R.id.card2);
                params7.setMargins(-80, 350, 0, 0);
                card7.setLayoutParams(params7);
                card7.setRotation(-10.0f);

                RelativeLayout.LayoutParams params8 = (RelativeLayout.LayoutParams) card8.getLayoutParams();
                params8.height=getResources().getDimensionPixelSize(R.dimen.my_card_height);
                params8.width=getResources().getDimensionPixelSize(R.dimen.my_card_width);
                params8.addRule(RelativeLayout.RIGHT_OF,R.id.card3);
                params8.addRule(RelativeLayout.ALIGN_TOP,R.id.myplayer);
                params8.setMargins(-95, 350, 0, 0);
                card8.setLayoutParams(params8);
                card8.setRotation(-10.0f);

                RelativeLayout.LayoutParams params9 = (RelativeLayout.LayoutParams) card9.getLayoutParams();
                params9.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params9.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params9.addRule(RelativeLayout.RIGHT_OF,R.id.card4);
                params9.setMargins(-80, 350, 0, 0);
                card9.setLayoutParams(params9);
                card9.setRotation(-10.0f);

                RelativeLayout.LayoutParams params10 = (RelativeLayout.LayoutParams) card10.getLayoutParams();
                params10.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params10.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params10.setMargins(-80, 350, 0, 0);
                params10.addRule(RelativeLayout.RIGHT_OF,R.id.card5);
                card10.setLayoutParams(params10);
                card10.setRotation(-10.0f);

                RelativeLayout.LayoutParams params11 = (RelativeLayout.LayoutParams) card11.getLayoutParams();
                params11.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params11.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params11.addRule(RelativeLayout.RIGHT_OF,R.id.card6);
                params11.setMargins(-60, 350, 0, 0);
                card11.setLayoutParams(params11);
                card11.setRotation(10.0f);

                RelativeLayout.LayoutParams params12 = (RelativeLayout.LayoutParams) card12.getLayoutParams();
                params12.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params12.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                params12.addRule(RelativeLayout.RIGHT_OF,R.id.card7);
                params12.setMargins(-60, 350, 0, 0);
                card12.setLayoutParams(params12);
                card12.setRotation(10.0f);


                RelativeLayout.LayoutParams params13 = (RelativeLayout.LayoutParams) card13.getLayoutParams();
                params13.height = getResources().getDimensionPixelSize(R.dimen.my_card_height);
                params13.width = getResources().getDimensionPixelSize(R.dimen.my_card_width);
                params13.addRule(RelativeLayout.RIGHT_OF,R.id.card8);
                params13.setMargins(-90, 350, 0, 0);
                card13.setLayoutParams(params13);
                card13.setRotation(10.0f);


                RelativeLayout.LayoutParams params14 = (RelativeLayout.LayoutParams) card14.getLayoutParams();
                params14.height = getResources().getDimensionPixelSize(R.dimen.card_height);
                params14.width = getResources().getDimensionPixelSize(R.dimen.card_width);
                params14.addRule(RelativeLayout.RIGHT_OF,R.id.card9);
                params14.setMargins(-60, 350, 0, 0);
                card14.setLayoutParams(params14);
                card14.setRotation(10.0f);


                RelativeLayout.LayoutParams params15 = (RelativeLayout.LayoutParams) card15.getLayoutParams();
                params15.height = getResources().getDimensionPixelSize(R.dimen.card_height);
                params15.width = getResources().getDimensionPixelSize(R.dimen.card_width);
                params15.addRule(RelativeLayout.RIGHT_OF,R.id.card10);
                params15.setMargins(-60, 350, 0, 0);
                card15.setLayoutParams(params15);
                card15.setRotation(10.0f);

                btn_see_cards.bringToFront();
                btn_see_cards.setVisibility(View.VISIBLE);
            }
        });

        btn_see_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeenpattiActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                card3.setImageResource(R.drawable.club_ace);
                card8.setImageResource(R.drawable.club_6);
                card13.setImageResource(R.drawable.club_ace);
                btn_see_cards.setVisibility(View.GONE);
            }
        });

        teenpattitble = (DrawerLayout) findViewById(R.id.teenpattitble);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        Intent intent = new Intent(TeenpattiActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    //////////////// Popup for InfoButton ///////////////////

        infobtn=(ImageView) findViewById(R.id.info);
        teenpattitble = (DrawerLayout) findViewById(R.id.teenpattitble);

        infobtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //instantiate the popup.xml three_two_one_leaderboard file
            LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        chatbtn=(ImageView) findViewById(R.id.chat);
        teenpattitble = (DrawerLayout) findViewById(R.id.teenpattitble);

        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.chat_popup, null);

                chatclosebtn = (ImageView) customView.findViewById(R.id.chatclose);
                chatclosebtn2 = (ImageView) customView.findViewById(R.id.chatclose2);
                //instantiate popup window
                chatpopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                chatpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                chatclosebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chatpopupWindow.dismiss();
                    }
                });
                chatclosebtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chatpopupWindow.dismiss();
                    }
                });
            }
        });

        //////////////// Popup for Userstatus ///////////////////

        myplayerbtn=(ImageView) findViewById(R.id.myplayer);
        teenpattitble = (DrawerLayout) findViewById(R.id.teenpattitble);

        myplayerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.player_status_popup, null);

                ustatusclosebtn = (ImageView) customView.findViewById(R.id.userstatusclose);
                //instantiate popup window
                ustatuspopupWindow = new PopupWindow(customView,RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                ustatuspopupWindow.showAtLocation(relativeLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                //close the popup window on button click
                ustatusclosebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ustatuspopupWindow.dismiss();
                    }
                });
            }
        });



        //////////////// Popup for Otheruserstatus ///////////////////

        oplayerbtn=(ImageView) findViewById(R.id.playerbg2);
        relativeLayout= (RelativeLayout) findViewById(R.id.teenpattitblerecycler);

       oplayerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.other_player_status, null);

                msgbtn=customView.findViewById(R.id.msg);

                // onclick event for message button
                msgbtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View customView1 = layoutInflater.inflate(R.layout.send_message_popup, null);
                        msgclosebtn=customView1.findViewById(R.id.msgclose);
                        //instantiate popup window
                        sendmsgpopupWindow = new PopupWindow(customView1,RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                        //display the popup window
                        sendmsgpopupWindow.showAtLocation(relativeLayout, Gravity.TOP, 0, 0);

                        //close the popup window on button click
                        msgclosebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendmsgpopupWindow.dismiss();
                            }
                        });

                        oustatuspopupWindow.dismiss();
                    }

                });

                oustatusclosebtn = (ImageView) customView.findViewById(R.id.ouserstatusclose);
                //instantiate popup window
                oustatuspopupWindow = new PopupWindow(customView,RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                oustatuspopupWindow.showAtLocation(relativeLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                //close the popup window on button click
                oustatusclosebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        oustatuspopupWindow.dismiss();
                    }
                });
            }
        });

        //////////////// Popup for Dealer ///////////////////

        dealerbtn=(ImageView) findViewById(R.id.dealer);
        teenpattitble = (DrawerLayout) findViewById(R.id.teenpattitble);

        dealerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                teenpattitble.openDrawer(navigationView);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        LayoutInflater layoutInflater = (LayoutInflater) TeenpattiActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                Intent intent = new Intent(TeenpattiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
//    public void increaseInteger(View v) {
//        minteger = minteger + 1;
//        display(minteger);
//
//    }
//    public void decreaseInteger(View v) {
//        minteger = minteger - 1;
//        display(minteger);
//    }
//
//    private void display(int number) {
//        TextView displayInteger = (TextView) findViewById(R.id.tipamount);
//        if (displayInteger.getVisibility()==View.VISIBLE) {
//            displayInteger.setText("" + number);
//        }
//    }

}