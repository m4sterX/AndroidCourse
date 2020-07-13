package com.example.ht8;

public class WeatherStateItem {

    private String weatherImage1;


    private String time1;


    private String description1;


    private String temperature1;


    public WeatherStateItem(String weatherImage1, String time1, String description1, String temperature1) {
        this.weatherImage1 = weatherImage1;
        this.time1 = time1;
        this.description1 = description1;
        this.temperature1 = temperature1;
    }

    public String getWeatherImage1() {
        return weatherImage1;
    }

    public void setWeatherImage1(String weatherImage1) {
        this.weatherImage1 = weatherImage1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(String temperature1) {
        this.temperature1 = temperature1;
    }
}
