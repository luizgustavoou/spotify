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

    public static void main(String[] args) throws EntityNotFoundException {
        int randomNumber = (int) (Math.random() * 100 + 1);

        Playlist playlistPadrao = new Playlist("Playlist padrão", UUID.fromString("d2add4ac-5509-45ef-87a5-d6c407f29a30"));

        ICreatePlaylistUseCase createPlaylistUseCase = new CreatePlaylistUseCaseImpl();

        System.out.println(createPlaylistUseCase.execute(playlistPadrao));

        User user = new User(UUID.randomUUID(), "Pato Agiota Fisiculturista" + randomNumber, "senha", "João", false, UUID.fromString("6328bba4-8fc1-11ee-b9d1-0242ac120002"));

        IFindOnePlaylistByIdUseCase findOnePlaylistByIdUseCase = new FindOnePlaylistByIdUseCaseImpl();

        Playlist foundPlaylist = findOnePlaylistByIdUseCase.execute(playlistPadrao.getId());

        if (foundPlaylist != null) {
            foundPlaylist.setUserId(user.getId());
        }

        ICreateUserUseCase createUserUseCase = new CreateUserUseCaseImpl();

        System.out.println(createUserUseCase.execute(user));

    }
}
