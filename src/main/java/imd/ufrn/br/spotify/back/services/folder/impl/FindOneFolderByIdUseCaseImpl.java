package imd.ufrn.br.spotify.back.services.folder.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.IFolderRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.back.services.folder.IFindOneFolderByIdUseCase;

import java.util.UUID;

public class FindOneFolderByIdUseCaseImpl implements IFindOneFolderByIdUseCase {
    IFolderRepository folderRepository;
    public FindOneFolderByIdUseCaseImpl() {
        this.folderRepository = new CSVFolderRepositoryImpl();
    }

    public FindOneFolderByIdUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public Folder execute(UUID id) throws EntityNotFoundException {
        return this.folderRepository.findOneById(id);
    }
}
