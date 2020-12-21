package com.example.leave_a_record.DataBase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserData {
    private String user_id;      // 사용자 id
    private String user_name;    // 사용자 이름
//    private String user_phone;   // 사용자 핸드폰번호(향후)
//    private String user_birth; // 생년월일(향후)
    private String user_pwd;// 사용자 비밀번호
    private String uid;
    private String user_image;
    private String user_about;
    private List<String> opendata;

    public List<String> getPostdata() {
        return opendata;
    }

    public void setPostdata(List<String> postdata) {
        this.opendata = postdata;
    }

    public UserData(){}
    public UserData(String user_id, String user_name, String user_pwd,String uid) {
        this.user_id = user_id;
        this.user_name = user_name;
//        this.user_phone = user_phone; 향후
        this.user_pwd = user_pwd;
        this.uid= uid;

        this.user_image="";
        this.user_about="Life is a Journery!";
//        this.opendata=" ";

//        this.user_birth=user_birth; 향후
    }

    public String getUser_about() {
        return user_about;
    }

    public void setUser_about(String user_about) {
        this.user_about = user_about;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }


}