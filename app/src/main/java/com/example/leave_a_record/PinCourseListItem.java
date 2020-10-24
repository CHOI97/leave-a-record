package com.example.leave_a_record;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

/**
 * Created by USER on 2018-07-23.
 */

public class PinCourseListItem {
    // 리스트 한칸에 들어가는 컴포넌트들의 getter, setter.
//    private CardView cardview;
//
//    public PinCourseListItem(CardView cardview, String date_time, String couse_time, String place1, String place2, String start, String finish, View viewline, int airplane) {
//        this.cardview = cardview;
//        this.date_time = date_time;
//        this.couse_time = couse_time;
//        this.place1 = place1;
//        this.place2 = place2;
//        this.start = start;
//        this.finish = finish;
//        this.viewline = viewline;
//        this.airplane = airplane;
//    }

    private String date_time;
//    private String couse_time;
//    private String place1, place2;
//    private String start,finish;
//    private View viewline;
//    private int airplane;

    public PinCourseListItem(String date_time) {
        this.date_time=date_time;
    }

//    public View getViewline() {
//        return viewline;
//    }
//
//    public void setViewline(View viewline) {
//        this.viewline = viewline;
//    }

//    public int getAirplane() {
//        return airplane;
//    }
//
//    public void setAirplane(int airplane) {
//        this.airplane = airplane;
//    }


//    public CardView getCardview() {
//        return cardview;
//    }
//
//    public void setCardview(CardView cardview) {
//        this.cardview = cardview;
//    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

//    public String getCouse_time() {
//        return couse_time;
//    }
//
//    public void setCouse_time(String couse_time) {
//        this.couse_time = couse_time;
//    }
//
//    public String getPlace1() {
//        return place1;
//    }
//
//    public void setPlace1(String place1) {
//        this.place1 = place1;
//    }
//
//    public String getPlace2() {
//        return place2;
//    }
//
//    public void setPlace2(String place2) {
//        this.place2 = place2;
//    }
//
//    public String getStart() {
//        return start;
//    }
//
//    public void setStart(String start) {
//        this.start = start;
//    }
//
//    public String getFinish() {
//        return finish;
//    }
//
//    public void setFinish(String finish) {
//        this.finish = finish;
//    }
}
