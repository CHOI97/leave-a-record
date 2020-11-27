package com.example.leave_a_record.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leave_a_record.R;

import java.util.List;

public class ScrapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<com.example.leave_a_record.Adapter.Item> items;

    public ScrapAdapter(List<com.example.leave_a_record.Adapter.Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new TripViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container,
                            parent,
                            false
                    )
            );

        }
        else {
            return new NewsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_news,
                        parent,
                        false)
        );

        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){

        if(getItemViewType(position)==0){
            Trip trip = (Trip) items.get(position).getObject();
            ((TripViewHolder)holder).setTripData(trip);
        }
        else {
            News news = (News) items.get(position).getObject();
            ((NewsViewHolder) holder).setNewsData(news);
        }

    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    @Override
    public int getItemViewType(int position){
        return items.get(position).getType();
    }


    static class TripViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageTrip;
        private TextView textTripTitle, textTrip;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            imageTrip = itemView.findViewById(R.id.imageTrip);
            textTripTitle = itemView.findViewById(R.id.textTripTitle);
            textTrip = itemView.findViewById(R.id.textTrip);
        }

        void setTripData (Trip trip){
            imageTrip.setImageResource(trip.getTripImage());
            textTripTitle.setText(trip.getTripTitle());
            textTrip.setText(trip.getTrip());
        }

    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView textNewsTitle, textNews;

        NewsViewHolder(@NonNull View itemView){
            super(itemView);
            textNewsTitle = itemView.findViewById(R.id.textNewsTitle);
            textNews = itemView.findViewById(R.id.textNews);
        }

        void setNewsData(News news){
            textNewsTitle.setText(news.getNewsTitle());
            textNews.setText(news.getNews());
        }
    }

}
