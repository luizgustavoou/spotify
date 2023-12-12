package imd.ufrn.br.spotify.back.services.playlist.impl;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.back.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.back.services.playlist.IFindAllPlaylistsUseCase;

import java.util.List;

public class FindAllPlaylistsUseCaseImpl implements IFindAllPlaylistsUseCase {
    IPlaylistRepository playlistRepository;
    public FindAllPlaylistsUseCaseImpl() {
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
    }

    public FindAllPlaylistsUseCaseImpl(IPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    @Override
    public List<Playlist> execute() {
        return this.playlistRepository.findAll();
    }

    public static void main(String[] args) {
        IFindAllPlaylistsUseCase findAllPlaylistsUseCase = new FindAllPlaylistsUseCaseImpl();

        System.out.println(findAllPlaylistsUseCase.execute());
    }
}
