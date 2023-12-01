package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;

import java.util.UUID;

public class CreatePlaylistUseCaseImpl implements ICreatePlaylistUseCase {
    IPlaylistRepository playlistRepository;

    public CreatePlaylistUseCaseImpl(){
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
    }

    public CreatePlaylistUseCaseImpl(IPlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist execute(Playlist value){
        return this.playlistRepository.create(value);
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist("Dark Country", UUID.fromString("20bbea2e-71f1-4a06-b17c-7291071c78c3"));

        ICreatePlaylistUseCase createPlaylistUseCase = new CreatePlaylistUseCaseImpl();

        System.out.println(createPlaylistUseCase.execute(playlist));
    }
}
