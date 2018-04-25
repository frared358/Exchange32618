package com.affwl.exchange.fx;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.affwl.exchange.R;
import com.affwl.exchange.fx.select_symbol.SelectSymbolActivity;

import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.affwl.exchange.fx.Fx_Chart_Fragment.rlchartb;

/** check update.........*/
public class FxActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "FxActivity";
    public NavigationView navigationView;
    public BottomNavigationView navigation;
    LinearLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;
    Fragment fragment = null;
    ImageView view;
    LinearLayout acc;
    Dialog myDialog,myDialog1,myDialog2;
    BottomClickSession bcs;
    private Fragment currentFragment;

    ImageView locButton,locButton1,locButton2,locButton3,locButton4,tvobject;
    private PopupWindow mDropdownnew = null;
    private PopupWindow mDropdown = null;
    private PopupWindow Dropdown = null;
    LayoutInflater mInflater,mInflater1,Inflater;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener () {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
            int id=item.getItemId ();
            //int position=item.getOrder ();
            switch (id){
                case R.id.nav_quotes1:
                    navigationView.setFocusable (true);
                    navigation.setFocusable (true);

                    navigationView.getMenu().getItem(0).setChecked(true);//  layout.setVisibility(View.INVISIBLE);
                    //  layout.setVisibility(View.INVISIBLE);
                    //  Fragment fragment2=new Fragment ();
                    // Toast.makeText (this,"currentFragment "+fragment1,Toast.LENGTH_LONG).show ();
                    Log.i (TAG,"Bottom bar clicked"+ id);
                    currentFragment =new Fx_Fragment_Quotes();

                    FragmentTransaction fragmentTransaction0=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction0.replace(R.id.xzz,currentFragment);
                    fragmentTransaction0.commit();
                    layout.setVisibility(View.INVISIBLE);
                    invalidateOptionsMenu();
                    setTitle("Quotes");

                    return  true;


                case  R.id.nav_charts1:
                    navigationView.getMenu().getItem(1).setChecked(true);
                    //fragment1=new Fx_Fragment_Quotes ();
                    Log.i (TAG,"nav_quotes clicked"+ id);
                    currentFragment=new Fx_Chart_Fragment ();
                    FragmentTransaction fragmentTransaction1=getSupportFragmentManager ().beginTransaction ();
                    fragmentTransaction1.replace (R.id.xzz,currentFragment);
                    fragmentTransaction1.commit();
                    layout.setVisibility(View.VISIBLE);
                 invalidateOptionsMenu();
                    setTitle("");
                    return  true;

            }

            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fxx);
        RelativeLayout  rlcharta = (RelativeLayout) findViewById(R.id.rlcharta);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        myDialog = new Dialog(this);
        myDialog1 = new Dialog(this);
        myDialog2 = new Dialog(this);


        /**Bottom navigation*/
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        /** hide frame layout */

        FrameLayout layout = (FrameLayout) findViewById(R.id.xzz);
        layout.setVisibility(View.GONE);           //View.GONE


        /**     RecyclerView    */
        RecyclerView programingList = (RecyclerView) findViewById(R.id.programingList);
        // RecyclerView programingList1=(RecyclerView) findViewById(R.id.programingList1);
        //how to position items in PecyclerView
        programingList.setLayoutManager(new LinearLayoutManager(this));
        //programingList1.setLayoutManager(new LinearLayoutManager(this));
        //set adapter
        String[] currency = {"INR", "USD", "URO", "AFN", "EUR", "AOA", "XCD", "ARS", "AMD", "SHP", "ARS", "AMD", "SHP", "ARS", "AMD", "SHP", "ARS", "AMD", "SHP"};
        // String[] rates={"1.98787","1.37867","1.98989","11.9878","87.0988","6.98789","55.4376","76.3388","2.37636","77.9988"};
        //String[] rates2={"2.98787","1.37867","1.98989","11.9878","87.0988","6.98789","55.4376","76.3388","2.37636","77.9988"};
        // String[] rates2={"2","1","1","11","87","6","55","88","2","77"};
        programingList.setAdapter(new Fx_ProgramingAdapter(currency, this)); /**context this for rcyclerview click*/
        // programingList1.setAdapter(new ProgramingAdapter(rates));
        //programingList.setAdapter(new ProgramingAdapter(currency));
        programingList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e){
                RecyclerView.Adapter adapter = rv.getAdapter();
                adapter.getItemCount();
                Toast.makeText(FxActivity.this, adapter.getItemCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){

            }
        });

        /**   Toolbar   */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        View view = findViewById(R.id.xzz);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);
        acc = headerview.findViewById(R.id.acc);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FrameLayout layout = (FrameLayout) findViewById(R.id.xzz);
                layout.setVisibility(View.VISIBLE);
                setTitle("Accounts");
                currentFragment = new Fx_Manage_Account();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.xzz, currentFragment);    //content_fx
                ft.commit();
                invalidateOptionsMenu();
                drawer.closeDrawer(GravityCompat.START);
