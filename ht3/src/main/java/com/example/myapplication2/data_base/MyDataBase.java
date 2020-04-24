package com.example.myapplication2.data_base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication2.dao.ContactDao;
import com.example.myapplication2.entity.Contact;

@Database(entities = {Contact.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
