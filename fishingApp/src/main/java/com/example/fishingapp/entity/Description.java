package com.example.fishingapp.entity;

import java.util.List;
import java.util.Objects;

public class Description {
    private List<Fish> fishList;
    private String title;
    private String text;

    public Description(List<Fish> fishList, String title, String text) {
        this.fishList = fishList;
        this.title = title;
        this.text = text;
    }

    public List<Fish> getFishList() {
        return fishList;
    }

    public void setFishList(List<Fish> fishList) {
        this.fishList = fishList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(fishList, that.fishList) &&
                Objects.equals(title, that.title) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fishList, title, text);
    }
}
