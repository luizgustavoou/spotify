package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.IRemovePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.impl.RemovePlaylistUseCaseImpl;

import java.util.UUID;

public class PlaylistController {
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final IRemovePlaylistUseCase removePlaylistUseCase;

    public PlaylistController() {
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
        this.removePlaylistUseCase = new RemovePlaylistUseCaseImpl();
    }

    public Playlist create(String name, UUID userId) {
        Playlist playlist = new Playlist(name, userId);

        return this.createPlaylistUseCase.execute(playlist);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.removePlaylistUseCase.execute(id);
    }
}
