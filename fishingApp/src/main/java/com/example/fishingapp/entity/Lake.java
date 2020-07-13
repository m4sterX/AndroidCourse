package com.example.fishingapp.entity;

public class Lake {
    private String name;
    private int description;
    private int photoFile;
    private Location location;

    public Lake(String name, int description, int photoFile, Location location) {
        this.name = name;
        this.description = description;
        this.photoFile = photoFile;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(int photoFile) {
        this.photoFile = photoFile;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
