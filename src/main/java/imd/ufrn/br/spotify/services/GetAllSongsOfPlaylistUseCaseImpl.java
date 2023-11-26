package imd.ufrn.br.spotify.services;

import imd.ufrn.br.spotify.entities.Song;

import java.util.List;
import java.util.UUID;

public class GetAllSongsOfPlaylistUseCaseImpl implements IGetAllSongsOfPlaylistUseCase{
    @Override
    public List<Song> execute(UUID playlistId) {
        return null;
    }
}
