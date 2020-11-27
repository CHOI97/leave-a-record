package com.example.leave_a_record.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.Vector;

/**
 * "여행코스" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class tripCourse extends Fragment {
    private CourseListAdapter listViewAdapter;
    private ListView listView;
    List<PostData> postdata=new ArrayList<PostData>();
//    List<Uri> posturis=new ArrayList<>();
    List<String> postStr=new ArrayList<>();
    Vector<Uri> posturis=new Vector<>();
    static final int postFirst= 0;

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

        Log.d("포스트  액티비로 가져오는데","등록전");
        Log.d("비동기  --- 전 ---",Integer.toString(listViewAdapter.getCount()));


        Log.d("리턴직전  ---","마지막에 나와야하는문장입니다.");
        onCallbackPost(new Callback <List<PostData>>() {
            @Override
            public void onCallback(final List<PostData> data) {
                if(data!=null) {
                    Log.d("데이터베이스가 listview의 진입", "추가하겠습니다.");
                    listView = view.findViewById(R.id.listview);
                    listViewAdapter = new CourseListAdapter();
                    //                    listViewAdapter.addItem(data.get(0).getPost_title(), data.get(0).getPost_date(),"https://firebasestorage.googleapis.com/v0/b/leave-a-record.appspot.com/o/images%2F202011132339180.jpg?alt=media&token=1f624a14-b776-4a74-908b-f0adc8aebc39");
                    for(int i=0;i<data.size();i++){
                        Log.d("data : ",Integer.toString(data.size()));
                        Log.d("data:" ,data.get(i).getPost_images_URI().get(postFirst));
                        Log.d("postStr size",Integer.toString(postStr.size()));
                        postStr.add(data.get(i).getPost_images_URI().get(postFirst));


                    }
                    posturis.setSize(data.size());
                    Log.d("현재 벡터의 사이즈는 : ", Integer.toString(posturis.size()));
//                    for (int i = 0; i < data.size(); i++) {
////                        Log.d("가져온 postStr:",postStr.get(i));
                        onCallbackImage(postStr, posturis, new Callback <Vector<Uri>>() {
                            @Override
                            public void onCallback(Vector<Uri> data_image) {
                                if (data_image != null) {
                                    Log.d("data : ",Integer.toString(data.size()));

                                    Log.d("데이터 넣는중 posturis :",Integer.toString(posturis.size()));
                                }
                                if (posturis.size() == data_image.size()) {
                                    Log.d("posturis :"+Integer.toString(posturis.size()),"data_image : "+Integer.toString(data_image.size()));

                                    for(int j=posturis.size()-1;j>=0;j--){
                                        listViewAdapter.addItem(data.get(j).getPost_title(), data.get(j).getPost_date(),posturis.get(j));
                                        Log.d("posturis :",Integer.toString(posturis.size()));
                                        Log.d("리스트뷰에 업로드중입니다.  ---", "업로드중인 파일은" + data.get(j).getPost_title());
                                    }
                                }
                                else{
                                    Log.d("posturis :"+Integer.toString(posturis.size()),"data : "+Integer.toString(data.size()));
                                    Log.d("리스트뷰 업로드중 에러발생.","에러사유는 모르겠다.");
                                }
                                if(listViewAdapter.getCount()==posturis.size()){
                                    listView.setAdapter(listViewAdapter);
                                }
                            }

                        });

//                    }
                }
                else{
                    Log.d("콜백값은  ---","false");
                }
            }

        });
        Log.d("드디어","성공");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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
    //  파일이름 리스트 , Uri를 받을 리스트 , 콜백
    public void onCallbackImage(List<String> str,Vector<Uri> posturi,final Callback<Vector<Uri>> callback){
        Log.d("메소드실행 onCallbackImage",": 포스트의 이미지 uri 다운로드를 시작하겠습니다.");
        Database_M.getInstance().AllgetImageUri(str, posturi,new Callback<Vector<Uri>>(){
            @Override
            public void onCallback(Vector<Uri> data) {
                callback.onCallback(data);
            }
        });
    }
}

