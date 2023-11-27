package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistsUseCase;

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
}
