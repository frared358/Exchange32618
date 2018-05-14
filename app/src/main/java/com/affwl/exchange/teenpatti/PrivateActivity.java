package com.affwl.exchange.teenpatti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import org.w3c.dom.DOMImplementation;

@SuppressWarnings( "deprecation" )
@SuppressLint("WrongViewCast")

public class PrivateActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView handle_right, backbtn,infobtn,infoclosebtn,profile,profile1,plus_btn,minus_btn,myplayerbtn,ustatusclosebtn,dealerbtn,dealerclsbtn,oplayerbtn,oustatusclosebtn,msgclosebtn,chngdealerclosebtn;;
    TextView closebtn,tipsbtn,chngdbtn,canceltipbtn,plusbtn,minusbtn,backtolobby,nametext,code,blind_btn,show_btn;
    PopupWindow popupWindow,infopopupWindow,chatpopupWindow,ustatuspopupWindow,dealerpopupWindow,oustatuspopupWindow,sendmsgpopupWindow,chngdpopupWindow;
    Button msgbtn,blockbtn;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3,privatetble;
    Session session;
    NavigationView navigationView;
    LinearLayout below_layout;
    TextView display_myplayer_bind;
    Animation animations;

    ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15;

    Animation animatecard1, animatecard2, animatecard3, animatecard4, animatecard5, animatecard6, animatecard7, animatecard8,
            animatecard9, animatecard10, animatecard11, animatecard12, animatecard13, animatecard14, animatecard15;

    TextView btn_see_cards;
    PercentRelativeLayout rl_bottom_caption;
    int minteger = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);

//        handle_right = findViewById(R.id.handle_right);
//        handle_right.setOnClickListener(this);
//
//
//        privatetble = (DrawerLayout) findViewById(R.id.privatetble);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, privatetble, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        privatetble.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView = (NavigationView) findViewById(R.id.teen_nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        display_myplayer_bind=findViewById(R.id.display_myplayer_bind);
        rl_bottom_caption=findViewById(R.id.rl_bottom_caption);
        below_layout=findViewById(R.id.below_layout);
        relativeLayout= (RelativeLayout) findViewById(R.id.privaterecycler);

        //        implemention of user profile pic
        profile=findViewById(R.id.inner_player_img);
        profile1=findViewById(R.id.inner_player_img1);
        nametext=findViewById(R.id.nametext);

        code=findViewById(R.id.code);
        session=new Session(this);
        String encodedimage=session.getImage();
        if (!encodedimage.equalsIgnoreCase(""))
        {
            byte[] b= Base64.decode(encodedimage, Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(b,0,b.length);
            profile.setImageBitmap(bmp);
            profile1.setImageBitmap(bmp);
        }
        String name=session.getName();
        nametext.setText(name);

        blind_btn=findViewById(R.id.blind);
//        Animation on blindshow_layout
//        LinearLayout blindshow_layout=findViewById(R.id.blindshow_layout);
//        TranslateAnimation animationsb = new TranslateAnimation(0.0f, 0.0f,
//                0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//        animationsb.setDuration(1000);  // animation duration
//        animationsb.setFillAfter(true);
//        blind_btn.startAnimation(animationsb);

//        Implementation of increament amount
        final TextView displayAmount = findViewById(R.id.start_amount);
        plus_btn=findViewById(R.id.inc_amount);
        plus_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sub= displayAmount.getText().toString().substring(1);
                minteger = Integer.parseInt(sub)*2;
                displayAmount.setText("₹" + minteger);
                plus_btn.setEnabled(false);
                plus_btn.setImageResource(R.drawable.disabled);
            }

        });

        //Implementation of decreament amount
        minus_btn=findViewById(R.id.dec_amount);
        minus_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                plus_btn.setImageResource(R.drawable.plus_btn);
                plus_btn.setEnabled(true);
                String sub= displayAmount.getText().toString().substring(1);
                minteger =Integer.parseInt(sub)/2;
                displayAmount.setText("₹" + minteger);
            }
        });

