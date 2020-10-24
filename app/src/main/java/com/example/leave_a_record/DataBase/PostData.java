package com.example.leave_a_record.DataBase;

import java.util.ArrayList;
import java.util.List;

public class PostData {
    private String user_id;      // 사용자 id
    private List<String> post_images_URI;
    private List<String> post_content;
    private String post_title;
    private List<String> post_meta_gps;
    private List<String> post_meta_datetime;

   public PostData(){}

    public PostData(String user_id, List<String> post_images_URI, List<String> post_content, String post_title, List<String> post_meta_gps, List<String> post_meta_datetime) {
        this.user_id = user_id;
        this.post_images_URI = post_images_URI;
        this.post_content = post_content;
        this.post_title = post_title;
        this.post_meta_gps = post_meta_gps;
        this.post_meta_datetime = post_meta_datetime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<String> getPost_images_URI() {
        return post_images_URI;
    }

    public void setPost_images_URI(List<String> post_images_URI) {
        this.post_images_URI = post_images_URI;
    }

    public List<String> getPost_content() {
        return post_content;
    }

    public void setPost_content(List<String> post_content) {
        this.post_content = post_content;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public List<String> getPost_meta_gps() {
        return post_meta_gps;
    }

    public void setPost_meta_gps(List<String> post_meta_gps) {
        this.post_meta_gps = post_meta_gps;
    }

    public List<String> getPost_meta_datetime() {
        return post_meta_datetime;
    }

    public void setPost_meta_datetime(List<String> post_meta_datetime) {
        this.post_meta_datetime = post_meta_datetime;
    }
}