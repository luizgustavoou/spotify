package imd.ufrn.br.spotify.front.repositories.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.ISongApi;
import imd.ufrn.br.spotify.front.apis.impl.SongApiImpl;
import imd.ufrn.br.spotify.front.repositories.ISongRepository;

import java.util.UUID;

public class SongRepositoryImpl implements ISongRepository {
    private final ISongApi songApi;

    public SongRepositoryImpl() {
        this.songApi = new SongApiImpl();
    }

    public Song create(String name, String path, UUID playlistId) {
        return this.songApi.create(name, path, playlistId);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.songApi.remove(id);
    }

    public void update(UUID id, String name, String path, UUID playlistId) throws EntityNotFoundException {
        this.songApi.update(id, name, path, playlistId);
    }
}
