package com.example.leave_a_record.InterfaceActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

//import java.io.Serializable;
import com.example.leave_a_record.Adapter.USERAdapter;
import com.example.leave_a_record.BackPressHandler;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.DataBase.PostData_image;
import com.example.leave_a_record.R;
import com.example.leave_a_record.image_edit_data;
import com.example.leave_a_record.DataBase.UserData;
import com.example.leave_a_record.post_data_image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.example.leave_a_record.DataBase.DatabaseManagement;
import com.google.protobuf.StringValue;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class edit_viewpager2 extends AppCompatActivity  {
    ViewPager2 viewPager2;
    USERAdapter userAdapter;
    Button save_content;
    TextView current_time;
    ArrayList<post_data_image> pd_datas_receive;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    DatabaseReference mdatabase;

    List<image_edit_data> imageditdataList;
    List<String> post_images_URI;

    List<String> post_meta_datetime;
    List<String> post_pin;
    List<String> post_meta_gps_Latitue;
    List<String> post_meta_gps_Longitude;
    EditText content;
    String content_data;
    EditText title;
    String title_data;
    PostData postData;
    PostData_image postData_image;
//    DatabaseManagement db
    private UserData userdata;
    private BackPressHandler backPressHandler = new BackPressHandler(this);

//    ArrayList<post_data_image> list =(ArrayList<post_data_image>)intent.getSerializableExtra("image-data");    ----오류-----

    protected void onCreate(Bundle savedInstanceState) {
        final UserData userData;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_viewpager_editpage);
        title=findViewById(R.id.edit_title);
        content=findViewById(R.id.edit_content);
        save_content=findViewById(R.id.save);
        current_time=findViewById(R.id.edit_date_time);
        pd_datas_receive = (ArrayList<post_data_image>)getIntent().getSerializableExtra("pd_datas");
        viewPager2 = findViewById(R.id.viewpager2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); /////////키보드 가림방지
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat post_upload_time_simple =new SimpleDateFormat("yyyy년 MM월 dd일의 기록");
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        String current_post_Time = simpleDate.format(mDate); //포스트용  시간
        String post_upload_time = post_upload_time_simple.format(mDate); // 게시물등록 고유 시간
        current_time.setText(post_upload_time);

        imageditdataList = new ArrayList<>(); //이미지를 위한 리스트
        post_images_URI = new ArrayList<>();
        post_meta_gps_Latitue=new ArrayList<>();
        post_meta_gps_Longitude=new ArrayList<>();
        post_meta_datetime=new ArrayList<>();
        post_pin = new ArrayList<>(); // ok
        userdata=new UserData();
////////////////////////////////////////////////////////////////////////////////////
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user =  mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mdatabase=database.getReference();
        mDatabase=database.getReference().child("posts").child(mAuth.getCurrentUser().getUid()).child(current_post_Time);
        //데이터 필드 posts -> uid -> current time(게시물들) -> (게시물내용)child(uri,content,pin), title,datetime
////////////////////////////////////////////////////////////////////////////////////

        for(int i=0;i<pd_datas_receive.size();i++){
            imageditdataList.add(new image_edit_data(Uri.parse(pd_datas_receive.get(i).getUri())));
            Log.d("현재 진행중인 것은", "Uri를 성공적으로 받았습니다.");
        }

        userAdapter =  new USERAdapter(this, imageditdataList);
        viewPager2.setAdapter(userAdapter);

        save_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "업로드중", Toast.LENGTH_SHORT).show();
                Log.d("현재 진행중인것은 ", "게시글작성중");
                if (title.getText().toString() != null) {
                    title_data = title.getText().toString();
                } else {
                    title_data = "기록일지";
                }
                if (content.getText().toString() != null) {
                    content_data = content.getText().toString();
                } else {
                    content_data = " ";
                }
                for (int i = 0; i < imageditdataList.size(); i++) {
                    uploadFile(i);
                }

                for (int i = 0; i < pd_datas_receive.size(); i++) {  //post_data에 넣기전 처리과정
                    if (pd_datas_receive.get(i).getData_gps_Latitude() != null) {
                        post_meta_gps_Latitue.add(pd_datas_receive.get(i).getData_gps_Latitude());
                    } else {
                        post_meta_gps_Latitue.add(" ");
                    }
                    if (pd_datas_receive.get(i).getData_gps_Longitude() != null) {
                        post_meta_gps_Longitude.add(pd_datas_receive.get(i).getData_gps_Longitude());
                    } else {
                        post_meta_gps_Latitue.add(" ");
                    }
                }
                autopin(post_meta_gps_Latitue, post_meta_gps_Longitude, post_pin);
                postData = new PostData(mAuth.getUid(), title_data);
                postData_image = new PostData_image(post_images_URI, content_data, post_meta_gps_Latitue, post_meta_gps_Longitude, post_meta_datetime, post_pin);


                mDatabase.child("postData").setValue(postData);
                mDatabase.child("postData_image").setValue(postData_image);
                goProfile();

            }
        });
    }
    private void uploadFile(int i) {

        Uri FilePath=Uri.parse(pd_datas_receive.get(i).getUri());
        String user_name= mAuth.getCurrentUser().getUid();
        Log.d("넘긴 아이디값은: ", user_name);
        String File_tag=Integer.toString(i);

        if (FilePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("기록을 남기는중입니다...");
            progressDialog.show();
//////////////// storage 와 cloud의 동시에 저장하자 . /////////////////////////////////
            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yymmss"+File_tag);
            Date now = new Date();
            String filename = formatter.format(now) +user_name+ ".jpg";
            post_images_URI.add("images/"+filename);
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://leave-a-record.appspot.com").child("images/" + filename);
            //올라가거라...
            storageRef.putFile(FilePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           // 스토리지 url -> post로 넘기기
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "기록 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
//                    진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("기록 작성중.. " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }
//    private void uploadFile(int i) {
//
//        Uri FilePath=Uri.parse(pd_datas_receive.get(i).getUri());
//        String user_name= mAuth.getCurrentUser().getUid();
//        Log.d("넘긴 아이디값은: ", user_name);
//        String File_tag=Integer.toString(i);
//        Toast.makeText(getApplicationContext(), "업로드중", Toast.LENGTH_SHORT).show();
//        if (FilePath != null) {
//            //업로드 진행 Dialog 보이기
//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("기록을 남기는중입니다...");
//            progressDialog.show();
////////////////// storage 와 cloud의 동시에 저장하자 . /////////////////////////////////
//            //storage
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//
//            //Unique한 파일명을 만들자.
//            SimpleDateFormat formatter = new SimpleDateFormat("yymmss"+File_tag);
//            Date now = new Date();
//            String filename = formatter.format(now) +user_name+ ".jpg";
//            //storage 주소와 폴더 파일명을 지정해 준다.
//            StorageReference storageRef = storage.getReferenceFromUrl("gs://leave-a-record.appspot.com").child("images/" + filename);
//            //올라가거라...
//            storageRef.putFile(FilePath)
//                    //성공시
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
//                            Toast.makeText(getApplicationContext(), "기록을 남겼습니다!", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    //실패시
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            progressDialog.dismiss();
//                            Toast.makeText(getApplicationContext(), "기록 실패!", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    //진행중
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
//                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
//                            //dialog에 진행률을 퍼센트로 출력해 준다
//                            progressDialog.setMessage("기록 작성중.. " + ((int) progress) + "% ...");
//                        }
//                    });
//        } else {
//            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
//        }
//    }
    public void autopin(List<String> lat ,List<String> lon,List<String> pin){
        int i;
        double sum=0;
        double arange;
        int num=1;
        pin.add("pin"+ String.valueOf(num));
        for(i=1;i<lat.size();i++){
            sum+=distance(pTos(lat.get(i-1)),pTos(lon.get(i-1)),pTos(lat.get(i)),pTos(lon.get(i)));
            arange=distance(pTos(lat.get(i-1)),pTos(lon.get(i-1)),pTos(lat.get(i)),pTos(lon.get(i)));
            if(arange>100) {
                num++;
                pin.add("pin" + String.valueOf(num));
            }else{
                pin.add("pin" + String.valueOf(num));
            }
        }
        sum=sum/lat.size();
    }
    public double pTos(String s){
        double D=Double.parseDouble(s);
        return D;
    }
    public String dTos(double d){
        String S=Double.toString(d);
        return S;
    }

    public double distance(double lat1,double lon1,double lat2,double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = dist * 1609.344;
        return dist;
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
    private void goProfile(){
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
    }
}
