package imd.ufrn.br.spotify.services.user.impl;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.helpers.bcrypt.ByCryptUseCaseImpl;
import imd.ufrn.br.spotify.helpers.bcrypt.IByCryptUseCase;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.services.user.ICreateUserUseCase;

import java.util.UUID;

public class CreateUserUseCaseImpl implements ICreateUserUseCase {
    IUserRepository userRepository;
    IByCryptUseCase byCryptUseCase;
    public CreateUserUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
        this.byCryptUseCase = new ByCryptUseCaseImpl();
    }

    public CreateUserUseCaseImpl(IUserRepository userRepository, IByCryptUseCase byCryptUseCase) {
        this.byCryptUseCase = byCryptUseCase;
        this.userRepository = userRepository;
    }

    @Override
    public User execute(User value) {
        String hashPassword = byCryptUseCase.generateHash(value.getPassword());

        value.setPassword(hashPassword);

        return this.userRepository.create(value);
    }

    public static void main(String[] args) {
        int randomNumber = (int) (Math.random() * 100 + 1);

        User user = new User(UUID.randomUUID(), "joaozin" + randomNumber, "senha", "João", false);
        ICreateUserUseCase createUserUseCase = new CreateUserUseCaseImpl();

        System.out.println(createUserUseCase.execute(user));

    }
}
