package com.example.servicenovigrad;

import java.util.ArrayList;
import java.util.List;

public class Service {
    public String serviceProviderUsername;
    public  String serviceType;
    public  Double servicePrice;
    public Integer serviceHash;
    public List<String> informationList = new ArrayList<String>();
    public List<String> documentList = new ArrayList<String>();

    public Service (String serviceProviderUsername, String serviceType, Double servicePrice, Integer Hash, List<String> informationList, List<String> documentList) {
        this.serviceProviderUsername = serviceProviderUsername;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
        this.serviceHash = Hash;
        this.informationList = informationList;
        this.documentList = documentList;
    }

    public Service(){

    }
}