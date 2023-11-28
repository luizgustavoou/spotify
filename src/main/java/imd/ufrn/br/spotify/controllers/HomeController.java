package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;

public class HomeController {
    IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;

    public HomeController(IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase) {
        this.getAllSongsOfPlaylistUseCase = getAllSongsOfPlaylistUseCase;
    }

    public HomeController() {
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
    }

    public static void main(String[] args) {
        HomeController homeController = new HomeController();
    }

}
