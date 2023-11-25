package imd.ufrn.br.spotify.services.song;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Song;

import java.util.List;

public interface IFindAllSongsUseCase {
    List<Song> execute();
}
