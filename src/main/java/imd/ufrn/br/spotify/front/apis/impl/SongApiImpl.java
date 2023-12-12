package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.controllers.SongController;
import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.ISongApi;

import java.util.UUID;

public class SongApiImpl implements ISongApi {
    SongController songController;

    public SongApiImpl() {
        this.songController = new SongController();
    }
    @Override
    public Song create(String name, String path, UUID playlistId) {
        return this.songController.create(name, path, playlistId);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        this.songController.remove(id);
    }

    @Override
    public void update(UUID id, String name, String path, UUID playlistId) throws EntityNotFoundException {
        this.songController.update(id, name, path, playlistId);
    }
}
