package imd.ufrn.br.spotify.back.repositories;

import imd.ufrn.br.spotify.back.entities.Song;

import java.util.List;
import java.util.UUID;

public interface ISongRepository extends IRepository<Song> {
    List<Song> findAllSongsOfPlaylist(UUID playlistId);
}
