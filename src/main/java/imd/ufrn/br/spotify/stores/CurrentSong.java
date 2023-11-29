package imd.ufrn.br.spotify.stores;

import imd.ufrn.br.spotify.entities.Song;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

public class CurrentSong {
    private final SimpleObjectProperty<Song> song;
    private CurrentSong() {
        this.song = new SimpleObjectProperty<>();
    }
    static public CurrentSong instance;
    static public CurrentSong getInstance() {
        if(instance == null) {
            instance = new CurrentSong();
        }
        return instance;
    }

    public Song getSong() {
        return song.get();
    }

    public void update(Song song) {
        this.song.set(song);
    }

    public void addListener(ChangeListener<Song> listener) {
        song.addListener(listener);
    }
}