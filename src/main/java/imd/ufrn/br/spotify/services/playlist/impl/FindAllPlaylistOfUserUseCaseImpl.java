package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistOfUserUseCase;

import java.util.List;
import java.util.UUID;

public class FindAllPlaylistOfUserUseCaseImpl implements IFindAllPlaylistOfUserUseCase {
    IPlaylistRepository playlistRepository;
    public FindAllPlaylistOfUserUseCaseImpl() {
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
    }

    public FindAllPlaylistOfUserUseCaseImpl(IPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    @Override
    public List<Playlist> execute(UUID userId) {
        return this.playlistRepository.findAllPlaylistOfUser(userId);
    }

    public static void main(String[] args) {
        IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();

        System.out.println(findAllPlaylistOfUserUseCase.execute(UUID.fromString("12b2a592-722b-44db-ad94-3540658abeab")));
    }
}
