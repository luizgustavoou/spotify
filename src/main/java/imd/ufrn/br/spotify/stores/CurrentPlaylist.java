package imd.ufrn.br.spotify.stores;


import imd.ufrn.br.spotify.entities.Playlist;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

public class CurrentPlaylist {
    private final SimpleObjectProperty<Playlist> playlist;
    private CurrentPlaylist() {
        this.playlist = new SimpleObjectProperty<>();
    }
    static public CurrentPlaylist instance;
    static public CurrentPlaylist getInstance() {
        if(instance == null) {
            instance = new CurrentPlaylist();
        }
        return instance;
    }

    public Playlist getPlaylist() {
        return playlist.get();
    }

    public SimpleObjectProperty<Playlist> playlistProperty() {
        return playlist;
    }

    public void update(Playlist playlist) {
        this.playlist.set(playlist);
    }

    public void addListener(ChangeListener<Playlist> listener) {
        playlist.addListener(listener);
    }
}

