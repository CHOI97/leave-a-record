package com.example.leave_a_record.InterfaceActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.leave_a_record.BackPressHandler;

import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.R;
import com.example.leave_a_record.fragment.myHistory;
import com.example.leave_a_record.fragment.tripCourse;
import com.example.leave_a_record.post_data_image;
import com.google.firebase.auth.FirebaseAuth;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private LinearLayout txt_myHistory, txt_tripCourse; // 내 기록, 여행코스 메뉴버튼을 제어하기 위한 변수
    private LinearLayout fragment_layout;        // 바뀌는 화면을 담당할 변수

    private FragmentManager fragmentManager;            // Framgent 매니저 클래스 변수
    private FragmentTransaction fragmentTransaction;    // Fragment 트랜잭션클래스 변수\
    private TextView Textname,Textabout;
    private FirebaseAuth mAuth;
    public ArrayList<post_data_image> pd_datas;
    private BackPressHandler backPressHandler = new BackPressHandler(this);
    private Database_M m;
    private RoundedImageView profile_image;

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("현재 진행중인 것은", "------------프로필페이지.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_profile);

//        checkSystemPermission();
        txt_myHistory = findViewById(R.id.txt_myHistory);
        txt_tripCourse = findViewById(R.id.txt_tripCourse);
        fragment_layout = findViewById(R.id.fragment_layout);
        profile_image=findViewById(R.id.img_profile);
        mAuth = FirebaseAuth.getInstance();
        Textname= findViewById(R.id.text_username);
        Textabout=findViewById(R.id.text_about1);
        m=new Database_M();



        Log.d("지금 로그인중인 아이디", mAuth.getCurrentUser().getUid());

        //toolbar
        Toolbar toolbar =findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //유저 프로필 이미지 동기화
        m.SingleImageUri(m.getmAuth().getUid(), new Callback<Uri>() {
            @Override
            public void onCallback(Uri data) {
                if(!data.equals(null)){
                    Glide.with(ProfileActivity.this).load(data).override(200,200).centerCrop().into(profile_image);
                }
                else{
                    Log.d("현재 이 아이디의 프로필사진은"," 없습니다");
                }

            }
        });
        //이름 동기화
        m.userName(new Callback<String>() {
            @Override
            public void onCallback(String data) {
                Textname.setText(data);
            }
        });
        //상태메세지 동기화
        m.userAbout(new Callback<String>() {
            @Override
            public void onCallback(String data) {
                Textabout.setText(data);
            }
        });

//        img_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("현재 진행중인 것은", "-------------갤러리로 넘기는중입니다.");
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//                //사진을 여러개 선택할수 있도록 한다
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
//            }
//        });

//        GridView gridView = (GridView)findViewById(R.id.gridview);
//        gridView.setAdapter(new HistoryListAdapter(this,));

        txt_myHistory.setOnClickListener(new menuClickListener());
        txt_tripCourse.setOnClickListener(new menuClickListener());
        //--------------- 메뉴 팝업 부분 -----------------//
//        img_more.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Log.d("팝업메뉴","=======눌렸음.");
//                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미
//                Log.d("팝업메뉴","=======생성중");
//                getMenuInflater().inflate(R.menu.menu_all, popup.getMenu());
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()){
//                            case R.id.Logout:
//                                Toast.makeText(getApplication(),"로그아웃",Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.settings:
//                                Toast.makeText(getApplication(),"설정",Toast.LENGTH_SHORT).show();
//                                break;
//                            default:
//                                break;
//                        }
//                        return false;
//                    }
//                });
//
//            }
//        });



//       img_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("현재 진행중인 것은", "-------------갤러리로 넘기는중입니다.");
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//                //사진을 여러개 선택할수 있도록 한다
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
//            }
//        });


        // Fragment 정보 초기화
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // "내 기록" Fragment 먼저 보여줌
        tripCourse fragment1 = new tripCourse();
        fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_profile, menu);

        return true;
    }
//    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode == 1){
//            int length = permissions.length;
//            for (int i = 0; i < length; i++) {
//                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
//                { // 동의
//                    Log.d("MainActivity","권한 허용 : " + permissions[i]);
//                }
//            }
//        }
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼
                onBackPressed();
                return true;
            case R.id.tool_image:
                if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
                    Intent intent = new Intent(Intent. ACTION_GET_CONTENT , android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                //사진을 여러개 선택할수 있도록 한다
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
                    break;
                }

            case R.id.tool_edit:
                Intent intent_edit;
                intent_edit=new Intent(ProfileActivity.this,Edit_ProfileActivity.class);
                startActivity(intent_edit);
                break;
            case R.id.tool_logout:
                m.SignOut();
                Intent intent_logout=new Intent(ProfileActivity.this,LoginActivity.class);
                finish();
                startActivity(intent_logout);
                goToast("로그아웃 되었습니다.");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ExifInterface exif;
        Intent intent;
        Uri urione;
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData(); //-----------이게 필요한가?..
                ClipData clipData = data.getClipData();
//                pd_data=new post_data_image[clipData.getItemCount()];-------일단보류


