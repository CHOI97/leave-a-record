//package com.example.leave_a_record;
//
//import android.Manifest;
//import android.content.ClipData;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//import android.media.ExifInterface;
//import android.net.Uri;
//import android.os.Bundle;
//
//import android.provider.MediaStore;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.annotation.NonNull;
//import androidx.loader.content.CursorLoader;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//import static java.util.Collections.rotate;
//
//
//public class SetGallery extends AppCompatActivity {
//
//    ImageView vi,vi2; // 갤러리에서 가져온 사진을 띄우는 변수
//    TextView mView; // 메타데이터를 띄울 textview
//    TextView mView2; // 메타데이터를 띄울 textview
//    Button btn;
//    TextView course_time;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);  ----------------------수정중------------------
//        checkSelfPermission(); //권한요청
//        course_time=findViewById(R.id.course_time);
//        btn = findViewById(R.id.btn);
//        mView = findViewById(R.id.gps_view); //gps 정보를 띄움
//        mView2 = findViewById(R.id.gps_view2); //gps 정보를 띄움
//        vi = findViewById(R.id.vi); // 갤러리 사진을 띄움
//        vi2= findViewById(R.id.vi2);
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) { //클릭시
////                Intent 를 통해 갤러리의 사진을 가져옴
////
////                Intent intent= new Intent(Intent.ACTION_PICK);
////                intent.setType(MediaStore.Images.Media.CONTENT_TYPE); //직접고른사진을 MediaStore를 사용해 가져옴
////                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
////                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                startActivityForResult(intent, 101);
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//                //사진을 여러개 선택할수 있도록 한다
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  101);
//            }
//
//
//        });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        ExifInterface exif;
//        String DatetimeArray[];
//        DatetimeArray=new String[2];
//        if (requestCode == 101) {
//            if (resultCode == RESULT_OK) {
//
//                //기존 이미지 지우기
//                vi.setImageResource(0);
//                vi2.setImageResource(0);
//                //ClipData 또는 Uri를 가져온다
//                Uri uri = data.getData();
//                ClipData clipData = data.getClipData();
//
//                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
//                if (clipData != null) {
//
//                    for (int i = 0; i < 10; i++) {
//                        if (i < clipData.getItemCount()) {
//                            Uri urione = clipData.getItemAt(i).getUri();
//                            switch (i) {
//                                case 0:
//                                    vi.setImageURI(urione);
//                                    try{
//                                        exif=new ExifInterface(getPath(urione));
//                                        DatetimeArray[0]=getDateTime(exif);
//                                        showExif(exif,mView);
//                                    }catch(Exception e)
//                                    {
//                                        e.printStackTrace();
//                                    }
//
//                                    break;
//                                case 1:
//                                    vi2.setImageURI(urione);
//
//                                    try{
//                                        exif=new ExifInterface(getPath(urione));
//                                        DatetimeArray[1]=getDateTime(exif);
//                                        showExif(exif,mView2);
//                                    }catch(Exception e)
//                                    {
//                                        e.printStackTrace();
//                                    }
//
//                                    break;
//                                }
//                        }
//                    }
//                    course_time.setText(CourseTime(time_conventer(DatetimeArray[0]),time_conventer(DatetimeArray[1])));
//
//                } else if (uri != null) {
//                    vi.setImageURI(uri);
//                    try{
//                        exif=new ExifInterface(getPath(uri));
//                        showExif(exif,mView);
//
//                    }catch(Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//
//        }
//    }
//
//
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
//////        CheckofExif(data.getData());
////        if (requestCode == 101 && resultCode == RESULT_OK) {
////            try {
////                InputStream is = getContentResolver().openInputStream(data.getData());
////                Bitmap bm = BitmapFactory.decodeStream(is);
////
//////                try {
//////                    exif = new ExifInterface(getPath(uri)); //Uri 정보를 String 형식으로 받아 ExifInterface 객체 선언
//////                    showExif(exif); //사진의 메타데이터를 보여준다
//////                } catch (IOException e) {
//////                    e.printStackTrace();
//////                    Toast.makeText(this, "메타데이터가 존재하지않습니다.", Toast.LENGTH_SHORT).show();
//////                }
////                is.close();
////                vi.setImageBitmap(bm);
////            } catch (Exception e) { //예외처리
////                e.printStackTrace();
////            }
////        } else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
////            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
////        }
////    }
//
////    public void CheckofExif(Uri uri,TextView view){
////        ExifInterface exif;
////        try{
////            exif=new ExifInterface(getPath(uri));
////            showExif(exif,view);
////        }catch(Exception e)
////        {
////            e.printStackTrace();
////        }
//
//
//    //회전 방어 코드//
////    public Bitmap getOrientationBitmap(Uri uri, Bitmap bm){
////        try {
////            ExifInterface exif = new ExifInterface(uri.getPath());
////            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
////            int exifDegree = exifOrientationToDegrees(exifOrientation);
////            bm = rotate(bm, exifDegree);
////            return bm;
////        }
////        catch (IOException e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
////
////    public  int exifOrientationToDegrees(int exifOrientation)
////    {
////        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
////        {
////            return 90;
////        }
////        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
////        {
////            return 180;
////        }
////        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
////        {
////            return 270;
////        }
////        return 0;
////    }
//
//    ///////////////////////// 좌표 받아오는 코드 /////////////////////////
//    public String gps_conventer(String tag){
//        String s1 =tag;
//        String s2;
//        String[] s3;
//        double StrTemp=0;
//        String result; //반환
//
//        s2 = s1.replace("/1,",".");
//        tag = s2.replace ("/100","");//문자열 필요없는 부분삭제
//
//
//        s3=tag.split("\\.");//문자열 분리 각 배열 3개의 각각 들어있음
//
//        // dms to dd
//        StrTemp+=Double.parseDouble(s3[0]);
//        StrTemp+=(Double.parseDouble(s3[1]))/60;
//        StrTemp+=(Double.parseDouble(s3[2]))/360000;
//
//        result =(String.format("%.5f",StrTemp)); //Double to String
//        return result;
//    }
//    public String time_conventer(String tag){
//        String s1 =tag;
//
//        tag = s1.replaceAll("[^0-9}]"," ");//문자열 필요없는 부분삭제
//
//        return tag;
//    }
//
//    public String CourseTime(String tag1,String tag2){
//        int courseTime[];
//        int temp1,temp2;
//        courseTime =new int[6];
//        //String 타입을 년월일시분초로 나눈것
//        String DateAndTime1[]=tag1.split(" ",6);
//        String DateAndTime2[]=tag2.split(" ",6);
//        //상수형 시간 나눈것들
//        DateAndTime1[5]=DateAndTime1[5].replace(" ","");
//        DateAndTime2[5]=DateAndTime2[5].replace(" ","");
//        int TimeToInteger1[]=new int[DateAndTime1.length];
//        int TimeToInteger2[]=new int[DateAndTime1.length];
//        for(int i=0; i<6;i++){
//            temp1=Integer.parseInt(DateAndTime1[i]);
//            TimeToInteger1[i]=temp1;
//            temp2=Integer.parseInt(DateAndTime2[i]);
//            TimeToInteger2[i]=temp2;
//        }
//        for(int j=0;j<5;j++){
//            courseTime[j]=TimeToInteger1[j]+TimeToInteger2[j];
//            courseTime[j]=TimeToInteger1[j]-TimeToInteger2[j];
//            if(courseTime[j]>0){
//                continue;
//            }
//            else if(courseTime[j]<0) {
//                if(j>=4) {
//                    courseTime[j - 1] += -1;
//                    courseTime[j] += 60;
//                }
//                else if (j==3){
//                    courseTime[j-1] += -1;
//                    courseTime[j] +=24;
//                }
//            }
//        }
//
//        //AllcourseTime Intager 배열합친거.
//        return CourseTime_converter(courseTime);
//    }
//    public String CourseTime_converter(int []courseTime){
//        String CourseTime="";
//        for(int i=0;i<6;i++){
//            switch(i){
//                case 0:
//                    CourseTime+=(String.format("%d",courseTime[i])+"년");
//                    break;
//                case 1:
//                    CourseTime+=(String.format("%d",courseTime[i])+"월");
//                    break;
//                case 2:
//                    CourseTime+=(String.format("%d",courseTime[i])+"일");
//                    break;
//                case 3:
//                    CourseTime+=(String.format("%d",courseTime[i])+"시간");
//                    break;
//                case 4:
//                    CourseTime+=(String.format("%d",courseTime[i])+"분");
//                    break;
//                case 5:
//                    CourseTime+=(String.format("%d",courseTime[i])+"초");
//                    break;
//
//
//            }
//
//
//        }
//        return CourseTime+"걸렸습니다";
//    }
//
//    public String getDateTime(ExifInterface exif){
//        String Datetime = getTagString(ExifInterface.TAG_DATETIME_ORIGINAL,exif);
//        return Datetime;
//    }
//
//
//    private void showExif(ExifInterface exif,TextView View) {
//
//
//        String Latitude =getTagString(ExifInterface.TAG_GPS_LATITUDE, exif);
//        String Longitude= getTagString(ExifInterface.TAG_GPS_LONGITUDE, exif);
//        String Datetime = getTagString(ExifInterface.TAG_DATETIME_ORIGINAL,exif);
//
//        View.setText("위도= "+gps_conventer(Latitude)+"\n"+ "경도= "+ gps_conventer(Longitude)+"\n"+"시간="+time_conventer(Datetime));
//    }
//    private String getTagString(String tag,ExifInterface exif){
//        return (exif.getAttribute(tag)+"\n");
//    }
//
////    public String getPath(Uri uri){
////        String []proj={MediaStore.Images.Media.DATA};
////        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);
////
////        Cursor cursor=cursorLoader.loadInBackground();
////        int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
////
////        cursor.moveToFirst();
////        return cursor.getString(index);
////    }
//public String getPath(Uri uri) {
//
//    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//    String path = null;
//    if(cursor!=null) {
//        if (cursor.moveToFirst()) {
//            String document_id = cursor.getString(0);
//            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//            cursor.close();
//
//            cursor = getContentResolver().query(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//            cursor.moveToFirst();
//            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            cursor.close();
//
//            return path;
//        }
//    }
//    return path;
//}
//
//
//
//
//
//    ////////////////////////////////// 권한 요청 ////////////////////////////////
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        if(requestCode==1){
//            int length = permissions.length;
//            for(int i=0;i<length; i++){
//                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
//                    Log.d("MainActivity","권한허용 : "+permissions[i]);
//                }
//            }
//        }
//    }
//    public void checkSelfPermission(){
//        String temp=" ";
//        if(ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
//        }
//
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
//        }
//        if (TextUtils.isEmpty(temp) == false) {
//            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
//        }
//        else {
//            Toast.makeText(this, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
//        }
//    }
//}