//package com.example.dodam.login;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.dodam.R;
//import com.example.dodam.data.UserData;
//import com.example.dodam.database.Callback;
//import com.example.dodam.database.DatabaseManagement;
//
//import java.util.ArrayList;
//
//public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
//    private int birthYear, birthMonth, birthDayOfMonth; // 생년,월,일
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        initialize();
//    }
//
//    // 필요한 항목 초기화
//    private void initialize() {
//        ImageView exitIV;
//        TextView completeTV;
//        EditText birthDayET, genderET;
//
//        ///////////////////////////////////////////////////////////////
//        // TextView 설정
//        exitIV      = findViewById(R.id.signUp_exit);
//        completeTV  = findViewById(R.id.signUp_completeTV);
//        ///////////////////////////////////////////////////////////////
//
//        ///////////////////////////////////////////////////////////////
//        // EditText 설정
//        birthDayET  = findViewById(R.id.signUp_birthDayET);
//        genderET    = findViewById(R.id.signUp_genderET);
//        ///////////////////////////////////////////////////////////////
//
//        ///////////////////////////////////////////////////////////////
//        // Click Listener 추가
//        exitIV.setOnClickListener(this);
//        completeTV.setOnClickListener(this);
//
//        birthDayET.setOnClickListener(this);
//        genderET.setOnClickListener(this);
//        ///////////////////////////////////////////////////////////////
//    }
//
//    @Override
//    public void onClick(View v) {
//        Intent intent;
//
//        switch(v.getId()) {
//            // exit
//            case R.id.signUp_exit:
//                finish();
//
//                break;
//
//            // 완료`
//            case R.id.signUp_completeTV:
//                // 회원가입
//                signUp();
//
//                break;
//
//            // 생년월일
//            case R.id.signUp_birthDayET:
//                // Dialog 띄우기
//                showBirthDayDialog();
//
//                break;
//
//            // 성별
//            case R.id.signUp_genderET:
//                // Dialog 띄우기
//                showGenderDialog();
//        }
//    }
//
//    // 회원가입
//    private void signUp() {
//        EditText emailET, passwordET, nameET, birthDayET, genderET;
//        final String email, password, name, birthDay, gender;
//
//        emailET     = findViewById(R.id.signUp_emailET);
//        passwordET  = findViewById(R.id.signUp_passwordET);
//        nameET      = findViewById(R.id.signUp_nameET);
//        birthDayET  = findViewById(R.id.signUp_birthDayET);
//        genderET    = findViewById(R.id.signUp_genderET);
//
//        // 텍스트 가져오기
//        email       = emailET.getText().toString();
//        password    = passwordET.getText().toString();
//        name        = nameET.getText().toString();
//        birthDay    = birthDayET.getText().toString();
//        gender      = genderET.getText().toString();
//
//        // 모든 항목이 입력되었는지 확인
//        if(!(email.equals("") || password.equals("") || name.equals("") || birthDay.equals("") || gender.equals(""))) {
//            // 비밀번호가 6자리 이상일 때만
//            if(password.length() >= 6) {
//                final UserData userData;  // 사용자 데이터 생성
//                int userAge;        // 사용자 나이
//                boolean userGender; // 사용자 성별
//                final Context context;
//
//                context = this;
//
//                // 생년월일로 부터 사용자 나이 구하기
//                userAge = UserData.getAgeFromBirthDay(birthDay);
//
//                // 성별 문자열로 부터 boolean값 구하기
//                userGender = UserData.getGenderFromString(gender);
//
//                // 사용자 데이터 생성
//                userData = new UserData(email, password, name, userAge, userGender);
//
//                // 파이어베이스 인증정보에 사용자 추가 및 DB에 등록
//                DatabaseManagement.getInstance().signUpEmail(userData, new Callback<Boolean>() {
//                    @Override
//                    public void onCallback(Boolean data) {
//                        if(data) {
//                            Toast.makeText(context, "회원가입에 성공했어요.", Toast.LENGTH_SHORT).show();
//
//                            finish();
//                        } else {
//                            Toast.makeText(context, "회원가입에 실패했어요.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            } else {
//                Toast.makeText(SignUpActivity.this, "비밀번호는 6자리 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            // 하나라도 비었으면 메시지 출력
//            Toast.makeText(SignUpActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    // 생년월일 Dialog 띄우기
//    private void showBirthDayDialog() {
//        AlertDialog alertDialog;
//        final DatePicker datePicker;
//        View dialogView;
//        AlertDialog.Builder builder;
//
//        dialogView = getLayoutInflater().inflate(R.layout.date_picker_dialog, null);
//        datePicker = dialogView.findViewById(R.id.datePicker);
//
//        builder = new AlertDialog.Builder(this);
//
//        builder.setView(dialogView);
//        builder.setTitle("생년월일 설정");
//
//        // 확인 버튼
//        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                EditText birthDayET;
//
//                // 년,월,일 가져오기
//                birthYear = datePicker.getYear();
//                birthMonth = datePicker.getMonth() + 1;
//                birthDayOfMonth = datePicker.getDayOfMonth();
//
//                // 년, 월, 일을 생년월일 EditText에 넣어주기
//                birthDayET = findViewById(R.id.signUp_birthDayET);
//                birthDayET.setText(birthYear + "년 " + birthMonth + "월 " + birthDayOfMonth + "일생");
//
//            }
//        });
//
//        // 취소 버튼
//        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//
//        // dialog 띄우기
//        alertDialog = builder.create();
//        alertDialog.show();
//    }
//
//    // DatePickerDialog 날짜 설정 Listener(선택되었을 때 값을 가져옴)
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        EditText birthDayET;
//
//        // 년, 월, 일을 생년월일 EditText에 넣어주기
//        birthDayET = findViewById(R.id.signUp_birthDayET);
//        birthDayET.setText(year + "년 " + month + "월 " + dayOfMonth + "일");
//    }
//
//    // 생년월일 Dialog 띄우기
//    private void showGenderDialog() {
//        AlertDialog alertDialog;
//        AlertDialog.Builder builder;
//        final String[] items;
//        final ArrayList<String> selectedItem;
//
//        // item으로 등록할 항목 string array에서 가져오기
//        items = getResources().getStringArray(R.array.GENDER_CONDITION);
//
//        selectedItem = new ArrayList<String>();
//
//        // 첫 항목이 선택되어 있게 하기
//        selectedItem.add(items[0]);
//
//        builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("성별 설정");
//
//        // Item 선택 이벤트
//        builder.setSingleChoiceItems(R.array.GENDER_CONDITION, 0, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int pos) {
//                selectedItem.clear();
//                selectedItem.add(items[pos]);
//            }
//        });
//
//        // 확인 버튼
//        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int pos) {
//                EditText genderET;
//
//                // 성별 설정 EditText 가져오고 선택한 항목으로 텍스트 변경
//                genderET = findViewById(R.id.signUp_genderET);
//                genderET.setText(selectedItem.get(0));
//            }
//        });
//
//        // 취소 버튼
//        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int pos) {
//                System.out.println("성별 설정 취소");
//            }
//        });
//
//        // dialog 띄우기
//        alertDialog = builder.create();
//        alertDialog.show();
//    }
//}