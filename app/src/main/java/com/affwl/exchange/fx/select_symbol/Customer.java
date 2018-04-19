package com.affwl.exchange.fx.select_symbol;

/**
 * Created by user on 4/15/2018.
 */

public class Customer {
    private Long id;
    private String name;
    private String emailAddress;


    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }



    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


}
