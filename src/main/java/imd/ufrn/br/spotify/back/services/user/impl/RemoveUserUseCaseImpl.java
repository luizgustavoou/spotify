package imd.ufrn.br.spotify.back.services.user.impl;

import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.IUserRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.back.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.back.services.playlist.IRemovePlaylistUseCase;
import imd.ufrn.br.spotify.back.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import imd.ufrn.br.spotify.back.services.playlist.impl.RemovePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.back.services.user.IRemoveUserUseCase;

import java.util.ArrayList;
import java.util.UUID;

public class RemoveUserUseCaseImpl implements IRemoveUserUseCase {
    IUserRepository userRepository;
    IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    IRemovePlaylistUseCase removePlaylistUseCase;

    public RemoveUserUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
        this.removePlaylistUseCase = new RemovePlaylistUseCaseImpl();
    }

    public RemoveUserUseCaseImpl(IUserRepository userRepository, IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase, IRemovePlaylistUseCase removePlaylistUseCase) {
        this.userRepository = userRepository;
        this.findAllPlaylistOfUserUseCase = findAllPlaylistOfUserUseCase;
        this.removePlaylistUseCase = removePlaylistUseCase;
    }

    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.userRepository.remove(id);

        ArrayList<Playlist> playlists = new ArrayList<>(this.findAllPlaylistOfUserUseCase.execute(id));
        playlists.forEach(playlist -> {
            try{
                this.removePlaylistUseCase.execute(playlist.getId());
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void main(String[] args) throws EntityNotFoundException {
        IRemoveUserUseCase removeUserUseCase = new RemoveUserUseCaseImpl();

        removeUserUseCase.execute(UUID.fromString("20bbea2e-71f1-4a06-b17c-7291071c78c3"));
    }
}
