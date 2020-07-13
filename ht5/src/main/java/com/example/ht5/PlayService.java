package com.example.ht5;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.List;

import static com.example.ht5.Channel.CHANNEL_ID;

public class PlayService extends Service {
    private MediaPlayer mediaPlayer;
    private final IBinder binder = new LocalBinder();
    private TestInterface test;
    private List<Song> songs;

    public class LocalBinder extends Binder {
        PlayService getService() {
            return PlayService.this;
        }
        void setTrack(TestInterface testInterface) {
            test = testInterface;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            songs = (List<Song>) intent.getSerializableExtra("songsList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int position = intent.getIntExtra("pos", 0);
        if (mediaPlayer == null) {

        }
        startMusic(songs.get(position).getSongRaw());


        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("title")
                .setContentText(songs.get(position).getSongName())
                .setSmallIcon(R.drawable.ic_play_arrow_black_24dp)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        Thread thread = new Thread(() -> mediaPlayer.start());
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public interface TestInterface {
        void onTrackChange();
    }
    public void stopMusic() {
        if (mediaPlayer != null)
        mediaPlayer.pause();
    }
    public void stopMediaPlayer() {
        mediaPlayer.stop();
    }
    public int resumeMusic() {
        if (mediaPlayer != null){
        if(!mediaPlayer.isPlaying()){
        mediaPlayer.start();
        return 1;
        } else {
            mediaPlayer.pause();
            return 2;
        }
        }
        return 2;
    }
    public void startMusic(int songRaw) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, songRaw);
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songRaw);
        }
    }
}
