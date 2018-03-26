package com.affwl.exchange.fx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.affwl.exchange.Tab1;
import com.affwl.exchange.Tab2;
import com.affwl.exchange.Tab3;

public class Fx_History_Fragment extends Fragment {

    //TabItem tabItem ;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    MenuItem fav;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fx_history,container,false);


    //    tabItem = (TabItem)findViewById(R.id.tabItem);

        //Typeface custom_font = createFromAsset(getAssets(), "assets/fonts/font/Oswald-Extra-LightItalic.ttf");

        //tabItem.setTypeface(custom_font);


        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) v.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO add your menu :
        inflater.inflate(R.menu.menu_main, menu);
        //TODO call super
        super.onCreateOptionsMenu(menu, inflater);
    }

//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        fav = menu.add("add");
//        fav.setIcon(R.drawable.a1);
//        inflater.inflate(R.menu.menu_main,menu);
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //  textView.setText(getString("", getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    Tab1 tab1= new Tab1();

                    return  tab1;
                case 1:
                    Tab2 tab2 = new Tab2();
                    return  tab2;
                case 2:
                    Tab3 tab3= new Tab3();
                    return  tab3;
            }

            return null;


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}


//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
///**  make sure select android.support.v4.app.Fragment  */
//public class Fx_History_Fragment extends Fragment {
//    @Nullable
//    @Override   /** Right click - Generate - Override Method - slect onCreateView */
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate (R.layout.fragment_fx_history,null);
//    }
//    /** Right click - Generate - Override Method - slect onViewCreate */
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated (view, savedInstanceState);
//
//        /** since it is a fragment we use view.findViewById */
////        view.findViewById (R.id.btn).setOnClickListener (new View.OnClickListener () {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText (getActivity (),"button clicked",Toast.LENGTH_SHORT).show ();
////            }
////        });
//    }
//
//
//}


