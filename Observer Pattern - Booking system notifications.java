// Observer Design Pattern
// Booking System to notify users and admins when a booking status changes

// Observer Interace
interface BookingObserver {
    void update(String status);
}

// Concrete Observer (User)
class UserObserver implements BookingObserver {
    private String name;

    public UserObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("User " + name + " notified of booking status: " + status);
    }
}

// Concrete Observer (Admin)
class AdminObserver implements BookingObserver {
    @Override
    public void update(String status) {
        System.out.println("Admin notified of booking status: " + status);
    }
}

// Subject Interface
interface BookingSubject {
    void addObserver(BookingObserver observer);
    void removeObserver(BookingObserver observer);
    void notifyObservers();
}

// Concrete Subject (Booking)
class Booking implements BookingSubject {
    private List<BookingObserver> observers = new ArrayList<>();
    private String status;

    public Booking(String status) {
        this.status = status;
    }

    @Override
    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BookingObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (BookingObserver observer : observers) {
            observer.update(status);
        }
    }

    // Method to change the booking status and notify observers
    public void updateStatus(String status) {
        this.status = status;
        notifyObservers();  // Notify all observers about the status change
    }
}

// Main class for testing Booking Observer
public class BookingSystemTest {
    public static void main(String[] args) {
        Booking booking = new Booking("Booking Pending");

        // Create observers
        BookingObserver user = new UserObserver("Alice");
        BookingObserver admin = new AdminObserver();

        // Register observers
        booking.addObserver(user);
        booking.addObserver(admin);

        // Update booking status
        booking.updateStatus("Booking Confirmed");
    }
}
