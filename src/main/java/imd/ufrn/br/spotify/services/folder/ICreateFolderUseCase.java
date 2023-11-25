package imd.ufrn.br.spotify.services.folder;

import imd.ufrn.br.spotify.entities.Folder;

public interface ICreateFolderUseCase {
    Folder execute(Folder value);
}
