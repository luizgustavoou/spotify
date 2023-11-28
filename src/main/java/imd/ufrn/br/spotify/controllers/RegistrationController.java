package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.services.user.ICreateUserUseCase;
import imd.ufrn.br.spotify.services.user.impl.CreateUserUseCaseImpl;

public class RegistrationController {
    ICreateUserUseCase createUserUseCase;

    public RegistrationController(ICreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }
    public RegistrationController() {
        this.createUserUseCase = new CreateUserUseCaseImpl();
    }

    public static void main(String[] args) {
        RegistrationController registrationController = new RegistrationController();
    }
}
