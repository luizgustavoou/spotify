package imd.ufrn.br.spotify.repositories;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    T create(T value );
    void update(UUID id, T value) throws EntityNotFoundException;
    void remove(UUID id) throws EntityNotFoundException;
    List<T> findAll();
    T findOneById(UUID id) throws EntityNotFoundException;

}
