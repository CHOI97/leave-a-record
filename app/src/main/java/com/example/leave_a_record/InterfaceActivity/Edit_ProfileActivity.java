package com.example.leave_a_record.InterfaceActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.core.view.Change;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class Edit_ProfileActivity extends AppCompatActivity {
    Database_M m=new Database_M();
    CircleImageView profile_image;
    EditText profile_name;
    Button edit_bt;
    Uri image_uri;
    String  uid= m.getmAuth().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_editprofile);
        profile_image=findViewById(R.id.edit_profile_image);
        profile_name=findViewById(R.id.edit_name);
        edit_bt=findViewById(R.id.edit_bt);


        m.SingleImageUri(uid, new Callback<Uri>() {
            @Override
            public void onCallback(Uri data) {
                Glide.with(Edit_ProfileActivity.this).load(data).override(200,200).centerCrop().into(profile_image);
            }
        });
        m.userName(new Callback<String>() {
            @Override
            public void onCallback(String data) {
                profile_name.setHint(data);
            }
        });
        profile_image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//                //사진을 여러개 선택할수 있도록 한다
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
            }
        });
        edit_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_ProfileActivity.this, ProfileActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                try {
                    image_uri=data.getData();
                    profile_image.setImageURI(image_uri);
                    Log.d("이미지를 바꾸기전입니다: ",".");
                    Change_image(image_uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void Change_name(String name){

    }
    public void Change_image(Uri Uri){
        if(!Uri.equals(null)) {
            Log.d("이미지를 바꿉니다: ", "Uri는" + Uri.toString());
            DeleteFile(uid);
        }
    }
    private void DeleteFile(String filename){
        m.DeleteImage(filename, new Callback<Boolean>() {
            @Override
            public void onCallback(Boolean data) {
                if(data){
                    uploadFile();
                }
            }
        });
    }
    private void uploadFile() {
//        String uid = m.getmAuth().getUid();
        Uri FilePath = image_uri;
        m.setProfileImage(uid);
        if (FilePath != null) {
            //업로드 진행 Dialog 보이기
//////////////// storage 와 cloud의 동시에 저장하자 . /////////////////////////////////
            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
//            SimpleDateFormat formatter = new SimpleDateFormat(File_tag);
//            Date now = new Date();
            String filename = uid;
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
                    });
//                    진행중

        } else {
            Toast.makeText(getApplicationContext(), "사진을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

}