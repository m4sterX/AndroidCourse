package com.example.ht8.entity;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class CityEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public CityEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}