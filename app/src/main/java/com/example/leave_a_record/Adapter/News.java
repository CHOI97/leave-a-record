package com.example.leave_a_record.Adapter;

public class News {

    private String newsTitle, news;

    public News(String newsTitle, String news){
        this.newsTitle = newsTitle;
        this.news = news;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNews() {
        return news;
    }
}
