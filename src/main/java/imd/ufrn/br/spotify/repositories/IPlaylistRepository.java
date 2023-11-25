package imd.ufrn.br.spotify.repositories;

import imd.ufrn.br.spotify.entities.Playlist;

import java.util.List;
import java.util.UUID;

public interface IPlaylistRepository extends IRepository<Playlist> {
    List<Playlist> findAllPlaylistOfUser(UUID userId);

}
