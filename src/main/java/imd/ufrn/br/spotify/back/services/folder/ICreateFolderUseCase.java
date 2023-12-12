package imd.ufrn.br.spotify.back.services.folder;

import imd.ufrn.br.spotify.back.entities.Folder;

public interface ICreateFolderUseCase {
    Folder execute(Folder value);
}
