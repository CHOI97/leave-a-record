package com.example.leave_a_record;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class post_data_image  implements Serializable,Comparable<post_data_image>{
    private String uri;
    private String date_time;
    private String data_gps_Latitude;
    private String data_gps_Longitude;

    public post_data_image(String uri,String date_time,String data_gps_Latitude,String data_gps_Longitude){
        this.uri=uri;
        this.date_time=date_time;
        this.data_gps_Latitude=data_gps_Latitude;
        this.data_gps_Longitude=data_gps_Longitude;
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

    public String getData_gps_Latitude() {
        return data_gps_Latitude;
    }

    public void setData_gps_Latitude(String data_gps_Latitude) {
        this.data_gps_Latitude = data_gps_Latitude;
    }

    public String getData_gps_Longitude() {
        return data_gps_Longitude;
    }

    public void setData_gps_Longitude(String data_gps_Longitude) {
        this.data_gps_Longitude = data_gps_Longitude;
    }

    @Override
    public int compareTo(post_data_image o) {

        String date1=(this.date_time);
        String date2=(o.date_time);
        if(date1.compareTo(date2)>0){
            return 1;
        }
        else if(date1.compareTo(date2)<0){
            return -1;
        }
        else{
            return 0;
        }
//        return 0;
    }
        public String time_conventer(String tag){
        String s1 =tag;

        tag = s1.replaceAll("[^0-9}]","");//문자열 필요없는 부분삭제

        return tag;
    }
}
