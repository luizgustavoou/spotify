package imd.ufrn.br.spotify.services.user.impl;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.services.user.IUpdateUserUseCase;

import java.util.UUID;

public class UpdateUserUseCaseImpl implements IUpdateUserUseCase {
    IUserRepository userRepository;

    public UpdateUserUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
    }

    public UpdateUserUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UUID id, User value) throws EntityNotFoundException {
        this.userRepository.update(id, value);
    }

    public static void main(String[] args) throws EntityNotFoundException {
        IUpdateUserUseCase updateUserUseCase = new UpdateUserUseCaseImpl();

        updateUserUseCase.execute(UUID.fromString("137b0fe7-bb20-441b-89c4-40106cce22ff"), new User(UUID.fromString("137b0fe7-bb20-441b-89c4-40106cce22ff"),"joabpato4", "123", "Joab Pato Agiota", true));
    }
}
