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

    public static void main(String[] args) throws EntityNotFoundException {
        IRemovePlaylistUseCase removePlaylistUseCase = new RemovePlaylistUseCaseImpl();

        removePlaylistUseCase.execute(UUID.fromString("69c06b05-262b-4fe8-bcf0-c9a035f910bb"));
    }
}
