package com.example.ht5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Song> songs = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnButtonClickListener onButtonClickListener;

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onClick(view, position);
            ImageButton b = holder.itemView.findViewById(R.id.buttonPlayItem);
            if(b.getVisibility() == View.INVISIBLE) {
                b.setVisibility(View.VISIBLE);
                b.setImageResource(R.drawable.ic_pause_black_24dp);
            }
        });
        holder.itemView.findViewById(R.id.buttonPlayItem).setOnClickListener(v -> {
            onButtonClickListener.onButtonClick(v , position);
        });
        TextView songName = holder.itemView.findViewById(R.id.songNameItem);
        songName.setText(songs.get(position).getSongName());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }
    public interface OnButtonClickListener {
        void onButtonClick(View v, int position);
    }
}
