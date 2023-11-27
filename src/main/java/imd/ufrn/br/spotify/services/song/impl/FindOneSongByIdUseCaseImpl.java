package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.song.IFindOneSongByIdUseCase;

import java.util.UUID;

public class FindOneSongByIdUseCaseImpl implements IFindOneSongByIdUseCase {
    ISongRepository songRepository;
    public FindOneSongByIdUseCaseImpl() {
        this.songRepository = new CSVSongRepositoryImpl();
    }

    public FindOneSongByIdUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public Song execute(UUID id) throws EntityNotFoundException {
        return this.songRepository.findOneById(id);
    }
}
