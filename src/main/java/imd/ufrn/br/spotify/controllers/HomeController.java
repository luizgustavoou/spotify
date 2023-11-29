package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;
import imd.ufrn.br.spotify.stores.PlaylistsStore;
import imd.ufrn.br.spotify.stores.SongsStore;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class HomeController implements Initializable {
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;


    public HomeController(IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase, IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase) {
        this.getAllSongsOfPlaylistUseCase = getAllSongsOfPlaylistUseCase;
        this.findAllPlaylistOfUserUseCase = findAllPlaylistOfUserUseCase;
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();
    }

    public HomeController() {
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();
    }

    public void getAllPlaylistsOfUser(String userId) {
        playlistsStore.clear();

        playlistsStore.addPlaylists(findAllPlaylistOfUserUseCase.execute(UUID.fromString(userId)));
    }

    public void getAllSongsOfPlaylist(String playlistId) {
        songsStore.clear();

        songsStore.addSongs(getAllSongsOfPlaylistUseCase.execute(UUID.fromString(playlistId)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = userStore.getUser();
        if (user != null) {
            this.getAllPlaylistsOfUser(user.getId().toString());


            if(!playlistsStore.getPlaylists().isEmpty()) {
                this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(0).getId().toString());
                System.out.println(playlistsStore.getPlaylists());
                System.out.println(songsStore.getSongs());

            }

        } else {
            System.out.println("User is null");
        }
    }
}
