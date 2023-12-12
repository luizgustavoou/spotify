package imd.ufrn.br.spotify.front.stores;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.back.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.back.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.UUID;

public class PlaylistsStore {
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    private final SimpleListProperty<Playlist> playlists;
    private PlaylistsStore() {
        this.playlists = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
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

    public void updateAllPlaylistsOfUser(UUID userId) {
        this.playlists.clear();
        this.addPlaylists(findAllPlaylistOfUserUseCase.execute(userId));
    }
}
