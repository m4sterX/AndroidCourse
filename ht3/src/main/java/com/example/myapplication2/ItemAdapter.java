package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener((View.OnClickListener) v -> {
            onItemClickListener.onClick(position);
        });


        TextView name = holder.itemView.findViewById(R.id.contactNameReady);
        TextView emailOrNumber = holder.itemView.findViewById(R.id.contactEmailOrPhoneReady);
        ImageView imgv = holder.itemView.findViewById(R.id.imgButtonFromItem);
        Item item = Store.getStore().get(position);
        name.setText(item.getName());
        emailOrNumber.setText(item.getEmail());
        imgv.setImageResource(item.getSetSRC());
    }

    @Override
    public int getItemCount() {
        return Store.getStore().size();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
