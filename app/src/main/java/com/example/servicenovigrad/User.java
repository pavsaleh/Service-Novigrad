package com.example.servicenovigrad;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class User {

    public String email;
    public String username;
    public String password;
    public String FirstName;
    public String LastName;
    public String AccountType;

    public static final String CUSTOMER  ="Customer";
    public static final String ADMIN  = "admin";
    public static final String EMPLOYEE  ="Employee";

    public User(){}

    public User(String username, String email, String password, String FirstName, String LastName, String AccountType) {
        this.password = password;
        this.email = email;
        this.FirstName=FirstName;
        this.LastName = LastName;
        this.AccountType = AccountType;
        this.username = username;;
    }
}