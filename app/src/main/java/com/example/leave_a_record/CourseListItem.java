package com.example.leave_a_record;

import android.net.Uri;

import java.net.URI;

/**
 * Created by USER on 2018-07-23.
 */

public class CourseListItem {
    // 리스트 한칸에 들어가는 컴포넌트들의 getter, setter.
    private Uri uri ;
    private String titleStr, dateStr ;

//    public void setIcon(int iconDrawable) {
//        mIconDrawable = iconDrawable ;
//    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDate(String date) {
        dateStr = date ;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

//    public int getIcon() {
//        return this.mIconDrawable ;
//    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDate() {
        return this.dateStr ;
    }
}
