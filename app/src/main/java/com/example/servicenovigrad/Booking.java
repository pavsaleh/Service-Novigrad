package com.example.servicenovigrad;

public class Booking {
    public String customerName;
    public String providerName;
    public String startTime;
    public String endTime;
    public String serviceName;
    public String firstName;
    public String lastName;
    public String DOB;
    public String address;

    public Booking(String customerName, String providerName, String startTime, String endTime, String serviceName, String firstName, String lastName, String DOB, String address) {
        this.customerName = customerName;
        this.providerName = providerName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceName = serviceName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.address = address;
    }
    public Booking(){};
}