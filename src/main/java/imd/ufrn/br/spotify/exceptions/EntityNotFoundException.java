package imd.ufrn.br.spotify.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(){ }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
