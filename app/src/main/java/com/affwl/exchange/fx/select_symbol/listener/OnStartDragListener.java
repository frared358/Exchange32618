package com.affwl.exchange.fx.select_symbol.listener;

import android.support.v7.widget.RecyclerView;


public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);


}
