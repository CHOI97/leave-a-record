package com.example.leave_a_record.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leave_a_record.R;
import com.example.leave_a_record.image_edit_data;
import com.google.android.material.textfield.TextInputEditText;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class USERAdapter extends RecyclerView.Adapter<USERAdapter.UserViewHolder>{
    Context mContext;
    List<image_edit_data> imageditdataList;

    private ArrayList<String> Strings  =new ArrayList<>();
    public USERAdapter(Context mContext, List<image_edit_data> imageditdataList) {
        this.mContext = mContext;
        this.imageditdataList = imageditdataList;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_viewpager_editpage,parent,false);
        return new UserViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder,final int position) {
        image_edit_data imageditdata = imageditdataList.get(position);
        holder.edit_myimg.setImageURI(imageditdata.getUri());

    }

    @Override
    public int getItemCount() {
        return imageditdataList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView edit_myimg;
//        EditText edit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            edit_myimg= itemView.findViewById(R.id.edit_myimg);
//            edit=itemView.findViewById(R.id.edit_location);
        }
    }

}
