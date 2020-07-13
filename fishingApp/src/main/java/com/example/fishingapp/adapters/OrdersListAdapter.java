package com.example.fishingapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;
import com.example.fishingapp.entity.OrderEntity;

import java.util.List;

public class OrdersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderEntity> orderEntityList;
    private OnMyItemClickListener onMyItemClickListener;

    public OrdersListAdapter(OnMyItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            onMyItemClickListener.onClick(position);
        });
        TextView title = holder.itemView.findViewById(R.id.mTitle);
        TextView hours = holder.itemView.findViewById(R.id.mHours);
        TextView house = holder.itemView.findViewById(R.id.mHouse);
        TextView equip = holder.itemView.findViewById(R.id.mEquipment);
        OrderEntity orderEntity = orderEntityList.get(position);
        title.setText(orderEntity.getName());
        hours.setText(orderEntity.getHoursCount());
        house.setText(orderEntity.getHouse());
        equip.setText(orderEntity.getEquipment());
    }

    @Override
    public int getItemCount() {
        return orderEntityList != null ? orderEntityList.size() : 0;
    }

    public interface OnMyItemClickListener {
        void onClick(int position);
    }
}
