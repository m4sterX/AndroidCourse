package com.example.ht8.city_package;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ht8.Dao.CityDao;
import com.example.ht8.DataBaseSingleton;
import com.example.ht8.R;
import com.example.ht8.data_base.DataBase;
import com.example.ht8.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    interface CityOnClickListener {
        void onItemClick(CityEntity city);
    }
    private List<CityEntity> cities = new ArrayList<>();
    private CityOnClickListener listener;
    private Handler handler = new Handler();

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    public CitiesAdapter(CityOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        return new CitiesViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DataBase mDataBase = DataBaseSingleton.getInstance().getDataBase();
                CityDao cityDao = mDataBase.cityDao();
                List<CityEntity> listCities = cityDao.getAll();
                CityEntity mCity = listCities.get(position);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.bind(mCity);
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public int getItemCount() {
        return cities != null ? cities.size() : 0;
    }

    static class CitiesViewHolder extends RecyclerView.ViewHolder {
        private CityOnClickListener cityOnClickListener;
        private TextView cityNameItem;


        public CitiesViewHolder(@NonNull View itemView, @NonNull CityOnClickListener cityOnClickListener) {
            super(itemView);
            this.cityOnClickListener = cityOnClickListener;
                cityNameItem = itemView.findViewById(R.id.cityNameItem);
        }

        void bind(CityEntity city) {
            itemView.setOnClickListener(v -> cityOnClickListener.onItemClick(city));
                    cityNameItem.setText(city.getName());
            }
        }
        public void addCity(CityEntity city) {
        cities.add(city);
        notifyDataSetChanged();
        }
    }



