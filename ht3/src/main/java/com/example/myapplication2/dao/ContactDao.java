package com.example.myapplication2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication2.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("Select * FROM contact")
    List<Contact> getAll();

    @Query("Select * FROM contact WHERE id = :id")
    Contact getContactById(int id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("Select id FROM contact WHERE id >= 0")
    List<Integer> getId();


}
