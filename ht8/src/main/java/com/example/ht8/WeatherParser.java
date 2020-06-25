package com.example.ht8;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.ht8.activities.SettingsActivity;
import com.example.ht8.hourly_forecast_package.WeatherNow;
import com.example.ht8.hourly_forecast_package.WeatherStateItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherParser {

    private String jsonData;

    public WeatherParser(@NonNull String jsonData) {
        this.jsonData = jsonData;
    }

    public List<WeatherStateItem> parseHourlyForecast() throws JSONException {
//        String tmp1;
//        String tmp2;
//        String tmp3;

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject firstObj = jsonObject.getJSONArray("list").getJSONObject(0);
        JSONObject main = firstObj.getJSONObject("main");

       double temperature = main.getDouble("temp");

        JSONArray weatherArray = firstObj.getJSONArray("weather");
        JSONObject weatherArrayObject = weatherArray.getJSONObject(0);

        String imgId = weatherArrayObject.getString("icon");
        String description = weatherArrayObject.getString("description");
        String time = firstObj.getString("dt_txt");


        JSONObject secondObj = jsonObject.getJSONArray("list").getJSONObject(1);
        JSONObject main2 = secondObj.getJSONObject("main");

        double temperature2 = main2.getDouble("temp");

        JSONArray weatherArray2 = secondObj.getJSONArray("weather");
        JSONObject weatherArrayObject2 = weatherArray2.getJSONObject(0);

        String imgId2= weatherArrayObject2.getString("icon");
        String description2 = weatherArrayObject2.getString("description");
        String time2 = secondObj.getString("dt_txt");

        JSONObject ThirdObj = jsonObject.getJSONArray("list").getJSONObject(2);
        JSONObject main3 = ThirdObj.getJSONObject("main");

        double temperature3 = main3.getDouble("temp");

        JSONArray weatherArray3 = ThirdObj.getJSONArray("weather");
        JSONObject weatherArrayObject3 = weatherArray3.getJSONObject(0);

        String imgId3= weatherArrayObject3.getString("icon");
        String description3 = weatherArrayObject3.getString("description");
        String time3 = ThirdObj.getString("dt_txt");
//        if (settingsActivity.isCesium()) {
//            tmp1 = kelvinToCesium(temperature);
//            tmp2 = kelvinToCesium(temperature2);
//            tmp3 = kelvinToCesium(temperature3);
//        } else {
//            tmp1 = kelvinToFahrenheit(temperature);
//            tmp2 = kelvinToFahrenheit(temperature2);
//            tmp3 = kelvinToFahrenheit(temperature3);
//        }

        WeatherStateItem wSI1 = new WeatherStateItem(imgId, time, description, String.valueOf(temperature));
        WeatherStateItem wST2 = new WeatherStateItem(imgId2, time2, description2, String.valueOf(temperature2));
        WeatherStateItem wSI3 = new WeatherStateItem(imgId3, time3, description3, String.valueOf(temperature3));
        List<WeatherStateItem> list = new ArrayList<>();
        list.add(wSI1);
        list.add(wST2);
        list.add(wSI3);
        return list;
    }

    public WeatherNow parseWeatherNow() throws JSONException{
        JSONObject jsonObject = new JSONObject(jsonData);
        String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        String iconId = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
        String temperature = jsonObject.getJSONObject("main").getString("temp");
        String name = jsonObject.getString("name");

        return new WeatherNow(name, temperature, description, iconId);
    }
    public String kelvinToCesium(double kelvin) {
        double temp = (kelvin - 273.15);
        int res = (int) temp;
         return res + " °C";

    }
    public String kelvinToFahrenheit(double kelvin) {
        double temp = 1.8 * kelvin - (273.15) + 32;
        int res = (int) temp;
       return res + " F";
    }
}
