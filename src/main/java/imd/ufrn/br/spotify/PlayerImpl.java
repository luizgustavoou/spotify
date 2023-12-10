package imd.ufrn.br.spotify;
import imd.ufrn.br.spotify.entities.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerImpl {

    public PlayerImpl() {}
    public PlayerImpl(Callback beginTimer, Callback cancelTimer) {
        this.beginTimer = beginTimer;
        this.cancelTimer = cancelTimer;
    }
    private Callback beginTimer;
    private Callback cancelTimer;

    private ArrayList<Song> songs;
    private int currentSong = 0;
    private Media media;
    private MediaPlayer mediaPlayer;

    public void setSongs(List<Song> songs) {
        this.songs = new ArrayList<>(songs);
    }

    private boolean hasNotSong() {
        return this.songs.isEmpty();
    }

    public void playMedia() {
        if(this.hasNotSong()) return;

        this.startMusicPlayback();

    }

    public void pauseMedia() {
        if(this.mediaPlayer == null) return;

        if(this.mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            this.mediaPlayer.play();
        }else if(this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            this.mediaPlayer.pause();
        }
    }

    public void stopPlayBack() {
        if(this.mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) return;

        this.mediaPlayer.stop();
        this.cancelTimer.execute();
    }

    public void startMusicPlayback() {
        if(this.mediaPlayer != null && this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            this.stopPlayBack();
        }

        File file = new File(this.songs.get(this.currentSong).getPath());
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        this.beginTimer.execute();

        this.mediaPlayer.setOnEndOfMedia(this::nextSong);
    }

    private void updateCurrentSong(int index) {
        if(songs.isEmpty()) {
            this.currentSong = -1;
            return;
        }

        this.currentSong = index % this.songs.size();
    }

    public void selectSong(int value) {
        this.updateCurrentSong(value);
    }

    public void previousSong() {
        if(this.hasNotSong()) return;

        if(this.currentSong == 0) {
            this.updateCurrentSong(this.songs.size() -1);
            this.startMusicPlayback();
        }
        else {
            this.updateCurrentSong(this.currentSong - 1);
            this.startMusicPlayback();
        }
    }

    public void nextSong() {
        if(this.hasNotSong()) return;

        this.updateCurrentSong(this.currentSong + 1);
        this.startMusicPlayback();

    }

    public double getDuration() {
        return this.media.getDuration().toSeconds();
    }

    public double getCurrentTime() {
        return this.mediaPlayer.getCurrentTime().toSeconds();

    }
}
