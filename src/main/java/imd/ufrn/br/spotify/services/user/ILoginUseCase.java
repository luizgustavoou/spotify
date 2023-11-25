package imd.ufrn.br.spotify.services.user;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;

public interface ILoginUseCase {
    User execute(String username, String password) throws EntityNotFoundException, UnauthorizedException;
}
