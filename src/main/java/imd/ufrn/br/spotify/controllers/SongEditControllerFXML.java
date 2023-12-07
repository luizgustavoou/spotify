package imd.ufrn.br.spotify.controllers;


import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.stores.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SongEditControllerFXML implements Initializable {
    private final SongController songController;

    private final SongsStore songStore;
    private final PlaylistsStore playlistsStore;
    private final CurrentPlaylist currentPlaylist;
    private final CurrentSong currentSong;


    public SongEditControllerFXML() {
        this.songController = new SongController();
        this.songStore = SongsStore.getInstance();

        this.currentPlaylist = CurrentPlaylist.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.currentSong = CurrentSong.getInstance();
    }
    @FXML
    private TextField songName;

    @FXML
    void updateSong(MouseEvent event) throws EntityNotFoundException {
        String strSongName = songName.getText();

        if(strSongName.isEmpty()) {
            System.out.println("Nome da playlist vázia!");
            return;
        }

        this.songController.update(songStore.getSongs().get(currentSong.getIndex()).getId(), strSongName, songStore.getSongs().get(currentSong.getIndex()).getPath() ,songStore.getSongs().get(currentSong.getIndex()).getPlaylistId());

        songStore.updateAllSongsOfPlaylist(playlistsStore.getPlaylists().get(currentPlaylist.getIndex()).getId());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.songName.setText(songStore.getSongs().get(currentSong.getIndex()).getName());
    }
}
