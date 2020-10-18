package com.example.leave_a_record;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.leave_a_record.fragment.myHistory;
import com.example.leave_a_record.fragment.tripCourse;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private TextView txt_myHistory, txt_tripCourse; // 내 기록, 여행코스 메뉴버튼을 제어하기 위한 변수
    private LinearLayout fragment_layout;        // 바뀌는 화면을 담당할 변수
    private Button img_add;
    private FragmentManager fragmentManager;            // Framgent 매니저 클래스 변수
    private FragmentTransaction fragmentTransaction;    // Fragment 트랜잭션클래스 변수
//    public post_data_image []pd_data;
//    public ArrayList<Uri> arr_uri;
//    public ArrayList<String> arr_date;
    public ArrayList<post_data_image> pd_datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        txt_myHistory = findViewById(R.id.txt_myHistory);
        txt_tripCourse = findViewById(R.id.txt_tripCourse);
        fragment_layout= findViewById(R.id.fragment_layout);
        img_add=findViewById(R.id.img_add);


//        GridView gridView = (GridView)findViewById(R.id.gridview);
//        gridView.setAdapter(new HistoryListAdapter(this,));

        txt_myHistory.setOnClickListener(new menuClickListener());
        txt_tripCourse.setOnClickListener(new menuClickListener());
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  101);
            }
        });
        // Fragment 정보 초기화
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // "내 기록" Fragment 먼저 보여줌
        myHistory fragment1 = new myHistory();
        fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ExifInterface exif;
//        post_data_image []pd_data;
        String DatetimeArray[];
        Intent intent;
        Uri urione;

        DatetimeArray=new String[2];
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
                                pd_datas.add(new post_data_image(urione.toString(),getDateTime(exif)));
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
                    intent = new Intent(this,test_viewpager2.class);
//                    intent.putExtra("image-data",pd_datas);
//                    for(int j=0;j<pd_data.length;j++) {
//                        to_edit.putExtra("image data - Uri", arr_uri);
//                        to_edit.putExtra("image data - date",arr_date);
//                    }
                    intent.putExtra("pd_datas",pd_datas);
                    Log.d("현재 진행중인 것은", "인텐트로 넘기기전입니다.");
                    startActivity(intent);
                }

                 else if (uri != null) {
                    try{
                        exif = new ExifInterface(getPath(uri));
//                        pd_data[0].setUri(uri);
//                        pd_data[0].setDate_time(getDateTime(exif));

                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }


        }
    }

    private String getDateTime(ExifInterface exif){
        String Datetime = getTagString(ExifInterface.TAG_DATETIME_ORIGINAL,exif);
        return Datetime;
    }
    private String getTagString(String tag,ExifInterface exif){
        return (exif.getAttribute(tag)+"\n");
    }
    private String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String path = null;
        if(cursor!=null) {
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
    private class menuClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            fragmentTransaction = fragmentManager.beginTransaction();
            // Fragment 정보 초기화
            switch (view.getId()){
                // 내 기록을 눌렀을때
                case R.id.txt_myHistory :
                    // 1. Fragment를 대체하고
                    myHistory fragment1 = new myHistory();
                    fragmentTransaction.replace(R.id.fragment_layout, fragment1).commitAllowingStateLoss();

                    // 2. 밑줄을 생성
                    txt_myHistory.setBackground(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.border));
                    txt_tripCourse.setBackgroundColor(Color.parseColor("#ffffff"));
                    break;
                // 코스여행을 눌렀을때
                case R.id.txt_tripCourse :
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
}
