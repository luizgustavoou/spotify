package imd.ufrn.br.spotify.back.services.playlist;

import imd.ufrn.br.spotify.back.entities.Playlist;

public interface ICreatePlaylistUseCase {
    Playlist execute(Playlist value);

}
