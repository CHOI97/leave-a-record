package com.example.leave_a_record.InterfaceActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.leave_a_record.BackPressHandler;
import com.example.leave_a_record.DataBase.DatabaseManagement;
import com.example.leave_a_record.R;
import com.example.leave_a_record.fragment.myHistory;
import com.example.leave_a_record.fragment.tripCourse;
import com.example.leave_a_record.post_data_image;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private TextView txt_myHistory, txt_tripCourse; // 내 기록, 여행코스 메뉴버튼을 제어하기 위한 변수
    private LinearLayout fragment_layout;        // 바뀌는 화면을 담당할 변수
    private Button img_add;
    private Button img_more;
    private FragmentManager fragmentManager;            // Framgent 매니저 클래스 변수
    private FragmentTransaction fragmentTransaction;    // Fragment 트랜잭션클래스 변수
    //    public post_data_image []pd_data;
//    public ArrayList<Uri> arr_uri;
//    public ArrayList<String> arr_date;
    private DatabaseManagement mAuth;
    public ArrayList<post_data_image> pd_datas;
    private BackPressHandler backPressHandler = new BackPressHandler(this);

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("현재 진행중인 것은", "------------프로필페이지.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_profile);
        txt_myHistory = findViewById(R.id.txt_myHistory);
        txt_tripCourse = findViewById(R.id.txt_tripCourse);
        fragment_layout = findViewById(R.id.fragment_layout);
        img_add = findViewById(R.id.img_add);
        img_more = findViewById(R.id.img_more);
        mAuth = new DatabaseManagement();

        Log.d("지금 로그인중인 아이디", mAuth.getFirebaseUser().getUid());

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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


        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("현재 진행중인 것은", "-------------갤러리로 넘기는중입니다.");
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
            }
        });
        // Fragment 정보 초기화
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // "내 기록" Fragment 먼저 보여줌
        myHistory fragment1 = new myHistory();
        fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_profile, menu);

        return true;
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getFirebaseAuth().getCurrentUser();
//        mAuth.getFirebaseAuth().updateUI(currentUser);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ExifInterface exif;
        Intent intent;
        Uri urione;
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {

                //기존 이미지 지우기
//                vi.setImageResource(0);
//                vi2.setImageResource(0);
                //ClipData 또는 Uri를 가져온다
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
                    intent = new Intent(this, edit_viewpager2.class);
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
                // 코스여행을 눌렀을때
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
