package imd.ufrn.br.spotify.back.services.folder;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IRemoveFolderUseCase {
    void execute(UUID id) throws EntityNotFoundException;
}
