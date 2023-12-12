package imd.ufrn.br.spotify.back.services.user;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;

public interface ILoginUseCase {
    User execute(String username, String password) throws EntityNotFoundException, UnauthorizedException;
}
