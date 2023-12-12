package imd.ufrn.br.spotify.back.services.playlist;

import imd.ufrn.br.spotify.back.entities.Playlist;

import java.util.List;

public interface IFindAllPlaylistsUseCase {
    List<Playlist> execute();
}
