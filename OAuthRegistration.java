public class OAuthRegistration implements RegistrationStrategy {

    @Override
    public void registerUser(User user) throws UserRegistrationException {
        try {
            // Example of input validation that may throw IllegalArgumentException
            if (user == null || user.getEmail() == null) {
                throw new IllegalArgumentException("User email is required");
            }
            // Registration logic here
        } catch (IllegalArgumentException e) {
            throw new UserRegistrationException("Invalid user data during OAuth registration", e);
        }
    }
}
