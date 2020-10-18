package com.example.leave_a_record;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class test_viewpager2 extends AppCompatActivity  {
    ViewPager2 viewPager2;
    List<USER> userList;
    USERAdapter userAdapter;

//    ArrayList<post_data_image> list =(ArrayList<post_data_image>)intent.getSerializableExtra("image-data");    ----오류-----
    ArrayList<post_data_image> pd_datas_receive;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewpager2);


        viewPager2 = findViewById(R.id.viewpager2);
        Log.d("현재 진행중인 것은", "화면전환에 성공하셨습니다..");
        pd_datas_receive=(ArrayList<post_data_image>)getIntent().getSerializableExtra("pd_datas");

        userList = new ArrayList<>();
        Log.d("현재 진행중인 것은", "리스트를 받기전입니다..");
        for(int i=0;i<pd_datas_receive.size();i++){
            userList.add(new USER(Uri.parse(pd_datas_receive.get(i).getUri())));
            Log.d("현재 진행중인 것은", "Uri를 성공적으로 받았습니다.");
        }



        userAdapter =  new USERAdapter(this,userList);
        viewPager2.setAdapter(userAdapter);

    }
}
