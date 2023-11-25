package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.song.IRemoveSongUseCase;

import java.util.UUID;

public class RemoveSongUseCaseImpl implements IRemoveSongUseCase {
    ISongRepository songRepository = new CSVSongRepositoryImpl();
    public RemoveSongUseCaseImpl() {}

    public RemoveSongUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.songRepository.remove(id);

    }
}
