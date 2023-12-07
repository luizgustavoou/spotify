package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.stores.CurrentPlaylist;
import imd.ufrn.br.spotify.stores.PlaylistsStore;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistEditControllerFXML implements Initializable {
    private final PlaylistController playlistController;

    private final PlaylistsStore playlistsStore;
    private final UserStore userStore;
    private final CurrentPlaylist currentPlaylist;

    public PlaylistEditControllerFXML() {
        this.playlistController = new PlaylistController();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.userStore = UserStore.getInstance();
        this.currentPlaylist = CurrentPlaylist.getInstance();
    }
    @FXML
    private TextField playlistName;

    @FXML
    void updatePlaylist(MouseEvent event) throws EntityNotFoundException {
        String strNamePlaylist = playlistName.getText();

        if(strNamePlaylist.isEmpty()) {
            System.out.println("Nome da playlist vázia!");
            return;
        }


        this.playlistController.update(playlistsStore.getPlaylists().get(currentPlaylist.getIndex()).getId() , strNamePlaylist, userStore.getId());

        playlistsStore.updateAllPlaylistsOfUser(userStore.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.playlistName.setText(playlistsStore.getPlaylists().get(currentPlaylist.getIndex()).getName());
    }
}
