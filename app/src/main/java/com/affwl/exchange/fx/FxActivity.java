package com.affwl.exchange.fx;

import android.app.Dialog;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.Add_symbol;
import com.affwl.exchange.Certificates;
import com.affwl.exchange.CustomSpinner;
import com.affwl.exchange.Indicators;
import com.affwl.exchange.Mailbox1;
import com.affwl.exchange.New_Account;
import com.affwl.exchange.Problem_Description;
import com.affwl.exchange.R;
import com.affwl.exchange.Selected_symbols;

import butterknife.OnClick;


public class FxActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public NavigationView navigationView;
    public BottomNavigationView navigation;
    LinearLayout layoutBottomSheet;

    BottomSheetBehavior sheetBehavior;

    Fragment fragment = null;
    private static String TAG = "FxActivity";
    ImageView view;
    LinearLayout acc;

    private Fragment currentFragment;
    Dialog myDialog,myDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fxx);

        myDialog = new Dialog(this);
        myDialog1 = new Dialog(this);
//spinner on mailbox icon
//        Spinner spinner=findViewById(R.id.spinner2);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinners, R.layout.activity_mailbox1);
//        adapter.setDropDownViewResource(R.layout.activity_mailbox1);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        //ButterKnife.bind(this);

//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_fx_chart,container,false);


//         mchart = (LineChart) findViewById(R.id.linechart);
////         mchart.setOnChartGestureListener(FxActivity.this);
////         mchart.setOnChartValueSelectedListener(FxActivity.this);
//         mchart.setDragEnabled(true);
//         mchart.setScaleEnabled(false);
//        ArrayList<Entry> yValues =new ArrayList<>();
//        yValues.add(new Entry(0,60f));
//        yValues.add(new Entry(1,50f));
//        yValues.add(new Entry(2,30f));
//        yValues.add(new Entry(3,70f));
//        yValues.add(new Entry(4,30f));
//        yValues.add(new Entry(5,10f));
//
//
//
//        LineDataSet set1= new LineDataSet(yValues, "Data set1");
//        set1.setFillAlpha(110);
//        set1.setColor(Color.RED);
//        set1.setLineWidth(3f);
//        set1.setValueTextColor(Color.GREEN);
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(set1);
//        LineData data= new LineData(dataSets);
//        mchart.setData(data);


        //  demo = findViewById(R.id.demo);


       /* ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        getActionBar().setTitle("Deep");*/

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
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                RecyclerView.Adapter adapter = rv.getAdapter();
                adapter.getItemCount();
                Toast.makeText(FxActivity.this, adapter.getItemCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        /**   Toolbar   */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View view = findViewById(R.id.xzz);
        //sheetBehavior = BottomSheetBehavior.from(view);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
//        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED: {
//                        //btnBottomSheet.setText("Close Sheet");
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_COLLAPSED: {
//
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });


        /**
         * manually opening / closing bottom sheet on button click
         */
