package travelbookingsystem;

public interface AccountCreationStrategy {
    void createAccount(String firstName, String lastName, String phone, String email, String username, String password);
}
