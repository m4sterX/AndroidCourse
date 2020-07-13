package com.example.fishingapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.R;
import com.example.fishingapp.fragment.OrdersFragment;

public class OrdersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_activity);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.order_activity_container, new OrdersFragment())
                .commit();
    }
}
