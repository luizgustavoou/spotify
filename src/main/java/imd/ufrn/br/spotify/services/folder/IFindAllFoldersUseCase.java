package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.entities.Folder;

import java.util.List;

public interface IFindAllFoldersUseCase {
    List<Folder> execute();
}
