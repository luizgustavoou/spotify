package imd.ufrn.br.spotify.services.user;

import imd.ufrn.br.spotify.entities.User;

import java.util.List;

public interface IFindAllUsersUseCase {
    List<User> execute();
}
