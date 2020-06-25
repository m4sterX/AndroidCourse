package com.example.ht8.data_base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ht8.Dao.CityDao;
import com.example.ht8.entity.CityEntity;

@Database(entities = {CityEntity.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract CityDao cityDao();
}
