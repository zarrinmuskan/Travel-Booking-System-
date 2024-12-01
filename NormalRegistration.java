public class NormalRegistration implements RegistrationStrategy {
    @Override
    public void registerUser(User user) throws UserRegistrationException {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new UserRegistrationException("Email and password cannot be null");
        }
        // Simulate saving to a database
        System.out.println("User registered with email: " + user.getEmail());
    }
}
