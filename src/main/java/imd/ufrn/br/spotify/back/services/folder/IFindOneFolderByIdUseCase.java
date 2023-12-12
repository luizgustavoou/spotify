package imd.ufrn.br.spotify.back.services.folder;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFindOneFolderByIdUseCase {
    Folder execute(UUID id) throws EntityNotFoundException;
}
