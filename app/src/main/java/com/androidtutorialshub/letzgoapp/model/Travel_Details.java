package com.androidtutorialshub.letzgoapp.model;

import java.util.Date;

public class Travel_Details {
    private String start_date;
    private Date end_date;
    private String loc;
    private String trip_type;

    public String getStart_date() {
        String str=start_date;
        int mnth=Integer.parseInt(str.substring(0,str.indexOf('/')));
        if(mnth==1)return "Winter";
        else if(mnth==2)return "Winter";
        else if(mnth==3)return "Summer";
        else if(mnth==4)return "Summer";
        else if(mnth==5)return "Summer";
        else if(mnth==6)return "Summer";
        else if(mnth==7)return "Summer";
        else if(mnth==8)return "Rainy";
        else if(mnth==9)return "Rainy";
        else if(mnth==10)return "Winter";
        else if(mnth==11)return "Winter";
        else return "Winter";

    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getLoc() {
        return loc;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }
}
