package com.example.leave_a_record;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.leave_a_record.fragment.myHistory;
import com.example.leave_a_record.fragment.tripCourse;

public class ProfileActivity extends AppCompatActivity {
    private TextView txt_myHistory, txt_tripCourse; // 내 기록, 여행코스 메뉴버튼을 제어하기 위한 변수
    private LinearLayout fragment_layout;        // 바뀌는 화면을 담당할 변수

    private FragmentManager fragmentManager;            // Framgent 매니저 클래스 변수
    private FragmentTransaction fragmentTransaction;    // Fragment 트랜잭션클래스 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        txt_myHistory = findViewById(R.id.txt_myHistory);
        txt_tripCourse = findViewById(R.id.txt_tripCourse);
        fragment_layout= findViewById(R.id.fragment_layout);

//        GridView gridView = (GridView)findViewById(R.id.gridview);
//        gridView.setAdapter(new HistoryListAdapter(this,));

        txt_myHistory.setOnClickListener(new menuClickListener());
        txt_tripCourse.setOnClickListener(new menuClickListener());

        // Fragment 정보 초기화
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // "내 기록" Fragment 먼저 보여줌
        myHistory fragment1 = new myHistory();
        fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();


    }


    // 내 기록, 여행코스를 눌렀을때 이벤트 리스너
    private class menuClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            fragmentTransaction = fragmentManager.beginTransaction();
            // Fragment 정보 초기화
            switch (view.getId()){
                // 내 기록을 눌렀을때
                case R.id.txt_myHistory :
                    // 1. Fragment를 대체하고
                    myHistory fragment1 = new myHistory();
                    fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();

                    // 2. 밑줄을 생성
                    txt_myHistory.setBackground(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.border));
                    txt_tripCourse.setBackgroundColor(Color.parseColor("#ffffff"));
                    break;
                // 코스여행을 눌렀을때
                case R.id.txt_tripCourse :
                    // 1. Fragment를 대체하고
                    txt_myHistory.setBackgroundColor(Color.parseColor("#ffffff"));
                    txt_tripCourse.setBackground(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.border));

                    // 2. 밑줄을 생성
                    tripCourse fragment2 = new tripCourse();
                    Log.d("success", "tripCourse case:success"); //로그찍기
                    fragmentTransaction.replace(R.id.fragment_layout, fragment2).commitAllowingStateLoss();
                    break;
            }
        }
    }
}
