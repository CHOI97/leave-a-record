package com.example.leave_a_record.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.leave_a_record.Adapter.CourseListAdapter;
import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.R;

import java.util.ArrayList;
import java.util.List;

/**
 * "여행코스" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class tripCourse extends Fragment {
    private CourseListAdapter listViewAdapter;
    private ListView listView;
    List<PostData> postdata=new ArrayList<PostData>();
    AsyncTask mTask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listViewAdapter = new CourseListAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.item_tripcourse, container, false);
        Log.d("success", "tripCourse:success"); //로그찍기
//        listView = view.findViewById(R.id.listview);
//        listViewAdapter = new CourseListAdapter();
//        listView.setAdapter(listViewAdapter);

        Log.d("포스트  액티비로 가져오는데","등록전");
//        listViewAdapter.addItem("제목1","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);
        Log.d("비동기  --- 전 ---",Integer.toString(listViewAdapter.getCount()));


        Log.d("리턴직전  ---","마지막에 나와야하는문장입니다.");
        onCallbackPost(new Callback <List<PostData>>() {
            @Override
            public void onCallback(List<PostData> data) {
                if(data!=null) {
                    Log.d("데이터베이스가 listview의 진입", "추가하겠습니다.");
                    listView = view.findViewById(R.id.listview);
                    listViewAdapter = new CourseListAdapter();
                    for (int i = 0; i < data.size(); i++) {
                        listViewAdapter.addItem(data.get(i).getPost_title(), data.get(i).getPost_date(), R.drawable.ic_edit);
                        Log.d("리스트뷰에 업로드중입니다.  ---","업로드중인 파일은"+data.get(i).getPost_title());
                    }
                    listView.setAdapter(listViewAdapter);
                }
                else{
                    Log.d("콜백값은  ---","false");
                }
            }

        });
        Log.d("드디어","성공");
        return view;
    }

    //    public void pullPost(final Callback<Boolean> callback){
//        final PostData[] postData = {new PostData()};
//        Database_M.getInstance().getPostData(0, new Callback<PostData>() {
//            @Override
//            public void onCallback(PostData data) {
//                if(!data.equals(null)){
//                    postData[0] =data;
//                    Log.d("포스트  액티비로 가져오는데","성공");
//                    listViewAdapter.addItem(postData[0].getPost_title(),postData[0].getPost_date(),R.drawable.ic_edit);
//                    listViewAdapter.addItem("제목2","2012-02-05 ~ 2015-03-05", R.drawable.ic_launcher_background);
//                    Log.d("포스트정보",":"+postData[0].getPost_title());
//                    Log.d("포스트정보",":"+postData[0].getPost_date());
//                    Log.d("비동기 --- 후 ---",Integer.toString(listViewAdapter.getCount()));
//                    callback.onCallback(true);
//                }
//            }
//
//        });
//
//    }
    public View callbackView(View view){
        return view;
    }
    public void onCallbackPost(final Callback <List<PostData>> callback){
//        for(int i=0;i< )
        Log.d("메소드실행 onCallbackPost", ":포스트 리스트뷰에 추가하는 메소드 ");
        Database_M.getInstance().AllgetPostData((ArrayList<PostData>) postdata,new Callback<List<PostData>>(){
            @Override
            public void onCallback(List<PostData> data) {
                callback.onCallback(data);
            }
        });
    }
//    public void onCallback(final Callback<PostData> callback) {
//        Log.d("콜백시작  ---","콜백시작");
//        pullPost(new Callback<PostData>() {
//            @Override
//            public void onCallback(PostData data) {
//                if(data!=null){
//                    callback.onCallback(data);
//                }
//            }
//
//        });
//    }
}

