package imd.ufrn.br.spotify.front.controllers;


import imd.ufrn.br.spotify.back.controllers.SongController;
import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.stores.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class SongEditControllerFXML  {

    Song song;
    private final SongController songController;

    private final SongsStore songStore;


    public SongEditControllerFXML() {
        this.songController = new SongController();
        this.songStore = SongsStore.getInstance();
    }

    public void setSong(Song song) {
        this.song = song;
        this.songName.setText(song.getName());
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

        this.songController.update(song.getId(), strSongName, song.getPath() ,song.getPlaylistId());

        songStore.updateAllSongsOfPlaylist(song.getPlaylistId());
    }


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.songName.setText(song.getName());
//    }
}
