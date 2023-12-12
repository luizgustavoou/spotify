package imd.ufrn.br.spotify.front.repositories.impl;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.front.apis.IAuthApi;
import imd.ufrn.br.spotify.front.apis.impl.AuthApiImpl;

public class AuthRepositoryImpl {
    private final IAuthApi authApi;

    public AuthRepositoryImpl() {
        this.authApi = new AuthApiImpl();
    }

    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        return this.authApi.login(username, password);
    }
    public User register(String username, String password, String fullName, String typeUser) throws EmptyTextFieldsException {
        return this.authApi.register(username, password, fullName, typeUser);
    }

}
