package com.example.leave_a_record.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostData {
    private String user_id;      // 사용자 id
    private String post_title;
    private List<String> post_images_URI;
    private String post_content;
    private List<String> post_meta_gps_Latitue;
    private List<String> post_meta_gps_Longitude;
    private List<String> post_meta_datetime;
    private List<String> post_pin;

    public Map<String, Boolean> stars = new HashMap<>();


   public PostData(){}

    public PostData(String user_id, String post_title,List<String> post_images_URI, String post_content,List<String> post_meta_gps_Latitue,List<String> post_meta_gps_Longitude, List<String> post_meta_datetime,List<String> post_pin) {
        this.user_id = user_id;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_images_URI = post_images_URI;
        this.post_meta_gps_Latitue=post_meta_gps_Latitue;
        this.post_meta_gps_Longitude=post_meta_gps_Longitude;
        this.post_meta_datetime = post_meta_datetime;
        this.post_pin=post_pin;



    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public List<String> getPost_images_URI() {
        return post_images_URI;
    }

    public void setPost_images_URI(List<String> post_images_URI) {
        this.post_images_URI = post_images_URI;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public List<String> getPost_meta_gps_Latitue() {
        return post_meta_gps_Latitue;
    }

    public void setPost_meta_gps_Latitue(List<String> post_meta_gps_Latitue) {
        this.post_meta_gps_Latitue = post_meta_gps_Latitue;
    }

    public List<String> getPost_meta_gps_Longitude() {
        return post_meta_gps_Longitude;
    }

    public void setPost_meta_gps_Longitude(List<String> post_meta_gps_Longitude) {
        this.post_meta_gps_Longitude = post_meta_gps_Longitude;
    }

    public List<String> getPost_meta_datetime() {
        return post_meta_datetime;
    }

    public void setPost_meta_datetime(List<String> post_meta_datetime) {
        this.post_meta_datetime = post_meta_datetime;
    }

    public List<String> getPost_pin() {
        return post_pin;
    }

    public void setPost_pin(List<String> post_pin) {
        this.post_pin = post_pin;
    }

}