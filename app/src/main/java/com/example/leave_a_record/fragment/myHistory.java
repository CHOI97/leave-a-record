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

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.DatabaseManagement;
import com.example.leave_a_record.DataBase.PostData;

import com.example.leave_a_record.DataBase.UserData;
//import com.example.leave_a_record.DataBase.postUpdate;
import com.example.leave_a_record.R;
import com.example.leave_a_record.post_data_image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
    final ArrayList<String> img_key_name = new ArrayList<>();
    final ArrayList<String> img = new ArrayList<>();
    DatabaseReference mDatabase;
    DatabaseManagement m;
    Callback<String> callback;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        String pd_datas_receive;
        Intent intent = getActivity().getIntent();
        final View view = inflater.inflate(R.layout.item_myhistory, container, false);
        Log.d("success", "myHistory"); //로그찍기
        final ArrayList<String> URI = new ArrayList<>();
        ArrayList<String> img=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        m =new DatabaseManagement();
        HistoryListAdapter adapter;

//        mDatabase=database.getInstance().getReference()
//                .child("posts")
//                .child(mAuth.getCurrentUser().getUid()).child(img.)
        FirebaseStorage storage;
        StorageReference storageRef;
        storage = FirebaseStorage.getInstance();
        FirebaseDatabase.getInstance().getReference()
                .child("posts")
                .child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        img_key_name.clear();
//                        img_key_name.add("");
                        URI.clear();
                        int i = 0;
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            img_key_name.add(child.getKey());
                            Log.d("key : ", img_key_name.get(i));
//                            gs://leave-a-record.appspot.com/images/"+current_post_Time+filename
//                            URI.add("gs://leave-a-record.appspot.com/images/"+img_key_name.get(i)+"0"+".jpg"); //key == 202010261243 i .jpg
                            URI.add(img_key_name.get(i)+"0"+".jpg"); //key == 202010261243 i .jpg
                            Log.d("key : ", URI.get(i));
                            i++;

                        }
                        HistoryListAdapter adapter = new HistoryListAdapter(
                                getContext(),
                                R.layout.item_history_list,       // GridView 항목의 레이아웃 row.xml
                                URI);    // 데이터


                        GridView gv = view.findViewById(R.id.gridview);
                        ViewCompat.setNestedScrollingEnabled(gv, true);
                        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onCallback(null);
                    }
                });
//        HistoryListAdapter adapter = new HistoryListAdapter(
//                getContext(),
//                R.layout.item_history_list,       // GridView 항목의 레이아웃 row.xml
//                URI);    // 데이터

//        GridView gv = view.findViewById(R.id.gridview);
//        ViewCompat.setNestedScrollingEnabled(gv, true);
//        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용
//        GridView gv = view.findViewById(R.id.gridview);
//        ViewCompat.setNestedScrollingEnabled(gv, true);
//        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        return view;
    }
}
//    }
//    public void imageLoad(final int i) {
//        FirebaseDatabase.getInstance().getReference()
//                .child("posts")
//                .child(mAuth.getCurrentUser().getUid())
//                .child(img.get(i))
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot data : dataSnapshot.getChildren()) {
//                            PostData postdata;
//                            postdata = data.getValue(PostData.class);
//                            img.add(postdata.getPost_images_URI().get(i));
////                        }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//    }
//
//}
