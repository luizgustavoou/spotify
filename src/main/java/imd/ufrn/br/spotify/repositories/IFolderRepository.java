package imd.ufrn.br.spotify.repositories;

import imd.ufrn.br.spotify.entities.Folder;

import java.util.List;
import java.util.UUID;

public interface IFolderRepository  extends  IRepository<Folder> {
    List<Folder> findAllFoldersOfUserByPlaylistId(UUID id);
}
