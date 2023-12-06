package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.stores.PlaylistsStore;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PlaylistAddController {

    ICreatePlaylistUseCase createPlaylistUseCase;
    UserStore userStore;
    PlaylistsStore playlistsStore;

    public PlaylistAddController() {
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
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

        Playlist playlist = new Playlist(strNamePlaylist, userStore.getUser().getId());

        this.createPlaylistUseCase.execute(playlist);

        playlistsStore.updateAllPlaylistsOfUser(userStore.getUser().getId());
        this.playlistName.setText("");

    }

}
