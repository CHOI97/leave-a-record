package com.example.leave_a_record.InterfaceActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

//import java.io.Serializable;
import com.example.leave_a_record.Adapter.USERAdapter;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.R;
import com.example.leave_a_record.image_edit_data;
import com.example.leave_a_record.DataBase.UserData;
import com.example.leave_a_record.post_data_image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.example.leave_a_record.DataBase.DatabaseManagement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class edit_viewpager2 extends AppCompatActivity  {
    ViewPager2 viewPager2;
    List<image_edit_data> imageditdataList;
    USERAdapter userAdapter;
    Button save_content;
    ArrayList<String> contentList;
    PostData postData;
    ArrayList<post_data_image> pd_datas_receive;
    DatabaseManagement mAuth;
//    DatabaseManagement db

//    ArrayList<post_data_image> list =(ArrayList<post_data_image>)intent.getSerializableExtra("image-data");    ----오류-----

    protected void onCreate(Bundle savedInstanceState) {
        final UserData userData;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_viewpager_editpage);
        save_content=findViewById(R.id.save);
        pd_datas_receive = (ArrayList<post_data_image>)getIntent().getSerializableExtra("pd_datas");
        viewPager2 = findViewById(R.id.viewpager2);
        mAuth=new DatabaseManagement();
        Log.d("현재 진행중인 것은", "화면전환에 성공하셨습니다..");

        imageditdataList = new ArrayList<>();
        Log.d("현재 진행중인 것은", "리스트를 받기전입니다..");
        for(int i=0;i<pd_datas_receive.size();i++){
            imageditdataList.add(new image_edit_data(Uri.parse(pd_datas_receive.get(i).getUri()),pd_datas_receive.get(i).getDate_time(),null));

            Log.d("현재 진행중인 것은", "Uri를 성공적으로 받았습니다.");
        }

        userAdapter =  new USERAdapter(this, imageditdataList);
        viewPager2.setAdapter(userAdapter);

        save_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("현재 진행중인것은 ", "게시글작성중");
                for(int i=0;i<imageditdataList.size();i++) {
                    if (imageditdataList.get(i).getContent() != null) { //예외처리구문
                        contentList.add(imageditdataList.get(i).getContent());
                    }
                    else // content안의 글이 없을 경우 공백으로 채운다.
                        contentList.add(" ");
                }
                Log.d("현재 진행중인것은 ", "업로드 진행전");


                for(int i=0;i<imageditdataList.size();i++) {
                    uploadFile(i);
                }
//                uploadFile(pd_datas_receive.size());
            }
        });

    }
    private void goToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void uploadFile(int i) {
        Uri FilePath=Uri.parse(pd_datas_receive.get(i).getUri());
        String user_name= mAuth.getFirebaseUser().getUid();
        Log.d("넘긴 아이디값은: ", user_name);
        String File_tag=Integer.toString(i);
        Toast.makeText(getApplicationContext(), "업로드중", Toast.LENGTH_SHORT).show();
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
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://leave-a-record.appspot.com").child("images/" + filename);
            //올라가거라...
            storageRef.putFile(FilePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
//                            Toast.makeText(getApplicationContext(), "기록을 남겼습니다!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "기록 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
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
}
