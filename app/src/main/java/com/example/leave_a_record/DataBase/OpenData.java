package com.example.leave_a_record.DataBase;

import java.util.List;

public class OpenData {
    private String post_title; // 포스트 제목
    private List<String> post_images_URI; // 포스트 이미지 uri (리스트로 구현)
    private String post_content; // 포스트에 대한 게시글
    private List<String> post_meta_gps_Latitue; // 포스트 이미지 좌표
    private List<String> post_meta_gps_Longitude; // 포스트 이미지 좌표
    private List<String> post_meta_datetime; // 포스트 이미지 시간
    private List<String> post_pin; //포스트 이미지 핀
    private String post_date;
    private int point;

    OpenData(){}
 //메타데이터가없는 이미지인경우 생성자를 다른하나를 더추가해야함.
    public OpenData(String post_title,List<String> post_images_URI, String post_content,List<String> post_meta_gps_Latitue,List<String> post_meta_gps_Longitude, List<String> post_meta_datetime,List<String> post_pin,String post_date) {
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_images_URI = post_images_URI;
        this.post_meta_gps_Latitue=post_meta_gps_Latitue;
        this.post_meta_gps_Longitude=post_meta_gps_Longitude;
        this.post_meta_datetime = post_meta_datetime;
        this.post_pin=post_pin;
        this.post_date=post_date;
        this.point = 0;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }
}
}