//                Toast.makeText(FxActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });


        navigationView.getMenu().getItem(0).setChecked(true);
        currentFragment = new Fx_Fragment_Quotes();
        FragmentTransaction fragmentTransaction0 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction0.replace(R.id.xzz, currentFragment);
        fragmentTransaction0.commit();
        layout.setVisibility(View.INVISIBLE);
        invalidateOptionsMenu();
        setTitle("Quotes");
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }




    //bootomsheet
    //@OnClick(R.id.demo)
    public void showBottomSheetDialog() {
        //Toast.makeText(FxActivity.this, "click", Toast.LENGTH_SHORT).show();
        View view = getLayoutInflater().inflate(R.layout.fragment_fx_quotes, null);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }

    @OnClick(R.id.btn_bottom_sheet_dialog_fragment)
    public void showBottomSheetDialogFragment() {
        Fx_Fragment_Quotes bottomSheetFragment = new Fx_Fragment_Quotes();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        bcs=new BottomClickSession(this);
        String val=bcs.getValue();
        if (val.equals("1"))
        {
            getMenuInflater().inflate(R.menu.charts,menu);

                    tvobject = (ImageView) menu.findItem(R.id.object).getActionView();
            tvobject.setImageDrawable(getResources().getDrawable(R.drawable.ic_objects_white));
            tvobject.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent i = new Intent(getApplication(), Chart_ObjectsActivity.class); //add CustomSpinner
                    startActivity(i);
//                        mQuickAction.show(v);
                }
            });

            locButton4 = (ImageView) menu.findItem(R.id.itemp1).getActionView();
            locButton4.setImageDrawable(getResources().getDrawable(R.drawable.ic_actionbar_new_order_normal));
            locButton4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent i = new Intent(getApplication(), CustomSpinner.class); //add CustomSpinner
                    startActivity(i);
//                        mQuickAction.show(v);
                }
            });
            locButton3 = (ImageView) menu.findItem(R.id.fadd).getActionView();
            locButton3.setImageDrawable(getResources().getDrawable(R.drawable.ic_chart_indicator));
            locButton3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent faddi = new Intent(getApplication(), Indicators.class);
                    startActivity(faddi);
//                        mQuickAction.show(v);
                }
            });


            locButton2 = (ImageView) menu.findItem(R.id.time).getActionView();
            locButton2.setImageDrawable(getResources().getDrawable(R.drawable.time));
            locButton2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    initiatePopupWindow2();
//                        mQuickAction.show(v);
                }
            });

            locButton1 = (ImageView) menu.findItem(R.id.new_window).getActionView();
            locButton1.setImageDrawable(getResources().getDrawable(R.drawable.copy));
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp1.setMargins(50, 2, -30, 2);
            locButton1.setLayoutParams(lp1);
            locButton1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    initiatePopupWindow1(v);
//                        mQuickAction.show(v);
                }
            });

            locButton = (ImageView) menu.findItem(R.id.dolllar).getActionView();
            locButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_symbols));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 2, 30, 2);
            locButton.setLayoutParams(lp);
            locButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    initiatePopupWindow();
