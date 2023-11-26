package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.repositories.ISongRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.services.song.IFindAllSongsOfPlaylistUseCase;

import java.util.List;
import java.util.UUID;

public class FindAllSongsOfPlaylistUseCaseImpl implements IFindAllSongsOfPlaylistUseCase {
    ISongRepository songRepository = new CSVSongRepositoryImpl();
    public FindAllSongsOfPlaylistUseCaseImpl() {}

    public FindAllSongsOfPlaylistUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public List<Song> execute(UUID playlistId) {
        return this.songRepository.findAllSongsOfPlaylist(playlistId);
    }
}
