package imd.ufrn.br.spotify.services.song;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IRemoveSongUseCase {
    void execute(UUID id) throws EntityNotFoundException;
}
