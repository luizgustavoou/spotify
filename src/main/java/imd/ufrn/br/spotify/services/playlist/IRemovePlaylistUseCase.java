package imd.ufrn.br.spotify.services.playlist;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IRemovePlaylistUseCase {
    void execute(UUID id) throws EntityNotFoundException;
}
