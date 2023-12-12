package imd.ufrn.br.spotify.front.apis;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFolderApi {
    public Folder create(String path, UUID playlistId);

    public void remove(UUID id) throws EntityNotFoundException;
}
