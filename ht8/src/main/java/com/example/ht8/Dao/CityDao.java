package com.example.ht8.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ht8.entity.CityEntity;


import java.util.List;
@Dao
public interface CityDao {
    @Insert
    void add(CityEntity city);
    @Update
    void update(CityEntity city);
    @Delete
    void delete(CityEntity city);

    @Query("Select * FROM CityEntity")
    List<CityEntity> getAll();
    @Query("Select name From CityEntity Where id =:id ")
    String getCityNameById(int id);
    @Query("SELECT name From CityEntity Where name =:name")
    String getCityByName(String name);
    @Query("Select id From CityEntity Where name  =:name")
    Integer getCityIdByName(String name);
}
