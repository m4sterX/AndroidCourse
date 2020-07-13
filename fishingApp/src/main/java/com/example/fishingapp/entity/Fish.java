package com.example.fishingapp.entity;

import java.util.Objects;

public class Fish {
    private String name;
    private String Description;
    private String photoFilePath;

    public Fish(String name, String description, String photoFilePath) {
        this.name = name;
        Description = description;
        this.photoFilePath = photoFilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return Objects.equals(name, fish.name) &&
                Objects.equals(Description, fish.Description) &&
                Objects.equals(photoFilePath, fish.photoFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Description, photoFilePath);
    }
}
