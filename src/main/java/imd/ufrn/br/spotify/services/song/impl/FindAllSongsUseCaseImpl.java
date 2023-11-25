package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.song.IFindAllSongsUseCase;

import java.util.List;

public class FindAllSongsUseCaseImpl implements IFindAllSongsUseCase {
    ISongRepository songRepository = new CSVSongRepositoryImpl();
    public FindAllSongsUseCaseImpl() {}

    public FindAllSongsUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public List<Song> execute() {
        return null;
    }
}
