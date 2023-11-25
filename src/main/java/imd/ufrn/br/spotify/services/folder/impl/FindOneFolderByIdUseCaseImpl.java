package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.folder.IFindOneFolderByIdUseCase;

import java.util.UUID;

public class FindOneFolderByIdUseCaseImpl implements IFindOneFolderByIdUseCase {
    @Override
    public Folder execute(UUID id) throws EntityNotFoundException {
        return null;
    }
}
