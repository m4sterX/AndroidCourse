package com.example.ht8;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.room.Room;

import com.example.ht8.data_base.DataBase;

@SuppressLint("Registered")
public class DataBaseSingleton extends Application {
    public static DataBaseSingleton instance;
    private DataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        dataBase = Room.databaseBuilder(this, DataBase.class, "dataBase")
                .build();
    }

    public static DataBaseSingleton getInstance() {
        return instance;
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
