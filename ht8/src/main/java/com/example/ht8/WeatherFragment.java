package com.example.ht8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ht8.Dao.CityDao;
import com.example.ht8.activities.MainActivity;
import com.example.ht8.activities.SecondActivity;
import com.example.ht8.activities.SettingsActivity;
import com.example.ht8.data_base.DataBase;
import com.example.ht8.entity.CityEntity;
import com.example.ht8.hourly_forecast_package.WeatherNow;
import com.example.ht8.hourly_forecast_package.WeatherStateItem;
import com.example.ht8.hourly_forecast_package.WeatherStateItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherFragment extends Fragment {

    private List<CityEntity> cities = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView cityName;
    private TextView temperature;

    private TextView weatherDescription;
    private ImageView weatherImage;
    private static final String API_KEY = "bc3492066e0ee0f94a45f0671eb7a5d1";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private static final String URL2 = "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s";
    private String city = "London";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Handler handler = new Handler();
    private SharedPreferences sharedPreferences;
    private boolean answerForAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name = getActivity().getIntent().getStringExtra("CITY_NAME");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataBase mDataBase = DataBaseSingleton.getInstance().getDataBase();
                CityDao cityDao = mDataBase.cityDao();
                cities = cityDao.getAll();
            }
        });
        thread.start();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionMainMenu);

        RecyclerView weatherStateRecycler = view.findViewById(R.id.weatherList);
        weatherStateRecycler.setHasFixedSize(true);
        weatherStateRecycler.setAdapter(new WeatherStateItemAdapter());
        weatherStateRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        weatherStateRecycler.addItemDecoration(dividerItemDecoration);
        //getting pref from activity
        sharedPreferences = view.getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);

        temperature = view.findViewById(R.id.temperature);
        weatherDescription = view.findViewById(R.id.weatherDescription);
        cityName = view.findViewById(R.id.cityName);
        weatherImage = view.findViewById(R.id.weatherImage);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), SecondActivity.class);
            intent.putExtra("citiesListWF", (Serializable) cities);
            startActivity(intent);
        });

        // если пришло нажатие на город с списке, то name того города, иначе дефолт = city = "London"
        if(name != null) {
            city = name;
        }
        String url = String.format(URL, city, API_KEY);
        final Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() || response.body() != null) {
                    try {
                        WeatherNow weatherNow = new WeatherParser(response.body().string()).parseWeatherNow();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String urlFormatted = "https://openweathermap.org/img/wn/%s@2x.png";
                                String icon = weatherNow.getIconId();
                                String urlForLoading = String.format(urlFormatted, icon);
                                Glide.with(view.getContext()).load(urlForLoading).into(weatherImage);
                            }
                        });

                        handler.post(() -> {
                                cityName.setText(weatherNow.getCityName());
                                weatherDescription.setText(weatherNow.getWeatherDescription());
                                boolean answer = sharedPreferences.getBoolean("isCelsius", true);
                                answerForAdapter = answer;
                                if (answer){
                                    String temp = kelvinToCelsius(weatherNow.getTemperature());
                                temperature.setText(temp);
                                } else {
                                    String temp = kelvinToFahrenheit(weatherNow.getTemperature());
                                    temperature.setText(temp);
                                }}
                        );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        url = String.format(URL2, city, API_KEY);
        final Request request1 = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() || response.body() != null) {
                    try {
                        List<WeatherStateItem> weatherStateItems = new WeatherParser(response.body().string()).parseHourlyForecast();
                        WeatherStateItemAdapter adapter = (WeatherStateItemAdapter) weatherStateRecycler.getAdapter();
                        adapter.setAnswer(answerForAdapter);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setWeatherStateItems(weatherStateItems);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
}
