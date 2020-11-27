package com.example.leave_a_record.InterfaceActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leave_a_record.BackPressHandler;
import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.example.leave_a_record.DataBase.Database_M;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; //인증객체
    private BackPressHandler backPressHandler = new BackPressHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.login_bt).setOnClickListener(onClickListener);
        findViewById(R.id.loginTosignup_bt).setOnClickListener(onClickListener);
        Handler hand = new Handler();


    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_bt:
                    signIn();
                    break;
                case R.id.loginTosignup_bt:
                    loginTosignup();
                    break;
            }
        }
    };
    private void signIn() {
        final String email = ((EditText) findViewById(R.id.login_id)).getText().toString();
        final String password = ((EditText) findViewById(R.id.login_pw)).getText().toString();
        // 로그인 인증하기
        Database_M.getInstance().SignInEmail(this,email,password
                , new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean data) {
                        // 로그인 작업 성공
                        if(data) {
                            Intent intent;
                            intent = new Intent(LoginActivity.this,ProfileActivity.class);
                            //ProfileActivity로 이동
                            startActivity(intent);
                            finish();
                        }

                    }
                });
    }
    private void loginTosignup() {
        Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
        finish();
        startActivity(intent);


    }

    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
    }
}
