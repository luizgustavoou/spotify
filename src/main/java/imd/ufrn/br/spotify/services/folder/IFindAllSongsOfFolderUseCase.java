package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.entities.Song;

import java.util.List;
import java.util.UUID;

public interface IFindAllSongsOfFolderUseCase {
    List<Song> execute(String path, UUID idPlaylist);
}