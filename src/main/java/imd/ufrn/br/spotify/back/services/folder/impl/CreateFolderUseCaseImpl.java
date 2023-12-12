package imd.ufrn.br.spotify.back.services.folder.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.back.repositories.IFolderRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.back.services.folder.ICreateFolderUseCase;

public class CreateFolderUseCaseImpl implements ICreateFolderUseCase {
    IFolderRepository folderRepository;

    public CreateFolderUseCaseImpl() {
        this.folderRepository = new CSVFolderRepositoryImpl();
    }

    public CreateFolderUseCaseImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Override
    public Folder execute(Folder value) {
        return this.folderRepository.create(value);
    }

    public static void main(String[] args) {

    }
}
