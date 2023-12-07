package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.services.user.impl.LoginUseCaseImpl;
import imd.ufrn.br.spotify.utils.PathViews;
import imd.ufrn.br.spotify.utils.TitleViews;

import java.io.IOException;

public class AuthController {
    ILoginUseCase loginUseCase;

    public AuthController() {
        this.loginUseCase = new LoginUseCaseImpl();
    }

    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        return this.loginUseCase.execute(username, password);
    }
}
