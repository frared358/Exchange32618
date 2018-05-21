package com.affwl.exchange.teenpatti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings( "deprecation" )
public class SixPatti extends AppCompatActivity implements View.OnClickListener{
    ImageView handle_right, backbtn,infobtn,infoclosebtn,profile,chatclosebtn,chatclosebtn2,closebtnsixpattileadboard,leaderboardsixpattibtn,myplayerbtn,ustatusclosebtn,dealerbtn,dealerclsbtn,oplayerbtn,oustatusclosebtn,msgclosebtn,chngdealerclosebtn;;
    TextView closebtn,tipsbtn,chngdbtn,canceltipbtn,plusbtn,minusbtn,nametext,sortbtn,gobtn,backtolobby,code;
    PopupWindow popupWindow,infopopupWindow,chatpopupWindow,themepopupWindow,ustatuspopupWindow,dealerpopupWindow,oustatuspopupWindow,sendmsgpopupWindow,chngdpopupWindow,sixpattileadboardpopupWindow;
    Button msgbtn,blockbtn;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3;
    DrawerLayout sixpattitble;
    NavigationView navigationView;
    int minteger = 0;
    ImageView my_card1,my_card2,my_card3,my_card4,my_card5,my_card6;
    Session session;
    ArrayList<Integer> cards;
    int value=0;
    int value1=0;
    int value2=0;
    int value3=0;
    int value4=0;
    int value5=0;

    ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17,
            card18, card19, card20, card21, card22, card23, card24, card25, card26, card27, card28, card29, card30;

    Animation animatecard1, animatecard2, animatecard3, animatecard4, animatecard5, animatecard6, animatecard7, animatecard8,
            animatecard9, animatecard10, animatecard11, animatecard12, animatecard13, animatecard14, animatecard15, animatecard16,
            animatecard17, animatecard18, animatecard19,animatecard20, animatecard21, animatecard22, animatecard23, animatecard24,
            animatecard25, animatecard26,animatecard27, animatecard28, animatecard29, animatecard30;

    Animation animate_my_card1_up, animate_my_card1_back_down, animate_my_card2_up, animate_my_card2_back_down,
            animate_my_card3_up, animate_my_card3_back_down, animate_my_card4_up, animate_my_card4_back_down,
            animate_my_card5_up, animate_my_card5_back_down, animate_my_card6_up, animate_my_card6_back_down;

    Animation animate_my_player_card;

    TextView btn_see_cards;

    ArrayList<String> selectedCardArray=new ArrayList<String>();

