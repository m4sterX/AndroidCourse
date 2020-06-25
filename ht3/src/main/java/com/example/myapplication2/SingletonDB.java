package com.example.myapplication2;


import android.annotation.SuppressLint;
import android.app.Application;

import androidx.room.Room;

import com.example.myapplication2.data_base.MyDataBase;

@SuppressLint("Registered")
public class SingletonDB extends Application {

    public static SingletonDB instance;

    private MyDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, MyDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static SingletonDB getInstance() {
        return instance;
    }

    public MyDataBase getDatabase() {
        return database;
    }
}