//        Implementation of Blind
        blind_btn=findViewById(R.id.blind);
        blind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_myplayer_bind.setText(displayAmount.getText().toString());
                display_myplayer_bind.bringToFront();
                animations = AnimationUtils.loadAnimation(PrivateActivity.this, R.anim.translate_text_up);
                display_myplayer_bind.startAnimation(animations);
                display_myplayer_bind.setVisibility(View.GONE);

                final Handler handler = new Handler();
                final Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                                below_layout.setVisibility(View.VISIBLE);
                                Animation animation = AnimationUtils.loadAnimation(PrivateActivity.this, R.anim.translate_up_below_layout);
//                                animation.setDuration(1000);
                                animation.setFillAfter(true);
                                below_layout.startAnimation(animation);
                                }
            }, 3000);

        handler1.postDelayed(new Runnable() {
        @Override
         public void run() {
                               Animation animation1 =  AnimationUtils.loadAnimation(PrivateActivity.this, R.anim.translate_bottom_blind_chaal);
//                               animation1.setDuration(1000);
                               animation1.setFillAfter(true);
                               rl_bottom_caption.startAnimation(animation1);
                               rl_bottom_caption.setVisibility(View.GONE);
                               blind_btn.setEnabled(false);
        }
}, 1000);
            }
        });

        //shuffling card animation
        animatecard1 = AnimationUtils.loadAnimation(this, R.anim.translate_top_left);
        animatecard2 = AnimationUtils.loadAnimation(this, R.anim.translate_top_right);
        animatecard3 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_left);
        animatecard4= AnimationUtils.loadAnimation(this, R.anim.translate_bottom_right);
        animatecard5 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);


        animatecard6 = AnimationUtils.loadAnimation(this, R.anim.translate_top_left);
        animatecard7 = AnimationUtils.loadAnimation(this, R.anim.translate_top_right);
        animatecard8 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_left);
        animatecard9= AnimationUtils.loadAnimation(this, R.anim.translate_bottom_right);
        animatecard10 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);

        animatecard11 = AnimationUtils.loadAnimation(this, R.anim.translate_top_left);
        animatecard12 = AnimationUtils.loadAnimation(this, R.anim.translate_top_right);
        animatecard13 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_left);
        animatecard14= AnimationUtils.loadAnimation(this, R.anim.translate_bottom_right);
        animatecard15 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);


        //card image
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

        //see myplayer card
        btn_see_cards=findViewById(R.id.btn_see_cards);
        show_btn=findViewById(R.id.show);
        btn_see_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card3.setImageResource(R.drawable.club_ace);
                card8.setImageResource(R.drawable.club_6);
                card13.setImageResource(R.drawable.club_ace);
                btn_see_cards.setVisibility(View.GONE);
                show_btn.setVisibility(View.VISIBLE);
            }
        });

        card3.bringToFront();
        card8.bringToFront();
        card13.bringToFront();

        //animate shuffle cards
        card1.startAnimation(animatecard1);
        animatecard2.setStartOffset(200);
        card2.startAnimation(animatecard2);
        animatecard3.setStartOffset(400);
        card3.startAnimation(animatecard3);
        animatecard4.setStartOffset(600);
        animatecard4.setFillAfter(true);
        card4.startAnimation(animatecard4);
        animatecard5.setStartOffset(800);
        card5.startAnimation(animatecard5);

        animatecard6.setStartOffset(1000);
        card6.startAnimation(animatecard6);
        animatecard7.setStartOffset(1200);
        card7.startAnimation(animatecard7);
        animatecard8.setStartOffset(1400);
        card8.startAnimation(animatecard8);
        animatecard9.setStartOffset(1600);
        animatecard9.setFillAfter(true);
        card9.startAnimation(animatecard9);
        animatecard10.setStartOffset(1800);
        card10.startAnimation(animatecard10);


        animatecard11.setStartOffset(2000);
        card11.startAnimation(animatecard11);
        animatecard12.setStartOffset(2200);
        card12.startAnimation(animatecard12);
        animatecard13.setStartOffset(2400);
        card13.startAnimation(animatecard13);
        animatecard14.setStartOffset(2600);
        animatecard14.setFillAfter(true);
        card14.startAnimation(animatecard14);
        animatecard15.setStartOffset(2800);
        card15.startAnimation(animatecard15);

        //display cards in position after animation overs
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

    /*            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) card1.getLayoutParams();
                params1.height=getResources().getDimensionPixelSize(R.dimen.card_height);
                params1.width=getResources().getDimensionPixelSize(R.dimen.card_width);
                                params1.setMargins(-100, 350, 0, 0);

                card1.setLayoutParams(params1);
                card1.setRotation(-30.0f);*/

                View view1 = findViewById(R.id.card1);
                PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                info1.widthPercent = 0.15f;
                info1.heightPercent=0.15f;
                info1.topMarginPercent=0.20f;
                info1.leftMarginPercent=0.10f;
                view1.setRotation(-30.0f);
                params1.addRule(RelativeLayout.RIGHT_OF,R.id.rl_playerbg1);
                params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                view1.requestLayout();

                View view2 = findViewById(R.id.card2);
                PercentRelativeLayout.LayoutParams params2 = (PercentRelativeLayout.LayoutParams) view2.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info2 = params2.getPercentLayoutInfo();
                info2.widthPercent = 0.15f;
                info2.heightPercent=0.15f;
                info2.topMarginPercent=0.60f;
                info2.leftMarginPercent=0.10f;
                params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params2.addRule(RelativeLayout.BELOW,R.id.rl_playerbg1);
                params2.addRule(RelativeLayout.RIGHT_OF,R.id.rl_playerbg2);
                view2.setRotation(-30.0f);
                view2.requestLayout();

                View view3 = findViewById(R.id.card3);
                PercentRelativeLayout.LayoutParams params3 = (PercentRelativeLayout.LayoutParams) view3.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info3 = params3.getPercentLayoutInfo();
                info3.topMarginPercent=0.65f;
                info3.widthPercent = 0.18f;
                info3.heightPercent=0.18f;
                info3.leftMarginPercent=0.52f;
                params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params3.addRule(RelativeLayout.RIGHT_OF,R.id.rl_myplayer);
                view3.setRotation(-30.0f);
                view3.requestLayout();

                View view4 = findViewById(R.id.card4);
                PercentRelativeLayout.LayoutParams params4 = (PercentRelativeLayout.LayoutParams) view4.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info4 = params4.getPercentLayoutInfo();
                info4.widthPercent = 0.15f;
                info4.heightPercent=0.15f;
                info4.topMarginPercent=0.25f;
                info4.rightMarginPercent=0.44f;
                params4.addRule(RelativeLayout.BELOW,R.id.rl_playerbg3);
                params4.addRule(RelativeLayout.ALIGN_PARENT_END);
                params4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                view4.setRotation(-30.0f);
                view4.requestLayout();

                View view5 = findViewById(R.id.card5);
                PercentRelativeLayout.LayoutParams params5 = (PercentRelativeLayout.LayoutParams) view5.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info5 = params5.getPercentLayoutInfo();
                info5.widthPercent = 0.15f;
                info5.heightPercent=0.15f;
                info5.topMarginPercent=0.20f;
                info5.rightMarginPercent=0.14f;
                params5.addRule(RelativeLayout.ALIGN_PARENT_END);
                params5.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                view5.setRotation(-30.0f);
                view5.requestLayout();

                View view6 = findViewById(R.id.card6);
                PercentRelativeLayout.LayoutParams params6 = (PercentRelativeLayout.LayoutParams) view6.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info6 = params6.getPercentLayoutInfo();
                info6.widthPercent = 0.15f;
                info6.heightPercent=0.15f;
                info6.topMarginPercent=0.20f;
                info6.leftMarginPercent=0.12f;
                params6.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                params6.setMargins(-80, 350, 0, 0);
                params6.addRule(RelativeLayout.RIGHT_OF,R.id.card1);
                view6.setRotation(-10.0f);
                view6.requestLayout();

                View view7 = findViewById(R.id.card7);
                PercentRelativeLayout.LayoutParams params7 = (PercentRelativeLayout.LayoutParams) view7.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info7 = params7.getPercentLayoutInfo();
                info7.widthPercent = 0.15f;
                info7.heightPercent=0.15f;
                info7.topMarginPercent=0.60f;
                info7.leftMarginPercent=0.12f;
                params7.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params7.addRule(RelativeLayout.RIGHT_OF,R.id.card2);
