package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.UUID;

public class SongsStore {
    private final SimpleListProperty<Song> songs;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private SongsStore() {
        this.songs = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
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

    public Property<ObservableList<Song>> getObservableSong() {
        return this.songs;
    }
    public SimpleListProperty<Song> songsProperty() {
        return songs;
    }

    public void addListener(ChangeListener<ObservableList<Song>> listener) {
        songs.addListener(listener);
    }

    public void updateAllSongsOfPlaylist(UUID playlistId) {
        this.songs.clear();
        this.addSongs(this.getAllSongsOfPlaylistUseCase.execute(playlistId));
    }
}
