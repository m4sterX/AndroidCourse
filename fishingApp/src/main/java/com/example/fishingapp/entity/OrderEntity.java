package com.example.fishingapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "orders")
public class OrderEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String hoursCount;
    private String house;
    private String equipment;

    public OrderEntity(String name, String hoursCount, String house, String equipment) {
        this.name = name;
        this.hoursCount = hoursCount;
        this.house = house;
        this.equipment = equipment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHoursCount() {
        return hoursCount;
    }

    public void setHoursCount(String hoursCount) {
        this.hoursCount = hoursCount;
    }

    public String isHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String isEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getHouse() {
        return house;
    }

    public String getEquipment() {
        return equipment;
    }
}
