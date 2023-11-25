package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IFolderRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.services.folder.IUpdateFolderUseCase;

import java.util.UUID;

public class UpdateFolderUseCaseImpl implements IUpdateFolderUseCase {
    IFolderRepository folderRepository = new CSVFolderRepositoryImpl();
    public UpdateFolderUseCaseImpl() {}

    public UpdateFolderUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public void execute(UUID id, Folder value) throws EntityNotFoundException {
        this.folderRepository.update(id, value);
    }
}
