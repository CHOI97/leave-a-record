package com.example.leave_a_record.DataBase;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;

import static com.example.leave_a_record.DataBase.Constant.DB_COLLECTION_USERS;
public class DatabaseManagement {

    private static DatabaseManagement dbM = new DatabaseManagement();
    private FirebaseAuth firebaseAuth;   // 파이어베이스 인증 객체
    private UserData userData;
    private FirebaseUser firebaseUser;
//    private ArrayList<String> post_image;
    private FirebaseFirestore database;  // 데이터베이스
    private DatabaseReference mDatabase;



    String TAG="실행중 : ";

    // 생성자
    public DatabaseManagement() {
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        post_image=new ArrayList<>();
    }
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
    public UserData getUserData() {
        return userData;
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

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
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

    public void getUserDataFromDatabase(String email, final Callback<UserData> callback) {
        DocumentReference userRef;

        userRef = database.collection(DB_COLLECTION_USERS).document(email);
        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserData user;

                        user = documentSnapshot.toObject(UserData.class);

                        callback.onCallback(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("DB에서 유저 정보를 가져오지 못함");
                    }
                });
    }


}
