//package com.example.leave_a_record;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class SignupActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    private static final String TAG="SignupActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sign_up);
//        mAuth = FirebaseAuth.getInstance();
//        findViewById(R.id.signup_bt).setOnClickListener(onClickListener);
//        findViewById(R.id.signTologin_bt).setOnClickListener(onClickListener);
//
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
//                case R.id.signup_bt:
//                    signUp();
//                    break;
//                case R.id.signTologin_bt:
//                    signTologinActivity();
//                    break;
//
//            }
//        }
//    };
//    //회원가입 확인하기
//    private void signUp() {
//        String email =((EditText)findViewById(R.id.signup_id)).getText().toString();
//        String password = ((EditText)findViewById(R.id.signup_pw)).getText().toString();
//        String passwordCheck=((EditText)findViewById(R.id.signup_pwd)).getText().toString();
//
//        if(email.length()>0&&password.length()>0&&passwordCheck.length()>0) {
//            if (password.equals(passwordCheck)) {
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    goToast("회원가입 성공");
//                                    signTologinActivity();
//
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                    goToast("회원가입 실패");
//                                }
//
//                                // ...
//                            }
//                        });
//            } else {
//                goToast("비밀번호가 일치하지 않습니다.");
//            }
//        }
//        else{
//            goToast("이메일 또는 비밀번호를 입력해주세요.");
//        }
//
//
//
//    }
//    private void goToast(String msg){
//        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
//    }
//    private void signTologinActivity(){
//        Intent intent = new Intent (this,LoginActivity.class);
//        startActivity(intent);
//    }
//}