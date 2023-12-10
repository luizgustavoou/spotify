package imd.ufrn.br.spotify;
import imd.ufrn.br.spotify.entities.Song;
import javafx.application.Platform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerImpl {

    public MediaPlayerImpl() {}
    public MediaPlayerImpl(List<Song> songs) {
        this.songs = new ArrayList<>(songs);
    }

    private ArrayList<Song> songs;
    private int currentSong = 0;
    private Media media;
    private MediaPlayer mediaPlayer;

    public void setSongs(List<Song> songs) {
        this.songs = new ArrayList<>(songs);
    }

    public void setCurrentSong(int currentSong) {
        this.currentSong = currentSong;
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

    public boolean hasNotSong() {
        return this.songs.isEmpty();
    }

    public void stopMedia() {
        if(this.mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) return;

        this.mediaPlayer.stop();
//        this.cancelTimer();
    }

    public void startMusicPlayback() {
        Platform.runLater(() -> {
            if(this.mediaPlayer != null && this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                this.stopMedia();
            }

            File file = new File(this.songs.get(this.currentSong).getPath());
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

//            this.beginTimer();

//            this.mediaPlayer.setOnEndOfMedia(this::nextSong);

        });

    }

    public double getDuration() {
        return this.media.getDuration().toSeconds();
    }

    public double getCurrentTime() {
        return this.mediaPlayer.getCurrentTime().toSeconds();

    }
}
