package imd.ufrn.br.spotify.front.services;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IPlaylistService {
    public Playlist create(String name, UUID userId);

    public void remove(UUID id) throws EntityNotFoundException;

    public void update(UUID id, String name, UUID userId) throws EntityNotFoundException;
}
