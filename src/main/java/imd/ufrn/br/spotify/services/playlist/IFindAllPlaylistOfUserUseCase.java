package imd.ufrn.br.spotify.services.playlist;

import imd.ufrn.br.spotify.entities.Playlist;

import java.util.List;
import java.util.UUID;

public interface IFindAllPlaylistOfUserUseCase {
    List<Playlist> execute(UUID userId);
}
