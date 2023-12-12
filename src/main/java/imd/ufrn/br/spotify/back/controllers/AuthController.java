package imd.ufrn.br.spotify.back.controllers;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.back.services.user.ICreateUserUseCase;
import imd.ufrn.br.spotify.back.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.back.services.user.impl.CreateUserUseCaseImpl;
import imd.ufrn.br.spotify.back.services.user.impl.LoginUseCaseImpl;

import java.util.Objects;

public class AuthController {
    private final ILoginUseCase loginUseCase;
    private final ICreateUserUseCase createUserUseCase;

    public AuthController() {
        this.loginUseCase = new LoginUseCaseImpl();
        this.createUserUseCase = new CreateUserUseCaseImpl();
    }

    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        return this.loginUseCase.execute(username, password);
    }

    public User register(String username, String password, String fullName, String typeUser) throws EmptyTextFieldsException {

        if(fullName.isEmpty()){
            throw new EmptyTextFieldsException("Digite um nome completo válido.");
        }
        else if(username.isEmpty()) {
            throw new EmptyTextFieldsException("Cadastre um nome de usuário válido.");
        }
        else if(password.isEmpty()){
            throw new EmptyTextFieldsException("Digite uma senha válida.");
        }
        else if(typeUser == null){
            throw new EmptyTextFieldsException("Escolha um tipo de usuário");
        }

        User user = new User(username, password, fullName, Objects.equals(typeUser, "VIP"));

        return this.createUserUseCase.execute(user);
    }
}
