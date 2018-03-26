package com.affwl.exchange.fx;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.affwl.exchange.R;


//Add a java class and inherit Adapter
public class Fx_ProgramingAdapter extends RecyclerView.Adapter<Fx_ProgramingAdapter.ProgramingViewHolder> {  //alt+enter (inside Generic)

    //Create Constructor after implementing method
    private  String[] data;
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
        holder.textView1.setOnClickListener (new View.OnClickListener () {
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

        /** implementation of  BottomSheet */
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
        /** implementation of  BottomSheet */
        holder.textView3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
               // Toast.makeText (ctx,"Clicked " +position,Toast.LENGTH_SHORT).show ();

                LayoutInflater inflater = LayoutInflater.from (ctx);
                View view =  inflater.inflate(R.layout.fragment_bottom_sheet_dialog, null);
                BottomSheetDialog dialog = new BottomSheetDialog(ctx);
                dialog.setContentView(view);
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
          //tells total numbers of count
            return data.length;
    }






    /**create nested class and inherit ViewHolder */                       /** set click on recyclerView item */
    public class ProgramingViewHolder extends RecyclerView.ViewHolder  {   //ALT+Enter
        TextView textView1,textView2,textView3;
        //ImageView imageView;
        String data[];
        Context ctx;

        public ProgramingViewHolder(View itemView, Context ctx, String data[]) {

            super(itemView);
            this.data=data;
            this.ctx=ctx;

           // imageView=itemView.findViewById(R.id.iv1);
            /** recycler view item click*/
//            itemView.setOnClickListener (this);

            textView1=itemView.findViewById(R.id.tv1);
            textView2=itemView.findViewById(R.id.tv2);
            textView3=itemView.findViewById(R.id.tv3);

        }

//        @Override
//        public void onClick(View v) {
//            /**  Set on recycler view Item Click */
//
//            int position=getAdapterPosition ();
//            Toast.makeText (this.ctx,"Clicked " +position,Toast.LENGTH_LONG).show ();
//            Log.i(TAG,"Item clicked " +position);
//            Getfragment f = new Getfragment();
//        }



    }

//    class Getfragment extends AppCompatActivity{
//
//        public  Getfragment(){
//            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
//            bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
//        }
//    }

}
