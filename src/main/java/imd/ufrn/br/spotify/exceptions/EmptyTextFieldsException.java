package imd.ufrn.br.spotify.exceptions;

public class EmptyTextFieldsException extends Exception{
    public EmptyTextFieldsException() {}

    public EmptyTextFieldsException(String message) {
        super(message);
    }
}
