package imd.ufrn.br.spotify.back.services.user.impl;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.back.repositories.IUserRepository;
import imd.ufrn.br.spotify.back.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.back.services.user.IFindAllUsersUseCase;

import java.util.List;

public class FindAllUsersUseCaseImpl implements IFindAllUsersUseCase {
    IUserRepository userRepository;

    public FindAllUsersUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
    }

    public FindAllUsersUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute() {
        return this.userRepository.findAll();
    }

    public static void main(String[] args) {
        IFindAllUsersUseCase findAllUsersUseCase = new FindAllUsersUseCaseImpl();

        System.out.println(findAllUsersUseCase.execute());
    }
}
