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
    private FirebaseAuth mAuth;

    private static final String TAG="SignupActivity";
    private static final String TAG2="Member Activity";
    public EditText id, name, pwd,pwd_c;

    public DatabaseReference mDatabase;
    public FirebaseDatabase database;
    private BackPressHandler backPressHandler = new BackPressHandler(this);
    public Database_M m;

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sign_up);
//        signTologinActivity();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        m=new Database_M();
        findViewById(R.id.signup_bt).setOnClickListener(onClickListener);
        findViewById(R.id.signTologin_bt).setOnClickListener(onClickListener);
        id = findViewById(R.id.signup_id);
        name = findViewById(R.id.signup_name);
        pwd = findViewById(R.id.signup_pw);
        pwd_c=findViewById(R.id.signup_pwd);


        //toolbar
//        myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼
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
                    m.signUp(SignupActivity.this,id.getText().toString(),pwd.getText().toString(),name.getText().toString(),pwd_c.getText().toString(),new Callback<Boolean>(){
                        @Override
                        public void onCallback(Boolean data) {
                            if(data){
                                signTologinActivity();
                            }
                        }
                    });

                    break;
                case R.id.signTologin_bt:
                    signTologinActivity();
                    break;

            }
        }
    };
    //회원가입 확인하기

    private void goToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void signTologinActivity(){

        Intent intent = new Intent (this, LoginActivity.class);
//        Intent intent = new Intent(this, NextActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);

    }
    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
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