package com.example.leave_a_record.InterfaceActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.leave_a_record.Adapter.PostAdapter;
import com.example.leave_a_record.Adapter.USERAdapter;
import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.DataBase.UserData;
import com.example.leave_a_record.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PostActivity extends AppCompatActivity {
    TextView title;
    TextView post_date;
    TextView post_content;
    String data_index;
    int index;
    ViewPager2 post_viewPager2;
    Vector<Uri> post_uri=new Vector<>();
    Vector<Uri>post_uri_record=new Vector<Uri>();
    List<String> post_string=new ArrayList<>();
    List<String> post_datetime=new ArrayList<>();
    List<String> post_pin = new ArrayList<>();
    PostAdapter postAdapter;
    Database_M m;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent receive=getIntent();
        setContentView(R.layout.page_post);

        m=new Database_M();

        title=findViewById(R.id.Text_Title);
        post_date=findViewById(R.id.post_date);
        post_content=findViewById(R.id.post_content);
        post_viewPager2=findViewById(R.id.post_viewpager2);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //포스트 인덱스
        data_index=receive.getStringExtra("item");
        index=Integer.parseInt(data_index);
        Log.d("액티비티전환하면서 받아온 인덱스 : ",data_index);
        m.getPosts_i(index, m.getmAuth().getUid(), new Callback<String>() {
            @Override
            public void onCallback(String data) {
                Log.d("getposts_i실행","실행중");
                m.getPostdata(data, m.getmAuth().getUid(), new Callback<PostData>() {
                    @Override
                    public void onCallback(PostData data) {

                        Log.d("getPostdata 실행중","실행중");
                        for(int i=0;i<data.getPost_images_URI().size();i++){
                            Log.d("현재가져온 게시물의 이미지갯수는 ",Integer.toString(data.getPost_images_URI().size()));
                            Log.d("현재가져온 게시물의 날짜",data.getPost_date());
                            post_string.add(data.getPost_images_URI().get(i));
                            post_datetime.add(Time_convert(data.getPost_meta_datetime().get(i)));
                            post_pin.add(data.getPost_pin().get(i));
                        }
                        //제목추가
                        title.setText(data.getPost_title());
                        post_content.setText(data.getPost_content());
                        post_date.setText(Time_convert(data.getPost_date()));
                        post_uri_record.setSize(post_string.size());
                        post_uri.setSize(post_string.size());
                        if(data.getPost_images_URI().size()==post_string.size()){
                                m.AllgetImageUri(post_string,post_uri_record, new Callback<Vector<Uri>>() {
                                    @Override
                                    public void onCallback(Vector<Uri> data) {
                                        Log.d("Singleimage 실행중","실행중");
                                        post_uri=data;
                                        Log.d("post uri size and post string size",Integer.toString(post_uri.size())+" and " +Integer.toString(post_string.size()));
                                        if(post_uri.size()==post_string.size()){
                                            Log.d("게시물 어댑터 연결!","연결중!");
                                            postAdapter =  new PostAdapter(PostActivity.this, post_uri,post_datetime,post_pin);
                                            post_viewPager2.setAdapter(postAdapter);
                                        }
                                    }
                                });


                        }
                    }
                });
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_map, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tool_map: //뒤로가기 버튼
                Intent intent=new Intent(this,MapsActivity.class);
//                intent.putExtra("map", (Parcelable) post_map);
                intent.putExtra("index",data_index);
                Log.d("MAPS 액티비티로 넘기는값 " ,data_index);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    public String Time_convert(String time){
        String result = time.replaceAll("[^0-9]","");
        String year;
        String month;
        String day;
        String date;
        result=result.substring(0, 8);
        year=result.substring(0,4);
        month=result.substring(4,6);
        day=result.substring(6,8);
        date=year+"년"+month+"월"+day+"일";
        return date;
    }

}
