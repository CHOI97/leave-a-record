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


import com.example.leave_a_record.Adapter.CourseListAdapter;
import com.example.leave_a_record.DataBase.DatabaseManagement;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * "여행코스" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class tripCourse extends Fragment {
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    DatabaseManagement m;
    private CourseListAdapter listViewAdapter;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.item_tripcourse, container, false);
        Log.d("success", "tripCourse:success"); //로그찍기
//

                // 리스트뷰에 어뎁터 장착
        listView = view.findViewById(R.id.listview);
        listViewAdapter = new CourseListAdapter();
        listView.setAdapter(listViewAdapter);
//        FirebaseDatabase.getInstance().getReference()
//                .child("posts")
//                .child(mAuth.getCurrentUser().getUid())
//        . addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot data : dataSnapshot.getChildren()) {
//                            PostData postdata=data.getValue(PostData.class);
//                            Log.d("postdata.getPost_title()",postdata.getPost_title());
//                            Log.d("postdata.getPost_title()",postdata.getPost_meta_datetime().get(0));
//                            Log.d("postdata.getPost_title()",postdata.getPost_meta_datetime().get(0));
//                            listViewAdapter.addItem(postdata.getPost_title(),postdata.getPost_meta_datetime().get(0)+" ~ "+postdata.getPost_meta_datetime().get(0),R.drawable.ic_launcher_background);
//                            Log.d("postdata.getPost_title()",postdata.getPost_title());
//                            Log.d("postdata.getPost_title()",postdata.getPost_meta_datetime().get(0));
//                            Log.d("postdata.getPost_title()",postdata.getPost_meta_datetime().get(0));
//
//
//                        }
//                                    // 만들어둔 어뎁터(CouseListAdapter.java)의 객체생성
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });
        listViewAdapter.addItem("테스트","2020.10.23 ~ 2020.10.23",R.drawable.ic_edit);
        return view;

    }
}
