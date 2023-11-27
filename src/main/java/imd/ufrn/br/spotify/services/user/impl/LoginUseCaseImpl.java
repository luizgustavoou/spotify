package imd.ufrn.br.spotify.services.user.impl;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.helpers.bcrypt.ByCryptUseCaseImpl;
import imd.ufrn.br.spotify.helpers.bcrypt.IByCryptUseCase;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.services.user.ILoginUseCase;

public class LoginUseCaseImpl implements ILoginUseCase {
    IUserRepository userRepository;
    IByCryptUseCase byCryptUseCase;

    public LoginUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
        this.byCryptUseCase = new ByCryptUseCaseImpl();

    }

    public LoginUseCaseImpl(IUserRepository userRepository, IByCryptUseCase byCryptUseCase) {
        this.byCryptUseCase = byCryptUseCase;
        this.userRepository = userRepository;
    }
    @Override
    public User execute(String username, String password) throws EntityNotFoundException, UnauthorizedException {
        User user = this.userRepository.findUserByUsername(username);

        if(!byCryptUseCase.compareContent(password, user.getPassword())) {
            throw new UnauthorizedException("Erro ao efetuar login: senha incorreta");
        }

        return user;
    }

    public static void main(String[] args) throws EntityNotFoundException, UnauthorizedException {
        ILoginUseCase loginUseCase = new LoginUseCaseImpl();

        System.out.println(loginUseCase.execute("joaozin17", "senha"));
    }
}
