package imd.ufrn.br.spotify.back.services.user;

import imd.ufrn.br.spotify.back.entities.User;

public interface ICreateUserUseCase {
    User execute(User value);
}
