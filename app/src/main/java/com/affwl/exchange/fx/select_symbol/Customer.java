package com.affwl.exchange.fx.select_symbol;

/**
 * Created by user on 4/15/2018.
 */

public class Customer {
    private Long id;
    private String name;
    private String emailAddress;
    private int imageId;
    private String imagePath;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getImagePath() {
        return imagePath;
    }
}
