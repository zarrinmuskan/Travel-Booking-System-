package travelbookingsystem;

public class FileLoginStrategy implements LoginStrategy {
    @Override
    public boolean authenticate(String username, String password) {
        // Replace this with file-based logic (e.g., reading from a file)
        return "root".equals(username) && "4288".equals(password);
    }
}
