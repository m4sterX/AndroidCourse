package com.example.ht8.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ht8.DataBaseSingleton;
import com.example.ht8.R;
import com.example.ht8.city.CitiesAdapter;
import com.example.ht8.city.CitiesFragment;
import com.example.ht8.city.CityDialog;
import com.example.ht8.dao.CityDao;
import com.example.ht8.data_base.DataBase;
import com.example.ht8.entity.CityEntity;

import java.io.Serializable;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements CityDialog.CityDialogListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setSupportActionBar(findViewById(R.id.SecondActivityToolbar));

        List<CityEntity> citiesListWF = (List<CityEntity>) getIntent().getSerializableExtra("citiesListWF");
        CitiesFragment citiesFragment = new CitiesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("citiesListSA", (Serializable) citiesListWF);
        citiesFragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.secondActContainer,citiesFragment)
                .commit();
    }

    @Override
    public void applyCity(String cityName) {
        Thread thread = new Thread(() -> {
            DataBase mDataBase = DataBaseSingleton.getInstance().getDataBase();
            CityDao cityDao = mDataBase.cityDao();
            CityEntity cityEntity = new CityEntity(cityName);
            cityDao.add(cityEntity);
        });
        thread.start();
        RecyclerView recyclerView = findViewById(R.id.citiesListRecycler);
        CitiesAdapter citiesAdapter = (CitiesAdapter) recyclerView.getAdapter();
        citiesAdapter.addCity(new CityEntity(cityName));
        citiesAdapter.notifyDataSetChanged();
    }
}
