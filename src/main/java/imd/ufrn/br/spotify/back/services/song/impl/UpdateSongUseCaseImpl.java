package imd.ufrn.br.spotify.back.services.song.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.ISongRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.back.services.song.IUpdateSongUseCase;

import java.util.UUID;

public class UpdateSongUseCaseImpl implements IUpdateSongUseCase {
    ISongRepository songRepository;
    public UpdateSongUseCaseImpl() {
        this.songRepository = new CSVSongRepositoryImpl();
    }

    public UpdateSongUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public void execute(UUID id, Song value) throws EntityNotFoundException {
        this.songRepository.update(id, value);
    }
}
