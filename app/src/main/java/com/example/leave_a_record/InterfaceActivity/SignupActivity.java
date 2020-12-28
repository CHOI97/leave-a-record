package com.example.leave_a_record.InterfaceActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leave_a_record.BackPressHandler;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
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

    public EditText signup_id, signup_name, signup_pwd,signup_pwd_c;
    public Intent intent;
    private BackPressHandler backPressHandler = new BackPressHandler(this);
    public Database_M m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sign_up);
        m=new Database_M();
        findViewById(R.id.signup_bt).setOnClickListener(onClickListener);
        findViewById(R.id.SignToLogin_bt).setOnClickListener(onClickListener);

        signup_id = findViewById(R.id.signup_id);
        signup_name = findViewById(R.id.signup_name);
        signup_pwd = findViewById(R.id.signup_pw);
        signup_pwd_c=findViewById(R.id.signup_pwd);
        intent =new Intent(SignupActivity.this, LoginActivity.class);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signup_bt:
                    Signup();
                    break;

                case R.id.SignToLogin_bt:
                    startActivity(intent);
                    break;

            }
        }
    };
    public void Signup(){
        final String id=signup_id.getText().toString();
        final String pwd=signup_pwd.getText().toString();
        final String name=signup_name.getText().toString();
        final String pwd_c=signup_pwd_c.getText().toString();

        m.getInstance().signUp(SignupActivity.this,id,pwd,name,pwd_c,new Callback<Boolean>(){
            @Override
            public void onCallback(Boolean data) {
                if(data){
                    startActivity(intent);
                    Toast.makeText(SignupActivity.this, name+"님 회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
    }
}