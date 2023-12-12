package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.ISongApi;

import java.util.UUID;

public class SongApiImpl implements ISongApi {
    @Override
    public Song create(String name, String path, UUID playlistId) {
        return null;
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {

    }

    @Override
    public void update(UUID id, String name, String path, UUID playlistId) throws EntityNotFoundException {

    }
}
