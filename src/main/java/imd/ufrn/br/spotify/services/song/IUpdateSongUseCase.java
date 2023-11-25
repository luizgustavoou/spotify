package imd.ufrn.br.spotify.services.song;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IUpdateSongUseCase {
    void execute(UUID id, Song value) throws EntityNotFoundException;
}
