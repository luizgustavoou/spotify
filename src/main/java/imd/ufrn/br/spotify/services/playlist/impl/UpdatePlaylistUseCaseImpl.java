package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.IUpdatePlaylistUseCase;

import java.util.UUID;

public class UpdatePlaylistUseCaseImpl implements IUpdatePlaylistUseCase {
    IPlaylistRepository playlistRepository;
    public UpdatePlaylistUseCaseImpl() {
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
    }

    public UpdatePlaylistUseCaseImpl(IPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    @Override
    public void execute(UUID id, Playlist value) throws EntityNotFoundException {
        this.playlistRepository.update(id, value);
    }
}
