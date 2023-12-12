package imd.ufrn.br.spotify.front.apis;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface ISongApi {
    public Song create(String name, String path, UUID playlistId);

    public void remove(UUID id) throws EntityNotFoundException;

    public void update(UUID id, String name, String path, UUID playlistId) throws EntityNotFoundException;
}
