package com.example.leave_a_record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Collections.rotate;


public class SetGallery extends AppCompatActivity {

    ImageView vi; // 갤러리에서 가져온 사진을 띄우는 변수
    TextView mView; // 메타데이터를 띄울 textview


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSelfPermission(); //권한요청

        mView = findViewById(R.id.gps_view); //gps 정보를 띄움
        vi = findViewById(R.id.vi); // 갤러리 사진을 띄움
        vi.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) { //클릭시
                //Intent 를 통해 갤러리의 사진을 가져옴
                Intent image1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //직접고른사진을 MediaStore를 사용해 가져옴
                startActivityForResult(image1, 101);
            }


        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ExifInterface exif;

        Uri uri =data.getData(); //uri , intent변수 data를 geta
            if (requestCode == 101 && resultCode == RESULT_OK) {
            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap bm = BitmapFactory.decodeStream(is);
                try {
                    exif = new ExifInterface(getPath(uri)); //Uri 정보를 String 형식으로 받아 ExifInterface 객체 선언
                    showExif(exif); //사진의 메타데이터를 보여준다
                } catch (IOException e) {
                    e.printStackTrace();
                }
                is.close();
                vi.setImageBitmap(bm);
            } catch (Exception e) { //예의처리
                e.printStackTrace();
            }
        } else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
        }
    }

    //회전 방어 코드//
    public Bitmap getOrientationBitmap(Uri uri, Bitmap bm){
        try {
            ExifInterface exif = new ExifInterface(uri.getPath());
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int exifDegree = exifOrientationToDegrees(exifOrientation);
            bm = rotate(bm, exifDegree);
            return bm;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  int exifOrientationToDegrees(int exifOrientation)
    {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
        {
            return 90;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
        {
            return 180;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
        {
            return 270;
        }
        return 0;
    }

    ///////////////////////// 좌표 받아오는 코드 /////////////////////////
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
    private void showExif(ExifInterface exif) {


        String Latitude =getTagString(ExifInterface.TAG_GPS_LATITUDE, exif);

        String Longitude= getTagString(ExifInterface.TAG_GPS_LONGITUDE, exif);

        mView.setText("위도= "+gps_conventer(Latitude)+"\n"+ "경도= "+ gps_conventer(Longitude));
    }
    private String getTagString(String tag,ExifInterface exif){
        return (exif.getAttribute(tag)+"\n");
    }

    public String getPath(Uri uri){
        String []proj={MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);

        Cursor cursor=cursorLoader.loadInBackground();
        int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(index);
    }






    ////////////////////////////////// 권한 요청 ////////////////////////////////

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode==1){
            int length = permissions.length;
            for(int i=0;i<length; i++){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("MainActivity","권한허용 : "+permissions[i]);
                }
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
}
