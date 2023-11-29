package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;

import java.util.ArrayList;
import java.util.List;

public class SongsStore {
    private ArrayList<Song> songs;
    private SongsStore() {
        this.songs = new ArrayList<>();
    }
    static public SongsStore instance;
    static public SongsStore getInstance() {
        if(instance == null) {
            instance = new SongsStore();
        }
        return instance;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void clear() {
        songs.clear();
    }

    public void addSongs(List<Song> songs) {
        this.songs.addAll(songs);
    }
}
