package travelbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Hotel Room class hierarchy using Factory Design Pattern
interface HotelRoom {
    String getDescription();
    double getPrice();
}

class SingleRoom implements HotelRoom {
    @Override
    public String getDescription() {
        return "Single Room";
    }

    @Override
    public double getPrice() {
        return 5000.0;
    }
}

class DoubleRoom implements HotelRoom {
    @Override
    public String getDescription() {
        return "Double Room";
    }

    @Override
    public double getPrice() {
        return 8000.0;
    }
}

class SuiteRoom implements HotelRoom {
    @Override
    public String getDescription() {
        return "Suite Room";
    }

    @Override
    public double getPrice() {
        return 15000.0;
    }
}

// Composite Design Pattern for Room Hierarchies
class RoomComposite implements HotelRoom {
    private final List<HotelRoom> rooms = new ArrayList<>();

    public void addRoom(HotelRoom room) {
        rooms.add(room);
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder("Composite Room: \n");
        for (HotelRoom room : rooms) {
            description.append(" - ").append(room.getDescription()).append("\n");
        }
        return description.toString();
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (HotelRoom room : rooms) {
            totalPrice += room.getPrice();
        }
        return totalPrice;
    }
}

// Factory for creating hotel rooms
class HotelRoomFactory {
    public static HotelRoom createRoom(String type) {
        return switch (type.toLowerCase()) {
            case "single" -> new SingleRoom();
            case "double" -> new DoubleRoom();
            case "suite" -> new SuiteRoom();
            default -> throw new IllegalArgumentException("Unknown room type");
        };
    }
}

// Proxy for controlling access to room booking
class RoomBookingProxy {
    private final Map<HotelRoom, Integer> roomAvailability = new HashMap<>();

    public void addRoom(HotelRoom room, int quantity) {
        roomAvailability.put(room, roomAvailability.getOrDefault(room, 0) + quantity);
    }

    public boolean bookRoom(HotelRoom room) {
        int available = roomAvailability.getOrDefault(room, 0);
        if (available > 0) {
            roomAvailability.put(room, available - 1);
            return true;
        }
        return false;
    }

    public boolean isRoomAvailable(HotelRoom room) {
        return roomAvailability.getOrDefault(room, 0) > 0;
    }

    public int getAvailableRooms(HotelRoom room) {
        return roomAvailability.getOrDefault(room, 0);
    }
}

// Hotel Booking GUI
public class HotelBooking extends JFrame {
    private JComboBox<String> hotelComboBox;
    private JComboBox<String> roomTypeComboBox;
    private JTextArea bookingDetailsArea;
    private Map<String, RoomBookingProxy> hotels = new HashMap<>();

    public HotelBooking() {
        setTitle("Hotel Booking System");
        setSize(800, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize hotels and rooms
        initializeHotels();

        // Top Panel for hotel and room selection
        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        add(topPanel, BorderLayout.NORTH);

        // Hotel Selection
        topPanel.add(new JLabel("Select Hotel:"));
        hotelComboBox = new JComboBox<>(hotels.keySet().toArray(new String[0]));
        topPanel.add(hotelComboBox);

        // Room Type Selection
        topPanel.add(new JLabel("Select Room Type:"));
        roomTypeComboBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        topPanel.add(roomTypeComboBox);

        // Book Room Button
        JButton bookButton = new JButton("Book Room");
        topPanel.add(bookButton);

        // Add Action Listener to Book Button
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookRoom();
            }
        });

        // Booking Details Area
        bookingDetailsArea = new JTextArea();
        bookingDetailsArea.setEditable(false);
        add(new JScrollPane(bookingDetailsArea), BorderLayout.CENTER);
    }

    private void initializeHotels() {
        // Hotel 1: Pan Pacific Sonargaon Hotel
        RoomBookingProxy hotelSonargaon = new RoomBookingProxy();
        hotelSonargaon.addRoom(HotelRoomFactory.createRoom("single"), 10);
        hotelSonargaon.addRoom(HotelRoomFactory.createRoom("double"), 5);
        hotelSonargaon.addRoom(HotelRoomFactory.createRoom("suite"), 2);
        hotels.put("Pan Pacific Sonargaon Hotel", hotelSonargaon);

        // Hotel 2: Hotel Intercontinental
        RoomBookingProxy hotelIntercontinental = new RoomBookingProxy();
        hotelIntercontinental.addRoom(HotelRoomFactory.createRoom("single"), 15);
        hotelIntercontinental.addRoom(HotelRoomFactory.createRoom("double"), 8);
        hotelIntercontinental.addRoom(HotelRoomFactory.createRoom("suite"), 3);
        hotels.put("Hotel Intercontinental", hotelIntercontinental);

        // Hotel 3: Radisson Hotel
        RoomBookingProxy radissonHotel = new RoomBookingProxy();
        radissonHotel.addRoom(HotelRoomFactory.createRoom("single"), 5);
        radissonHotel.addRoom(HotelRoomFactory.createRoom("double"), 10);
        radissonHotel.addRoom(HotelRoomFactory.createRoom("suite"), 5);
        hotels.put("Radisson Hotel", radissonHotel);
    }

    private void bookRoom() {
        // Get selected hotel and room type
        String selectedHotel = (String) hotelComboBox.getSelectedItem();
        String selectedRoomType = (String) roomTypeComboBox.getSelectedItem();

        // Get the corresponding proxy
        RoomBookingProxy bookingProxy = hotels.get(selectedHotel);

        // Create room using factory
        HotelRoom room = HotelRoomFactory.createRoom(selectedRoomType);

        // Check availability and book room
        if (bookingProxy.isRoomAvailable(room)) {
            bookingProxy.bookRoom(room);

            // Update booking details
            bookingDetailsArea.append("Booked: " + selectedRoomType + " at " + selectedHotel + "\n");
            bookingDetailsArea.append("Price: BDT " + room.getPrice() + "\n\n");
        } else {
            bookingDetailsArea.append("Room not available: " + selectedRoomType + " at " + selectedHotel + "\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelBooking bookingSystem = new HotelBooking();
            bookingSystem.setVisible(true);
        });
    }
}
