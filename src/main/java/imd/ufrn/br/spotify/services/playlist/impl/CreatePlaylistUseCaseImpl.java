package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;

import java.util.UUID;

public class CreatePlaylistUseCaseImpl implements ICreatePlaylistUseCase {
    IPlaylistRepository playlistRepository = new CSVPlaylistRepositoryImpl();

    public CreatePlaylistUseCaseImpl(){

    }

    public CreatePlaylistUseCaseImpl(IPlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist execute(Playlist value){
        return this.playlistRepository.create(value);
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist("Playlist 1", UUID.fromString("11498d8b-97ec-4907-a174-ae4f9e7596d2"));

        ICreatePlaylistUseCase createPlaylistUseCase = new CreatePlaylistUseCaseImpl();

        System.out.println(createPlaylistUseCase.execute(playlist));
    }
}
