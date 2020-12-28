package com.example.leave_a_record.DataBase;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.leave_a_record.InterfaceActivity.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;

public class Database_M  {

    private static Database_M dbM = new Database_M();
    private FirebaseAuth mAuth;   // 파이어베이스 인증 객체
    private static Data_M data_m = new Data_M();
    private UserData userData;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore database;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference desertRef;

    // Create a reference with an initial file path and name
//    StorageReference pathReference = storageRef.child("images/stars.jpg");

    // Create a reference to a file from a Google Cloud Storage URI
//    StorageReference gsReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg");

    // Create a reference from an HTTPS URL
// Note that in the URL, characters are URL escaped!
//    StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");

    String TAG="실행중 : ";
    // 생성자
    public Database_M() {
//        private static DatabaseManagement mData = new DatabaseManagement();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public static Database_M getInstance() {
        return dbM;
    }


    //로그인
    public void SignInEmail(final Activity activity , String email, String password , final Callback<Boolean> callback){
        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        // 작업 완료시
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // 이메일 로그인 성공
                            if(task.isSuccessful()) {
                                Log.d("현재 진행중인 것은", "-------------로그인중입니다.");
                                getUserData(mAuth.getCurrentUser().getUid(), new Callback<UserData>() {
                                    @Override
                                    public void onCallback(UserData data) {
                                        Log.d("정보가져오기 ", "-----------------시작 -----------------");

                                        if (data != null) {
                                            Data_M.getInstance().setUserData(data);
                                            Log.d("정보가져오기 ", "-----------------복사 완료 -----------------");
                                            callback.onCallback(true);
                                        }else{
                                            callback.onCallback(false);
                                        }
                                    }
                                });
                                Toast.makeText(activity,"로그인되었습니다.",Toast.LENGTH_SHORT).show();
                            } else {
                                // 로그인 실패시
                                Toast.makeText(activity,"아이디와 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        else {
            Toast.makeText(activity, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
    public void signUp(final Activity activity, String id, String pwd, String name, String pwd_c, Callback<Boolean> callback) {
        final String email = id;
        final String passwd = pwd;
        final String userName = name;
        String passwd_c=pwd_c;
//        String email =((EditText)findViewById(R.id.signup_id)).getText().toString();
//        String password = ((EditText)findViewById(R.id.signup_pw)).getText().toString();
//        String passwordCheck=((EditText)findViewById(R.id.signup_pwd)).getText().toString();

        if(email.length()>0&&passwd.length()>0&&passwd_c.length()>0&&userName.length()>0) {
            if (passwd.equals(passwd_c)) {
                mAuth.createUserWithEmailAndPassword(email, passwd)
                        .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user =  mAuth.getCurrentUser();

                                    UserData userdata=new UserData(email,userName,passwd,user.getUid());
//                                    mDatabase.child("Users").setValue(userdata);
                                    Log.d("회원가입중 유저의 데이터 보기",userdata.getUser_about());

                                    Log.d(TAG, "유저데이터 객체 생성 성공하였음======================:success");

//                                    FirebaseFirestore db=FirebaseFirestore.getInstance();

                                    Log.d(TAG, "db인스턴스 성공했음 ======================:success");
                                    Log.d(TAG, "child 로 넘겼음 유저데이터 =====================:success");

                                    if(user !=null)
                                        mDatabase.child(user.getUid()).setValue(userdata)
//                                    db.collection("users").document(user.getUid()).set(userdata)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "회원등록 성공");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "회원등록 실패", e);
                                                    }
                                                });
                                    Toast.makeText(activity,"회원가입성공",Toast.LENGTH_SHORT).show();
//                                    updateUI(user);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                    goToast("이미 존재하는 아이디입니다.");
                                }

                                // ...
                            }
                        });
            } else {
//                goToast("비밀번호가 일치하지 않습니다.");
            }
        }
        else{
//            goToast("모든항목을 작성해주세요.");
        }



    }
    public void SignOut(){
        mAuth.getInstance().signOut();
    }
    //유저데이터를 가져오는 메소드
    public void getUserData(String uid, final Callback<UserData> callback) {
        FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        userData = dataSnapshot.getValue(UserData.class);
                        Log.d("정보가져오기 ", "-----------------성공 -----------------");
                        callback.onCallback(userData);
                        Log.d("지금 로그인된 아이디는", userData.getUser_name());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError){
                        Log.d("정보가져오기 ", "-----------------실패 -----------------");
                    }
        });
    }
    //post 전부 들을 가져오는 메소드
    public void getPosts(String uid,final Callback<List<String>> callback){
        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).child("postData")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> posts=new ArrayList<>();
                        int i=0;
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            posts.add(postSnapshot.getKey());
                            Log.d("지금 가져오는키는",posts.get(i));
                            i++;
                        }
                        Log.d("정보가져오기" ,"성공적으로 포스트들을 가져왔습니다.");
                        callback.onCallback(posts);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("정보가져오기","포스트를 가져오는데 실패했습니다.");
                        callback.onCallback(null);
                    }
                });
    }
    //포스트갯수를 가져오는 메소드
    public void CountPosts(String uid,final Callback<Integer> callback){
        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).child("postData")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i;
