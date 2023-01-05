package com.hall_event_reservation_system.models;

public class users_add_events_model {
    String doc_id , sports, fullname , phonenumber , homeaddress,time_from, time_to,date,datetime ,status;

    public users_add_events_model() {
    }

    public users_add_events_model(String doc_id, String sports, String fullname,
                                  String phonenumber, String homeaddress, String time_from,
                                  String time_to, String date, String datetime, String status) {
        this.doc_id = doc_id;
        this.sports = sports;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.homeaddress = homeaddress;
        this.time_from = time_from;
        this.time_to = time_to;
        this.date = date;
        this.datetime = datetime;
        this.status = status;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to() {
        return time_to;
    }

    public void setTime_to(String time_to) {
        this.time_to = time_to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
