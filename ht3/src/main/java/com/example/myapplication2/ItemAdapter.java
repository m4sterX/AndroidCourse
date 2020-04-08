package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public ItemAdapter(Context context) {
    this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(context, EditContact.class);
                    intent.putExtra("index", index);
                    context.startActivity(intent);
                }
        );
        TextView name = holder.itemView.findViewById(R.id.contactNameReady);
        TextView emailOrNumber = holder.itemView.findViewById(R.id.contactEmailOrPhoneReady);
        ImageView imgv = holder.itemView.findViewById(R.id.imgButtonFromItem);
        Item item = Store.getStore().get(index);
        name.setText(item.getName());
        emailOrNumber.setText(item.getEmail());
        imgv.setImageResource(item.getSetSRC());
    }

    @Override
    public int getItemCount() {
        return Store.getStore().size();
    }
}
