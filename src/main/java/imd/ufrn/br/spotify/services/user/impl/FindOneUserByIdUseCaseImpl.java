package imd.ufrn.br.spotify.services.user.impl;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.services.user.IFindOneUserByIdUseCase;

import java.util.UUID;

public class FindOneUserByIdUseCaseImpl implements IFindOneUserByIdUseCase {

    IUserRepository userRepository;

    public FindOneUserByIdUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
    }

    public FindOneUserByIdUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(UUID id) throws EntityNotFoundException {
        return this.userRepository.findOneById(id);
    }

    public static void main(String[] args) throws EntityNotFoundException {
        IFindOneUserByIdUseCase findOneUserByIdUseCase = new FindOneUserByIdUseCaseImpl();

        System.out.println(findOneUserByIdUseCase.execute(UUID.fromString("3b3837b4-e6a0-4d0e-90e6-c54e2a5951db")));
    }
}
