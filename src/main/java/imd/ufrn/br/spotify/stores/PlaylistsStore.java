package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.User;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsStore {
    private ArrayList<Playlist> playlists;
    private PlaylistsStore() {
        playlists = new ArrayList<>();
    }
    static public PlaylistsStore instance;
    static public PlaylistsStore getInstance() {
        if(instance == null) {
            instance = new PlaylistsStore();
        }
        return instance;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void clear() {
        playlists.clear();
    }

    public void addPlaylists(List<Playlist> playlists) {
        this.playlists.addAll(playlists);
    }
}
