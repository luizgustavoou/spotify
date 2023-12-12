package imd.ufrn.br.spotify.front.lib;

import imd.ufrn.br.spotify.back.entities.Song;

import java.util.ArrayList;
import java.util.List;

public interface Player {
    public void playMedia();

    public void pauseMedia();

    public void stopPlayBack();

    public void startMusicPlayback();

    public void selectSong(int value);

    public void previousSong();

    public void nextSong();

    public double getDuration();

    public double getCurrentTime();
    public void setSongs(List<Song> songs);

}
