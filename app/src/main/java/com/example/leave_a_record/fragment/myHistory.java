package com.example.leave_a_record.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.example.leave_a_record.Adapter.HistoryListAdapter;

import com.example.leave_a_record.DataBase.DatabaseManagement;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.DataBase.PostData_image;
import com.example.leave_a_record.DataBase.UserData;
import com.example.leave_a_record.DataBase.postUpdate;
import com.example.leave_a_record.R;
import com.example.leave_a_record.post_data_image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * "내 기록" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class myHistory extends Fragment {
   private FirebaseAuth mAuth;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        String pd_datas_receive;
        Intent intent = getActivity().getIntent();
        View view = inflater.inflate(R.layout.item_myhistory, container, false);
        Log.d("success", "myHistory"); //로그찍기
        final ArrayList<String> img = new ArrayList<>();
        ArrayList<String> URI = new ArrayList<>();
        final ArrayList<String> call = new ArrayList<>();
        FirebaseStorage storage;
        StorageReference storageRef;
        storage = FirebaseStorage.getInstance();
        final String uriToString;
        postUpdate postdata_uploadtime;
        int j;
        pd_datas_receive = intent.getStringExtra("first-image");// 커스텀 아답타 생성
        FirebaseDatabase.getInstance().getReference()
                .child("posts").child(mAuth.getCurrentUser().getUid()).child("postUpdate_time")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            postUpdate postUpdate=data.getValue(postUpdate.class);
                            for (int i = 0; i < postUpdate.getPostUpdate_time().size(); i++) {
                                call.add(postUpdate.getPostUpdate_time().get(i));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        for (j = 0; j < call.size(); j++) {
            final int finalJ = j;
            FirebaseDatabase.getInstance().getReference()
                    .child("posts").child(mAuth.getCurrentUser().getUid()).child(call.get(j)).child("postData_image")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                PostData_image postdata;
                                postdata = data.getValue(PostData_image.class);
                                img.add(postdata.getPost_images_URI().get(finalJ));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        for (int k = 0; k < img.size(); k++) {
            storageRef = storage.getReferenceFromUrl("gs://leave-a-record.appspot.com")
                    .child("imagse/")
                    .child(img.get(k));
            storageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri uri = task.getResult();
                        String string_dwload = uri.toString();
                        img.add(string_dwload);
                    } else {
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

        HistoryListAdapter adapter = new HistoryListAdapter(
                getContext(),
                R.layout.item_history_list,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        GridView gv = view.findViewById(R.id.gridview);
        ViewCompat.setNestedScrollingEnabled(gv, true);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        return view;
    }
}
