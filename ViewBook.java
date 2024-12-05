package travelbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Booking entity
class Booking {
    private String type; // "Hotel" or "Flight"
    private String details;
    private double price;

    public Booking(String type, String details, double price) {
        this.type = type;
        this.details = details;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Type: %s\nDetails: %s\nPrice: BDT %.2f\n", type, details, price);
    }
}

// Subject interface (Observable)
interface BookingSubject {
    void addObserver(BookingObserver observer);
    void removeObserver(BookingObserver observer);
    void notifyObservers();
}

// Observer interface
interface BookingObserver {
    void update(List<Booking> bookings);
}

// BookingManager (Subject)
class BookingManager implements BookingSubject {
    private List<Booking> bookings = new ArrayList<>();
    private List<BookingObserver> observers = new ArrayList<>();

    public void loadBookingsFromDatabase() {
        bookings.clear();
        try {
            // Database connection (replace with your database credentials)
            String dbUrl = "jdbc:mysql://localhost:3306/travelbookingsystem";
            String dbUsername = "root";
            String dbPassword = "4288";
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            String query = "SELECT type, details, price FROM bookings";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    String details = resultSet.getString("details");
                    double price = resultSet.getDouble("price");
                    bookings.add(new Booking(type, details, price));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        notifyObservers();
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
            observer.update(bookings);
        }
    }
}

// ViewBook (Observer)
public class ViewBook extends JFrame implements BookingObserver {
    private JTextArea bookingDetailsArea;
    private BookingManager bookingManager;

    public ViewBook(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
        bookingManager.addObserver(this);

        setTitle("View Bookings");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel with Refresh Button
        JPanel topPanel = new JPanel(new FlowLayout());
        JButton refreshButton = new JButton("Refresh Bookings");
        topPanel.add(refreshButton);
        add(topPanel, BorderLayout.NORTH);

        // Booking Details Area
        bookingDetailsArea = new JTextArea();
        bookingDetailsArea.setEditable(false);
        add(new JScrollPane(bookingDetailsArea), BorderLayout.CENTER);

        // Add Action Listener to Refresh Button
        refreshButton.addActionListener((ActionEvent e) -> bookingManager.loadBookingsFromDatabase());

        // Load initial bookings
        bookingManager.loadBookingsFromDatabase();
    }

    @Override
    public void update(List<Booking> bookings) {
        bookingDetailsArea.setText("");
        if (bookings.isEmpty()) {
            bookingDetailsArea.append("No bookings found.\n");
        } else {
            for (Booking booking : bookings) {
                bookingDetailsArea.append(booking.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingManager bookingManager = new BookingManager();
            ViewBook viewBook = new ViewBook(bookingManager);
            viewBook.setVisible(true);
        });
    }
}

