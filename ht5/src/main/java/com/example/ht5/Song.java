package com.example.ht5;


import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable {
    private String songName;
    private int songRaw;

    public Song(String songName, int songRaw) {
        this.songName = songName;
        this.songRaw = songRaw;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongRaw() {
        return songRaw;
    }

    public void setSongRaw(int songRaw) {
        this.songRaw = songRaw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songRaw == song.songRaw &&
                Objects.equals(songName, song.songName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName, songRaw);
    }
}
