package imd.ufrn.br.spotify.front.services.impl;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.front.repositories.IAuthRepository;
import imd.ufrn.br.spotify.front.repositories.impl.AuthRepositoryImpl;
import imd.ufrn.br.spotify.front.services.IAuthService;

public class AuthServiceImpl implements IAuthService {
    private final IAuthRepository authRepository;

    public AuthServiceImpl() {
        this.authRepository = new AuthRepositoryImpl();
    }

    @Override
    public User login(String username, String password) throws UnauthorizedException, EntityNotFoundException {
        return this.authRepository.login(username, password);
    }

    @Override
    public User register(String username, String password, String fullName, String typeUser) throws EmptyTextFieldsException {
        return this.authRepository.register(username, password, fullName, typeUser);
    }
}
