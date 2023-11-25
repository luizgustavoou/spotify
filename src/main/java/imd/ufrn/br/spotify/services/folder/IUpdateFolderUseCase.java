package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IUpdateFolderUseCase {
    void execute(UUID id, Folder value) throws EntityNotFoundException;
}
