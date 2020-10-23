package com.example.leave_a_record;

import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.net.URI;

public class image_edit_data {
//    private String edit_title;
//    private RadioButton p1,p2,p3,p4,p5;
    private String content;
    private String date_time;
    private Uri uri;




    //////////////////////////test code
public image_edit_data(Uri uri , String data_time,String content) {
//    this.edit_title = edit_title;
//    this.p1 = p1;
//    this.p2 = p2;
//    this.p3 = p3;
//    this.p4 = p4;
//    this.p5 = p5;
//    this.edit_iv = edit_iv;
    this.uri=uri;
    this.content=content;
    this.date_time = date_time;
//    this.save = save;
}
/////////////////////////////////////


    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


}
