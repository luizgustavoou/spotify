package imd.ufrn.br.spotify.back.services.playlist;

import imd.ufrn.br.spotify.back.entities.Playlist;

import java.util.List;
import java.util.UUID;

public interface IFindAllPlaylistOfUserUseCase {
    List<Playlist> execute(UUID userId);
}
