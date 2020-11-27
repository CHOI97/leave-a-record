package com.example.leave_a_record.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.example.leave_a_record.Adapter.HistoryListAdapter;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;

//import com.example.leave_a_record.DataBase.postUpdate;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * "내 기록" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class myHistory extends Fragment {

    GridView gv;
    List<PostData> postdata=new ArrayList<PostData>();
    List<String> postStr=new ArrayList<>();
    static final int postFirst= 0;
    Vector<Uri> posturis=new Vector<>();
    HistoryListAdapter adapter;
    Database_M m;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.item_myhistory, container, false);
        Log.d("success", "myHistory"); //로그찍기


        onCallbackPost(new Callback<List<PostData>>() {
            @Override
            public void onCallback(List<PostData> data) {
                if (data != null) {
                    Log.d("데이터베이스가 myHistory 진입", "추가하겠습니다");
                    gv = view.findViewById(R.id.gridview);
                    for (int i = data.size()-1; i >=0 ; i--) {
                        postStr.add(data.get(i).getPost_images_URI().get(postFirst));
                        Log.d("리스트뷰에 업로드중입니다.  ---", "업로드중인 파일은" + data.get(i).getPost_title());
                        Log.d("리스트뷰에 업로드중입니다.  ---", "업로드중인 파일은" + data.get(i).getPost_images_URI().get(postFirst));

                    }
                }
                posturis.setSize(data.size());
                Log.d("현재 벡터의 사이즈는 : ", Integer.toString(posturis.size()));
                onCallbackImage(postStr, posturis, new Callback<Vector<Uri>>() {
                    @Override
                    public void onCallback(Vector<Uri> data_image) {
                        if (data_image != null) {
                            Log.d("posturis 값들:",(posturis.get(0).toString()));

                        }
                        if (posturis.size() == data_image.size()) {
                            adapter = new HistoryListAdapter(
                                    getContext(),
                                    R.layout.item_history_list,       // GridView 항목의 레이아웃 row.xml
                                    posturis);    // 데이터
                            ViewCompat.setNestedScrollingEnabled(gv,true);
                            gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
                        }
                        else{
                            Log.d("List view 에서 이미지 다운로드 과정에서 오류발생","이유모름");
                        }

                    }
                });
            }
        });

            // 커스텀 아답타 생성
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
    public void onCallbackImage(List<String> str,Vector<Uri> posturi,final Callback <Vector<Uri>> callback){
        Log.d("메소드실행 onCallbackImage",": 포스트의 이미지 uri 다운로드를 시작하겠습니다.");
        Database_M.getInstance().AllgetImageUri(str,posturi,new Callback<Vector<Uri>>(){
            @Override
            public void onCallback(Vector<Uri> data) {
                callback.onCallback(data);
            }
        });
    }
}


