public interface RegistrationStrategy {
    void registerUser(User user) throws UserRegistrationException;
}
