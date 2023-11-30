package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Playlist;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistsStore {
    private final SimpleListProperty<Playlist> playlists;
    private PlaylistsStore() {
        this.playlists = new SimpleListProperty<>(FXCollections.observableArrayList());
    }
    static public PlaylistsStore instance;
    static public PlaylistsStore getInstance() {
        if(instance == null) {
            instance = new PlaylistsStore();
        }
        return instance;
    }

    public void clear() {
        playlists.clear();
    }

    public void addPlaylists(List<Playlist> playlists) {
        this.playlists.addAll(playlists);
    }

    public ObservableList<Playlist> getPlaylists() {
        return playlists.get();
    }

    public Property<ObservableList<Playlist>> getObservablePlaylist() {
        return this.playlists;
    }

    public SimpleListProperty<Playlist> playlistsProperty() {
        return playlists;
    }

    public void addListener(ChangeListener<ObservableList<Playlist>> listener) {
        playlists.addListener(listener);
    }
}
