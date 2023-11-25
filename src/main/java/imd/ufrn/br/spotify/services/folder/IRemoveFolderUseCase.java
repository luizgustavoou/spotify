package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IRemoveFolderUseCase {
    void execute(UUID id) throws EntityNotFoundException;
}
