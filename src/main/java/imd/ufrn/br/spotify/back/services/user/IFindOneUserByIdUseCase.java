package imd.ufrn.br.spotify.back.services.user;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IFindOneUserByIdUseCase {
    User execute(UUID id) throws EntityNotFoundException;
}
