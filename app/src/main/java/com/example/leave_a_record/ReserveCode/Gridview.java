//package com.example.leave_a_record;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.GridView;
//import android.widget.Toast;
//
//public class Gridview extends Activity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.gridview);
//
//        GridView gridView = (GridView)findViewById(R.id.gridview);
//        gridView.setAdapter(new ImageAdapter(this));
//

//        gridView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(Gridview.this, ""+position, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}