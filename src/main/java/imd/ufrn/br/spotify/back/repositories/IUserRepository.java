package imd.ufrn.br.spotify.back.repositories;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

public interface IUserRepository extends IRepository<User> {
    User findUserByUsername(String username) throws EntityNotFoundException;
}
