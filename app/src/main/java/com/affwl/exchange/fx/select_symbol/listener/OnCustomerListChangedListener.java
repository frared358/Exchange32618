package com.affwl.exchange.fx.select_symbol.listener;

import com.affwl.exchange.fx.select_symbol.Customer;

import java.util.List;



public interface OnCustomerListChangedListener {
    void onNoteListChanged(List<Customer> customers);
}
