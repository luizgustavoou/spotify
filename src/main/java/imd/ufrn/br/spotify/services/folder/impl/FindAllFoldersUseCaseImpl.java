package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.repositories.IFolderRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.services.folder.IFindAllFoldersUseCase;

import java.util.List;

public class FindAllFoldersUseCaseImpl implements IFindAllFoldersUseCase {
    IFolderRepository folderRepository;
    public FindAllFoldersUseCaseImpl() {
        this.folderRepository = new CSVFolderRepositoryImpl();
    }

    public FindAllFoldersUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public List<Folder> execute() {
        return this.folderRepository.findAll();
    }
}
