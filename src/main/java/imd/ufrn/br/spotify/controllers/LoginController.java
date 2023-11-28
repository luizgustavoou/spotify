package imd.ufrn.br.spotify.controllers;


import imd.ufrn.br.spotify.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.services.user.impl.LoginUseCaseImpl;

public class LoginController {
    ILoginUseCase loginUseCase;

    public LoginController(ILoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }
    public LoginController() {
        this.loginUseCase = new LoginUseCaseImpl();
    }

    public static void main(String[] args) {
        LoginController loginController = new LoginController();
    }
}