    RelativeLayout playerbg2relativeLayout,rl_myplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_patti);


        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);

        // implemention of user profile pic
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


        //        Implementation of cards
        gobtn=findViewById(R.id.sixpatti_go);
        sortbtn=findViewById(R.id.sort);
        my_card1=findViewById(R.id.my_card1);
        my_card2=findViewById(R.id.my_card2);
        my_card3=findViewById(R.id.my_card3);
        my_card4=findViewById(R.id.my_card4);
        my_card5=findViewById(R.id.my_card5);
        my_card6=findViewById(R.id.my_card6);

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

        animatecard16 = AnimationUtils.loadAnimation(this, R.anim.translate_top_left);
        animatecard17 = AnimationUtils.loadAnimation(this, R.anim.translate_top_right);
        animatecard18 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_left);
        animatecard19= AnimationUtils.loadAnimation(this, R.anim.translate_bottom_right);
        animatecard20 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);

        animatecard21 = AnimationUtils.loadAnimation(this, R.anim.translate_top_left);
        animatecard22 = AnimationUtils.loadAnimation(this, R.anim.translate_top_right);
        animatecard23 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_left);
        animatecard24= AnimationUtils.loadAnimation(this, R.anim.translate_bottom_right);
        animatecard25 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);

        animatecard26 = AnimationUtils.loadAnimation(this, R.anim.translate_top_left);
        animatecard27 = AnimationUtils.loadAnimation(this, R.anim.translate_top_right);
        animatecard28 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom_left);
        animatecard29= AnimationUtils.loadAnimation(this, R.anim.translate_bottom_right);
        animatecard30 = AnimationUtils.loadAnimation(this, R.anim.translate_bottom);


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
        card16=findViewById(R.id.card16);
        card17=findViewById(R.id.card17);
        card18=findViewById(R.id.card18);
        card19=findViewById(R.id.card19);
        card20=findViewById(R.id.card20);
        card21=findViewById(R.id.card21);
        card22=findViewById(R.id.card22);
        card23=findViewById(R.id.card23);
        card24=findViewById(R.id.card24);
        card25=findViewById(R.id.card25);
        card26=findViewById(R.id.card26);
        card27=findViewById(R.id.card27);
        card28=findViewById(R.id.card28);
        card29=findViewById(R.id.card29);
        card30=findViewById(R.id.card30);

        //see myplayer card
        btn_see_cards=findViewById(R.id.btn_see_cards);

        btn_see_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card3.setImageResource(R.drawable.club_ace);
                card8.setImageResource(R.drawable.club_6);
                card13.setImageResource(R.drawable.club_ace);
                btn_see_cards.setVisibility(View.GONE);
            }
        });

        card3.bringToFront();
        card8.bringToFront();
        card13.bringToFront();
        card18.bringToFront();
        card23.bringToFront();
        card28.bringToFront();

        //animate shuffle cards
        card1.startAnimation(animatecard1);
        animatecard2.setStartOffset(200);
        card2.startAnimation(animatecard2);
        animatecard3.setStartOffset(400);
        card3.startAnimation(animatecard3);
        animatecard4.setStartOffset(600);
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
        card14.startAnimation(animatecard14);
        animatecard15.setStartOffset(2800);
        card15.startAnimation(animatecard15);


        animatecard16.setStartOffset(3000);
        card16.startAnimation(animatecard16);
        animatecard17.setStartOffset(3200);
        card17.startAnimation(animatecard17);
        animatecard18.setStartOffset(3400);
        card18.startAnimation(animatecard18);
        animatecard19.setStartOffset(3600);
        card19.startAnimation(animatecard19);
        animatecard20.setStartOffset(3800);
        card20.startAnimation(animatecard20);


        animatecard21.setStartOffset(4000);
        card21.startAnimation(animatecard21);
        animatecard22.setStartOffset(4200);
        card22.startAnimation(animatecard22);
        animatecard23.setStartOffset(4400);
        card23.startAnimation(animatecard23);
        animatecard24.setStartOffset(4600);
        card24.startAnimation(animatecard24);
        animatecard25.setStartOffset(4800);
        card25.startAnimation(animatecard25);


        animatecard26.setStartOffset(5000);
        card26.startAnimation(animatecard26);
        animatecard27.setStartOffset(5200);
        card27.startAnimation(animatecard27);
        animatecard28.setStartOffset(5400);
        card28.startAnimation(animatecard28);
        animatecard29.setStartOffset(5600);
        card29.startAnimation(animatecard29);
        animatecard30.setStartOffset(5800);
        card30.startAnimation(animatecard30);

        //display cards in position after animation overs
        animatecard30.setAnimationListener(new Animation.AnimationListener() {
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
                my_card1.setVisibility(View.VISIBLE);
                my_card2.setVisibility(View.VISIBLE);
                my_card3.setVisibility(View.VISIBLE);
                my_card4.setVisibility(View.VISIBLE);
                my_card5.setVisibility(View.VISIBLE);
                my_card6.setVisibility(View.VISIBLE);
            }
        });

//        Implementation of cards

        cards=new ArrayList<>();
        cards.add(106);// six of clubs
        cards.add(209);// nine of diamond
        cards.add(111);// ace of clubs
        cards.add(312);// jack of hearts
        cards.add(413);// queen of spades
        cards.add(214);// king of diamond


        // deal the firts 6 cards


