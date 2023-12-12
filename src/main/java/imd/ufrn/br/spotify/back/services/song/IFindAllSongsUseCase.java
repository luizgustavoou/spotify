package imd.ufrn.br.spotify.back.services.song;

import imd.ufrn.br.spotify.back.entities.Song;

import java.util.List;

public interface IFindAllSongsUseCase {
    List<Song> execute();
}
