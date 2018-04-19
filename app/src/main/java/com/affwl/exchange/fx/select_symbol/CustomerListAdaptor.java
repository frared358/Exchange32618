package com.affwl.exchange.fx.select_symbol;

/**
 * Created by user on 4/15/2018.
 */

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.squareup.picasso.Picasso;
import com.affwl.exchange.fx.select_symbol.listener.OnCustomerListChangedListener;
import  com.affwl.exchange.fx.select_symbol.listener.OnStartDragListener;
import  com.affwl.exchange.fx.select_symbol.utilities.ItemTouchHelperAdapter;
import  com.affwl.exchange.fx.select_symbol.utilities.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;


class CustomerListAdapter extends           //changed
        RecyclerView.Adapter<CustomerListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Customer> mCustomers;
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnCustomerListChangedListener mListChangedListener;

    public CustomerListAdapter(List<Customer> customers, Context context,
                               OnStartDragListener dragLlistener,
                               OnCustomerListChangedListener listChangedListener){
        mCustomers = customers;
        mContext = context;
        mDragStartListener = dragLlistener;
        mListChangedListener = listChangedListener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.row_customer_list, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final Customer selectedCustomer = mCustomers.get(position);

        holder.customerName.setText(selectedCustomer.getName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
//        Picasso.with(mContext)
//                .load(selectedCustomer.getImagePath())
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .into(holder.profileImage);



        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mCustomers, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mCustomers);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        public final TextView customerName, customerEmail;
        public final ImageView handleView;



        public ItemViewHolder(View itemView) {
            super(itemView);
            customerName = (TextView)itemView.findViewById(R.id.text_view_customer_name);
            customerEmail = (TextView)itemView.findViewById(R.id.text_view_customer_email);
            handleView = (ImageView)itemView.findViewById(R.id.handle);

        }

        @Override
        public void onItemSelected() {
            itemView.getId ();       //changed
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}