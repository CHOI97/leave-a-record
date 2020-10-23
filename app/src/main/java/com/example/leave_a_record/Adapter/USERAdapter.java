package com.example.leave_a_record.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leave_a_record.R;
import com.example.leave_a_record.image_edit_data;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class USERAdapter extends RecyclerView.Adapter<USERAdapter.UserViewHolder>{
    Context mContext;
    List<image_edit_data> imageditdataList;

    public USERAdapter(Context mContext, List<image_edit_data> imageditdataList) {
        this.mContext = mContext;
        this.imageditdataList = imageditdataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.page_edit,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        image_edit_data imageditdata = imageditdataList.get(position);
        holder.edit_iv.setImageURI(imageditdata.getUri()); //test를위해
        holder.edit_date_time.setText(imageditdata.getDate_time());

        ////////test code
//        holder.edit_iv.setImageResource(user.getEdit_iv());
        ////////
//        holder.edit_Text_date.setText(user.getEdit_Text_date());

    }

    @Override
    public int getItemCount() {
        return imageditdataList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
//        TextInputEditText edit_title;
//        RadioButton p1,p2,p3,p4,p5;
        ImageView edit_iv;
        TextView edit_date_time;
        EditText edit_content;
//        TextInputEditText Text_content;
//        Button save;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

//            p1 =  itemView.findViewById(R.id.edit_Pin1);
//            p2= itemView.findViewById(R.id.edit_Pin2);
//            p3=itemView.findViewById(R.id.edit_Pin3);
//            p4=itemView.findViewById(R.id.edit_Pin4);
//            p5=itemView.findViewById(R.id.edit_Pin5);
            edit_iv= itemView.findViewById(R.id.edit_iv);
            edit_date_time=itemView.findViewById(R.id.edit_date_time);
            edit_content=itemView.findViewById(R.id.edit_content);


        }
    }
}
