package com.example.leave_a_record;

/**
 * Created by USER on 2018-07-23.
 */

public class CourseListItem {
    // 리스트 한칸에 들어가는 컴포넌트들의 getter, setter.
    private int mIconDrawable ;
    private String titleStr, dateStr ;

    public void setIcon(int iconDrawable) {
        mIconDrawable = iconDrawable ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDate(String date) {
        dateStr = date ;
    }

    public int getIcon() {
        return this.mIconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDate() {
        return this.dateStr ;
    }
}
