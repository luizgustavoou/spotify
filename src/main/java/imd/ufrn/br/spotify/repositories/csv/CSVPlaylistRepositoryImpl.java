package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;

import java.util.List;
import java.util.UUID;

public class CSVPlaylistRepositoryImpl implements IPlaylistRepository {
    @Override
    public List<Playlist> findAllPlaylistOfUser(UUID userId) {

        return null;
    }
    @Override
    public Playlist create(Playlist value) {
        return null;
    }

    @Override
    public void update(UUID id, Playlist value) throws EntityNotFoundException {

    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {

    }

    @Override
    public List<Playlist> findAll() {
        return null;
    }

    @Override
    public Playlist findOneById(UUID id) throws EntityNotFoundException {
        return null;
    }
}
