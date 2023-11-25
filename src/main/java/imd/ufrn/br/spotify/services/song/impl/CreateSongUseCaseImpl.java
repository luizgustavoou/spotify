package imd.ufrn.br.spotify.services.song.impl;


import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.song.ICreateSongUseCase;

public class CreateSongUseCaseImpl implements ICreateSongUseCase {
    ISongRepository songRepository = new CSVSongRepositoryImpl();
    public CreateSongUseCaseImpl() {}

    public CreateSongUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song execute(Song value) {
        return null;
    }
}
