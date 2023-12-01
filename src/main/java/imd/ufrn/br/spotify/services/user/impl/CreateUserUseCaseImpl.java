package imd.ufrn.br.spotify.services.user.impl;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.helpers.bcrypt.ByCryptUseCaseImpl;
import imd.ufrn.br.spotify.helpers.bcrypt.IByCryptUseCase;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVUserRepositoryImpl;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.IFindOnePlaylistByIdUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.impl.FindOnePlaylistByIdUseCaseImpl;
import imd.ufrn.br.spotify.services.user.ICreateUserUseCase;

import java.util.Objects;
import java.util.UUID;

public class CreateUserUseCaseImpl implements ICreateUserUseCase {
    IUserRepository userRepository;
    IByCryptUseCase byCryptUseCase;
    ICreatePlaylistUseCase createPlaylistUseCase;
    public CreateUserUseCaseImpl() {
        this.userRepository = new CSVUserRepositoryImpl();
        this.byCryptUseCase = new ByCryptUseCaseImpl();
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
    }

    public CreateUserUseCaseImpl(IUserRepository userRepository, IByCryptUseCase byCryptUseCase, ICreatePlaylistUseCase createPlaylistUseCase) {
        this.byCryptUseCase = byCryptUseCase;
        this.userRepository = userRepository;
        this.createPlaylistUseCase = createPlaylistUseCase;
    }

    @Override
    public User execute(User value) {
        String hashPassword = byCryptUseCase.generateHash(value.getPassword());

        value.setPassword(hashPassword);

        User user =  this.userRepository.create(value);

        this.createPlaylistUseCase.execute(new Playlist("Playlist Padrão", user.getId()));

        return user;
    }

    public static void main(String[] args) throws EntityNotFoundException {
        int randomNumber = (int) (Math.random() * 100 + 1);

        User user = new User(UUID.randomUUID(), "joaozin" + randomNumber, "senha", "João", false);

        ICreateUserUseCase createUserUseCase = new CreateUserUseCaseImpl();

        System.out.println(createUserUseCase.execute(user));

    }
}
