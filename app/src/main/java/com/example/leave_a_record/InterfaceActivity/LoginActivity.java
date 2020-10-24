package com.example.leave_a_record.InterfaceActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.DatabaseManagement;
import com.example.leave_a_record.DataBase.UserData;
import com.example.leave_a_record.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private DatabaseManagement mAuth;
    private static final String TAG = "LoginActivity";

    Callback<Boolean> callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);
        mAuth =new DatabaseManagement();
        findViewById(R.id.login_bt).setOnClickListener(onClickListener);
        findViewById(R.id.loginTosignup_bt).setOnClickListener(onClickListener);

    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getFirebaseAuth().getCurrentUser();
//        updateUI(currentUser);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_bt:
                    signInEmail(callback);
                    break;
                case R.id.loginTosignup_bt:
                    loginTosignup();
                    break;
            }
        }
    };
    public void signInEmail(final Callback<Boolean> callback) {
        final String email = ((EditText) findViewById(R.id.login_id)).getText().toString();
        final String password = ((EditText) findViewById(R.id.login_pw)).getText().toString();
        if (email.length() > 0 && password.length() > 0) {
        mAuth.getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    // 작업 완료시
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // 이메일 로그인 성공
                        if(task.isSuccessful()) {
                            logintomainActivity();
                            Log.d("현재 진행중인 것은", "-------------로그인중입니다.");
                            goToast("로그인 성공");
                            // DB에서 유저데이터 가져오기
//                            mAuth.getUserDataFromDatabase(email, new Callback<UserData>() {
//                                @Override
//                                public void onCallback(UserData data) {
//                                    // 유저데이터를 성공적으로 가져왔을 때
//                                    if(data != null) {
////                                        mAuth.getInstance().setUserData(post);
//
//                                        callback.onCallback(true);
//                                    } else {
//                                        callback.onCallback(false);
//                                    }
//                                }
//                            });
                        } else {
                            // 로그인 실패시
                            goToast("로그인 실패");
                        }
                    }
                });
        }
        else{
            goToast("아이디와 비밀번호를 입력해주세요.");
        }
    }

//    private void toLogin() {
//        String email = ((EditText) findViewById(R.id.login_id)).getText().toString();
//        String password = ((EditText) findViewById(R.id.login_pw)).getText().toString();
//
//
//        if (email.length() > 0 && password.length() > 0) {
//
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//
//                                Log.d(TAG, "signInWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                getUserDataFromDatabase(email,new CallBack(UserData))
//                                updateUI(user);
//                                goToast("로그인 성공");
//                                logintomainActivity();
//                            } else {
//
//                                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                goToast("로그인 실패");
//                                updateUI(null);
//
//                                // ...
//                            }
//
//                            // ...
//                        }
//                    });
//
//        } else {
//            goToast("아이디와 비밀번호를 입력해주세요.");
//        }
//    }


    private void goToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void logintomainActivity(){
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }
    private void loginTosignup(){
        Intent intent = new Intent (this, SignupActivity.class);
        startActivity(intent);
}
}
