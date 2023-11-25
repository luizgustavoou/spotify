package imd.ufrn.br.spotify.services.user;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IRemoveUserUseCase {
    void execute(UUID id) throws EntityNotFoundException;
}
