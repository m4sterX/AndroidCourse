package com.example.fishingapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import com.example.fishingapp.ConfirmDialog;
import com.example.fishingapp.R;

import com.example.fishingapp.dao.OrdersDao;
import com.example.fishingapp.date_base.OrdersDatabase;

import com.example.fishingapp.entity.OrderEntity;
import com.example.fishingapp.fragment.OrderFragment;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import static com.example.fishingapp.date_base.OrdersDatabase.getINSTANCE;


public class OrderActivity extends AppCompatActivity implements ConfirmDialog.ConfirmListener {
    private OrderFragment orderFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        orderFragment.setArguments(bundle);
        this.orderFragment = orderFragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.orderActivity_cont, orderFragment)
                .commit();
    }

    @Override
    public void confirmOrder(String[] strings) {
        OrdersDatabase database = OrdersDatabase.getINSTANCE(getApplicationContext());
        ExecutorService executorService = database.getDataBaseExecutorService();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                OrderEntity orderEntity = new OrderEntity(strings[0], strings[1], strings[2], strings[3]);
                OrdersDao ordersDao = database.getOrdersDao();
                ordersDao.insert(orderEntity);
            }
        });
    }
}
