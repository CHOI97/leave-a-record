package com.example.leave_a_record.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leave_a_record.R;
import com.example.leave_a_record.image_edit_data;

import java.util.List;

public class USERAdapter extends RecyclerView.Adapter<USERAdapter.UserViewHolder>{
    Context mContext;
    List<image_edit_data> imageeditdataList;

    public USERAdapter(Context mContext, List<image_edit_data> imageeditdataList) {
        this.mContext = mContext;
        this.imageeditdataList = imageeditdataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.page_edit,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        image_edit_data imageeditdata = imageeditdataList.get(position);
        holder.edit_iv.setImageURI(imageeditdata.getUri()); //test를위해
        ////////test code
//        holder.edit_iv.setImageResource(user.getEdit_iv());
        ////////
//        holder.edit_Text_date.setText(user.getEdit_Text_date());

    }

    @Override
    public int getItemCount() {
        return imageeditdataList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
//        TextInputEditText edit_title;
//        RadioButton p1,p2,p3,p4,p5;
        ImageView edit_iv;
//        TextInputEditText edit_Text_date;
//        TextInputEditText Text_content;
//        Button save;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
//            edit_title = itemView.findViewById(R.id.edit_title);
//            p1 =  itemView.findViewById(R.id.edit_Pin1);
//            p2= itemView.findViewById(R.id.edit_Pin2);
//            p3=itemView.findViewById(R.id.edit_Pin3);
//            p4=itemView.findViewById(R.id.edit_Pin4);
//            p5=itemView.findViewById(R.id.edit_Pin5);
            edit_iv= itemView.findViewById(R.id.edit_iv);
//            edit_Text_date=itemView.findViewById(R.id.edit_Text_date);
//            Text_content=itemView.findViewById(R.id.Text_content);
//            save=itemView.findViewById(R.id.edit_save);

        }
    }
}
