package imd.ufrn.br.spotify.front.services.impl;

import imd.ufrn.br.spotify.back.entities.Song;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.repositories.ISongRepository;
import imd.ufrn.br.spotify.front.repositories.impl.SongRepositoryImpl;
import imd.ufrn.br.spotify.front.services.ISongService;

import java.util.UUID;

public class SongServiceImpl implements ISongService {
    private final ISongRepository songRepository;

    public SongServiceImpl() {
        this.songRepository = new SongRepositoryImpl();
    }

    @Override
    public Song create(String name, String path, UUID playlistId) {
        return this.songRepository.create(name, path, playlistId);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        this.songRepository.remove(id);
    }

    @Override
    public void update(UUID id, String name, String path, UUID playlistId) throws EntityNotFoundException {
        this.songRepository.update(id, name, path, playlistId);
    }
}
