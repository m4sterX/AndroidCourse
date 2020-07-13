package com.example.ht8;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class WidgetWeather {
    @SerializedName("dt")
    private String date;

    @SerializedName("name")
    private String city;

    @SerializedName("temp")
    private String temperature;

    @SerializedName("icon")
    private String iconId;

    public WidgetWeather(String time, String city, String temperature, String iconId) {
        this.date = time;
        this.city = city;
        this.temperature = temperature;
        this.iconId = iconId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetWeather that = (WidgetWeather) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(city, that.city) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(iconId, that.iconId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, city, temperature, iconId);
    }
}
