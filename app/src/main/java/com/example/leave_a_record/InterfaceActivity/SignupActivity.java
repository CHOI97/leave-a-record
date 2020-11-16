package com.example.leave_a_record.InterfaceActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.leave_a_record.BackPressHandler;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private static final String TAG="SignupActivity";
    private static final String TAG2="Member Activity";
    public EditText id, name, pwd,pwd_c;

    public DatabaseReference mDatabase;
    public FirebaseDatabase database;
    private BackPressHandler backPressHandler = new BackPressHandler(this);


    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sign_up);
//        signTologinActivity();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase=database.getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signup_bt).setOnClickListener(onClickListener);
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
            signUp();
        }
    };
    //회원가입 확인하기
    private void signUp() {
        final String email = id.getText().toString();
        final String passwd = pwd.getText().toString();
        final String userName = name.getText().toString();
        String passwd_c=pwd_c.getText().toString();
        Database_M.getInstance().SignUp(this, email, passwd, passwd_c, userName, new Callback<Boolean>(){
            @Override
            public void onCallback(Boolean data) {
                if (data) {
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("회원가입: ","----실패----");
                }
            }
        });

    }
    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
    }
}