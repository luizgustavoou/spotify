package imd.ufrn.br.spotify.back.services.user;

import imd.ufrn.br.spotify.back.entities.User;

import java.util.List;

public interface IFindAllUsersUseCase {
    List<User> execute();
}
