package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class HomeController {
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    public final ArrayList<Song> songs;
    public final ArrayList<Playlist> playlists;


    public HomeController(IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase, IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase) {
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        this.getAllSongsOfPlaylistUseCase = getAllSongsOfPlaylistUseCase;
        this.findAllPlaylistOfUserUseCase = findAllPlaylistOfUserUseCase;
    }

    public HomeController() {
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
    }

    public void getAllPlaylistsOfUser(String userId) {
        playlists.clear();

        playlists.addAll(findAllPlaylistOfUserUseCase.execute(UUID.fromString(userId)));

    }

    public void getAllSongsOfPlaylist(String playlistId) {
        songs.clear();

        songs.addAll(getAllSongsOfPlaylistUseCase.execute(UUID.fromString(playlistId)));


    }

    public static void main(String[] args) {
        HomeController homeController = new HomeController();

        homeController.getAllPlaylistsOfUser("12b2a592-722b-44db-ad94-3540658abeab"); // pegar playlists do joaozin10

        homeController.getAllSongsOfPlaylist(homeController.playlists.get(3).getId().toString());




    }

}
