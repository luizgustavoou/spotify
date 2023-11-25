package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.song.IFindOneSongByIdUseCase;

import java.util.UUID;

public class FindOneSongByIdUseCaseImpl implements IFindOneSongByIdUseCase {
    @Override
    public Song execute(UUID id) throws EntityNotFoundException {
        return null;
    }
}
