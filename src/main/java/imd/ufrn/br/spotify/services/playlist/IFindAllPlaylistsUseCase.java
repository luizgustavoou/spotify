package imd.ufrn.br.spotify.services.playlist;

import imd.ufrn.br.spotify.entities.Playlist;

import java.util.List;

public interface IFindAllPlaylistsUseCase {
    List<Playlist> execute();
}
