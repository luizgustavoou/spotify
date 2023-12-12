package imd.ufrn.br.spotify.back.services.song;

import imd.ufrn.br.spotify.back.entities.Song;

public interface ICreateSongUseCase {
    Song execute(Song value);
}
