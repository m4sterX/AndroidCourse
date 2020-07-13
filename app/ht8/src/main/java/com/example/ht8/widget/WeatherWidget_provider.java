package com.example.ht8.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.ht8.R;
import com.example.ht8.WidgetWeather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WeatherWidget_provider extends AppWidgetProvider {
    private final String API_KEY = "e82b665387f81d9e5ae70048568ee29a";
    private String URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SHEARED_WIDGET", 0);
        String city = sharedPreferences.getString("CITY_NAME_WIDGET", "London");

        SharedPreferences sharedPreferencesSettings = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        boolean isCelsius = sharedPreferencesSettings.getBoolean("isCelsius", true);

        String url = String.format(URL, city, API_KEY);
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() || responseBody!=null) {
                    String json = responseBody.string();
                    Type type = new TypeToken<WidgetWeather>(){}.getType();
                    WidgetWeather widgetWeather = new Gson().fromJson(json, type);
                    new Handler(context.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            setWeather(context, appWidgetManager, appWidgetIds, widgetWeather, isCelsius);
                        }
                    });
                }
            }
        });
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    private void setWeather(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, WidgetWeather widgetWeather, Boolean isCelsius) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            remoteViews.setTextViewText(R.id.city_widget_textView, widgetWeather.getCity());
            remoteViews.setTextViewText(R.id.weather_widget_textView, temperatureReady(isCelsius, widgetWeather.getTemperature()));
            remoteViews.setTextViewText(R.id.date_widget_textView, jsonDateFormattedToString(widgetWeather.getDate()));

            Bitmap tmpIcon = setIcon(widgetWeather.getIconId(), context);
            if (tmpIcon != null) {
            remoteViews.setImageViewBitmap(R.id.icon_widget, tmpIcon);
            }
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }
    public String kelvinToCelsius(String kelvin) {
        double temp = Double.parseDouble(kelvin);
        double tempR = (temp - 273.15);
        int res = (int) tempR;
        return res + " °C";

    }
    public String kelvinToFahrenheit(String kelvin) {
        double doubleKelvin = Double.parseDouble(kelvin);
        double tempR = 1.8 * doubleKelvin - (273.15) + 32;
        int res = (int) tempR;
        return res + " F";
    }
    public String temperatureReady(boolean isCelsius, String kelvin) {
        return isCelsius ? kelvinToCelsius(kelvin) : kelvinToFahrenheit(kelvin);
    }
    public String jsonDateFormattedToString(String unix) {
        int unixInt = Integer.parseInt(unix);
        Date date = new Date((long) unixInt * 1000);
        return date.toString();
    }
    public Bitmap setIcon(String iconId, Context context) {
        final Bitmap[] mBitmap = new Bitmap[1];
        String URL = "http://openweathermap.org/img/wn/%s@2x.png";
        String url = String.format(URL, iconId);
        Glide.with(context).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                mBitmap[0] = bitmap;
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
        return mBitmap[0];
    }
}
