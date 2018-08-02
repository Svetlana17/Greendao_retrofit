package com.example.user.greendao_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.greendao_retrofit.adapter.NewsAdapter;
import com.example.user.greendao_retrofit.api.ApiClient;
import com.example.user.greendao_retrofit.api.ApiInterface;
import com.example.user.greendao_retrofit.database.DaoSession;
import com.example.user.greendao_retrofit.database.NewsItem;
import com.example.user.greendao_retrofit.database.NewsItemDao;
import com.example.user.greendao_retrofit.model.ResponseMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DaoSession mDaoSession = null;
    private NewsItemDao mNewsItemDa = null;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

             mDaoSession =((App)getApplication()).getDaoSession();
            mNewsItemDa = mDaoSession.getNewsItemDao();
////
////
////            // set adapter
            setAdapter();
////
////            // getting data from api
            fetchNews();
       }
////
        private void fetchNews(){
//            //набор абстрактных методов для запроса на сервер
            ApiInterface apiService =
//                    //ретрофит реализует интерфейс и у всех асбтрактных методов появляется тело
                    ApiClient.getClient().create(ApiInterface.class);
////// вызывает метод getLatesNews и записвается результат в класс оболочку call
            Call<ResponseMode> call = apiService.getLatestNews();
//////ассинхронный запрос на сервер
            call.enqueue(new Callback<ResponseMode>() {
                @Override
                public void onResponse(Call<ResponseMode> call, Response<ResponseMode> response) {
//
////                    // response  -отвер сервера достает из bodyответа список новостей
                    List<NewsItem> news = response.body().getNewsItems();
////
////                    // сохранем список новостей в БД если таких там нет,
                    mNewsItemDa.insertOrReplaceInTx( news);
////
////                    // setting up adapter
                    setAdapter();
                }
////
                @Override
                public void onFailure(Call<ResponseMode> call, Throwable t) {
//                    // Log error here since request failed

               }
            });
        }


        private void setAdapter() {

            // loading data from database by loadAll method
            List<NewsItem> newsItemList = mNewsItemDa.loadAll();
//
//
////            // Creating adapter for recyclerview
            NewsAdapter newsAdapter = new NewsAdapter(newsItemList);
            mRecyclerView.setAdapter(newsAdapter);
       }
    }
