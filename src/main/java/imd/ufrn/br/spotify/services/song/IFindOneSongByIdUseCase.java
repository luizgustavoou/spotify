package imd.ufrn.br.spotify.services.song;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFindOneSongByIdUseCase {
    Song execute(UUID id) throws EntityNotFoundException;
}