//                params7.setMargins(-80, 350, 0, 0);
                view7.setRotation(-10.0f);
                view7.requestLayout();

                View view8 = findViewById(R.id.card8);
                PercentRelativeLayout.LayoutParams params8 = (PercentRelativeLayout.LayoutParams) view8.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info8 = params8.getPercentLayoutInfo();
                info8.widthPercent = 0.18f;
                info8.heightPercent=0.18f;
                info8.topMarginPercent=0.65f;
                info8.leftMarginPercent=0.54f;
                params8.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                params8.addRule(RelativeLayout.CENTER_HORIZONTAL);
//                info8.rightMarginPercent=0.70f;
//                params8.addRule(RelativeLayout.CENTER_HORIZONTAL);
//                params8.addRule(RelativeLayout.RIGHT_OF,R.id.card3);
                params8.addRule(RelativeLayout.RIGHT_OF,R.id.rl_myplayer);
//                params8.setMargins(-95, 350, 0, 0);
                view8.setRotation(-10.0f);
                view8.requestLayout();

                View view9 = findViewById(R.id.card9);
                PercentRelativeLayout.LayoutParams params9 = (PercentRelativeLayout.LayoutParams) view9.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info9 = params9.getPercentLayoutInfo();
                info9.widthPercent = 0.15f;
                info9.heightPercent=0.15f;
                info9.topMarginPercent=0.25f;
                info9.rightMarginPercent=0.42f;
                params9.addRule(RelativeLayout.ALIGN_PARENT_END);
                params9.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params9.addRule(RelativeLayout.BELOW,R.id.rl_playerbg3);
