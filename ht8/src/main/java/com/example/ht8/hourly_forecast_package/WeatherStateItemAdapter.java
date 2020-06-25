package com.example.ht8.hourly_forecast_package;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ht8.R;

import java.util.ArrayList;
import java.util.List;

public class WeatherStateItemAdapter extends RecyclerView.Adapter<WeatherStateItemAdapter.NewsItemViewHolder> {

    private List<WeatherStateItem> weatherStateItems = new ArrayList<>();
    private boolean answer;
    public void setWeatherStateItems(List<WeatherStateItem> weatherStateItems) {
        this.weatherStateItems = weatherStateItems;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.bind(weatherStateItems.get(position));
    }

    @Override
    public int getItemCount() {
        return (weatherStateItems != null) ? weatherStateItems.size() : 0;
    }

    public void addAllItems(@NonNull List<WeatherStateItem> weatherStateItems) {
        this.weatherStateItems.addAll(weatherStateItems);
        notifyDataSetChanged();
    }

     class NewsItemViewHolder extends RecyclerView.ViewHolder {
        public NewsItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private ImageView weatherImage = itemView.findViewById(R.id.weather_image);
        private TextView time = itemView.findViewById(R.id.time);
        private TextView description = itemView.findViewById(R.id.description);
        private TextView temperature = itemView.findViewById(R.id.temperature);

        void bind(WeatherStateItem weatherStateItem) {
            time.setText(weatherStateItem.getTime1());
            description.setText(weatherStateItem.getDescription1());
            if (answer) {
                temperature.setText(kelvinToCelsius(weatherStateItem.getTemperature1()));
            } else {
                temperature.setText(kelvinToFahrenheit(weatherStateItem.getTemperature1()));
            }
            String urlF = "https://openweathermap.org/img/wn/%s@2x.png";
            String icon = weatherStateItem.getWeatherImage1();
            String url = String.format(urlF, icon);
            Glide.with(itemView.getContext()).load(url).into(weatherImage);
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
    }

