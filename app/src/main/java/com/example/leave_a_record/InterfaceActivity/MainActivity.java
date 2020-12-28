package com.example.leave_a_record.InterfaceActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.DataBase.UserData;
import com.example.leave_a_record.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView textname;
    private Database_M m;
    private Intent intent;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);
        textname= findViewById(R.id.text_username);
        findViewById(R.id.imgbtn_newphoto).setOnClickListener(onClickListener);
        findViewById(R.id.imgbtn_profile).setOnClickListener(onClickListener);

        m=new Database_M();

        m.userName(new Callback<String>() {
                       @Override
                       public void onCallback(String data) {
                           textname.setText(data);
                       }
                   });
    }

    View.OnClickListener onClickListener=new View.OnClickListener(){
        public void onClick(View v){
            // new photo는 사진 추가로 기능 전환시켜야함
            switch (v.getId()){
                case R.id.imgbtn_newphoto:
                    intent =new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(intent);
                    break;
                case R.id.imgbtn_profile:
                    intent =new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
