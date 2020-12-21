package com.example.leave_a_record.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.leave_a_record.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    private ArrayList<String> mtimer;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
//        public TextView TextView_title;
//        public TextView TextView_content;
//        public ImageView ImageView_title;


        public TextView date_time;
        public TextView course_time;
        public TextView place1, place2;
        public TextView start,finish;
//        public ImageView airplane;


        public MyViewHolder(View v) {
            super(v);
//            TextView_title = v.findViewById(R.id.TextView_title);
//            TextView_content=v.findViewById(R.id.TextView_content);
//            ImageView_title=v.findViewById(R.id.ImageView_title);


            date_time = itemView.findViewById(R.id.TextTripData);
            course_time= itemView.findViewById(R.id.TextTripTime);
            place1=itemView.findViewById(R.id.detailStart);
            place2=itemView.findViewById(R.id.detailFinish);
            start=itemView.findViewById(R.id.textStart);
            finish=itemView.findViewById(R.id.textFinish);
//            airplane=itemView.findViewById(R.id.airplane);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset,ArrayList<String> timer) {
        //{"1","2"}
        mDataset = myDataset;
        if(timer.size()!=0){
            mtimer=timer;
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.date_time.setText(mDataset.get(position));
        if(mtimer.size()!=0){
            holder.course_time.setText(mtimer.get(position));
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}