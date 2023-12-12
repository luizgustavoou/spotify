package imd.ufrn.br.spotify.front.repositories;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFolderRepository {
    public Folder create(String path, UUID playlistId);

    public void remove(UUID id) throws EntityNotFoundException;
}
