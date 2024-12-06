package travelbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Flight class hierarchy using Factory Design Pattern
interface Flight {
    String getDetails();
    double getBasePrice();
}

class DomesticFlight implements Flight {
    @Override
    public String getDetails() {
        return "Domestic Flight";
    }

    @Override
    public double getBasePrice() {
        return 10000.0;
    }
}

class InternationalFlight implements Flight {
    @Override
    public String getDetails() {
        return "International Flight";
    }

    @Override
    public double getBasePrice() {
        return 40000.0;
    }
}

class BudgetFlight implements Flight {
    @Override
    public String getDetails() {
        return "Budget Flight";
    }

    @Override
    public double getBasePrice() {
        return 8000.0;
    }
}

class PremiumFlight implements Flight {
    @Override
    public String getDetails() {
        return "Premium Flight";
    }

    @Override
    public double getBasePrice() {
        return 70000.0;
    }
}

// Factory for creating flights
class FlightFactory {
    public static Flight createFlight(String type) {
        return switch (type.toLowerCase()) {
            case "domestic" -> new DomesticFlight();
            case "international" -> new InternationalFlight();
            case "budget" -> new BudgetFlight();
            case "premium" -> new PremiumFlight();
            default -> throw new IllegalArgumentException("Unknown flight type");
        };
    }
}

// Pricing Strategy Design Pattern
interface PricingStrategy {
    double calculatePrice(double basePrice);
}

class BasePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 1; // Base Price
    }
}

class DynamicPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 1.2; // 20% price increase for dynamic pricing
    }
}

class SeasonalDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.8; // 20% discount for seasonal pricing
    }
}

// Flight Booking GUI
public class FlightBooking extends JFrame {
    private JComboBox<String> flightTypeComboBox;
    private JComboBox<String> pricingStrategyComboBox;
    private JTextArea bookingDetailsArea;
    private List<Flight> bookedFlights = new ArrayList<>();

    public FlightBooking() {
        setTitle("Flight Booking System");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top Panel for flight and pricing selection
        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        add(topPanel, BorderLayout.NORTH);

        // Flight Type Selection
        topPanel.add(new JLabel("Select Flight Type:"));
        flightTypeComboBox = new JComboBox<>(new String[]{"Domestic", "International", "Budget", "Premium"});
        topPanel.add(flightTypeComboBox);

        // Pricing Strategy Selection
        topPanel.add(new JLabel("Select Pricing Strategy:"));
        pricingStrategyComboBox = new JComboBox<>(new String[]{"Dynamic Pricing", "Seasonal Discount", "Base Price"});
        topPanel.add(pricingStrategyComboBox);

        // Book Flight Button
        JButton bookButton = new JButton("Book Flight");
        topPanel.add(bookButton);

        // Add Action Listener to Book Button
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookFlight();
            }
        });

        // Booking Details Area
        bookingDetailsArea = new JTextArea();
        bookingDetailsArea.setEditable(false);
        add(new JScrollPane(bookingDetailsArea), BorderLayout.CENTER);
    }

    private void bookFlight() {
        // Get selected flight type and pricing strategy
        String selectedFlightType = (String) flightTypeComboBox.getSelectedItem();
        String selectedPricingStrategy = (String) pricingStrategyComboBox.getSelectedItem();

        // Create flight using factory
        Flight flight = FlightFactory.createFlight(selectedFlightType);

        // Determine pricing strategy
        PricingStrategy strategy;
        if (selectedPricingStrategy.equals("Dynamic Pricing")) {
            strategy = new DynamicPricingStrategy();
        } 
        else if (selectedPricingStrategy.equals("Seasonal Discount")) {
            strategy = new SeasonalDiscountStrategy();
        }
            else {
            strategy = new BasePricingStrategy();
        }

        // Calculate final price
        double finalPrice = strategy.calculatePrice(flight.getBasePrice());

        // Add flight to booked flights
        bookedFlights.add(flight);

        // Update booking details
        bookingDetailsArea.append("Booked: " + flight.getDetails() + "\n");
        bookingDetailsArea.append("Final Price: BDT " + finalPrice + "\n\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightBooking bookingSystem = new FlightBooking();
            bookingSystem.setVisible(true);
        });
    }
}
