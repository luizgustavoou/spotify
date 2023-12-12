package imd.ufrn.br.spotify.front.services;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;

public interface IAuthService {
    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException;
    public User register(String username, String password, String fullName, String typeUser) throws EmptyTextFieldsException;
}
