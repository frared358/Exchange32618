package com.affwl.exchange.fx.select_symbol.listener;

import com.affwl.exchange.fx.select_symbol.Customer;

import java.util.List;

/**
 * Created by user on 4/15/2018.
 */

public interface OnCustomerListChangedListener {
    void onNoteListChanged(List<Customer> customers);
}
