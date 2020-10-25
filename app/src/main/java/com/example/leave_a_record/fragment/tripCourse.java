package com.example.leave_a_record.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.leave_a_record.CourseListAdapter;
import com.example.leave_a_record.R;
/**
 * "여행코스" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class tripCourse extends Fragment {
    private CourseListAdapter listViewAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tripcourse, container, false);
        Log.d("success", "tripCourse:success"); //로그찍기
//

        listView = view.findViewById(R.id.listview);            // 리스트뷰 아이디할당
        listViewAdapter = new CourseListAdapter();              // 만들어둔 어뎁터(CouseListAdapter.java)의 객체생성
        listView.setAdapter(listViewAdapter);                   // 리스트뷰에 어뎁터 장착


        // 이코드를 한줄을 추가할수록 ListView에 한 줄씩 추가됩니다.
        listViewAdapter.addItem("제목1","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);
        listViewAdapter.addItem("제목2","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);
        listViewAdapter.addItem("제목2","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);
        listViewAdapter.addItem("제목2","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);
        listViewAdapter.addItem("제목2","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);


        return view;

    }
}
