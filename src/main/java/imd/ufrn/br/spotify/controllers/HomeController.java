package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class HomeController implements Initializable {
    private final UserStore userStore;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    public final ArrayList<Song> songs;
    public final ArrayList<Playlist> playlists;


    public HomeController(IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase, IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase) {
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        this.getAllSongsOfPlaylistUseCase = getAllSongsOfPlaylistUseCase;
        this.findAllPlaylistOfUserUseCase = findAllPlaylistOfUserUseCase;
        this.userStore = UserStore.getInstance();
    }

    public HomeController() {
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
        this.userStore = UserStore.getInstance();
    }

    public void getAllPlaylistsOfUser(String userId) {
        playlists.clear();

        playlists.addAll(findAllPlaylistOfUserUseCase.execute(UUID.fromString(userId)));

    }

    public void getAllSongsOfPlaylist(String playlistId) {
        songs.clear();

        songs.addAll(getAllSongsOfPlaylistUseCase.execute(UUID.fromString(playlistId)));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = userStore.getUser();
        if (user != null) {
            System.out.println(user.getId());
        } else {
            System.out.println("User is null");
        }
    }
}
