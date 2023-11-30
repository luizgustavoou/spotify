package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SongsStore {
    private final SimpleListProperty<Song> songs;
    private SongsStore() {
        this.songs = new SimpleListProperty<>(FXCollections.observableArrayList());
    }
    static public SongsStore instance;
    static public SongsStore getInstance() {
        if(instance == null) {
            instance = new SongsStore();
        }
        return instance;
    }

    public void clear() {
        songs.clear();
    }

    public void addSongs(List<Song> songs) {
        this.songs.addAll(songs);
    }

    public ObservableList<Song> getSongs() {
        return songs.get();
    }

    public SimpleListProperty<Song> songsProperty() {
        return songs;
    }

    public void addListener(ChangeListener<ObservableList<Song>> listener) {
        songs.addListener(listener);
    }
}
