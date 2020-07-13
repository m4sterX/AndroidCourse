package com.example.fishingapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import com.example.fishingapp.R;
import com.example.fishingapp.fragment.MapFragment;


public class MapActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        Intent intent = getIntent();
        Double lat = intent.getDoubleExtra("lat",0);
        Double lon = intent.getDoubleExtra("lon", 0);
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lon", lon);

        FragmentManager manager = getSupportFragmentManager();
        MapFragment mapFragment = new MapFragment();

        mapFragment.setArguments(bundle);
        manager.beginTransaction()
                .add(R.id.mapFragment_inLayout,mapFragment)
                .commit();
    }
}
