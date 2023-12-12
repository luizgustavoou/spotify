package imd.ufrn.br.spotify.back.services.folder.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.back.repositories.IFolderRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.back.services.folder.IFindAllFoldersOfPlaylistUseCase;

import java.util.List;
import java.util.UUID;

public class FindAllFoldersOfPlaylistImpl implements IFindAllFoldersOfPlaylistUseCase {
    IFolderRepository folderRepository;
    public FindAllFoldersOfPlaylistImpl() {
        this.folderRepository = new CSVFolderRepositoryImpl();
    }

    public FindAllFoldersOfPlaylistImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public List<Folder> execute(UUID playlistId) {
        return this.folderRepository.findAllFoldersOfPlaylist(playlistId);
    }
}
