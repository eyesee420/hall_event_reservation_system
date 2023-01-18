package com.hall_event_reservation_system.models;

public class create_users_model {
    public String doc_id , fullname , homeaddress , phonenumber , emailaddress ;

    public create_users_model() {
    }

    public create_users_model(String doc_id, String fullname, String homeaddress, String phonenumber, String emailaddress) {
        this.doc_id = doc_id;
        this.fullname = fullname;
        this.homeaddress = homeaddress;
        this.phonenumber = phonenumber;
        this.emailaddress = emailaddress;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}
