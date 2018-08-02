package com.example.user.greendao_retrofit;


import android.app.Application;

import com.example.user.greendao_retrofit.database.DaoMaster;
import com.example.user.greendao_retrofit.database.DaoSession;

public class App extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}