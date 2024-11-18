// Interface for Factory products
interface Entity {
    void displayInfo();
}

// Concrete User class
class User implements Entity {
    private String userID;
    private String name;

    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + userID + ", Name: " + name);
    }
}

// Concrete ServiceProvider class
class ServiceProvider implements Entity {
    private String providerID;
    private String name;

    public ServiceProvider(String providerID, String name) {
        this.providerID = providerID;
        this.name = name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Service Provider ID: " + providerID + ", Name: " + name);
    }
}

// Concrete Administrator class
class Administrator implements Entity {
    private String adminID;
    private String name;

    public Administrator(String adminID, String name) {
        this.adminID = adminID;
        this.name = name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Administrator ID: " + adminID + ", Name: " + name);
    }
}

// Factory Class
class EntityFactory {
    public static Entity createEntity(String type, String id, String name) {
        switch (type.toLowerCase()) {
            case "user":
                return new User(id, name);
            case "serviceprovider":
                return new ServiceProvider(id, name);
            case "administrator":
                return new Administrator(id, name);
            default:
                throw new IllegalArgumentException("Unknown entity type");
        }
    }
}

// Main Class to Test the Factory
public class Factory {
    public static void main(String[] args) {
        Entity user = EntityFactory.createEntity("user", "U001", "Alice");
        Entity serviceProvider = EntityFactory.createEntity("serviceprovider", "SP001", "Hotel ABC");
        Entity administrator = EntityFactory.createEntity("administrator", "A001", "Admin John");

        // Display Info
        user.displayInfo();
        serviceProvider.displayInfo();
        administrator.displayInfo();
    }
}
