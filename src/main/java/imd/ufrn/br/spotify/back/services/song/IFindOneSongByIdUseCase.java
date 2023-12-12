package imd.ufrn.br.spotify.back.services.song;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFindOneSongByIdUseCase {
    Song execute(UUID id) throws EntityNotFoundException;
}
