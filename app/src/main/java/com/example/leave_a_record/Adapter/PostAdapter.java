package com.example.leave_a_record.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leave_a_record.R;
import com.example.leave_a_record.image_edit_data;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.UserViewHolder> {
    Context mContext;
    List<Uri> post_uri;
    List<String> post_datetime;
    List<String> post_pin;
    private ArrayList<String> Strings = new ArrayList<>();

    public PostAdapter(Context mContext, List<Uri> post_uri,List<String> post_datetime,List<String> post_pin) {
        this.mContext = mContext;
        this.post_uri = post_uri;
        this.post_datetime=post_datetime;
        this.post_pin=post_pin;
    }

    @NonNull
    @Override
    public PostAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_post, parent, false);
        return new PostAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.UserViewHolder holder, int position) {
        Uri uri = post_uri.get(position);
//        holder.img_header.setImageURI(uri);
        Log.d("게시물 뷰페이져 받아온 Uri 체크: ",uri.toString());
        Glide.with(holder.itemView.getContext()).load(uri).into(holder.img_header);
        holder.img_header.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.text_location.setText(post_pin.get(position));
        holder.text_photodate.setText(post_datetime.get(position));

    }


    @Override
    public int getItemCount() {
        return post_uri.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView img_header;
        TextView text_location;
        TextView text_photodate;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_header = itemView.findViewById(R.id.img_header);
            text_location=itemView.findViewById(R.id.Text_location);
            text_photodate=itemView.findViewById(R.id.Text_photodate);

        }
    }
}
