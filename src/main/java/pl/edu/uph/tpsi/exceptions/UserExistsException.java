package pl.edu.uph.tpsi.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String s) {
        super(s);
    }
}
