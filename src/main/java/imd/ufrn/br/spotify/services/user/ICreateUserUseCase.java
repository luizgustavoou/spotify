package imd.ufrn.br.spotify.services.user;

import imd.ufrn.br.spotify.entities.User;

public interface ICreateUserUseCase {
    User execute(User value);
}
