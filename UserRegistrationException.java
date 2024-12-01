public class UserRegistrationException extends Exception {

    // Constructor with a message
    public UserRegistrationException(String message) {
        super(message);
    }

    // Constructor with a message and a cause
    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}