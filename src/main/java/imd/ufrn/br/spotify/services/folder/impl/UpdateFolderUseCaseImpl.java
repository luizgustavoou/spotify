package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.folder.IUpdateFolderUseCase;

import java.util.UUID;

public class UpdateFolderUseCaseImpl implements IUpdateFolderUseCase {
    @Override
    public void execute(UUID id, Folder value) throws EntityNotFoundException {

    }
}
