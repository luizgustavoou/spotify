package imd.ufrn.br.spotify.back.services.song.impl;


import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.back.repositories.ISongRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.back.services.song.ICreateSongUseCase;

import java.util.UUID;

public class CreateSongUseCaseImpl implements ICreateSongUseCase {
    ISongRepository songRepository;
    public CreateSongUseCaseImpl() {
        this.songRepository = new CSVSongRepositoryImpl();
    }

    public CreateSongUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song execute(Song value) {
        return this.songRepository.create(value);
    }

    public static void main(String[] args) {
        ICreateSongUseCase createSongUseCase = new CreateSongUseCaseImpl();

        Song song = new Song("chuva corea relaxante", "caminho", UUID.fromString("e0e0c95d-9453-4c2e-8940-2fe3bf090097"));
        createSongUseCase.execute(song);
    }
}
