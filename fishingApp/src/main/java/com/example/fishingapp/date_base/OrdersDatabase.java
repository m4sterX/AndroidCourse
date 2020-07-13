package com.example.fishingapp.date_base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.fishingapp.dao.OrdersDao;
import com.example.fishingapp.entity.OrderEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {OrderEntity.class}, version = 1)
public abstract class OrdersDatabase extends RoomDatabase {

    private static volatile OrdersDatabase INSTANCE;
    private static final int CORE_NUMBER = Runtime.getRuntime().availableProcessors();
    private ExecutorService dataBaseExecutorService = Executors.newFixedThreadPool(CORE_NUMBER);

    public static OrdersDatabase getINSTANCE(@NonNull final Context context) {
        if(INSTANCE == null) {
            synchronized (OrdersDatabase.class) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, OrdersDatabase.class, "orders").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract OrdersDao getOrdersDao();

    public ExecutorService getDataBaseExecutorService() {
        return dataBaseExecutorService;
    }
}
