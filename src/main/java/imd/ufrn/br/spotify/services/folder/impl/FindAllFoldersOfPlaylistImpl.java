package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.repositories.IFolderRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVFolderRepositoryImpl;
import imd.ufrn.br.spotify.services.folder.IFindAllFoldersOfPlaylist;

import java.util.List;
import java.util.UUID;

public class FindAllFoldersOfPlaylistImpl implements IFindAllFoldersOfPlaylist {
    IFolderRepository folderRepository = new CSVFolderRepositoryImpl();
    public FindAllFoldersOfPlaylistImpl() {}

    public FindAllFoldersOfPlaylistImpl(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    @Override
    public List<Folder> execute(UUID playlistId) {
        return this.folderRepository.findAllFoldersOfPlaylist(playlistId);
    }
}
