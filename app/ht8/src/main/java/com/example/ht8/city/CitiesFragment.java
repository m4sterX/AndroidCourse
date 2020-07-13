package com.example.ht8.city;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ht8.entity.CityEntity;
import com.example.ht8.activities.MainActivity;
import com.example.ht8.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CitiesFragment extends Fragment {
    private List<CityEntity> cities = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cities = (List<CityEntity>) getArguments().getSerializable("citiesListSA");
        RecyclerView recyclerCitiesList = view.findViewById(R.id.citiesListRecycler);
        recyclerCitiesList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerCitiesList.setHasFixedSize(true);
        CitiesAdapter citiesAdapter = new CitiesAdapter(new CitiesAdapter.CityOnClickListener() {
            @Override
            public void onItemClick(CityEntity city) {
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("SHEARED_WIDGET", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("CITY_NAME_WIDGET", city.getName());
                editor.apply();

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("CITY_NAME", city.getName());
                startActivityForResult(intent,0);
            }
        });
        recyclerCitiesList.setAdapter(citiesAdapter);

        CitiesAdapter adapter = (CitiesAdapter) recyclerCitiesList.getAdapter();
        adapter.setCities(cities);
        adapter.notifyDataSetChanged();
        DividerItemDecoration dividerItemDecoration =  new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        recyclerCitiesList.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fl = view.findViewById(R.id.FloatingActionCities);
        fl.setOnClickListener(v -> {
            CityDialog dialog = new CityDialog();
            dialog.show(getActivity().getSupportFragmentManager(),"TAG");
        });
    }
}
