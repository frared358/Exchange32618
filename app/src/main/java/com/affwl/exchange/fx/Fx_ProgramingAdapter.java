package com.affwl.exchange.fx;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;


//Add a java class and inherit Adapter
public class Fx_ProgramingAdapter extends RecyclerView.Adapter<Fx_ProgramingAdapter.ProgramingViewHolder> {  //alt+enter (inside Generic)

    //Create Constructor after implementing method
    private  String[] data;
    View viewdialog;
    Context ctx; /** for recyclerview click handler*/

    private static final String TAG = "MainFragment";

    public Fx_ProgramingAdapter(String[] data, Context ctx){
        this.data=data;
        this.ctx=ctx;

    }
    @Override   //use layoutInflater to store views and preferences
    public ProgramingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.fx_list_item_layout,parent,false);

        return new ProgramingViewHolder(view,ctx,data);  /**ctx and data for click handling*/
    }



    @Override
    public void onBindViewHolder(ProgramingViewHolder holder, int position) {
        String title=data[position];
        String rate=data[position];
        String rate2=data[position];
        holder.textView1.setText(title);

        /** implementation of  BottomSheet */
       /* holder.textView1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
//              Toast.makeText (ctx,"Clicked " +position,Toast.LENGTH_SHORT).show ();

                LayoutInflater inflater = LayoutInflater.from (ctx);
                View view =  inflater.inflate(R.layout.fragment_bottom_sheet_dialog, null);
                BottomSheetDialog dialog = new BottomSheetDialog(ctx);
                dialog.setContentView(view);
                dialog.show();
            }
        });

        *//** implementation of  BottomSheet *//*
        holder.textView2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
//              Toast.makeText (ctx,"Clicked " +position,Toast.LENGTH_SHORT).show ();

                LayoutInflater inflater = LayoutInflater.from (ctx);
                View view =  inflater.inflate(R.layout.fragment_bottom_sheet_dialog, null);
                BottomSheetDialog dialog = new BottomSheetDialog(ctx);
                dialog.setContentView(view);
                dialog.show();
            }
        });
        *//** implementation of  BottomSheet *//*
        holder.textView3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
               // Toast.makeText (ctx,"Clicked " +position,Toast.LENGTH_SHORT).show ();


            }
        });*/

    }

    @Override
    public int getItemCount() {
          //tells total numbers of count
            return data.length;
    }





    /**create nested class and inherit ViewHolder */                       /** set click on recyclerView item */
    public class ProgramingViewHolder extends RecyclerView.ViewHolder  {   //ALT+Enter
        TextView textView1,textView2,textView3;
        CardView demo_card;
        //ImageView imageView;
        String data[];
        Context ctx;

        public ProgramingViewHolder(View itemView, final Context ctx, String data[]) {

            super(itemView);
            this.data=data;
            this.ctx=ctx;

           // imageView=itemView.findViewById(R.id.iv1);
            // imageView=itemView.findViewById(R.id.iv1);
            /** recycler view item click*/
//            itemView.setOnClickListener (this);

            demo_card=itemView.findViewById (R.id.demo_card);

            textView1=itemView.findViewById(R.id.tv1);
            textView2=itemView.findViewById(R.id.tv2);
            textView3=itemView.findViewById(R.id.tv3);

            demo_card.setOnClickListener (new View.OnClickListener () {
                public LinearLayout llCurrency,llChart,llNewOrder,llProperties,llMarketStatistics,llAVW;

                @Override
                public void onClick(final View v) {
                    LayoutInflater inflater = LayoutInflater.from (ctx);
                    viewdialog =  inflater.inflate(R.layout.fragment_bottom_sheet_dialog, null);

                    llCurrency=viewdialog.findViewById (R.id.llCurrency);
                    llChart=viewdialog.findViewById (R.id.llChart);
                    llNewOrder=viewdialog.findViewById (R.id.llNewOrder);
                    llProperties=viewdialog.findViewById (R.id.llProperties);
                    llMarketStatistics=viewdialog.findViewById (R.id.llMarketStatistics);
                    llAVW=viewdialog.findViewById (R.id.llAVW);

                    LinearLayout time_spread_layout= ((FxActivity)viewdialog.getContext ()).findViewById(R.id.time_spread_layout);
                    TextView text_view_mode=viewdialog.findViewById(R.id.text_view_mode);

                    if(time_spread_layout.getVisibility()== View.GONE)
                    {
                        text_view_mode.setText("Advanced view mode");
                    }
                    else
                    {
                        text_view_mode.setText("Simple view mode");
                    }

                    final BottomSheetDialog dialog = new BottomSheetDialog(ctx);

                    llCurrency.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext ().startActivity (new Intent (viewdialog.getContext (),Fx_Currency_Activity.class));
//                            Toast.makeText (viewdialog.getContext (), "Currency", Toast.LENGTH_SHORT).show ();
                        }
                    });

                    llChart.setOnClickListener (new View.OnClickListener () {
                        private Fx_Chart_Fragment currentFragment;
                        FrameLayout layout = (FrameLayout)((FxActivity)viewdialog.getContext ()).findViewById(R.id.xzz);

                        @Override
                        public void onClick(View v) {
                            ((FxActivity)viewdialog.getContext ()).navigationView.getMenu().getItem(1).setChecked(true);
                            currentFragment=new Fx_Chart_Fragment ();
                            android.support.v4.app.FragmentTransaction fragmentTransaction1=((FxActivity)viewdialog.getContext ()).getSupportFragmentManager ().beginTransaction ();
                            fragmentTransaction1.replace (R.id.xzz,currentFragment);
                            fragmentTransaction1.commit();
                            layout.setVisibility(View.VISIBLE);
                            ((FxActivity)viewdialog.getContext ()).setTitle("abc");
                           ((FxActivity)viewdialog.getContext ()).invalidateOptionsMenu();
                            //MenuInflater inflater = getActivity().getMenuInflater();
                            dialog.dismiss();
                        }
                    });

                    llNewOrder.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext ().startActivity (new Intent (viewdialog.getContext (),CustomSpinner.class));

                            Toast.makeText (viewdialog.getContext (), "Order", Toast.LENGTH_SHORT).show ();
                        }
                    });

                    llProperties.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext ().startActivity (new Intent (viewdialog.getContext (),Fx_Properties_Activity.class));

                            Toast.makeText (viewdialog.getContext (), "Properties", Toast.LENGTH_SHORT).show ();
                        }
                    });

                    llMarketStatistics.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext ().startActivity (new Intent (viewdialog.getContext (),Fx_MarketStatistics_Activity.class));

                            Toast.makeText (viewdialog.getContext (), "Market Statistics", Toast.LENGTH_SHORT).show ();
                        }
                    });


                    llAVW.setOnClickListener (new View.OnClickListener () {
                     LinearLayout time_spread_layout= ((FxActivity)viewdialog.getContext ()).findViewById(R.id.time_spread_layout);
                     LinearLayout low_layout=((FxActivity)viewdialog.getContext ()).findViewById(R.id.low_layout);
                     LinearLayout high_layout=((FxActivity)viewdialog.getContext ()).findViewById(R.id.high_layout);

                        @Override
                        public void onClick(View v) {

                            if(time_spread_layout.getVisibility()== View.GONE) {

                               time_spread_layout.setVisibility(View.VISIBLE);
                               low_layout.setVisibility(View.VISIBLE);
                               high_layout.setVisibility(View.VISIBLE);
                           }
                           else
                           {
                               time_spread_layout.setVisibility(View.GONE);
                               low_layout.setVisibility(View.GONE);
                               high_layout.setVisibility(View.GONE);
                           }

                            dialog.dismiss();
//                            viewdialog.getContext ().startActivity (new Intent (viewdialog.getContext (),FxActivity.class));

//                            Toast.makeText (viewdialog.getContext (), "Advanced view mode", Toast.LENGTH_SHORT).show ();
                        }
                    });

                    dialog.setContentView(viewdialog);
                    dialog.show();
                }
            });

        }


//        @Override
//        public boolean OnCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//            inflater.inflate(R.menu.forecastfragment, menu);
//            return true;
//        }


    }





}
