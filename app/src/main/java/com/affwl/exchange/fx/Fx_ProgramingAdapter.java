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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
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

    /**
     * for recyclerview click handler
     */

    private static final String TAG = "MainFragment";
    View viewdialog;
    Context ctx;
    BottomClickSession bcs;
    //Create Constructor after implementing method
    private String[] data;

    public Fx_ProgramingAdapter(String[] data, Context ctx) {
        this.data = data;
        this.ctx = ctx;

    }

    @Override   //use layoutInflater to store views and preferences
    public ProgramingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fx_list_item_layout, parent, false);

        return new ProgramingViewHolder(view, ctx, data);  /**ctx and data for click handling*/
    }


    @Override
    public void onBindViewHolder(ProgramingViewHolder holder, int position) {
        String title = data[position];
        String rate = data[position];
        String rate2 = data[position];
        holder.textView1.setText(title);

        /**Subscript and superscript start*/
        Log.i("Check Now",holder.tvCurrencyLow.getText ().toString ());
        SpannableStringBuilder cs = new SpannableStringBuilder( "1.40785");      //"X3 + X2"
        cs.setSpan(new RelativeSizeSpan (0.65f), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new SuperscriptSpan (), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.45f), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tvAdvCurrencyLow.setText(cs);

        SpannableStringBuilder cs1 = new SpannableStringBuilder("1.40785");      //"X3 + X2"
        cs1.setSpan(new RelativeSizeSpan (0.65f), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs1.setSpan(new SuperscriptSpan (), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs1.setSpan(new RelativeSizeSpan(0.45f), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tvAdvCurrencyHigh.setText(cs1);
        /**Subscript and superscript end*/


    }

    @Override
    public int getItemCount() {
        //tells total numbers of count
        return data.length;
    }


    /**create nested class and inherit ViewHolder */
    /**
     * set click on recyclerView item
     */
    public class ProgramingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {   //ALT+Enter
        TextView textView1, tvCurrencyLow,tvCurrencyHigh,tvAdvCurrencyLow,tvAdvCurrencyHigh;
        CardView demo_card;
        //ImageView imageView;
        String data[];
        Context ctx;

        public ProgramingViewHolder(View itemView, final Context ctx, String data[]) {

            super(itemView);
            this.data = data;
            this.ctx = ctx;



            demo_card = itemView.findViewById(R.id.demo_card);

            textView1 = itemView.findViewById(R.id.tv1);
            tvCurrencyLow=itemView.findViewById(R.id.tvCurrencyLow);
            tvAdvCurrencyHigh=itemView.findViewById (R.id.tvAdvCurrencyHigh);
            tvAdvCurrencyLow=itemView.findViewById (R.id.tvAdvCurrencyLow);
            tvCurrencyHigh=itemView.findViewById(R.id.tvCurrencyHigh);

            demo_card.setOnClickListener(new View.OnClickListener() {
                public LinearLayout llCurrency, llChart, llNewOrder, llProperties, llMarketStatistics, llAVW;

                @Override
                public void onClick(final View v) {
                    LayoutInflater inflater = LayoutInflater.from(ctx);
                    viewdialog = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, null);

                    llCurrency = viewdialog.findViewById(R.id.llCurrency);
                    llChart = viewdialog.findViewById(R.id.llChart);
                    llNewOrder = viewdialog.findViewById(R.id.llNewOrder);
                    llProperties = viewdialog.findViewById(R.id.llProperties);
                    llMarketStatistics = viewdialog.findViewById(R.id.llMarketStatistics);
                    llAVW = viewdialog.findViewById(R.id.llAVW);

                    LinearLayout time_spread_layout = ((FxActivity) viewdialog.getContext()).findViewById(R.id.time_spread_layout);
                    TextView text_view_mode = viewdialog.findViewById(R.id.text_view_mode);

                    if (time_spread_layout.getVisibility() == View.GONE) {
                        text_view_mode.setText("Advanced view mode");
                    } else {
                        text_view_mode.setText("Simple view mode");
                    }

                    final BottomSheetDialog dialog = new BottomSheetDialog(ctx);

                    llCurrency.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext().startActivity(new Intent(viewdialog.getContext(), Fx_Currency_Activity.class));
//                            Toast.makeText (viewdialog.getContext (), "Currency", Toast.LENGTH_SHORT).show ();
                        }
                    });

                    llChart.setOnClickListener(new View.OnClickListener() {
                        FrameLayout layout = (FrameLayout) ((FxActivity) viewdialog.getContext()).findViewById(R.id.xzz);
                        private Fx_Chart_Fragment currentFragment;

                        @Override
                        public void onClick(View v) {
                            bcs=new BottomClickSession(v.getContext());
                            bcs.setValue("1");
                            ((FxActivity) viewdialog.getContext()).navigationView.getMenu().getItem(1).setChecked(true);
                            currentFragment = new Fx_Chart_Fragment();
                            android.support.v4.app.FragmentTransaction fragmentTransaction1 = ((FxActivity) viewdialog.getContext()).getSupportFragmentManager().beginTransaction();
                            fragmentTransaction1.replace(R.id.xzz, currentFragment);
                            fragmentTransaction1.commit();
                            layout.setVisibility(View.VISIBLE);
                            ((FxActivity) viewdialog.getContext()).setTitle("");

                            ((FxActivity) viewdialog.getContext()).invalidateOptionsMenu();

                            dialog.dismiss();
                        }


                    });

                    llNewOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext().startActivity(new Intent(viewdialog.getContext(), CustomSpinner.class));

                            Toast.makeText(viewdialog.getContext(), "Order", Toast.LENGTH_SHORT).show();
                        }
                    });

                    llProperties.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext().startActivity(new Intent(viewdialog.getContext(), Fx_Properties_Activity.class));

                            Toast.makeText(viewdialog.getContext(), "Properties", Toast.LENGTH_SHORT).show();
                        }
                    });

                    llMarketStatistics.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewdialog.getContext().startActivity(new Intent(viewdialog.getContext(), Fx_MarketStatistics_Activity.class));

                            Toast.makeText(viewdialog.getContext(), "Market Statistics", Toast.LENGTH_SHORT).show();
                        }
                    });



                    llAVW.setOnClickListener(new View.OnClickListener() {
                        LinearLayout time_spread_layout = ((FxActivity) viewdialog.getContext()).findViewById(R.id.time_spread_layout);
                        LinearLayout low_layout = ((FxActivity) viewdialog.getContext()).findViewById(R.id.low_layout);
                        LinearLayout high_layout = ((FxActivity) viewdialog.getContext()).findViewById(R.id.high_layout);
                        TextView tvCurrencyLow=((FxActivity) viewdialog.getContext()).findViewById(R.id.tvCurrencyLow);
                        TextView tvCurrencyHigh=((FxActivity) viewdialog.getContext()).findViewById(R.id.tvCurrencyHigh);

                        @Override
                        public void onClick(View v) {

                            if (time_spread_layout.getVisibility() == View.GONE ) {


                                //tvCurrencyHigh.setVisibility (View.GONE);
                                //tvCurrencyLow.setVisibility (View.GONE);

                                time_spread_layout.setVisibility (View.VISIBLE);
                                low_layout.setVisibility (View.VISIBLE);
                                high_layout.setVisibility (View.VISIBLE);
                                tvCurrencyLow.setVisibility (View.GONE);
                                tvCurrencyHigh.setVisibility (View.GONE);

                             /*   if (tvCurrencyLow.getVisibility ()==View.GONE || tvCurrencyHigh.getVisibility ()==View.GONE ){
                                    tvCurrencyHigh.setVisibility (View.GONE);
                                    tvCurrencyLow.setVisibility (View.GONE);
                                    tvAdvCurrencyLow.setVisibility (View.VISIBLE);
                                    tvAdvCurrencyHigh.setVisibility (View.VISIBLE);

                                }

                            } else if (tvAdvCurrencyLow.getVisibility ()==View.GONE || tvAdvCurrencyHigh.getVisibility ()==View.GONE ){
                                time_spread_layout.setVisibility(View.GONE);
                                low_layout.setVisibility(View.GONE);
                                high_layout.setVisibility(View.GONE);
                                tvCurrencyLow.setVisibility (View.VISIBLE);
                                tvCurrencyHigh.setVisibility (View.VISIBLE);
                                //Toast.makeText (viewdialog.getContext (), "Simple view mode", Toast.LENGTH_SHORT).show ();
                            }*/


                            }
                            else {
                                time_spread_layout.setVisibility (View.GONE);
                                low_layout.setVisibility (View.GONE);
                                high_layout.setVisibility (View.GONE);
                                tvCurrencyLow.setVisibility (View.VISIBLE);
                                tvCurrencyHigh.setVisibility (View.VISIBLE);

                            }
                            dialog.dismiss();
//                            viewdialog.getContext ().startActivity (new Intent (viewdialog.getContext (),FxActivity.class));

                           //Toast.makeText (viewdialog.getContext (), "Advanced view mode", Toast.LENGTH_SHORT).show ();
                        }
                    });

                    dialog.setContentView(viewdialog);
                    dialog.show();
                }
            });

        }


        @Override
        public void onClick(View v) {
            Toast.makeText (viewdialog.getContext (), "clicked", Toast.LENGTH_SHORT).show ();
        }
    }


}