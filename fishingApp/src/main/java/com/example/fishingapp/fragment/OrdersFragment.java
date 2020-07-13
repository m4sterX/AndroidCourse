package com.example.fishingapp.fragment;

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

import com.example.fishingapp.R;
import com.example.fishingapp.adapters.OrdersListAdapter;
import com.example.fishingapp.dao.OrdersDao;
import com.example.fishingapp.date_base.OrdersDatabase;
import com.example.fishingapp.entity.OrderEntity;

import java.util.List;

public class OrdersFragment extends Fragment {

    private List<OrderEntity> mOrderEntityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.orders_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OrdersDatabase ordersDatabase = OrdersDatabase.getINSTANCE(view.getContext());
                OrdersDao ordersDao = ordersDatabase.getOrdersDao();
                mOrderEntityList = ordersDao.getAll();
            }
        });
        thread.start();
        RecyclerView ordersListRecycler = view.findViewById(R.id.recyclerOrdersList);
        ordersListRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        DividerItemDecoration decoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL);
        ordersListRecycler.addItemDecoration(decoration);
        ordersListRecycler.setAdapter(new OrdersListAdapter(position -> {
        }));

        OrdersListAdapter adapter = (OrdersListAdapter) ordersListRecycler.getAdapter();

            adapter.setOrderEntityList(mOrderEntityList);
            adapter.notifyDataSetChanged();
    }
}
