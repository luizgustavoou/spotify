package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IFolderRepository;

import java.util.List;
import java.util.UUID;

public class CSVFolderRepositoryImpl implements IFolderRepository {
    @Override
    public List<Folder> findAllFoldersOfUser(UUID id) {
        return null;
    }

    @Override
    public Folder create(Folder value) {
        return null;
    }

    @Override
    public void update(UUID id, Folder value) throws EntityNotFoundException {

    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {

    }

    @Override
    public List<Folder> findAll() {
        return null;
    }

    @Override
    public Folder findOneById(UUID id) throws EntityNotFoundException {
        return null;
    }
}
