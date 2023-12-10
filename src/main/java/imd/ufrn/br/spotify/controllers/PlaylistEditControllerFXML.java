package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.stores.PlaylistsStore;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



public class PlaylistEditControllerFXML  {
    private Playlist playlist;
    private final PlaylistController playlistController;
    private final PlaylistsStore playlistsStore;

    public PlaylistEditControllerFXML() {
        this.playlistController = new PlaylistController();
        this.playlistsStore = PlaylistsStore.getInstance();
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        this.playlistName.setText(playlist.getName());

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


        this.playlistController.update(playlist.getId() , strNamePlaylist, playlist.getUserId());

        playlistsStore.updateAllPlaylistsOfUser(playlist.getUserId());
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.playlistName.setText(playlist.getName());
//    }
}
