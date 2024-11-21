// Singleton Class
class Singleton {
    // Step 1: Creating a private static instance of the Singleton class
    private static Singleton instance;

    // Step 2: Making the constructor private to prevent instantiation
    private Singleton() {
        System.out.println("Singleton instance created!");
    }

    // Step 3: Provide a public static method to get the instance
    public static Singleton getInstance() {
        if (instance == null) { // Create instance if it does not exist
            instance = new Singleton();
        }
        return instance; // Return the single instance
    }

    // Example method to demonstrate functionality
    public void displayMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Main Class to Test Singleton
public class SingletonTest {
    public static void main(String[] args) {
        // Get the single instance of Singleton
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        // Use the Singleton instance
        singleton1.displayMessage();

        // Verify that both references point to the same instance
        System.out.println("Are both instances the same? " + (singleton1 == singleton2));
    }
}
