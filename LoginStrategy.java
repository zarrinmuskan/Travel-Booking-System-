package travelbookingsystem;

public interface LoginStrategy {
    boolean authenticate(String username, String password);
}
