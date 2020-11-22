package com.example.servicenovigrad;

public class userProfile {
    String Address;
    String Phonenumber;
    String CompanyName;
    String username;

    public userProfile(String address, String phonenumber, String companyName, String un) {
        Address = address;
        Phonenumber = phonenumber;
        CompanyName = companyName;
        username = un;
    }
    public userProfile(){
    }
}