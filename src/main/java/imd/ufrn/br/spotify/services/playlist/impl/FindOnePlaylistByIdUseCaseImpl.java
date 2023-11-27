package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.IFindOnePlaylistByIdUseCase;

import java.util.UUID;

public class FindOnePlaylistByIdUseCaseImpl implements IFindOnePlaylistByIdUseCase {
    IPlaylistRepository playlistRepository;
    public FindOnePlaylistByIdUseCaseImpl() {
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
    }

    public FindOnePlaylistByIdUseCaseImpl(IPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    @Override
    public Playlist execute(UUID id) throws EntityNotFoundException {
        return this.playlistRepository.findOneById(id);
    }
}