//                        mQuickAction.show(v);
                }
            });
            bcs.setValue("0");



        }
        else if ((currentFragment != null && currentFragment instanceof Fx_Chart_Fragment)||val.equals("1")) {
                // navigation.setSelectedItemId (R.id.nav_charts1);  /** Chart moving constantaly */
                getMenuInflater().inflate(R.menu.charts, menu);

            locButton4 = (ImageView) menu.findItem(R.id.itemp1).getActionView();
            locButton4.setImageDrawable(getResources().getDrawable(R.drawable.ic_actionbar_new_order_normal));
            locButton4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent i = new Intent(getApplication(), CustomSpinner.class); //add CustomSpinner
                    startActivity(i);
//                        mQuickAction.show(v);
                }
            });
            locButton3 = (ImageView) menu.findItem(R.id.fadd).getActionView();
            locButton3.setImageDrawable(getResources().getDrawable(R.drawable.ic_chart_indicator));
            locButton3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent faddi = new Intent(getApplication(), Indicators.class);
                    startActivity(faddi);
//                        mQuickAction.show(v);
                }
            });


                locButton2 = (ImageView) menu.findItem(R.id.time).getActionView();
                locButton2.setImageDrawable(getResources().getDrawable(R.drawable.time));
                locButton2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        initiatePopupWindow2();
//                        mQuickAction.show(v);
                    }
                });

                locButton1 = (ImageView) menu.findItem(R.id.new_window).getActionView();
                //Toast.makeText(FxActivity.this, "click r", Toast.LENGTH_SHORT).show();

                locButton1.setImageDrawable(getResources().getDrawable(R.drawable.copy));
                locButton1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        initiatePopupWindow1(v);
//                        mQuickAction.show(v);
                    }
                });

                locButton = (ImageView) menu.findItem(R.id.dolllar).getActionView();
                locButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_symbols));
                locButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        initiatePopupWindow();
//                        mQuickAction.show(v);
                    }
                });

                //Toast.makeText(FxActivity.this, "click r", Toast.LENGTH_SHORT).show();

            }

            //quotesmenuicon
        else if (currentFragment != null && currentFragment instanceof Fx_Fragment_Quotes) {
//            navigation.setSelectedItemId (R.id.nav_quotes1);
                getMenuInflater().inflate(R.menu.quotes_menu, menu);
            }
        else if (currentFragment != null && currentFragment instanceof Fx_Manage_Fragment) {
                getMenuInflater().inflate(R.menu.manageacc, menu);
            }
        else  if (currentFragment != null && currentFragment instanceof Fx_Manage_Account) {
                getMenuInflater().inflate(R.menu.manageacc, menu);
            }




