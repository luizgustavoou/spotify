package imd.ufrn.br.spotify.back.services.folder.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.back.repositories.IFolderRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.back.services.folder.IFindAllFoldersUseCase;

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
