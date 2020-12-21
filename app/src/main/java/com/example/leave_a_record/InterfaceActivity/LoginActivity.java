package com.example.leave_a_record.InterfaceActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.R;
import com.example.leave_a_record.DataBase.Database_M;

public class LoginActivity extends AppCompatActivity {

    Database_M m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);
        findViewById(R.id.login_bt).setOnClickListener(onClickListener);
        findViewById(R.id.loginTosignup_bt).setOnClickListener(onClickListener);
        m=new Database_M();


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
                            intent = new Intent(LoginActivity.this,MainActivity.class);
                            //ProfileActivity로 이동
                            finish();
                            startActivity(intent);

                        }

                    }
                });
    }

    private void loginTosignup() {
        Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
        finish();
        startActivity(intent);


    }
}
