package imd.ufrn.br.spotify.back.repositories;

import imd.ufrn.br.spotify.back.entities.Folder;

import java.util.List;
import java.util.UUID;

public interface IFolderRepository  extends  IRepository<Folder> {
    List<Folder> findAllFoldersOfPlaylist(UUID playlistId);
}
