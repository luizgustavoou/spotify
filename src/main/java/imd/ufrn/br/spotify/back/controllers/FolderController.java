package imd.ufrn.br.spotify.back.controllers;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.back.services.folder.IRemoveFolderUseCase;
import imd.ufrn.br.spotify.back.services.folder.impl.CreateFolderUseCaseImpl;
import imd.ufrn.br.spotify.back.services.folder.impl.RemoveFolderUseCaseImpl;

import java.util.UUID;

public class FolderController {
    private final ICreateFolderUseCase createFolderUseCase;
    private final IRemoveFolderUseCase removeFolderUseCase;

    public FolderController() {
        this.createFolderUseCase = new CreateFolderUseCaseImpl();
        this.removeFolderUseCase = new RemoveFolderUseCaseImpl();
    }

    public Folder create(String path, UUID playlistId) {
        Folder folder = new Folder(path, playlistId);

        return this.createFolderUseCase.execute(folder);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.removeFolderUseCase.execute(id);
    }
}
