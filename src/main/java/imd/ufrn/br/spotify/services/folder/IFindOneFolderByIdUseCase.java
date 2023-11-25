package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFindOneFolderByIdUseCase {
    Folder execute(UUID id) throws EntityNotFoundException;
}
