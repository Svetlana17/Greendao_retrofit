package com.example.user.greendao_retrofit.api;


import com.example.user.greendao_retrofit.model.ResponseMode;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("top-headlines?sources=techcrunch&apiKey=e33a1361c14048d5be3ed7f551b6be16")

    Call<ResponseMode> getLatestNews();

}
