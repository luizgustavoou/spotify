package imd.ufrn.br.spotify.back.services.folder;

import imd.ufrn.br.spotify.back.entities.Folder;

import java.util.List;

public interface IFindAllFoldersUseCase {
    List<Folder> execute();
}
