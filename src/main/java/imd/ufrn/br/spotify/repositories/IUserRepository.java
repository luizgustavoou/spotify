package imd.ufrn.br.spotify.repositories;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;

public interface IUserRepository extends IRepository<User> {
    User findUserByUsername(String username) throws EntityNotFoundException;
}
