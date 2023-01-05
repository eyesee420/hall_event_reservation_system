package com.hall_event_reservation_system.models;

public class post_models {

    public String doc_id , image_uri ,post_descriptions ,date_time;

    public post_models() {
    }

    public post_models(String doc_id, String image_uri, String post_descriptions, String date_time) {
        this.doc_id = doc_id;
        this.image_uri = image_uri;
        this.post_descriptions = post_descriptions;
        this.date_time = date_time;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getPost_descriptions() {
        return post_descriptions;
    }

    public void setPost_descriptions(String post_descriptions) {
        this.post_descriptions = post_descriptions;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
