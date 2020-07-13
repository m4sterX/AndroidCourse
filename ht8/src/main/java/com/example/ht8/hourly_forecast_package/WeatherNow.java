package com.example.ht8.hourly_forecast_package;

import java.util.Objects;

public class WeatherNow {
    private String cityName;
    private String temperature;
    private String weatherDescription;
    private String iconId;

    public WeatherNow(String cityName, String temperature, String weatherDescription, String iconId) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.weatherDescription = weatherDescription;
        this.iconId = iconId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
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
        WeatherNow that = (WeatherNow) o;
        return Objects.equals(cityName, that.cityName) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(weatherDescription, that.weatherDescription) &&
                Objects.equals(iconId, that.iconId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, temperature, weatherDescription, iconId);
    }
}
