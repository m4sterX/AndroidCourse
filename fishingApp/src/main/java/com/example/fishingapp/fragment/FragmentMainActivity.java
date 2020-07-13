package com.example.fishingapp.fragment;

import android.content.Intent;
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

import com.example.fishingapp.activity.LakeActivity;
import com.example.fishingapp.activity.OrdersActivity;
import com.example.fishingapp.adapters.LakesListAdapter;
import com.example.fishingapp.R;
import com.example.fishingapp.entity.Lake;
import com.example.fishingapp.entity.Location;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class FragmentMainActivity extends Fragment {

    private List<Lake> lakesList = new ArrayList<>();
    private FloatingActionButton order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_lakes_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        order = view.findViewById(R.id.floating_check_plans);
        order.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), OrdersActivity.class);
            startActivityForResult(intent, 0);
        });

        String name_1 = "Нарочанский национальный парк";
        String name_2 = "Чигиринское водохранилище";
        String name_3 = "Браславские озера";
        String name_4 = "Неман";
        String name_5 = "Вилия";

        int description_1 = R.string.description_1;
        int description_2 = R.string.description_2;
        int description_3 = R.string.description_3;
        int description_4 = R.string.description_4;
        int description_5 = R.string.description_5;

        int photo_1 = R.drawable.naroch2;
        int photo_2 = R.drawable.chigiri3;
        int photo_3 = R.drawable.braslavskie;
        int photo_4 = R.drawable.neman2;
        int photo_5 = R.drawable.vilia2;

        Location location_1 = new Location(54.8948, 26.6970);
        Location location_2 = new Location(53.4787, 29.8382);
        Location location_3 = new Location(55.6392, 27.0319);
        Location location_4 = new Location(55.0371, 22.0303);
        Location location_5 = new Location(53.8873, 27.5744);

        Lake lake_1 = new Lake(name_1, description_1, photo_1, location_1);
        Lake lake_2 = new Lake(name_2, description_2, photo_2, location_2);
        Lake lake_3 = new Lake(name_3, description_3, photo_3, location_3);
        Lake lake_4 = new Lake(name_4, description_4, photo_4, location_4);
        Lake lake_5 = new Lake(name_5, description_5, photo_5, location_5);

        lakesList.add(lake_1);
        lakesList.add(lake_2);
        lakesList.add(lake_3);
        lakesList.add(lake_4);
        lakesList.add(lake_5);

        RecyclerView lakesListRecycler = view.findViewById(R.id.recyclerLakesList);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        lakesListRecycler.addItemDecoration(dividerItemDecoration);
        lakesListRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        LakesListAdapter lakesListAdapter = new LakesListAdapter(lakesList, lake ->
        {
            Location location = lake.getLocation();
            Intent intent = new Intent(view.getContext(), LakeActivity.class);
            intent.putExtra("name", lake.getName());
            intent.putExtra("photoFile", lake.getPhotoFile());
            intent.putExtra("description", lake.getDescription());

            intent.putExtra("lat", location.getLatitude());
            intent.putExtra("lon", location.getLongitude());
            startActivity(intent);

        });
        lakesListRecycler.setAdapter(lakesListAdapter);
    }

}
