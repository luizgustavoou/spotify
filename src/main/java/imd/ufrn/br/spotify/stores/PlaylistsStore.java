package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.User;

public class PlaylistsStore {
    private Playlist playlist;
    private PlaylistsStore() {}
    static public PlaylistsStore instance;
    static public PlaylistsStore getInstance() {
        if(instance == null) {
            instance = new PlaylistsStore();
        }
        return instance;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