//        my_card1.setVisibility(View.VISIBLE);
//        my_card2.setVisibility(View.VISIBLE);
//        my_card3.setVisibility(View.VISIBLE);
//        my_card4.setVisibility(View.VISIBLE);
//        my_card5.setVisibility(View.VISIBLE);
//        my_card6.setVisibility(View.VISIBLE);

        playerbg2relativeLayout=findViewById(R.id.playerbg2relativeLayout);
        rl_myplayer=findViewById(R.id.rl_myplayer);
        // Implementaion of six cards onclick and translate animation

        animate_my_card1_up = AnimationUtils.loadAnimation(this, R.anim.translate_card_up);
        animate_my_card1_back_down = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        animate_my_card2_up = AnimationUtils.loadAnimation(this, R.anim.translate_card_up);
        animate_my_card2_back_down = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        animate_my_card3_up = AnimationUtils.loadAnimation(this, R.anim.translate_card_up);
        animate_my_card3_back_down = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        animate_my_card4_up = AnimationUtils.loadAnimation(this, R.anim.translate_card_up);
        animate_my_card4_back_down = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        animate_my_card5_up = AnimationUtils.loadAnimation(this, R.anim.translate_card_up);
        animate_my_card5_back_down = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        animate_my_card6_up = AnimationUtils.loadAnimation(this, R.anim.translate_card_up);
        animate_my_card6_back_down = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        my_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value==0) {
                    my_card1.clearAnimation();
                    animate_my_card1_up.setFillAfter(true);
                    my_card1.startAnimation(animate_my_card1_up);
                    value = 1;
                    selectedCardArray.add("value");
                    return;
                }
                if (value==1)
                {
                    my_card1.clearAnimation();
                    animate_my_card1_back_down.setFillAfter(true);
                    my_card1.startAnimation(animate_my_card1_back_down);
                    value=0;
                    selectedCardArray.remove("value");
                    return;
                }
            }
        });

        my_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value1==0) {
                    my_card2.clearAnimation();
                    animate_my_card2_up.setFillAfter(true);
                    my_card2.startAnimation(animate_my_card2_up);
                    value1 = 1;
                    selectedCardArray.add("value1");
                    return;
                }
                if (value1==1)
                {
                    my_card2.clearAnimation();
                    animate_my_card2_back_down.setFillAfter(true);
                    my_card2.startAnimation(animate_my_card2_back_down);
                    value1=0;
                    selectedCardArray.remove("value1");
                    return;
                }
            }
        });

        my_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value2==0) {
                    my_card3.clearAnimation();
                    animate_my_card3_up.setFillAfter(true);
                    my_card3.startAnimation(animate_my_card3_up);
                    value2= 1;
                    selectedCardArray.add("value2");
                    return;
                }
                if (value2==1)
                {
                    my_card3.clearAnimation();
                    animate_my_card3_back_down.setFillAfter(true);
                    my_card3.startAnimation(animate_my_card3_back_down);
                    value2=0;
                    selectedCardArray.remove("value2");
                    return;
                }
            }
        });

        my_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value3==0) {
                    my_card4.clearAnimation();
                    animate_my_card4_up.setFillAfter(true);
                    my_card4.startAnimation(animate_my_card4_up);
                    value3= 1;
                    selectedCardArray.add("value3");
                    return;
                }
                if (value3==1)
                {
                    my_card4.clearAnimation();
                    animate_my_card4_back_down.setFillAfter(true);
                    my_card4.startAnimation(animate_my_card4_back_down);
                    value3=0;
                    selectedCardArray.remove("value3");
                    return;
                }
            }
        });

        my_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value4==0) {
                    my_card5.clearAnimation();
                    animate_my_card5_up.setFillAfter(true);
                    my_card5.startAnimation(animate_my_card5_up);
                    value4= 1;
                    selectedCardArray.add("value4");
                    return;
                }
                if (value4==1)
                {
                    my_card5.clearAnimation();
                    animate_my_card5_back_down.setFillAfter(true);
                    my_card5.startAnimation(animate_my_card5_back_down);
                    value4=0;
                    selectedCardArray.remove("value4");
                    return;
                }
            }
        });

        my_card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value5==0) {
                    my_card6.clearAnimation();
                    animate_my_card6_up.setFillAfter(true);
                    my_card6.startAnimation(animate_my_card6_up);
                    value5= 1;
                    selectedCardArray.add("value5");
                    return;
                }
                if (value5==1)
                {
                    my_card6.clearAnimation();
                    animate_my_card6_back_down.setFillAfter(true);
                    my_card6.startAnimation(animate_my_card6_back_down);
                    value5=0;
                    selectedCardArray.remove("value5");
                    return;
                }
            }
        });

        // Click on Sort button
        sortbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.sort(cards);


                // deal the firts 6 cards

                assignImages(cards.get(0),my_card1);
                assignImages(cards.get(1),my_card2);
                assignImages(cards.get(2),my_card3);
                assignImages(cards.get(3),my_card4);
                assignImages(cards.get(4),my_card5);
                assignImages(cards.get(5),my_card6);

            }
        });

        animate_my_player_card = AnimationUtils.loadAnimation(this, R.anim.translate_card_back_down);

        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TranslateAnimation animations = new TranslateAnimation(0.0f, 0.0f,
//                        -20.0f, -50.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//                animations.setDuration(100);  // animation duration
                animate_my_player_card.setFillAfter(true);
                //animation.setFillAfter(true);
               if(selectedCardArray.size()==3){
                    if(selectedCardArray.get(0).equalsIgnoreCase("value")){
                        my_card1.startAnimation(animate_my_player_card);
                        View view1 = findViewById(R.id.my_card1);
                        PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                        info1.bottomMarginPercent=0.25f;
                        info1.widthPercent = 0.10f;
                        info1.heightPercent=0.10f;
                        info1.leftMarginPercent=0.52f;
                        params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        view1.setRotation(-30.0f);
                        view1.bringToFront();
                        view1.requestLayout();

                     /*   RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) my_card1.getLayoutParams();
                        params.setMargins(-100, 0, 0, 50);
                        params.addRule(RelativeLayout.CENTER_IN_PARENT);
                        params.addRule(RelativeLayout.RIGHT_OF, R.id.rl_myplayer);
                        my_card1.setLayoutParams(params);
                        my_card1.setRotation(-30.0f);*/
                    }
                    if(selectedCardArray.get(0).equalsIgnoreCase("value1")){
                        my_card2.startAnimation(animate_my_player_card);
                        View view1 = findViewById(R.id.my_card2);
                        PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                        info1.bottomMarginPercent=0.25f;
                        info1.widthPercent = 0.10f;
                        info1.heightPercent=0.10f;
                        info1.leftMarginPercent=0.52f;
                        params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        view1.setRotation(-30.0f);
                        view1.bringToFront();
                        view1.requestLayout();
                    }
                    if(selectedCardArray.get(0).equalsIgnoreCase("value2")){
                        my_card3.startAnimation(animate_my_player_card);
                        View view1 = findViewById(R.id.my_card3);
                        PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                        info1.bottomMarginPercent=0.25f;
                        info1.widthPercent = 0.10f;
                        info1.heightPercent=0.10f;
                        info1.leftMarginPercent=0.52f;
                        params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        view1.setRotation(-30.0f);
                        view1.bringToFront();
                        view1.requestLayout();
                    }
                    if(selectedCardArray.get(0).equalsIgnoreCase("value3")){
                        my_card4.startAnimation(animate_my_player_card);
                        View view1 = findViewById(R.id.my_card4);
                        PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                        info1.bottomMarginPercent=0.25f;
                        info1.widthPercent = 0.10f;
                        info1.heightPercent=0.10f;
                        info1.leftMarginPercent=0.52f;
                        params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        view1.setRotation(-30.0f);
                        view1.bringToFront();
                        view1.requestLayout();
                    }
                    if(selectedCardArray.get(0).equalsIgnoreCase("value4")){
                        my_card5.startAnimation(animate_my_player_card);
                        View view1 = findViewById(R.id.my_card5);
                        PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                        info1.bottomMarginPercent=0.25f;
                        info1.widthPercent = 0.10f;
                        info1.heightPercent=0.10f;
                        info1.leftMarginPercent=0.52f;
                        params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        view1.setRotation(-30.0f);
                        view1.bringToFront();
                        view1.requestLayout();
                    }
                    if(selectedCardArray.get(0).equalsIgnoreCase("value5")){
                        my_card6.startAnimation(animate_my_player_card);
                        View view1 = findViewById(R.id.my_card6);
                        PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                        PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                        info1.bottomMarginPercent=0.25f;
                        info1.widthPercent = 0.10f;
                        info1.heightPercent=0.10f;
                        info1.leftMarginPercent=0.52f;
                        params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        view1.setRotation(-30.0f);
                        view1.bringToFront();
                        view1.requestLayout();
                    }
                if(selectedCardArray.get(1).equalsIgnoreCase("value")){
                    my_card1.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card1);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.54f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(-10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(1).equalsIgnoreCase("value1")){
                    my_card2.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card2);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.54f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(-10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(1).equalsIgnoreCase("value2")){
                    my_card3.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card3);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.54f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(-10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(1).equalsIgnoreCase("value3")){
                    my_card4.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card4);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.54f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(-10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(1).equalsIgnoreCase("value4")){
                    my_card5.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card5);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.54f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(-10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(1).equalsIgnoreCase("value5")){
                    my_card6.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card6);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.54f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(-10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }

                if(selectedCardArray.get(2).equalsIgnoreCase("value")){
                    my_card1.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card1);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.56f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(2).equalsIgnoreCase("value1")){
                    my_card2.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card2);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.56f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(2).equalsIgnoreCase("value2")){
                    my_card3.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card3);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.56f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(2).equalsIgnoreCase("value3")){
                    my_card4.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card4);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.56f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(2).equalsIgnoreCase("value4")){
                    my_card5.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card5);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.56f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                if(selectedCardArray.get(2).equalsIgnoreCase("value5")){
                    my_card6.startAnimation(animate_my_player_card);
                    View view1 = findViewById(R.id.my_card6);
                    PercentRelativeLayout.LayoutParams params1 = (PercentRelativeLayout.LayoutParams) view1.getLayoutParams();
                    PercentLayoutHelper.PercentLayoutInfo info1 = params1.getPercentLayoutInfo();
                    info1.bottomMarginPercent=0.25f;
                    info1.widthPercent = 0.10f;
                    info1.heightPercent=0.10f;
                    info1.leftMarginPercent=0.56f;
                    params1.addRule(RelativeLayout.ALIGN_TOP,R.id.table);
                    params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    view1.setRotation(10.0f);
                    view1.bringToFront();
                    view1.requestLayout();
                }
                            gobtn.setVisibility(View.GONE);
                    }
                    else {
                        Toast.makeText(SixPatti.this, "Only 3 cards", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        //////////////// Popup for Backbutton ///////////////////

        backbtn=(ImageView) findViewById(R.id.back);
        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.backbutton_popup, null);

                closebtn = (TextView) customView.findViewById(R.id.close);
                backtolobby=(TextView) customView.findViewById(R.id.backtolobby);

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
                        startActivity(new Intent(SixPatti.this, MainActivity.class));
                        finish();
                    }
                });
            }
        });

        //////////////// Popup for InfoButton ///////////////////

        infobtn=(ImageView) findViewById(R.id.info);
        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.six_patti_infogame_popup, null);

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
//        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);
//
//        chatbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);
//
//        myplayerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.other_player_status, null);
//
//                msgbtn = customView.findViewById(R.id.msg);
//
//                // onclick event for message button
//                msgbtn.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);

        dealerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml three_two_one_leaderboard file
                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                        LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//
