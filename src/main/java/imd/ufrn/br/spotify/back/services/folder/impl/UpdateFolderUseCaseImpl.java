package imd.ufrn.br.spotify.back.services.folder.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.IFolderRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.back.services.folder.IUpdateFolderUseCase;

import java.util.UUID;

public class UpdateFolderUseCaseImpl implements IUpdateFolderUseCase {
    IFolderRepository folderRepository;
    public UpdateFolderUseCaseImpl() {
        this.folderRepository = new CSVFolderRepositoryImpl();
    }

    public UpdateFolderUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public void execute(UUID id, Folder value) throws EntityNotFoundException {
        this.folderRepository.update(id, value);
    }
}