//                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                            i++;
//                        }
                        i=(int)snapshot.getChildrenCount();
                        Log.d("정보가져오기" ,"현재 포스트의 개수는 : "+Integer.toString(i));
                        callback.onCallback(i);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("정보가져오기","포스트를 가져오는데 실패했습니다.");
                        callback.onCallback(null);
                    }
                });
    }

    //포스트에 인덱스부분만 가져오는 메소드
    public void getPosts_i(final int index,String uid,final Callback<String> callback){
        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).child("postData")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> posts=new ArrayList<>();
                        int i=0;
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            posts.add(postSnapshot.getKey());
                            Log.d("지금 가져오는키는",posts.get(i));
                            i++;
                        }
                        Log.d("정보가져오기" ,"성공적으로 포스트들을 가져왔습니다.");
                        Log.d("콜백키는" ,posts.get(index));
                        callback.onCallback(posts.get(index));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("정보가져오기","포스트를 가져오는데 실패했습니다.");
                        callback.onCallback(null);
                    }
                });
    }
    public void getPostdata(String post_date,String uid,final Callback<PostData> callback){

        FirebaseDatabase.getInstance().getReference().child("posts").child(uid).child("postData").child(post_date)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       PostData postdata = snapshot.getValue(PostData.class);
                       callback.onCallback(postdata);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
    }
    //post를 전부읽는 메소드
    public void AllgetPostData(final ArrayList<PostData> postdatas , final Callback<List<PostData>> callback){
        // 포스트의 날짜들을 가져오는 메소드
        getPosts(mAuth.getCurrentUser().getUid(), new Callback<List<String>>() {
            @Override
            public void onCallback(final List<String> data) {
                if (data != null) {
                    int i;
                    for(i=0;i<data.size();i++) {
                        Log.d("현재 인덱스는 : ",Integer.toString(i));
                        // 날짜가 존재할경우 포스트를 읽기시작한다.
                        readPost(data.get(i), new Callback<PostData>() {
                            @Override
                            public void onCallback(PostData postdata) {
                                if (postdata != null) {
                                   postdatas.add(postdata);
                                    Log.d("post가져와서 추가중", "추가중");
                                }
//                                else if(postdatas.size()== data.size()-1){
//                                    Log.d("post가져와서 추가중", "마지막작업 리스트뷰 onCallbackPost의 업로드 ");
//                                    callback.onCallback(postdatas);
//                                }
                                else
                                 {
                                    Log.d("post가져오는중", "에러발생");
                                }
                                if(postdatas.size()==data.size()){
                                    Log.d("post가져와서 추가중", "마지막작업 리스트뷰 onCallbackPost의 업로드 ");
                                    callback.onCallback(postdatas);
                                }
                            }

                        });
                    }
//                    Log.d("가져오 데이터의 제목 : ",postdatas.get(0).getPost_title());
                    Log.d("post가져와서 추가완료", "추가완료되었습니다.");
                }
                else {
                    Log.d("post가져오는중","에러발생");
                }

            }

        });

    }
    //post의 일부 데이터를 가져와서 읽어들이는 메소드
