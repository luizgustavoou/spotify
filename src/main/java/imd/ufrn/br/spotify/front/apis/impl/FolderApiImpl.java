package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.apis.IFolderApi;

import java.util.UUID;

public class FolderApiImpl implements IFolderApi {
    @Override
    public Folder create(String path, UUID playlistId) {
        return null;
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {

    }
}
