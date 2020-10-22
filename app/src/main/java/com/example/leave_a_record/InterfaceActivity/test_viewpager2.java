package com.example.leave_a_record.InterfaceActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

//import java.io.Serializable;
import com.example.leave_a_record.Adapter.USERAdapter;
import com.example.leave_a_record.R;
import com.example.leave_a_record.USER;
import com.example.leave_a_record.fragment.myHistory;
import com.example.leave_a_record.post_data_image;

import java.util.ArrayList;
import java.util.List;

public class test_viewpager2 extends AppCompatActivity  {
    ViewPager2 viewPager2;
    List<USER> userList;
    USERAdapter userAdapter;
    Button save_content;
    String firstUri;
//    ArrayList<post_data_image> list =(ArrayList<post_data_image>)intent.getSerializableExtra("image-data");    ----오류-----
    ArrayList<post_data_image> pd_datas_receive;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_viewpager_editpage);
        save_content=findViewById(R.id.save);

        viewPager2 = findViewById(R.id.viewpager2);
        Log.d("현재 진행중인 것은", "화면전환에 성공하셨습니다..");
        pd_datas_receive=(ArrayList<post_data_image>)getIntent().getSerializableExtra("pd_datas");

        userList = new ArrayList<>();
        Log.d("현재 진행중인 것은", "리스트를 받기전입니다..");
        for(int i=0;i<pd_datas_receive.size();i++){
            userList.add(new USER(Uri.parse(pd_datas_receive.get(i).getUri()),null));
            Log.d("현재 진행중인 것은", "Uri를 성공적으로 받았습니다.");
        }
       firstUri=pd_datas_receive.get(0).getUri();



        userAdapter =  new USERAdapter(this,userList);
        viewPager2.setAdapter(userAdapter);

        save_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAndProfile(firstUri);
                goToast("저장완료");

            }
        });

    }
    private void goToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void SaveAndProfile(String string){
        Intent intent = new Intent (this, myHistory.class);
        intent.putExtra("first-image",(string));
        Log.d("현재 진행중인 것은", "인텐트로 넘기기전입니다.");

        startActivity(intent);
    }
}
