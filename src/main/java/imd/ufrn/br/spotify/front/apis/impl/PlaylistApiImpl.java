package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.IPlaylistApi;

import java.util.UUID;

public class PlaylistApiImpl implements IPlaylistApi {
    @Override
    public Playlist create(String name, UUID userId) {
        return null;
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {

    }

    @Override
    public void update(UUID id, String name, UUID userId) throws EntityNotFoundException {

    }
}
