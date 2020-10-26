package com.example.leave_a_record.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HistoryListAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<String> img;
    LayoutInflater inf;

    public HistoryListAdapter(Context context, int layout, ArrayList<String> img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null)
//            convertView = inf.inflate(layout, null);
//        AppCompatImageView iv = (AppCompatImageView) convertView.findViewById(R.id.imageView1);
//        iv.setImageResource(img[position]);dk
//
//        return convertView;
//    }
//}

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            Log.d("success", "myHistory"); //로그찍기
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300,300));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(img.get(position)).into(imageView);
            Log.d("what the Fuck sibal",img.get(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.set
//            imageView.setPadding(1, 1, 1, 1);
//            Log.d("success", "good"); //로그찍기
////            Log.d("success", img.get(position)); //로그찍기

        }else{
            imageView = (ImageView)convertView;
        }

//        imageView.setImageURI(Uri.parse(img.get(position)));

        return imageView;
    }

}