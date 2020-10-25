package com.example.leave_a_record.InterfaceActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leave_a_record.DataBase.Constant;
import com.example.leave_a_record.R;
import com.example.leave_a_record.DataBase.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private static final String TAG="SignupActivity";
    private static final String TAG2="Member Activity";
    public EditText id, name, pwd,pwd_c;

    public DatabaseReference mDatabase;
    public FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sign_up);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase=database.getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signup_bt).setOnClickListener(onClickListener);
        findViewById(R.id.signTologin_bt).setOnClickListener(onClickListener);
        id = findViewById(R.id.signup_id);
        name = findViewById(R.id.signup_name);
        pwd = findViewById(R.id.signup_pw);
        pwd_c=findViewById(R.id.signup_pwd);

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signup_bt:
                    signUp();
                    break;
                case R.id.signTologin_bt:
                    signTologinActivity();
                    break;

            }
        }
    };
    //회원가입 확인하기
    private void signUp() {
        final String email = id.getText().toString();
        final String passwd = pwd.getText().toString();
        final String userName = name.getText().toString();
        String passwd_c=pwd_c.getText().toString();

//        String email =((EditText)findViewById(R.id.signup_id)).getText().toString();
//        String password = ((EditText)findViewById(R.id.signup_pw)).getText().toString();
//        String passwordCheck=((EditText)findViewById(R.id.signup_pwd)).getText().toString();

        if(email.length()>0&&passwd.length()>0&&passwd_c.length()>0&&userName.length()>0) {
            if (passwd.equals(passwd_c)) {
                mAuth.createUserWithEmailAndPassword(email, passwd)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user =  mAuth.getCurrentUser();

                                    UserData userdata=new UserData(email,userName,passwd,user.getUid());
//                                    mDatabase.child("Users").setValue(userdata);


                                    Log.d(TAG, "유저데이터 객체 생성 성공하였음======================:success");

//                                    FirebaseFirestore db=FirebaseFirestore.getInstance();

                                    Log.d(TAG, "db인스턴스 성공했음 ======================:success");
                                    Log.d(TAG, "child 로 넘겼음 유저데이터 =====================:success");

                                    if(user !=null)
                                        mDatabase.child(user.getUid()).setValue(userdata)
//                                    db.collection("users").document(user.getUid()).set(userdata)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "회원등록 성공");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "회원등록 실패", e);
                                                }
                                            });
                                    goToast("회원가입 성공");
//                                    updateUI(user);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    goToast("이미 존재하는 아이디입니다.");
                                }

                                // ...
                            }
                        });
            } else {
                goToast("비밀번호가 일치하지 않습니다.");
            }
        }
        else{
            goToast("모든항목을 작성해주세요.");
        }



    }
    private void goToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void signTologinActivity(){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }

//    public void updateUI(FirebaseUser account){
//
//        if(account != null){
//            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(this,LoginActivity.class));
//
//        }else {
//            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
//        }
//
//    }
}