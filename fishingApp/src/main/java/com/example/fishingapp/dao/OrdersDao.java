package com.example.fishingapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fishingapp.entity.OrderEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrdersDao {

    @Query("SELECT * FROM orders")
    List<OrderEntity> getAll();

    @Query("SELECT * FROM orders WHERE id =:id")
    OrderEntity getLakeById(long id);

    @Insert(onConflict = REPLACE)
    void insert(OrderEntity orderEntity);
}
