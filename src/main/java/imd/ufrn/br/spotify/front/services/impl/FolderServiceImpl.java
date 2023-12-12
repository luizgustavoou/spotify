package imd.ufrn.br.spotify.front.services.impl;

import imd.ufrn.br.spotify.back.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.repositories.IFolderRepository;
import imd.ufrn.br.spotify.front.repositories.impl.FolderRepositoryImpl;
import imd.ufrn.br.spotify.front.services.IFolderService;

import java.util.UUID;

public class FolderServiceImpl implements IFolderService {
    private final IFolderRepository folderRepository;

    public FolderServiceImpl() {
        this.folderRepository = new FolderRepositoryImpl();
    }
    @Override
    public Folder create(String path, UUID playlistId) {
        return this.folderRepository.create(path, playlistId);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        this.folderRepository.remove(id);
    }
}
