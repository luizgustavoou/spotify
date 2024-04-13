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

/**
 * Controller for handling authentication related operations
 */
public class AuthController {
    private final ILoginUseCase loginUseCase;
    private final ICreateUserUseCase createUserUseCase;

    /**
     * Constructs an AuthController with the provided login and create user use
     * cases.
     * 
     * @param loginUseCase
     * @param createUserUseCase
     */
    public AuthController(ILoginUseCase loginUseCase, ICreateUserUseCase createUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    /**
     * Default construct that initializes the login and create user use cases.
     */
    public AuthController() {
        this.loginUseCase = new LoginUseCaseImpl();
        this.createUserUseCase = new CreateUserUseCaseImpl();
    }

    /**
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user.
     * @throws UnauthorizedException   UnauthorizedException If the provided
     *                                 credentials are invalid.
     * @throws EntityNotFoundException If the user entity is not found
     */
    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        return this.loginUseCase.execute(username, password);
    }

    /**
     * Registers a new user with the provided details.
     * 
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param fullName The full name of the new user.
     * @param typeUser The type of user ("VIP" or "normal").
     * @return The registered user.
     * @throws EmptyTextFieldsException If any of the required fields are empty.
     */
    public User register(String username, String password, String fullName, String typeUser)
            throws EmptyTextFieldsException {

        if (fullName.isEmpty()) {
            throw new EmptyTextFieldsException("Digite um nome completo válido.");
        } else if (username.isEmpty()) {
            throw new EmptyTextFieldsException("Cadastre um nome de usuário válido.");
        } else if (password.isEmpty()) {
            throw new EmptyTextFieldsException("Digite uma senha válida.");
        } else if (typeUser == null) {
            throw new EmptyTextFieldsException("Escolha um tipo de usuário");
        }

        User user = new User(username, password, fullName, Objects.equals(typeUser, "VIP"));

        return this.createUserUseCase.execute(user);
    }
}
