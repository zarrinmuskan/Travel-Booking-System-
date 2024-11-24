// Booking.java
public class Booking {
    private String bookingID;
    private String userID;
    private String bookingType;
    private String bookingDate;
    private double totalCost;

    // Private constructor to enforce Builder usage
    private Booking(BookingBuilder builder) {
        this.bookingID = builder.bookingID;
        this.userID = builder.userID;
        this.bookingType = builder.bookingType;
        this.bookingDate = builder.bookingDate;
        this.totalCost = builder.totalCost;
    }

    // Static nested Builder class
    public static class BookingBuilder {
        private String bookingID;
        private String userID;
        private String bookingType;
        private String bookingDate;
        private double totalCost;

        public BookingBuilder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public BookingBuilder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public BookingBuilder setBookingType(String bookingType) {
            this.bookingType = bookingType;
            return this;
        }

        public BookingBuilder setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public BookingBuilder setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }

    @Override
    public String toString() {
        return "Booking [ID=" + bookingID + ", UserID=" + userID + ", Type=" + bookingType +
                ", Date=" + bookingDate + ", TotalCost=" + totalCost + "]";
    }
}

// Usage Example
public class Main {
    public static void main(String[] args) {
        Booking booking = new Booking.BookingBuilder()
                .setBookingID("B123")
                .setUserID("U456")
                .setBookingType("Flight")
                .setBookingDate("2024-11-18")
                .setTotalCost(350.75)
                .build();

        System.out.println(booking);
    }
}
