package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.entities.Folder;

import java.util.List;
import java.util.UUID;

public interface IFindAllFoldersOfPlaylist {
    List<Folder> execute(UUID playlistId);
}
