package imd.ufrn.br.spotify.back.services.song.impl;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.repositories.ISongRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.back.services.song.IRemoveSongUseCase;

import java.util.UUID;

public class RemoveSongUseCaseImpl implements IRemoveSongUseCase {
    ISongRepository songRepository;
    public RemoveSongUseCaseImpl() {
        this.songRepository = new CSVSongRepositoryImpl();
    }

    public RemoveSongUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.songRepository.remove(id);

    }

    public static void main(String[] args) throws EntityNotFoundException {
        IRemoveSongUseCase removeSongUseCase = new RemoveSongUseCaseImpl();

        removeSongUseCase.execute(UUID.fromString("36f3c8e6-7d42-4f64-92f8-510b03d1a827"));
    }
}
