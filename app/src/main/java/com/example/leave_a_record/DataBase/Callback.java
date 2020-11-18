package com.example.leave_a_record.DataBase;

import android.view.View;

public interface Callback<T> { //비동기를 해결하기위한 callback 메소드
    void onCallback(T data);
}
