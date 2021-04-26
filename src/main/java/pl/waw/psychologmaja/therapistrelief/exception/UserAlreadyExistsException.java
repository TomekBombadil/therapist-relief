package pl.waw.psychologmaja.therapistrelief.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
