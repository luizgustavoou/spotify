package imd.ufrn.br.spotify.back.controllers;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.back.services.playlist.IRemovePlaylistUseCase;
import imd.ufrn.br.spotify.back.services.playlist.IUpdatePlaylistUseCase;
import imd.ufrn.br.spotify.back.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.back.services.playlist.impl.RemovePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.back.services.playlist.impl.UpdatePlaylistUseCaseImpl;

import java.util.UUID;

public class PlaylistController {
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final IRemovePlaylistUseCase removePlaylistUseCase;
    private final IUpdatePlaylistUseCase updatePlaylistUseCase;

    public PlaylistController() {
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
        this.removePlaylistUseCase = new RemovePlaylistUseCaseImpl();
        this.updatePlaylistUseCase = new UpdatePlaylistUseCaseImpl();
    }

    public Playlist create(String name, UUID userId) {
        Playlist playlist = new Playlist(name, userId);

        return this.createPlaylistUseCase.execute(playlist);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.removePlaylistUseCase.execute(id);
    }

    public void update(UUID id, String name, UUID userId) throws EntityNotFoundException {
        Playlist playlist = new Playlist(id, name, userId);


        this.updatePlaylistUseCase.execute(id, playlist);
    }
}