//                params9.addRule(RelativeLayout.RIGHT_OF,R.id.card4);
//                params9.setMargins(-80, 350, 0, 0);
                view9.setRotation(-10.0f);
                view9.requestLayout();

                View view10 = findViewById(R.id.card10);
                PercentRelativeLayout.LayoutParams params10 = (PercentRelativeLayout.LayoutParams) view10.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info10 = params10.getPercentLayoutInfo();
                info10.widthPercent = 0.15f;
                info10.heightPercent=0.15f;
                info10.topMarginPercent=0.20f;
                info10.rightMarginPercent=0.12f;
                params10.addRule(RelativeLayout.ALIGN_PARENT_END);
                params10.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                params10.setMargins(-80, 350, 0, 0);
//                params10.addRule(RelativeLayout.RIGHT_OF,R.id.card5);
                view10.setRotation(-10.0f);
                view10.requestLayout();

                View view11 = findViewById(R.id.card11);
                PercentRelativeLayout.LayoutParams params11 = (PercentRelativeLayout.LayoutParams) view11.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info11 = params11.getPercentLayoutInfo();
                info11.widthPercent = 0.15f;
                info11.heightPercent=0.15f;
                info11.topMarginPercent=0.20f;
                info11.leftMarginPercent=0.14f;
                params11.addRule(RelativeLayout.RIGHT_OF,R.id.card6);
                params11.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                params11.setMargins(-60, 350, 0, 0);
                view11.setRotation(10.0f);
                view11.requestLayout();

                View view12 = findViewById(R.id.card12);
                PercentRelativeLayout.LayoutParams params12 = (PercentRelativeLayout.LayoutParams) view12.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info12 = params12.getPercentLayoutInfo();
                info12.widthPercent = 0.15f;
                info12.heightPercent=0.15f;
                info12.topMarginPercent=0.60f;
                info12.leftMarginPercent=0.14f;
                params12.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params12.addRule(RelativeLayout.RIGHT_OF,R.id.card7);
//                params12.setMargins(-60, 350, 0, 0);
                view12.setRotation(10.0f);
                view12.requestLayout();


                View view13 = findViewById(R.id.card13);
                PercentRelativeLayout.LayoutParams params13 = (PercentRelativeLayout.LayoutParams) view13.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info13 = params13.getPercentLayoutInfo();
                info13.widthPercent = 0.18f;
                info13.heightPercent=0.18f;
                info13.topMarginPercent=0.65f;
                info13.leftMarginPercent=0.56f;
                params13.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

