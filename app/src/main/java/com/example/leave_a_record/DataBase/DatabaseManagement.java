package com.example.leave_a_record.DataBase;
import android.telecom.Call;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.leave_a_record.Adapter.HistoryListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;

public class DatabaseManagement {

    private static DatabaseManagement dbM = new DatabaseManagement();
    private FirebaseAuth cAuth;   // 파이어베이스 인증 객체
    private UserData userData;
    private FirebaseUser firebaseUser;
    //    private ArrayList<String> post_image;
    private FirebaseFirestore database;  // 데이터베이스
    private DatabaseReference mDatabase;
    private ArrayList<String> img_key_name;
    private ArrayList<String> img;
    HistoryListAdapter adapter;
//    private Callback<Boolean> callback;

    String TAG="실행중 : ";

    // 생성자
    public DatabaseManagement() {
//        private static DatabaseManagement mData = new DatabaseManagement();
        cAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        img_key_name=new ArrayList<>();
        img =new ArrayList<>();
//        callback = new Callback<Boolean?
//        post_image=new ArrayList<>();
    }

//    public void getKey(final Callback<String> callback){
//        FirebaseDatabase.getInstance().getReference()
//                .child("posts")
//                .child(cAuth.getCurrentUser().getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        img_key_name.clear();
//                        img_key_name.add("");
//                        int i = 0;
//                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                            img_key_name.add(child.getKey());
//                            Log.d("key : ", img_key_name.get(i));
////                                imageLoad(i);
////                            callback.onCallback(true);
//                            callback.onCallback(img_key_name.get(i));
//                            image_show(callback);
//                            i++;
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        callback.onCallback(null);
//                    }
//                });
//    }
//    public void image_show(final Callback<String> callback){
//        FirebaseDatabase.getInstance().getReference()
//                .child("posts")
//                .child(cAuth.getCurrentUser().getUid()).child(callback.toString())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot data : dataSnapshot.getChildren()) {
//                            PostData postData=data.getValue(PostData.class);
//                            img.add(postData.getPost_images_URI().get(0));
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }

    public FirebaseAuth getmAuth() {
        return cAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.cAuth = mAuth;
    }

    public ArrayList<String> getImg_key_name() {
        return img_key_name;
    }

    public void setImg_key_name(ArrayList<String> img_key_name) {
        this.img_key_name = img_key_name;
    }

    public UserData getUserData() {
        return userData;
    }
    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public static DatabaseManagement getDbM() {
        return dbM;
    }

    public static void setDbM(DatabaseManagement dbM) {
        DatabaseManagement.dbM = dbM;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseFirestore database) {
        this.database = database;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    // 객체 가져오기
    public static DatabaseManagement getInstance() {
        return dbM;
    }



}
