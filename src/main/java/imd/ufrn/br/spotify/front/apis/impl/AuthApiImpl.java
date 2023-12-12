package imd.ufrn.br.spotify.front.apis.impl;

import imd.ufrn.br.spotify.back.controllers.AuthController;
import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.front.apis.IAuthApi;

public class AuthApiImpl implements IAuthApi {
    AuthController authController;

    public AuthApiImpl() {
        this.authController = new AuthController();
    }

    @Override
    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        return this.authController.login(username, password);
    }

    @Override
    public User register(String username, String password, String fullName, String typeUser) throws EmptyTextFieldsException {
        return this.authController.register(username, password, fullName, typeUser);
    }
}
