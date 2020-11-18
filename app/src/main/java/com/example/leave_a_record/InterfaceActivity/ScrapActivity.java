package com.example.leave_a_record.InterfaceActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leave_a_record.Adapter.Item;
import com.example.leave_a_record.Adapter.News;
import com.example.leave_a_record.Adapter.ScrapAdapter;
import com.example.leave_a_record.Adapter.Trip;
import com.example.leave_a_record.R;

import java.util.ArrayList;
import java.util.List;

public class ScrapActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<>();



        Trip trip1 = new Trip(R.drawable.sample_1, "totoro", "2020.11.18");
        items.add(new Item(0,trip1) );
        //type 0 은 사진 + 제목 + 날짜

        News news1 = new News(
                "Totoro","hahahahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        );
        items.add(new Item(1,news1));
        //type 1은 게시자 + 게시물 내용


        Trip trip2 = new Trip(R.drawable.sample_2, "haul", "2020.11.18");
        items.add(new Item(0,trip2) );

        News news2 = new News(
                "Haul","hahahahahahaha"
        );
        items.add(new Item(1,news2));

        Trip trip3 = new Trip(R.drawable.sample_2, "haul", "2020.11.18");
        items.add(new Item(0,trip3) );

        News news3 = new News(
                "Haul","hahahahahahaha"
        );
        items.add(new Item(1,news3));


        recyclerView.setAdapter(new ScrapAdapter(items));

        //toolbar
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //버튼을 눌렀을때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
