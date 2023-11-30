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
        Playlist playlist = new Playlist("Playlist triste do joaozin62", UUID.fromString("d2add4ac-5509-45ef-87a5-d6c407f29a30"));

        ICreatePlaylistUseCase createPlaylistUseCase = new CreatePlaylistUseCaseImpl();

        System.out.println(createPlaylistUseCase.execute(playlist));
    }
}