//                params13.addRule(RelativeLayout.CENTER_HORIZONTAL);
//                params13.addRule(RelativeLayout.RIGHT_OF,R.id.card8);
//                params13.setMargins(-90, 350, 0, 0);
                view13.setRotation(10.0f);
                view13.requestLayout();


                View view14 = findViewById(R.id.card14);
                PercentRelativeLayout.LayoutParams params14 = (PercentRelativeLayout.LayoutParams) view14.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info14 = params14.getPercentLayoutInfo();
                info14.widthPercent = 0.15f;
                info14.heightPercent=0.15f;
                info14.topMarginPercent=0.25f;
                info14.rightMarginPercent=0.40f;
                params14.addRule(RelativeLayout.ALIGN_PARENT_END);
                params14.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params14.addRule(RelativeLayout.BELOW,R.id.rl_playerbg3);

//                params14.setMargins(-60, 350, 0, 0);
                view14.setRotation(10.0f);
                view14.requestLayout();


              /*  PercentLayoutHelper.PercentLayoutParams params15 = (PercentLayoutHelper.PercentLayoutParams) card15.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info15 = params15.getPercentLayoutInfo();
                info15.widthPercent = 0.15f;
                info15.heightPercent=0.15f;
                info15.topMarginPercent=0.20f;
                info15.endMarginPercent=0.55f;
//                params15.addRule(RelativeLayout.RIGHT_OF,R.id.card10);
//                params15.setMargins(-60, 350, 0, 0);
                card15.setLayoutParams((ViewGroup.LayoutParams) params15);
                card15.setRotation(10.0f);*/

                View view15 = findViewById(R.id.card15);
                PercentRelativeLayout.LayoutParams params15 = (PercentRelativeLayout.LayoutParams) view15.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info15 = params15.getPercentLayoutInfo();
                info15.widthPercent = 0.15f;
                info15.heightPercent=0.15f;
                info15.topMarginPercent=0.20f;
                info15.rightMarginPercent=0.10f;
                params15.addRule(RelativeLayout.ALIGN_PARENT_END);
                params15.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                params15.addRule(RelativeLayout.RIGHT_OF,R.id.card10);
                view15.setRotation(10.0f);
                view15.requestLayout();

                btn_see_cards.bringToFront();
                btn_see_cards.setVisibility(View.VISIBLE);
                rl_bottom_caption.setVisibility(View.VISIBLE);
                below_layout.setVisibility(View.GONE);
            }
        });

        //////////////// Popup for Backbutton ///////////////////

        backbtn=(ImageView) findViewById(R.id.back);
        privatetble = (RelativeLayout) findViewById(R.id.privatetble);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        Intent intent = new Intent(PrivateActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        //////////////// Popup for InfoButton ///////////////////

        infobtn=(ImageView) findViewById(R.id.info);
        privatetble = (RelativeLayout) findViewById(R.id.privatetble);

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.private_gameinfo_popup, null);

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
//        privatetble = (DrawerLayout) findViewById(R.id.privatetble);
//
//        chatbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//        privatetble = (DrawerLayout) findViewById(R.id.privatetble);
//
//        myplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//                LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.other_player_status, null);
//
//                msgbtn=customView.findViewById(R.id.msg);
//
//                // onclick event for message button
//                msgbtn.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        privatetble = (RelativeLayout) findViewById(R.id.privatetble);

        dealerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                //Implementation of increament tip button
                final TextView displayInteger = customView.findViewById(R.id.tipamount);
                plusbtn=customView.findViewById(R.id.plus);
                plusbtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        String sub= displayInteger.getText().toString().substring(1);
                        minteger = Integer.parseInt(sub)*2;
                        displayInteger.setText("₹" + minteger);

                    }

                });

                //Implementation of decreament tip
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
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.handle_right:
//                privatetble.openDrawer(navigationView);
//                break;
//        }
//    }


    @Override
    public void onBackPressed() {
        LayoutInflater layoutInflater = (LayoutInflater) PrivateActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                Intent intent = new Intent(PrivateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }





    /////////// Onclick for Backtolobby /////////////

    public void backtolobby(View view)
    {
        Intent intent = new Intent(PrivateActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        return;
    }
}