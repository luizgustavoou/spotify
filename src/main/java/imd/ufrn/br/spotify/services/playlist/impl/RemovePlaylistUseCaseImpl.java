package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.IRemovePlaylistUseCase;

import java.util.UUID;

public class RemovePlaylistUseCaseImpl implements IRemovePlaylistUseCase {
    IPlaylistRepository playlistRepository;
    public RemovePlaylistUseCaseImpl() {
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
    }

    public RemovePlaylistUseCaseImpl(IPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.playlistRepository.remove(id);
    }
}
