package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IFolderRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.services.folder.IFindOneFolderByIdUseCase;

import java.util.UUID;

public class FindOneFolderByIdUseCaseImpl implements IFindOneFolderByIdUseCase {
    IFolderRepository folderRepository = new CSVFolderRepositoryImpl();
    public FindOneFolderByIdUseCaseImpl() {}

    public FindOneFolderByIdUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public Folder execute(UUID id) throws EntityNotFoundException {
        return this.folderRepository.findOneById(id);
    }
}
