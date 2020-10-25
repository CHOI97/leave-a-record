package com.example.leave_a_record;

import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.net.URI;

public class image_edit_data {
//    private String edit_title;
    private RadioButton rb;
    private RadioGroup radioGroup;
    private EditText content; // 내용 직접 써야하는 내용
    private Uri uri; //받아서 뿌리는것

public image_edit_data(Uri uri) {
//    this.edit_title = edit_title;

//    this.content=content;
    this.uri=uri;

//    this.save = save;
}
/////////////////////////////////////


//    public String getDate_time() {
//        return date_time;
//    }
//
//    public void setDate_time(String date_time) {
//        this.date_time = date_time;
//    }


    public EditText getContent() {
        return content;
    }

    public void setContent(EditText content) {
        this.content = content;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public RadioButton getRb() {
        return rb;
    }

    public void setRb(RadioButton rb) {
        this.rb = rb;
    }
}
