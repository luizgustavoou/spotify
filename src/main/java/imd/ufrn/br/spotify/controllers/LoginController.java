package imd.ufrn.br.spotify.controllers;


import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.services.user.impl.LoginUseCaseImpl;

public class LoginController {
    private final ILoginUseCase loginUseCase;

    private LoginController(ILoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }
    public LoginController() {
        this.loginUseCase = new LoginUseCaseImpl();
    }

    public void login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        System.out.println(this.loginUseCase.execute(username, password));
    }


    public static void main(String[] args) throws UnauthorizedException, EntityNotFoundException {
        LoginController loginController = new LoginController();

        loginController.login("joaozin11", "senha");
    }
}
