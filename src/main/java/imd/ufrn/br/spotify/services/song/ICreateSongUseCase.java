package imd.ufrn.br.spotify.services.song;

import imd.ufrn.br.spotify.entities.Song;

public interface ICreateSongUseCase {
    Song execute(Song value);
}
