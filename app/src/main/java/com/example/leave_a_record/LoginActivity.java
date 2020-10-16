//package com.example.leave_a_record;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class LoginActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    private static final String TAG = "LoginActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//        mAuth = FirebaseAuth.getInstance();
//        findViewById(R.id.login_bt).setOnClickListener(onClickListener);
//        findViewById(R.id.loginTosignup_bt).setOnClickListener(onClickListener);
//    }
//
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//    }
//
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.login_bt:
//                    toLogin();
//                    break;
//                case R.id.loginTosignup_bt:
//                    loginTosignup();
//                    break;
//            }
//        }
//    };
//
//    //회원가입 확인하기
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
//                                goToast("로그인 성공");
//                                logintomainActivity();
//                            } else {
//
//                                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                goToast("로그인 실패");
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
//
//
//    private void goToast(String msg){
//        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
//    }
//    private void logintomainActivity(){
//        Intent intent = new Intent (this,SetGallery.class);
//        startActivity(intent);
//    }
//    private void loginTosignup(){
//        Intent intent = new Intent (this,SignupActivity.class);
//        startActivity(intent);
//}
//}
