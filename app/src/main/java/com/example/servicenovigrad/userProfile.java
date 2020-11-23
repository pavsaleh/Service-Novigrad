package com.example.servicenovigrad;

public class userProfile {
    public String Address;
    public String Phonenumber;
    public  String CompanyName;
    public String username;

    public userProfile(String address, String phonenumber, String companyName, String un) {
        Address = address;
        Phonenumber = phonenumber;
        CompanyName = companyName;
        username = un;
    }
    public userProfile(){
    }
}