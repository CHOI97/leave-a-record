package com.example.leave_a_record;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class post_data_image  implements Serializable {
    private String uri;
    private String date_time;

    public post_data_image(String uri,String date_time){
        this.uri=uri;
        this.date_time=date_time;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }


}
