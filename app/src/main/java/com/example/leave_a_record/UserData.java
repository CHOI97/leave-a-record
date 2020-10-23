package com.example.leave_a_record;

import java.util.ArrayList;
import java.util.Calendar;

public class UserData {
    private String user_id;      // 사용자 id
    private String user_name;    // 사용자 이름
//    private String user_phone;   // 사용자 핸드폰번호(향후)
//    private String user_birth; // 생년월일(향후)
    private String user_pwd;// 사용자 비밀번호
    private ArrayList<String> post_images;
//    private String signupDate; //오늘 날짜 (향후)

    public UserData() {
        post_images=new ArrayList<>();
    }

    public UserData(String user_id, String user_name, String user_pwd) {
        this.user_id = user_id;
        this.user_name = user_name;
//        this.user_phone = user_phone; 향후
        this.user_pwd = user_pwd;
//        this.user_birth=user_birth; 향후

        post_images=new ArrayList<>();
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

    public ArrayList<String> getPost_images() {
        return post_images;
    }

    public void setPost_images(ArrayList<String> post_images) {
        this.post_images = post_images;
    }

//    -------------향후 계획-----------
//    public String getSignupDate() {
//        return signupDate;
//    }
//
//    public void setSignupDate(String signupDate) {
//        this.signupDate = signupDate;
//    }
}