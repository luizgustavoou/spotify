package imd.ufrn.br.spotify.front.repositories.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.IFolderApi;
import imd.ufrn.br.spotify.front.apis.impl.FolderApiImpl;

import java.util.UUID;

public class FolderRepositoryImpl {
    private final IFolderApi folderApi;

    public FolderRepositoryImpl() {
        this.folderApi = new FolderApiImpl();
    }

    public Folder create(String path, UUID playlistId) {
        return this.folderApi.create(path, playlistId);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.folderApi.remove(id);
    }
}