//    public void getPostData(final int i,final Callback<PostData> callback){
//        // 포스트의 날짜들을 가져오는 메소드
//        getPosts_i(i,mAuth.getCurrentUser().getUid(), new Callback<String>() {
//            @Override
//            public void onCallback(String data) {
//                if (data != null) {
//                    // 날짜가 존재할경우 포스트를 읽기시작한다.
//                    readPost(data, new Callback<PostData>() {
//                        @Override
//                        public void onCallback(PostData postdata) {
//                            if(postdata!=null) {
//                                callback.onCallback(postdata);
//                            }
//                            else{
//                                Log.d("post가져오는중","에러발생");
//                            }
//                        }
//                    });
//
//                }
//                else {
//                    Log.d("post가져오는중","에러발생");
//                }
//
//            }
//        });
//    }
    public void readPost(final String post_date,final Callback<PostData> callback){
        FirebaseDatabase.getInstance().getReference().child("posts").child(mAuth.getCurrentUser().getUid()).child("postData")
                .child(post_date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("정보가져오기",post_date);
                PostData postdata=snapshot.getValue(PostData.class);
                Log.d("정보가져오기","게시물의 데이터를 성공적으로 가져왔습니다!!!!!!!!");
                Log.d("정보가져오기",postdata.getPost_title());
                callback.onCallback(postdata);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("정보가져오기","게시물의 데이터를 가져오는데 실패하였습니다");
                callback.onCallback(null);
            }
        });
    }


   // 유저이름 동기화
    public void userName(final Callback<String> callback) {
//        UserData userData = Data_M.getInstance().getUserData();
//        String name = userData.getUser_name();
//        callback.onCallback(name);
        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData userData=snapshot.getValue(UserData.class);
                Log.d("정보가져오기","게시물의 데이터를 성공적으로 가져왔습니다!!!!!!!!");
                Log.d("정보가져오기",userData.getUser_name());
                callback.onCallback(userData.getUser_name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("정보가져오기","게시물의 데이터를 가져오는데 실패하였습니다");
                callback.onCallback(null);
            }
        });
    }
    public void userAbout(final Callback<String> callback) {
        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserData userData=snapshot.getValue(UserData.class);
                        Log.d("정보가져오기","게시물의 데이터를 성공적으로 가져왔습니다!!!!!!!!");
                        Log.d("정보가져오기",userData.getUser_about());
                        callback.onCallback(userData.getUser_about());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("정보가져오기","게시물의 데이터를 가져오는데 실패하였습니다");
                        callback.onCallback(null);
                    }
                });
    }
    // 회원가입
    public void SignUp(final Activity activity, final String email, final String passwd, String passwd_c, final String userName, final Callback<Boolean> callback){
        if (email.length() > 0 && passwd.length() > 0 && passwd_c.length() > 0 && userName.length() > 0) {
            if (passwd.equals(passwd_c)) {
                mAuth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(activity,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserData userdata = new UserData(email, userName, passwd, user.getUid());
                                    if (user != null)
                                        mDatabase.child("users").child(user.getUid()).setValue(userdata)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "회원등록 성공");
                                                        callback.onCallback(true);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "회원등록 실패", e);
                                                        callback.onCallback(false);
                                                    }
                                                });
                                    Toast.makeText(activity, "회원가입성공", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(activity, "이미존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                    callback.onCallback(false);
                                }
                            }
                        });
            } else {
                Toast.makeText(activity, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
                callback.onCallback(false);
            }
        } else {
            Toast.makeText(activity, "모든항목을 작성해주세요.", Toast.LENGTH_SHORT).show();
            callback.onCallback(false);
        }
    }

    public void AllgetImageUri(final List<String> str,final Vector<Uri> postImageUri,final Callback<Vector<Uri>> callback){
        for(int i=0;i<str.size();i++){
            getImageUri(i, str,postImageUri, new Callback <Vector<Uri>>() {
                @Override
                public void onCallback(Vector<Uri> data) {
                    if(data!=null) {
                        Log.d("AllgetImageUri : ",postImageUri.toString());
                    }
                    else{
                        Log.d("AllgetImageUri : ","error");
                    }
                    if(NulllCheck(postImageUri)){
                        callback.onCallback(postImageUri);
                    }
                }
            });
        }

    }

    public void getImageUri(final int i , List<String> str, final Vector<Uri> postImageUri, final Callback<Vector<Uri>> callback) {
            storageRef.child("images/" + str.get(i)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.d("이미지다운로드 성공", ": Success -이미지가 다운로드되었습니다.");
                    Log.d("이미지다운로도된 Uri 는 : ", uri.toString());
                    Log.d("현재 벡터의 사이즈는 : ", Integer.toString(postImageUri.size()));

                    postImageUri.set(i, uri);
                    callback.onCallback(postImageUri);
//                    if(!uri.equals(null)){
//                        callback.onCallback(uri);
//                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d("이미지다운로드 실패", ": failed -이미지가 다운로드되지 않았습니다.");
                }
            });
    }
    public Boolean NulllCheck(Vector<Uri> temp) {
        for (int i = 0; i < temp.size(); i++) {
            try {
                //액션을 취해야함. 확인체크
                if(temp.get(i).equals(null)){
                    return false;
                }
                Log.d("널포인터 체크 pass index:", Integer.toString(i));
            }
            catch(NullPointerException e){
                return false;
            }
        }

        return true;
    }
    public void SingleImageUri(String image_str,final Callback<Uri> callback){
        storageRef.child("images/" + image_str).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("Single이미지 다운로드 성공", ": Success - SINGLE 이미지가 다운로드되었습니다.");
                callback.onCallback(uri);
            }
        });
    }
    public void setProfileImage(String profileimage) {
        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).child("user_image").setValue(profileimage);
    }
    public void DeleteImage(String image_file,final Callback<Boolean> callback){
        desertRef=storageRef.child("images/"+image_file+".jpg");
        desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("이미지파일이 삭제되었습니다 : ","Success");
                callback.onCallback(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("삭제할 이미지가 없습니다. : ","Failed");
                callback.onCallback(true);
            }
        });
    }
    public void Name_change(String name){
        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).child("user_name").setValue(name);
    }
    public void About_change(String about){
        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).child("user_about").setValue(about);
    }
    public void Call_reco_post(final Callback<List<String>> callback){
        Log.d("어디인거야","콜포");
        FirebaseDatabase.getInstance().getReference().child("open").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> open=new ArrayList<>();
                int i=0;
                for(DataSnapshot openSnapshot:snapshot.getChildren()){
                    open.add(openSnapshot.getKey());
                    Log.d("지금 가져오는키는",open.get(i));
                    i++;
                }
                callback.onCallback(open);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onCallback(null);
            }
        });
    }
    public void open_post_call(String str,final Callback<PostData> callback) {
        Log.d("어디인거야","포콜");
        FirebaseDatabase.getInstance().getReference().child("open").child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PostData opendata=snapshot.getValue(PostData.class);
                callback.onCallback(opendata);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Check_reco(final List<PostData> List, final Callback <List<PostData>> callback) {

        Call_reco_post(new Callback<List<String>>() {
            @Override
            public void onCallback(final List<String> data_str) {
                for(int i=0;i<data_str.size();i++) {
                    open_post_call(data_str.get(i), new Callback<PostData>() {
                        @Override
                        public void onCallback(PostData data_post) {
                            List<PostData> open_source=new ArrayList<>();
                            open_source.add(data_post);
                            List.add(data_post);
                           }
                        });
                    }
                callback.onCallback(List);
                }
            });
    }


    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        Log.d("어디인거야","디텐");
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if(unit == "meter"){
            dist = dist * 1609.344;
        }

        Log.d("dist 값은",Double.toString(dist));
        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }







