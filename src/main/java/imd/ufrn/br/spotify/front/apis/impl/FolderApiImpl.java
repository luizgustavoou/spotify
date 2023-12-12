package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.controllers.FolderController;
import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.IFolderApi;

import java.util.UUID;

public class FolderApiImpl implements IFolderApi {
    FolderController folderController;

    public FolderApiImpl() {
        this.folderController = new FolderController();
    }

    @Override
    public Folder create(String path, UUID playlistId) {
        return this.folderController.create(path, playlistId);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        this.folderController.remove(id);
    }
}
