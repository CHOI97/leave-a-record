package com.example.leave_a_record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class HistoryListAdapter extends BaseAdapter {
    Context context;
    int layout;
    int img[];
    LayoutInflater inf;

    public HistoryListAdapter(Context context, int layout, int[] img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
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
//        iv.setImageResource(img[position]);
//
//        return convertView;
//    }
//}

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300,300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
        }else{
            imageView = (ImageView)convertView;
        }

        imageView.setImageResource(img[position]);

        return imageView;
    }

}