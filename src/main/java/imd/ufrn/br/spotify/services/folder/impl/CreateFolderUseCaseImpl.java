package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.repositories.IFolderRepository;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.folder.ICreateFolderUseCase;

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
