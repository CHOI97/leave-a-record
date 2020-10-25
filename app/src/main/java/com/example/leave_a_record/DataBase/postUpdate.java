package com.example.leave_a_record.DataBase;

import java.util.ArrayList;
import java.util.List;

public class postUpdate {
    private ArrayList<String> postUpdate_time;

    public postUpdate(){}
    public postUpdate(ArrayList<String> postUpdate_time){
        this.postUpdate_time=postUpdate_time;
    }

    public ArrayList<String> getPostUpdate_time() {
        return postUpdate_time;
    }

    public void setPostUpdate_time(ArrayList<String> postUpdate_time) {
        this.postUpdate_time = postUpdate_time;
    }
}
