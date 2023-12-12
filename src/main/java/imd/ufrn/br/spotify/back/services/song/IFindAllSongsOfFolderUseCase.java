package imd.ufrn.br.spotify.back.services.song;

import imd.ufrn.br.spotify.back.entities.Song;

import java.util.List;
import java.util.UUID;

public interface IFindAllSongsOfFolderUseCase {
    List<Song> execute(String path, UUID idPlaylist);
}
