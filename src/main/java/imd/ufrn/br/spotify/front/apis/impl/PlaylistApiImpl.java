package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.controllers.PlaylistController;
import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.IPlaylistApi;

import java.util.UUID;

public class PlaylistApiImpl implements IPlaylistApi {
    PlaylistController playlistController;

    public PlaylistApiImpl() {
        this.playlistController = new PlaylistController();

    }
    @Override
    public Playlist create(String name, UUID userId) {
        return this.playlistController.create(name, userId);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        this.playlistController.remove(id);
    }

    @Override
    public void update(UUID id, String name, UUID userId) throws EntityNotFoundException {
        this.playlistController.update(id, name, userId);
    }
}
