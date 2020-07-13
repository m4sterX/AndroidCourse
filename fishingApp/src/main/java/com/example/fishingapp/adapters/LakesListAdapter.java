package com.example.fishingapp.adapters;

import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;
import com.example.fishingapp.entity.Lake;


import java.util.List;

public class LakesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListenerM onItemClickListener;
    private List<Lake> lakeList;


    public LakesListAdapter(List<Lake> lakeList, OnItemClickListenerM onItemClickListener) {
        this.lakeList = lakeList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lake_layout, parent, false)){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Lake lake = lakeList.get(position);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick((lake)));

        TextView name = holder.itemView.findViewById(R.id.TV_titleMain);
        ImageView lakeImg = holder.itemView.findViewById(R.id.imageView_lakeImage);

        name.setText(lake.getName());
        lakeImg.setImageResource(lake.getPhotoFile());
    }

    @Override
    public int getItemCount() {
        return lakeList != null ? lakeList.size() : 0;
    }
    public interface OnItemClickListenerM{
        void onItemClick(Lake lake);
    }
}
