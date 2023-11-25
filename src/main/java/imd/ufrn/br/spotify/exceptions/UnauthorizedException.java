package imd.ufrn.br.spotify.exceptions;

public class UnauthorizedException extends Exception{
    public UnauthorizedException() {}

    public UnauthorizedException(String message) {
        super(message);
    }
}