//    @OnClick(R.id.btn_bottom_sheet)
//    public void toggleBottomSheet() {
//        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            btnBottomSheet.setText("Close sheet");
//        } else {
//            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            btnBottomSheet.setText("Expand sheet");
//        }
//    }


        /**FloatingActionButton*/
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
            public void onClick(View v) {
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


    }

    //bootomsheet
    //@OnClick(R.id.demo)
    public void showBottomSheetDialog() {
        Toast.makeText(FxActivity.this, "click", Toast.LENGTH_SHORT).show();
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

        if (currentFragment != null && currentFragment instanceof Fx_Chart_Fragment) {
            //navigation.setSelectedItemId (R.id.nav_charts1);
            getMenuInflater().inflate(R.menu.charts, menu);

        }
        if (currentFragment != null && currentFragment instanceof Fx_History_Fragment) {
            navigation.setSelectedItemId (R.id.nav_history1);
            getMenuInflater().inflate(R.menu.menu_main, menu);

        }

        if (currentFragment != null && currentFragment instanceof Fx_Trade_Fragment) {
            navigation.setSelectedItemId (R.id.nav_trade1);
            getMenuInflater().inflate(R.menu.trade_main, menu);


        }
        if (currentFragment != null && currentFragment instanceof Fx_Fragment_Mailbox) {
            getMenuInflater().inflate(R.menu.mailbox_menu, menu);
        }
        if (currentFragment != null && currentFragment instanceof Fx_news_Fragment) {
            navigation.setSelectedItemId (R.id.nav_news1);
            getMenuInflater().inflate(R.menu.news_menu, menu);
        }
        if (currentFragment != null && currentFragment instanceof Fx_Journal_Fragment) {
            getMenuInflater().inflate(R.menu.journal_menu, menu);
        }
        if (currentFragment != null && currentFragment instanceof Fx_Messages_Fragment) {
            getMenuInflater().inflate(R.menu.msg, menu);
        }
        //quotesmenuicon
        if (currentFragment != null && currentFragment instanceof Fx_Fragment_Quotes) {
            getMenuInflater().inflate(R.menu.quotes_menu, menu);
        }
        if (currentFragment != null && currentFragment instanceof Fx_Manage_Fragment) {
            getMenuInflater().inflate(R.menu.manageacc, menu);
        }
        if (currentFragment != null && currentFragment instanceof Fx_Manage_Account) {
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
                case R.id.itemp1:
                    Intent i = new Intent(this, CustomSpinner.class); //add CustomSpinner
                    this.startActivity(i);
                    return true;
                case R.id.fadd:
                    Intent faddi = new Intent(this, Indicators.class);
                    this.startActivity(faddi);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        if (currentFragment != null && currentFragment instanceof Fx_Trade_Fragment) {
            switch (item.getItemId()) {
                case R.id.itemp:
                    Intent i = new Intent(this, CustomSpinner.class);
                    this.startActivity(i);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

        }
        if (currentFragment != null && currentFragment instanceof Fx_Journal_Fragment) {
            switch (item.getItemId()) {
                case R.id.send:
                    Intent i = new Intent(this, Problem_Description.class);
                    this.startActivity(i);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

        }


        if (currentFragment != null && currentFragment instanceof Fx_Fragment_Quotes) {
            switch (item.getItemId()) {
                case R.id.edit:
                    Intent i = new Intent(this, Selected_symbols.class);
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


            if (currentFragment != null && currentFragment instanceof Fx_Fragment_Mailbox) {
                switch (item.getItemId()) {
                    case R.id.m1:
                        Intent i = new Intent(this, Mailbox1.class);
                        this.startActivity(i);
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }

            }
            return super.onOptionsItemSelected(item);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
        //Fragment currentFragment=null;

        if (id == R.id.nav_quotes) {
            // Handle the quotes action
            Intent resultIntent = new Intent(this, FxActivity.class);
            startActivity (resultIntent);
            layout.setVisibility(View.VISIBLE);
            setTitle("Quotes");
//            currentFragment=new Fx_Fragment_Quotes ();
//            FragmentManager fragmentManager=getSupportFragmentManager ();
//            FragmentTransaction ft=fragmentManager.beginTransaction ();
//            ft.replace (R.id.xzz ,currentFragment);    //content_fx
//            ft.commit ();
//            invalidateOptionsMenu();

            /** hide frame layout */
            //FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
           // layout.setVisibility(View.INVISIBLE);
            //View.GONE
            //currentFragment=new Fx_Fragment_Quotes ();

        } else if (id == R.id.nav_charts) {
            setTitle("");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Chart_Fragment ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();

            invalidateOptionsMenu();
        } else if (id == R.id.nav_trade) {
            setTitle("Trade");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Trade_Fragment ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_history) {
            setTitle("History");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_History_Fragment();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_mailbox) {
            setTitle("Mailbox");

            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Fragment_Mailbox ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);
            //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_news) {
            setTitle("News");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_news_Fragment ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_message) {
            setTitle("Chat");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Messages_Fragment ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_settings) {
            setTitle("Settings");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Fragment_Settings();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_journal) {
            setTitle("Journal");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_Journal_Fragment ();
            FragmentManager fragmentManager=getSupportFragmentManager ();
            FragmentTransaction ft=fragmentManager.beginTransaction ();
            ft.replace (R.id.xzz ,currentFragment);    //content_fx
            ft.commit ();
            invalidateOptionsMenu();
        } else if (id == R.id.nav_about) {
            setTitle("About");
            layout.setVisibility(View.VISIBLE);
            currentFragment=new Fx_About_Fragment();
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
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener () {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FrameLayout layout = (FrameLayout)findViewById(R.id.xzz);
            int id=item.getItemId ();
            //int position=item.getOrder ();
            switch (id){
                case R.id.nav_quotes1:
                    navigationView.getMenu().getItem(0).setChecked(true);
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

                //  FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction ();
                //fragmentTransaction.replace (R.id.xzz,fragment1,"");
                // fragmentTransaction.replace (R.id.xzz,fragment2);
                // fragmentTransaction.commit ();

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

                case R.id.nav_trade1:
                    navigationView.getMenu().getItem(2).setChecked(true);
                    currentFragment=new Fx_Trade_Fragment ();
                    FragmentTransaction fragmentTransaction2=getSupportFragmentManager ().beginTransaction ();
                    fragmentTransaction2.replace (R.id.xzz,currentFragment);
                    fragmentTransaction2.commit();
                    Log.i (TAG,"nav_quotes clicked"+ id);
                    layout.setVisibility(View.VISIBLE);
                    setTitle("Trade");
                    invalidateOptionsMenu();
                    return  true;
                case  R.id.nav_history1:
                    navigationView.getMenu().getItem(3).setChecked(true);
                    currentFragment=new Fx_History_Fragment ();
                    FragmentTransaction fragmentTransaction3=getSupportFragmentManager ().beginTransaction ();
                    fragmentTransaction3.replace (R.id.xzz,currentFragment);
                    fragmentTransaction3.commit();
                    setTitle("History");
                    invalidateOptionsMenu();
                    Log.i (TAG,"nav_quotes clicked"+ id);
                    layout.setVisibility(View.VISIBLE);
                    return  true;
                case R.id.nav_news1:
                    navigationView.getMenu().getItem(4).setChecked(true);
                    currentFragment=new Fx_news_Fragment ();
                    FragmentTransaction fragmentTransaction4=getSupportFragmentManager ().beginTransaction ();
                    fragmentTransaction4.replace (R.id.xzz,currentFragment);
                    fragmentTransaction4.commit();
                    setTitle("News");
                    invalidateOptionsMenu();
                    layout.setVisibility(View.VISIBLE);
                    return  true;
            }
            //FragmentTransaction fragmentTransaction=getSupportFragmentManager ().beginTransaction ();
            //fragmentTransaction.replace (R.id.xzz,fragment1,"");
            //fragmentTransaction.replace (R.id.xzz,currentFragment);
            //fragmentTransaction.commit ();
            return true;
        }

    };
    //spinner on mailbox icon
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//String text=parent.getItemAtPosition(position).toString();
//Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
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
