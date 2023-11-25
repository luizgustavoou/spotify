package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.song.IUpdateSongUseCase;

import java.util.UUID;

public class UpdateSongUseCaseImpl implements IUpdateSongUseCase {
    ISongRepository songRepository = new CSVSongRepositoryImpl();
    public UpdateSongUseCaseImpl() {}

    public UpdateSongUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public void execute(UUID id, Song value) throws EntityNotFoundException {
        this.songRepository.update(id, value);
    }
}