//               try{            -------------------------------보류중 오류없음.
//                    pd_datas = new ArrayList<post_data_image>(clipData.getItemCount());
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
                pd_datas = new ArrayList<post_data_image>();
                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {

                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        if (i < clipData.getItemCount()) {
                            try {
                                urione = clipData.getItemAt(i).getUri();
                                exif = new ExifInterface(getPath(urione));
                                pd_datas.add(new post_data_image(urione.toString(), getDateTime(exif),getGPS_Latitude(exif),getGps_Longitude(exif)));
//                                arr_uri.add(urione);
//                                arr_date.add(getDateTime(exif));
//                                pd_data[i].setUri(urione);
//                                pd_data[i].setDate_time(getDateTime(exif));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Log.d("현재 진행중인 것은", "인텐트로 넘기기전입니다.");
                    intent = new Intent(this, EditActivity.class);
//                    intent.putExtra("image-data",pd_datas);
//                    for(int j=0;j<pd_data.length;j++) {
//                        to_edit.putExtra("image data - Uri", arr_uri);
//                        to_edit.putExtra("image data - date",arr_date);
//                    }
                    intent.putExtra("pd_datas", pd_datas);
                    Log.d("현재 진행중인 것은", "인텐트로 넘기기전입니다.");

                    startActivity(intent);
                } else if (uri != null) {
                    try {
                        goToast("여러장의 이미지를 선택해주세요");  // 일단 여러이미지만 받는걸로 수정.

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


        }
    }
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(ProfileActivity.this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }
    private String getDateTime(ExifInterface exif) {
        String Datetime = getTagString(ExifInterface.TAG_DATETIME_ORIGINAL, exif);
        return Datetime;
    }

    private String getTagString(String tag, ExifInterface exif) {
        return (exif.getAttribute(tag) + "\n");
    }

    private String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String path = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String document_id = cursor.getString(0);
                document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
                cursor.close();
                cursor = getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
                cursor.moveToFirst();
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
                return path;
            }
        }
        return path;
    }

    // 내 기록, 여행코스를 눌렀을때 이벤트 리스너
    private class menuClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            fragmentTransaction = fragmentManager.beginTransaction();
            // Fragment 정보 초기화
            switch (view.getId()) {
                // 내 기록을 눌렀을때
                case R.id.txt_myHistory:
                    // 1. Fragment를 대체하고
                    myHistory fragment1 = new myHistory();
                    fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();

                    // 2. 밑줄을 생성
                    txt_myHistory.setBackground(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.border));
                    txt_tripCourse.setBackgroundColor(Color.parseColor("#ffffff"));
                    break;
//                 코스여행을 눌렀을때
                case R.id.txt_tripCourse:
                    // 1. Fragment를 대체하고
                    txt_myHistory.setBackgroundColor(Color.parseColor("#ffffff"));
                    txt_tripCourse.setBackground(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.border));

                    // 2. 밑줄을 생성
                    tripCourse fragment2 = new tripCourse();
                    Log.d("success", "tripCourse case:success"); //로그찍기
                    fragmentTransaction.replace(R.id.fragment_layout, fragment2).commitAllowingStateLoss();
                    break;
            }
        }
    }
    public void checkSelfPermission(){
        String temp=" ";
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }
        if (TextUtils.isEmpty(temp) == false) {
            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
        }
        else {
            Toast.makeText(this, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home: //뒤로가기 버튼
//                onBackPressed();
//                return true;
//
//            case R.id.tool_edit: //수정하기 버튼
//
//            case R.id.tool_add: //추가하기 버튼
//
//            case R.id.tool_logout: //로그아웃 버튼
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_profile, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    public boolean checkSystemPermission() {

        boolean permission = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //23버전 이상
            permission = Settings.System.canWrite(this);
            Log.d("test", "Can Write Settings: " + permission);
            if(permission){
                Log.e("test", "허용");
            }else{
                Log.e("test", "불허용");
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 2127);
                permission = false;
            }
        } else {

        }

        return permission;
    }




    private void goToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private String getGPS_Latitude(ExifInterface exif) {
        String Latitude =getTagString(ExifInterface.TAG_GPS_LATITUDE, exif);
        String Longitude= getTagString(ExifInterface.TAG_GPS_LONGITUDE, exif);
//        String Datetime = getTagString(ExifInterface.TAG_DATETIME_ORIGINAL,exif);
        return  gps_conventer(Latitude);
    }
    private String getGps_Longitude(ExifInterface exif){
        String Longitude= getTagString(ExifInterface.TAG_GPS_LONGITUDE, exif);
        return gps_conventer(Longitude);
    }


    public String gps_conventer(String tag){
        String s1 =tag;
        String s2;
        String[] s3;
        double StrTemp=0;
        String result; //반환

        s2 = s1.replace("/1,",".");
        tag = s2.replace ("/100","");//문자열 필요없는 부분삭제


        s3=tag.split("\\.");//문자열 분리 각 배열 3개의 각각 들어있음

        // dms to dd
        StrTemp+=Double.parseDouble(s3[0]);
        StrTemp+=(Double.parseDouble(s3[1]))/60;
        StrTemp+=(Double.parseDouble(s3[2]))/360000;

        result =(String.format("%.5f",StrTemp)); //Double to String
        return result;
    }
    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
    }
//    public String time_conventer(String tag){
//        String s1 =tag;
//
//        tag = s1.replaceAll("[^0-9}]"," ");//문자열 필요없는 부분삭제
//
//        return tag;
//    }
}

