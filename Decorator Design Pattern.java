// Decorator Design Pattern
// Booking System with extra services (airport transfer, special request service)

// Booking Interface
interface Booking {
    void processBooking();  // Base functionality to process a booking
}

// Concrete Component (Basic Booking)
class BasicBooking implements Booking {
    @Override
    public void processBooking() {
        System.out.println("Processing basic booking.");
    }
}

// Abstract Decorator (Booking Decorator)
abstract class BookingDecorator implements Booking {
    protected Booking wrappedBooking;

    public BookingDecorator(Booking booking) {
        this.wrappedBooking = booking;
    }

    @Override
    public void processBooking() {
        wrappedBooking.processBooking();  // Delegate to wrapped booking
    }
}

// Decorator 1: Airport Transfer Service
class AirportTransferDecorator extends BookingDecorator {
    public AirportTransferDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public void processBooking() {
        super.processBooking();  // Delegate to the wrapped booking
        System.out.println("Adding airport transfer service to the booking.");
    }
}

// Decorator 2: Special Request Service
class SpecialRequestDecorator extends BookingDecorator {
    public SpecialRequestDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public void processBooking() {
        super.processBooking();  // Delegate to the wrapped booking
        System.out.println("Adding special requests (e.g., extra bed, VIP service) to the booking.");
    }
}

// Main class for testing Booking with Extra Services (Decorator Pattern)
public class BookingSystemTest {
    public static void main(String[] args) {
        // Basic Booking
        Booking basicBooking = new BasicBooking();

        // Decorate the basic booking with additional services
        Booking bookingWithAirportTransfer = new AirportTransferDecorator(basicBooking);
        Booking bookingWithSpecialRequests = new SpecialRequestDecorator(bookingWithAirportTransfer);

        // Process the final booking with all services
        finalBooking.processBooking();
    }
}