//
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /** Handle action bar item clicks here. The action bar will
         automatically handle clicks on the Home/Up button, so long
         as you specify a parent activity in AndroidManifest.xml.    */
        int id = item.getItemId();
        if (currentFragment != null && currentFragment instanceof Fx_Chart_Fragment) {
            switch (item.getItemId()) {
                case R.id.idHighLight:
                    FragmentManager fm = getSupportFragmentManager();  /** call method from fragment */
                    //if you added fragment via layout xml
                    Fx_Chart_Fragment fragment = (Fx_Chart_Fragment)fm.findFragmentById(R.id.xzz);
                    fragment.showHighLight();
                    return true;

                case R.id.itemp1:
                    Intent i = new Intent(this, CustomSpinner.class); //add CustomSpinner
                    this.startActivity(i);
                    return true;
                case R.id.fadd:
                    Intent faddi = new Intent(this, Indicators.class);
                    this.startActivity(faddi);
                    return true;

       /*         case R.id.mnNewWindow:
                     Toast.makeText (this,"New window clicked", Toast.LENGTH_LONG).show ();*/
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        bcs=new BottomClickSession(this);
        String val=bcs.getValue();
        if (val.equals("1"))
        {
            switch (item.getItemId()) {
                case R.id.itemp1:
                    Intent i = new Intent(this, CustomSpinner.class); //add CustomSpinner
                    this.startActivity(i);
                    return true;
                case R.id.fadd:
                    Intent faddi = new Intent(this, Indicators.class);
                    this.startActivity(faddi);
                    return true;

       /*         case R.id.mnNewWindow:
                     Toast.makeText (this,"New window clicked", Toast.LENGTH_LONG).show ();*/
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        if (currentFragment != null && currentFragment instanceof Fx_Fragment_Quotes) {
            switch (item.getItemId()) {
                case R.id.edit:
                    Intent i = new Intent(this, SelectSymbolActivity.class);
                    this.startActivity(i);
                    return true;
                case R.id.add:
                    Intent add = new Intent(this, Add_symbol.class);
                    this.startActivity(add);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

        }

        if (currentFragment != null && currentFragment instanceof Fx_Manage_Account) {
            switch (item.getItemId()) {
                case R.id.dip:
                    Intent i = new Intent(this, Certificates.class);
                    this.startActivity(i);
                    getMenuInflater();
                    return true;
                case R.id.addacc:
                    Intent addacci = new Intent(this, New_Account.class);
                    this.startActivity(addacci);
                    return true;
                case R.id.clearspass:
                    ShowPopup1();
                     break;
                case R.id.delacc:
                    ShowPopup2();
                    break;
                default:

            }
        }



            return super.onOptionsItemSelected(item);

    }
    private PopupWindow initiatePopupWindow2() {

        try {
            Inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = Inflater.inflate(R.layout.activity_chart_quickmenu_windowpopup_activity, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itemA = (TextView) layout.findViewById(R.id.ItemA);

            final TextView itemB = (TextView) layout.findViewById(R.id.ItemB);

            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            Dropdown = new PopupWindow(layout,FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,true);
            Drawable background = getResources().getDrawable(android.R.drawable.alert_light_frame);
            Dropdown.setBackgroundDrawable(background);
            Dropdown.showAsDropDown(locButton2 , -150, -225);
//            mDropdown.showAtLocation(view, Gravity.LEFT, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Dropdown;
    }

    private PopupWindow initiatePopupWindow() {

        try {
            mInflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.activity_chart_dollar_activity, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itemA = (TextView) layout.findViewById(R.id.ItemA);

            final TextView itemB = (TextView) layout.findViewById(R.id.ItemB);

            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            mDropdown = new PopupWindow(layout,FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,true);
            Drawable background = getResources().getDrawable(android.R.drawable.alert_light_frame);
            mDropdown.setBackgroundDrawable(background);
            mDropdown.showAsDropDown(locButton , -220, -225);
//            mDropdown.showAtLocation(view, Gravity.LEFT, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdown;
    }

    private PopupWindow initiatePopupWindow1(final View vchart) {

                    try {

                        mInflater1 = (LayoutInflater) getApplicationContext()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View layout = mInflater1.inflate(R.layout.activity_chart_new_popupwindow_activity, null);

                        //If you want to add any listeners to your textviews, these are two //textviews.
                        final TextView itema = (TextView) layout.findViewById(R.id.Itema);
                        final TextView itemb = (TextView) layout.findViewById(R.id.Itemb);
                        LinearLayout llnew_window=(LinearLayout)layout.findViewById(R.id.llnew_window);
                        final LinearLayout llchart=layout.findViewById(R.id.llchart);
                        final CheckBox cbeuro =(CheckBox) layout.findViewById(R.id.cbeuro);
                        final CheckBox   cbeuro1=(CheckBox) layout.findViewById(R.id.cbeuro1);
                       TextView  Itemd =(TextView)layout.findViewById(R.id.Itemd);


                        layout.measure(View.MeasureSpec.UNSPECIFIED,
                                View.MeasureSpec.UNSPECIFIED);
                        mDropdownnew = new PopupWindow(layout,FrameLayout.LayoutParams.WRAP_CONTENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT,true);
                        Drawable background = getResources().getDrawable(android.R.drawable.alert_light_frame);
                        mDropdownnew.setBackgroundDrawable(background);
                        mDropdownnew.showAsDropDown(locButton1 , -500, -100);


Itemd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v){
        if(cbeuro1.isChecked())
        {
            rlchartb.setVisibility(View.GONE);

        }
        if(cbeuro.isChecked())
        {
            rlchartb.setVisibility(View.GONE);
        }

    }
});


                        llnew_window.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
//
                       /*         if(llchart.getVisibility()==View.VISIBLE) {
                                    llchart.setVisibility(View.GONE);
                                }else {*/
                                    llchart.setVisibility(View.VISIBLE);
//                                }

                                //Toast.makeText(v.getContext(),"chart",Toast.LENGTH_LONG).show();
                                //Toast.makeText(v.getContext(),""+view,Toast.LENGTH_LONG).show();
                                Fx_Chart_Fragment.DisplayChart();
                                bcs.setValue("1");

                  //  mDropdownnew.dismiss();
                                Toast.makeText(FxActivity.this, bcs.getValue(), Toast.LENGTH_SHORT).show();
                            }
            });
//            mDropdown.showAtLocation(view, Gravity.LEFT, 100, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdownnew;

    }

    /*** Navigation drawer*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
        //Fragment currentFragment=null;

        if (id == R.id.nav_quotes) {
            // Handle the quotes action
//           Intent resultIntent = new Intent(this, FxActivity.class);
//            startActivity (resultIntent);
            navigationView.getMenu().findItem(R.id.nav_quotes).setChecked(true); /** set drawer navigation checked*/
            navigation.getMenu().findItem(R.id.nav_quotes1).setChecked(true);  /**select bottom nav checked*/
            layout.setVisibility(View.VISIBLE);
            setTitle("Quotes");
            currentFragment=new Fx_Fragment_Quotes ();
            FragmentManager fragmentManager=getSupportFragmentManager ();

            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
          invalidateOptionsMenu();

            /** hide frame layout */
            //FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
           // layout.setVisibility(View.INVISIBLE);
            //View.GONE
            //currentFragment=new Fx_Fragment_Quotes ();

        } else if (id == R.id.nav_charts) {
            setTitle("");
            navigationView.getMenu().findItem(R.id.nav_charts).setChecked(true); /** set drawer navigation checked*/
            navigation.getMenu().findItem(R.id.nav_charts1).setChecked(true);
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Chart_Fragment ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        }
      else if (id == R.id.nav_settings) {
            setTitle("Settings");
            navigationView.getMenu().findItem(R.id.nav_settings).setChecked(true); /** set drawer navigation checked*/
            navigation.getMenu().findItem(R.id.nav_quotes1).setChecked(false);  /**select bottom nav checked*/
            navigation.getMenu().findItem(R.id.nav_charts1).setChecked(false);  /**select bottom nav checked*/

            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Fragment_Settings();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
         invalidateOptionsMenu();
        }

        if (currentFragment != null){

            /**show frame layout */
            //FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
            layout.setVisibility(View.VISIBLE);           //View.GONE

            /** Make sure we are using android.support.v4.app and not android.app */
           /** Toast.makeText (this,"currentFragment "+currentFragment,Toast.LENGTH_LONG).show ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();   */
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

public void ShowPopup1() {
    TextView txtclose;
    Button btnFollow;
    Button btnFollow1;

    myDialog1.setContentView(R.layout.setting_clear_saved_password_popup);



    txtclose = myDialog1.findViewById(R.id.btnfollow2);

    txtclose.setText("Cancel");

    btnFollow = myDialog1.findViewById(R.id.btnfollow2);
    txtclose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myDialog1.dismiss();
        }
    });


    myDialog1.show();
}
    public void ShowPopup2() {
        TextView txtclose;
        Button btnFollow;
        Button btnFollow1;

        myDialog.setContentView(R.layout.delete_account_popup);



        txtclose = myDialog.findViewById(R.id.btnfollow3);

        txtclose.setText("Cancel");

        btnFollow = myDialog.findViewById(R.id.btnfollow3);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        myDialog.show();
    }


}
