package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.services.user.ICreateUserUseCase;
import imd.ufrn.br.spotify.services.user.impl.CreateUserUseCaseImpl;

import java.util.UUID;

public class RegistrationController {
    private final ICreateUserUseCase createUserUseCase;

    public RegistrationController(ICreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }
    public RegistrationController() {
        this.createUserUseCase = new CreateUserUseCaseImpl();
    }

    public void register(String username, String password, String fullName, boolean isVip) {
        User user = new User(username, password, fullName, isVip);

        this.createUserUseCase.execute(user);
    }

    public static void main(String[] args) {
        RegistrationController registrationController = new RegistrationController();
        int randomNumber = (int) (Math.random() * 100 + 1);

        registrationController.register("joaozin" + randomNumber,  "senha", "João Da Silva " + randomNumber, false);

    }
}
