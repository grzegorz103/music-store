package pl.edu.uph.tpsi.exceptions;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String s) {
        super(s);
    }
}
