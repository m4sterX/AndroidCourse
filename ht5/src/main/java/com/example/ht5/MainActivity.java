package com.example.ht5;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PlayService playService;
    private boolean mBound;
    private ItemAdapter mAdapter;
    private ImageButton imageButtonState;
    private int positionState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("song_1", R.raw.song1));
        songs.add(new Song("song_2", R.raw.song2));
        songs.add(new Song("song_3", R.raw.song3));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ItemAdapter adapter = new ItemAdapter(this);

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        ItemAdapter itemA = (ItemAdapter) recyclerView.getAdapter();
        mAdapter = itemA;
        assert itemA != null;
        itemA.setSongs(songs);

        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), PlayService.class);
                intent.putExtra("pos", position);
                intent.putExtra("songsList", (Serializable) songs);
                ImageButton playButton  = v.findViewById(R.id.buttonPlayItem);

                if (positionState != position) {
                    if(imageButtonState != null) {
                    imageButtonState.setVisibility(View.INVISIBLE);
                    }
                }
                positionState = position;
                imageButtonState = playButton;

                ContextCompat.startForegroundService(getApplicationContext(), intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    // Connecting to service
    private ServiceConnection connection = new ServiceConnection() {
        private ImageButton imageButton;
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayService.LocalBinder binder = (PlayService.LocalBinder) service;
            playService = binder.getService();
            mAdapter.setOnButtonClickListener(new ItemAdapter.OnButtonClickListener() {
                @Override
                public void onButtonClick(View v, int position) {
                    int answer = playService.resumeMusic();
                    ImageButton playButton  = v.findViewById(R.id.buttonPlayItem);
                    imageButton = playButton;
                    if (answer == 1) {
                        playButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        playButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }
                }
            });
                    mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
