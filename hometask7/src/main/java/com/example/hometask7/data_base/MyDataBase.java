package com.example.hometask7.data_base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hometask7.DAO.ContactDao;
import com.example.hometask7.entity.Contact;


@Database(entities = {Contact.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
