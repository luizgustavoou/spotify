package imd.ufrn.br.spotify.back.services.folder;

import imd.ufrn.br.spotify.back.entities.Folder;

import java.util.List;
import java.util.UUID;

public interface IFindAllFoldersOfPlaylistUseCase {
    List<Folder> execute(UUID playlistId);
}
