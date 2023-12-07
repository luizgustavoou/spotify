package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.services.folder.IRemoveFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.CreateFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.folder.impl.RemoveFolderUseCaseImpl;

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
