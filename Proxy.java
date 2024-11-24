// User.java
public class User {
    private String userID;
    private String name;
    private String email;

    public User(String userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [ID=" + userID + ", Name=" + name + ", Email=" + email + "]";
    }
}

// SensitiveDataAccess.java
public interface SensitiveDataAccess {
    User getUser(String userID);
}

// RealSensitiveDataAccess.java
public class RealSensitiveDataAccess implements SensitiveDataAccess {
    @Override
    public User getUser(String userID) {
        // Simulate fetching user from database
        System.out.println("Fetching user from database...");
        return new User(userID, "John Doe", "john.doe@example.com");
    }
}

// ProxySensitiveDataAccess.java
public class ProxySensitiveDataAccess implements SensitiveDataAccess {
    private RealSensitiveDataAccess realAccess;
    private boolean hasAccess;

    public ProxySensitiveDataAccess(boolean hasAccess) {
        this.realAccess = new RealSensitiveDataAccess();
        this.hasAccess = hasAccess;
    }

    @Override
    public User getUser(String userID) {
        if (hasAccess) {
            return realAccess.getUser(userID);
        } else {
            System.out.println("Access denied: Unable to fetch user information.");
            return null;
        }
    }
}

// Usage Example
public class Main {
    public static void main(String[] args) {
        SensitiveDataAccess proxy = new ProxySensitiveDataAccess(true); // Change to false to test access control
        User user = proxy.getUser("U456");

        if (user != null) {
            System.out.println(user);
        }
    }
}
