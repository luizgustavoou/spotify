package imd.ufrn.br.spotify.back.controllers;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.back.services.song.ICreateSongUseCase;
import imd.ufrn.br.spotify.back.services.song.IRemoveSongUseCase;
import imd.ufrn.br.spotify.back.services.song.IUpdateSongUseCase;
import imd.ufrn.br.spotify.back.services.song.impl.CreateSongUseCaseImpl;
import imd.ufrn.br.spotify.back.services.song.impl.RemoveSongUseCaseImpl;
import imd.ufrn.br.spotify.back.services.song.impl.UpdateSongUseCaseImpl;

import java.util.UUID;

public class SongController {
    private final ICreateSongUseCase createSongUseCase;
    private final IRemoveSongUseCase removeSongUseCase;
    private final IUpdateSongUseCase updateSongUseCase;
    public SongController() {
        this.createSongUseCase = new CreateSongUseCaseImpl();
        this.removeSongUseCase = new RemoveSongUseCaseImpl();
        this.updateSongUseCase = new UpdateSongUseCaseImpl();
    }

    public Song create(String name, String path, UUID playlistId) {
        Song song = new Song(name, path, playlistId);

        return createSongUseCase.execute(song);
    }

    public void remove(UUID id) throws EntityNotFoundException {
        this.removeSongUseCase.execute(id);
    }

    public void update(UUID id, String name, String path, UUID playlistId) throws EntityNotFoundException {
        Song song = new Song(id, name, path, playlistId);


        this.updateSongUseCase.execute(id, song);
    }
}
