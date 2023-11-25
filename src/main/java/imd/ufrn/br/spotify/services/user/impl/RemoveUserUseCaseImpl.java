package imd.ufrn.br.spotify.services.user.impl;

import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.services.user.IRemoveUserUseCase;

import java.util.UUID;

public class RemoveUserUseCaseImpl implements IRemoveUserUseCase {
    IUserRepository userRepository = new CSVUserRepositoryImpl();

    public RemoveUserUseCaseImpl() {}

    public RemoveUserUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.userRepository.remove(id);
    }

    public static void main(String[] args) throws EntityNotFoundException {
        IRemoveUserUseCase removeUserUseCase = new RemoveUserUseCaseImpl();

        removeUserUseCase.execute(UUID.fromString("f0105aa4-bab2-412b-9e7f-99bd00b030f9"));
    }
}
