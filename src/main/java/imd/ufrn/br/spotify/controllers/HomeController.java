package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class HomeController {
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final ArrayList<Song> songs;


    public HomeController(IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase) {
        songs = new ArrayList<>();
        this.getAllSongsOfPlaylistUseCase = getAllSongsOfPlaylistUseCase;
    }

    public HomeController() {
        songs = new ArrayList<>();
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
    }

    public void getAllSongsOfPlaylist(UUID playlistId) {
//        ArrayList<Song> songs = new ArrayList<>(getAllSongsOfPlaylistUseCase.execute(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9")));
        System.out.println(this.getAllSongsOfPlaylistUseCase.execute(playlistId));

//        System.out.println(songs);

//        System.out.println(songs);



    }

    public static void main(String[] args) {
        HomeController homeController = new HomeController();

        homeController.getAllSongsOfPlaylistUseCase.execute(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9"));  // Suponha que a playlist selecionada seja "Playlist boazinha"




    }

}
