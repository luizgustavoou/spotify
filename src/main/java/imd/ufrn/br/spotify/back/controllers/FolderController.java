package imd.ufrn.br.spotify.back.controllers;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.back.services.folder.IRemoveFolderUseCase;
import imd.ufrn.br.spotify.back.services.folder.impl.CreateFolderUseCaseImpl;
import imd.ufrn.br.spotify.back.services.folder.impl.RemoveFolderUseCaseImpl;

import java.util.UUID;

/**
 * Controller for handling folder related operations
 * 
 */
public class FolderController {
    private final ICreateFolderUseCase createFolderUseCase;
    private final IRemoveFolderUseCase removeFolderUseCase;

    /**
     * Default construct that initialize the createFolder and removeFolder use
     * cases.
     */
    public FolderController() {
        this.createFolderUseCase = new CreateFolderUseCaseImpl();
        this.removeFolderUseCase = new RemoveFolderUseCaseImpl();
    }

    /**
     * Constructs an FolderController with the provided createFolder and
     * removeFolder use cases.
     * 
     * @param iCreateFolderUseCase
     * @param iRemoveFolderUseCase
     */
    public FolderController(ICreateFolderUseCase iCreateFolderUseCase, IRemoveFolderUseCase iRemoveFolderUseCase) {
        this.createFolderUseCase = iCreateFolderUseCase;
        this.removeFolderUseCase = iRemoveFolderUseCase;
    }

    /**
     * Creates a new folder with the provided path and playlist ID.
     * 
     * @param path       The path of the new Folder
     * @param playlistId The ID of the playlist associated with the folder.
     * @return
     */
    public Folder create(String path, UUID playlistId) {
        Folder folder = new Folder(path, playlistId);

        return this.createFolderUseCase.execute(folder);
    }

    /**
     * Remove a folder with the provided ID
     * 
     * @param id The ID of the folder to be removed.
     * @throws EntityNotFoundException If the folder entity is not found.
     */
    public void remove(UUID id) throws EntityNotFoundException {
        this.removeFolderUseCase.execute(id);
    }
}
