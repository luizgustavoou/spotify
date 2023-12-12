package imd.ufrn.br.spotify.back.services.playlist.impl;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.back.services.playlist.IUpdatePlaylistUseCase;

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

    public static void main(String[] args) throws EntityNotFoundException {
        IUpdatePlaylistUseCase updatePlaylistUseCase = new UpdatePlaylistUseCaseImpl();

        updatePlaylistUseCase.execute(UUID.fromString("881a3904-2c20-475c-a10b-0e1f272b2e22"), new Playlist(UUID.fromString("881a3904-2c20-475c-a10b-0e1f272b2e22"), "Playlist das piores", UUID.fromString("11498d8b-97ec-4907-a174-ae4f9e7596d2")));
    }
}
