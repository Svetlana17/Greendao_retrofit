package com.example.user.greendao_retrofit.model;

import com.example.user.greendao_retrofit.database.NewsItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;





public class ResponseMode {

    @SerializedName("articles")
    private List<NewsItem> newsItems;


    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
