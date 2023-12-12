package imd.ufrn.br.spotify.back.services.song.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.back.repositories.ISongRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVSongRepositoryImpl;
import imd.ufrn.br.spotify.back.services.song.IFindAllSongsOfPlaylistUseCase;

import java.util.List;
import java.util.UUID;

public class FindAllSongsOfPlaylistUseCaseImpl implements IFindAllSongsOfPlaylistUseCase {
    ISongRepository songRepository;
    public FindAllSongsOfPlaylistUseCaseImpl() {
        this.songRepository = new CSVSongRepositoryImpl();
    }

    public FindAllSongsOfPlaylistUseCaseImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public List<Song> execute(UUID playlistId) {
        return this.songRepository.findAllSongsOfPlaylist(playlistId);
    }
}
