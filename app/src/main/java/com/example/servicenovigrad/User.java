package com.example.servicenovigrad;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties

public class User {

    public String email;
    public String username;
    public String password;
    public String FirstName;
    public String LastName;
    public String AccountType;
    public ArrayList<Integer> servicesOffered;

    public static final String CUSTOMER  ="Customer";
    public static final String ADMIN  = "admin";
    public static final String EMPLOYEE  ="Employee";

    public User(){}

    public User(String username, String email, String password, String FirstName, String LastName, String AccountType) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.FirstName=FirstName;
        this.LastName = LastName;
        this.AccountType = AccountType;
    }

    public void addServiceRef(int ServHash){
        servicesOffered.add(ServHash);
    }

}