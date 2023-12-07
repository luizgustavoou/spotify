package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.stores.PlaylistsStore;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PlaylistAddControllerFXML {

    PlaylistController playlistController;
    UserStore userStore;
    PlaylistsStore playlistsStore;

    public PlaylistAddControllerFXML() {
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.playlistController = new PlaylistController();
    }
    @FXML
    private TextField playlistName;

    @FXML
    void addPlaylist(MouseEvent event) {
        String strNamePlaylist = playlistName.getText();

        if(strNamePlaylist.isEmpty()) {
            System.out.println("Nome da playlist vázia!");
            return;
        }


        this.playlistController.create(strNamePlaylist, userStore.getId());

        playlistsStore.updateAllPlaylistsOfUser(userStore.getId());
        this.playlistName.setText("");

    }

}
