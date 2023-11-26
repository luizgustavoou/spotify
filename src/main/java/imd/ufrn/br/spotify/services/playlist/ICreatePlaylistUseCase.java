package imd.ufrn.br.spotify.services.playlist;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;

public interface ICreatePlaylistUseCase {
    Playlist execute(Playlist value);

}
