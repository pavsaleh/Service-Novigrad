package com.example.servicenovigrad;

public class AvailableHours {
    public String MonStart;
    public String MonEnd;
    public String TuesStart;
    public String TuesEnd;
    public String WedStart;
    public String WedEnd;
    public String ThursStart;
    public String ThursEnd;
    public String FriStart;
    public String FriEnd;
    public String SatStart;
    public String SatEnd;
    public String SunStart;
    public String SunEnd;
    public String username;

    public AvailableHours
            (String StrMonStart, String StrMonEnd, String StrTuesStart, String StrTuesEnd, String StrWedStart,
             String StrWedEnd, String StrThursStart, String StrThursEnd, String StrFriStart, String StrFriEnd,
             String StrSatStart, String StrSatEnd, String StrSunStart, String StrSunEnd, String strUsername) {

        this.MonStart = StrMonStart ;
        this.TuesStart = StrTuesStart;
        this.WedStart = StrWedStart;
        this.ThursStart = StrThursStart;
        this.FriStart = StrFriStart;
        this.SatStart = StrSatStart;
        this.SunStart = StrSunStart;

        this.MonEnd = StrMonEnd;
        this.TuesEnd = StrTuesEnd;
        this.WedEnd = StrWedEnd;
        this.ThursEnd = StrThursEnd;
        this.FriEnd = StrFriEnd;
        this.SatEnd = StrSatEnd;
        this.SunEnd = StrSunEnd;
        username = strUsername;

    }
    public AvailableHours(){

    }
}