//        leaderboardsixpattibtn=(ImageView) findViewById(R.id.leaderboardsixpatti);
//        relativeLayout= (RelativeLayout) findViewById(R.id.sixpattirecycler);
//
//        leaderboardsixpattibtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml three_two_one_leaderboard file
//                LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.six_patti_leaderboard, null);
//
//                closebtnsixpattileadboard = (ImageView) customView.findViewById(R.id.closeleadsix);
//
//                //instantiate popup window
//                sixpattileadboardpopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//
//                //display the popup window
//                sixpattileadboardpopupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
//
//                //close the popup window on button click
//                closebtnsixpattileadboard.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        sixpattileadboardpopupWindow.dismiss();
//                    }
//                });
//            }
//        });
    }

    /////////// Slider /////////////
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.handle_right:
                sixpattitble.openDrawer(navigationView);
                break;
        }
    }


    @Override
    public void onBackPressed() {

        LayoutInflater layoutInflater = (LayoutInflater) SixPatti.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.backbutton_popup, null);

        closebtn = (TextView) customView.findViewById(R.id.close);
        backtolobby=(TextView) customView.findViewById(R.id.backtolobby);

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
                startActivity(new Intent(SixPatti.this, MainActivity.class));
                finish();
            }
        });

    }

    public void assignImages(int card, ImageView image){
        switch (card)
        {
            case 106:
                image.setImageResource(R.drawable.club_6);
                break;
            case 111:
                image.setImageResource(R.drawable.club_ace);
                break;


            case 209:
                image.setImageResource(R.drawable.diamond_9);
                break;
            case 214:
                image.setImageResource(R.drawable.diamond_king);
                break;


            case 312:
                image.setImageResource(R.drawable.heart_jack);
                break;


            case 413:
                image.setImageResource(R.drawable.spade_queen);
                break;

        }
    }

}