//    public void getKey(final Callback<String> callback){
//        FirebaseDatabase.getInstance().getReference()
//                .child("posts")
//                .child(cAuth.getCurrentUser().getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        img_key_name.clear();
//                        img_key_name.add("");
//                        int i = 0;
//                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                            img_key_name.add(child.getKey());
//                            Log.d("key : ", img_key_name.get(i));
////                                imageLoad(i);
////                            callback.onCallback(true);
//                            callback.onCallback(img_key_name.get(i));
//                            image_show(callback);
//                            i++;
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        callback.onCallback(null);
//                    }
//                });
//    }
//    public void image_show(final Callback<String> callback){
//        FirebaseDatabase.getInstance().getReference()
//                .child("posts")
//                .child(cAuth.getCurrentUser().getUid()).child(callback.toString())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot data : dataSnapshot.getChildren()) {
//                            PostData postData=data.getValue(PostData.class);
//                            img.add(postData.getPost_images_URI().get(0));
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
//    public UserData getUserData() {
//        return userData;
//    }
//    public void setUserData(UserData userData) {
//        this.userData = userData;
//    }

    public static Database_M getDbM() {
        return dbM;
    }

    public static void setDbM(Database_M dbM) {
        Database_M.dbM = dbM;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseFirestore database) {
        this.database = database;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    // 객체 가져오기



}
