package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.song.ICreateSongUseCase;
import imd.ufrn.br.spotify.services.song.IRemoveSongUseCase;
import imd.ufrn.br.spotify.services.song.impl.CreateSongUseCaseImpl;
import imd.ufrn.br.spotify.services.song.impl.RemoveSongUseCaseImpl;

import java.util.UUID;

public class SongController {
    private final ICreateSongUseCase createSongUseCase;
    private final IRemoveSongUseCase removeSongUseCase;
    public SongController() {
        this.createSongUseCase = new CreateSongUseCaseImpl();
        this.removeSongUseCase = new RemoveSongUseCaseImpl();
    }

    public Song create(String name, String path, UUID playlistId) {
        Song song = new Song(name, path, playlistId);

        return createSongUseCase.execute(song);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.removeSongUseCase.execute(id);
    }
}
