package imd.ufrn.br.spotify.back.services.song.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.back.repositories.ISongRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.back.services.song.IFindAllSongsUseCase;

import java.util.List;

public class FindAllSongsUseCaseImpl implements IFindAllSongsUseCase {
    ISongRepository songRepository;
    public FindAllSongsUseCaseImpl() {
        this.songRepository = new CSVSongRepositoryImpl();
    }

    public FindAllSongsUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public List<Song> execute() {
        return this.songRepository.findAll();
    }
}
