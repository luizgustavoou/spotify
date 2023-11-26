package imd.ufrn.br.spotify.services.song;

import imd.ufrn.br.spotify.entities.Song;

import java.util.List;
import java.util.UUID;

public interface IFindAllSongsOfPlaylistUseCase {
    List<Song> execute(UUID playlistId);
}
