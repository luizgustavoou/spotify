package imd.ufrn.br.spotify.front.services.impl;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.front.repositories.impl.PlaylistRepositoryImpl;
import imd.ufrn.br.spotify.front.services.IPlaylistService;

import java.util.UUID;

public class PlaylistServiceImpl implements IPlaylistService {
    private final IPlaylistRepository playlistRepository;

    public PlaylistServiceImpl() {
        this.playlistRepository = new PlaylistRepositoryImpl();
    }
    @Override
    public Playlist create(String name, UUID userId) {
        return this.playlistRepository.create(name, userId);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        this.playlistRepository.remove(id);
    }

    @Override
    public void update(UUID id, String name, UUID userId) throws EntityNotFoundException {
        this.playlistRepository.update(id, name, userId);
    }
}
