package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Song;

public class SongsStore {
    private Song song;
    private SongsStore() {}
    static public SongsStore instance;
    static public SongsStore getInstance() {
        if(instance == null) {
            instance = new SongsStore();
        }
        return instance;
    }

    public Song getSong() {
        return this.song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
