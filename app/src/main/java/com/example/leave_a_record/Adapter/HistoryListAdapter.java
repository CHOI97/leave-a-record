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
import java.util.List;

public class HistoryListAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Uri> img;
    LayoutInflater inf;

    public HistoryListAdapter(Context context, int layout, List<Uri> img) {
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


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            Log.d("success", "myHistory"); //로그찍기
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(400,400));

            Log.d("현재의 이미지 업데이트 포지션은 ",Integer.toString(position));
            Log.d("현재의 이미지 업로드 uri는 :",(img.get(position)).toString());
            Glide.with(context).load(img.get(position)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setPadding(1, 1, 1, 1);

        }else{
            imageView = (ImageView)convertView;
        }
        return imageView;
    }

}