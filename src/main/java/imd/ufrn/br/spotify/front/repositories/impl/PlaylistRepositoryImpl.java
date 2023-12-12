package imd.ufrn.br.spotify.front.repositories.impl;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.IPlaylistApi;
import imd.ufrn.br.spotify.front.apis.impl.PlaylistApiImpl;

import java.util.UUID;

public class PlaylistRepositoryImpl {
    private final IPlaylistApi playlistApi;

    public PlaylistRepositoryImpl() {
        this.playlistApi = new PlaylistApiImpl();
    }

    public Playlist create(String name, UUID userId) {
        return this.playlistApi.create(name, userId);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.playlistApi.remove(id);
    }

    public void update(UUID id, String name, UUID userId) throws EntityNotFoundException {
        this.playlistApi.update(id, name, userId);
    }
}
