package imd.ufrn.br.spotify.services.playlist;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IUpdatePlaylistUseCase {
    void execute(UUID id, Playlist value) throws EntityNotFoundException;
}