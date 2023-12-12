package imd.ufrn.br.spotify.back.repositories;

import imd.ufrn.br.spotify.back.entities.Playlist;

import java.util.List;
import java.util.UUID;

public interface IPlaylistRepository extends IRepository<Playlist> {
    List<Playlist> findAllPlaylistOfUser(UUID userId);

}
