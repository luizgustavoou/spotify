package imd.ufrn.br.spotify.back.services.user.impl;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.back.helpers.bcrypt.ByCryptUseCaseImpl;
import imd.ufrn.br.spotify.back.helpers.bcrypt.IByCryptUseCase;
import imd.ufrn.br.spotify.back.repositories.IUserRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.back.services.user.ILoginUseCase;

/**
 * Use case for execute login operation
 */
public class LoginUseCaseImpl implements ILoginUseCase {
    IUserRepository userRepository;
    IByCryptUseCase byCryptUseCase;

    /**
     * Default construct that initialize the user repository and byCrypt use case.
     */
    public LoginUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
        this.byCryptUseCase = new ByCryptUseCaseImpl();

    }

    /**
     * Constructs an LoginUseCaseImpl with the provided user repository and byCrypt
     * use case.
     * 
     * @param userRepository
     * @param byCryptUseCase
     */
    public LoginUseCaseImpl(IUserRepository userRepository, IByCryptUseCase byCryptUseCase) {
        this.byCryptUseCase = byCryptUseCase;
        this.userRepository = userRepository;
    }

    /**
     * @param username The username of the user
     * @param password The password of the user
     * @return The authenticated user.
     * @throws EntityNotFoundException If the provided credentials are invalid.
     * @throws UnauthorizedException   If the user entity is not found
     */
    @Override
    public User execute(String username, String password) throws EntityNotFoundException, UnauthorizedException {
        User user = this.userRepository.findUserByUsername(username);

        if (!byCryptUseCase.compareContent(password, user.getPassword())) {
            throw new UnauthorizedException("Senha incorreta ou inválida. Tente novamente");
        }

        return user;
    }

    public static void main(String[] args) throws EntityNotFoundException, UnauthorizedException {
        ILoginUseCase loginUseCase = new LoginUseCaseImpl();

        System.out.println(loginUseCase.execute("joaozin17", "senha"));
    }
}
