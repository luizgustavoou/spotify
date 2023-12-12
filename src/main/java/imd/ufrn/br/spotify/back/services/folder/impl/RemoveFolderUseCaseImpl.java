package imd.ufrn.br.spotify.back.services.folder.impl;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.IFolderRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.back.services.folder.IRemoveFolderUseCase;

import java.util.UUID;

public class RemoveFolderUseCaseImpl implements IRemoveFolderUseCase {
    IFolderRepository folderRepository;
    public RemoveFolderUseCaseImpl() {
        this.folderRepository = new CSVFolderRepositoryImpl();
    }

    public RemoveFolderUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.folderRepository.remove(id);
    }
}
