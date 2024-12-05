package travelbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    public Dashboard() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Header
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0, 0, 102));
        p1.setBounds(0, 0, 1600, 65);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png"));
        Image i2 = i1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        JLabel icon = new JLabel(new ImageIcon(i2));
        icon.setBounds(5, 0, 70, 70);
        p1.add(icon);

        JLabel title = new JLabel("Travel Booking Dashboard");
        title.setBounds(600, 10, 900, 40);
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        p1.add(title);

        // Left Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(0, 0, 102));
        navPanel.setBounds(0, 50, 200, 550);
        navPanel.setLayout(null);
        add(navPanel);

        // Add Buttons in Specified Order
        String[] buttonLabels = {
            "My Profile", "View Bookings", "Flight Booking", "Hotel Booking",
            "Payment", "Review", "Logout"
        };

        Color[] buttonColors = {
            new Color(133, 193, 233), // Blue
            new Color(133, 193, 233), // Blue
            new Color(34, 139, 34),   // Green
            new Color(70, 130, 180),  // Steel Blue
            new Color(255, 193, 7),   // Yellow
            new Color(133, 193, 233), // Blue
            new Color(255, 69, 58)    // Red
        };

        int yOffset = 50;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(20, yOffset, 160, 40);
            button.setBackground(buttonColors[i]);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Raleway", Font.PLAIN, 16));
            button.addActionListener(this);
            navPanel.add(button);
            yOffset += 60;
        }

        // Main Content Area
        JLabel contentArea = new JLabel("Welcome to the Dashboard!");
        contentArea.setBounds(250, 100, 500, 30);
        contentArea.setFont(new Font("Raleway", Font.BOLD, 22));
        contentArea.setForeground(Color.DARK_GRAY);
        add(contentArea);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("My Profile")) {
            JOptionPane.showMessageDialog(this, "Opening profile...");
            
            // Add functionality for viewing/editing profile
            MyProfile myProfile = new MyProfile();
            myProfile.displayGUI();  // Ensure the GUI is displayed
            
        } else if (action.equals("View Bookings")) {
            JOptionPane.showMessageDialog(this, "Viewing all bookings...");
            
            // Add functionality for viewing bookings
            BookingManager bookingManager = new BookingManager();
            ViewBook viewBook = new ViewBook(bookingManager);
            viewBook.setVisible(true);
            
        } else if (action.equals("Flight Booking")) {
            JOptionPane.showMessageDialog(this, "Booking a flight...");
            
            // Add functionality for flight booking
            FlightBooking flightBookingWindow = new FlightBooking();
            flightBookingWindow.setVisible(true); 
            
        } else if (action.equals("Hotel Booking")) {
            JOptionPane.showMessageDialog(this, "Booking a hotel...");
            
            // Add functionality for hotel booking
            HotelBooking hotelBookingWindow = new HotelBooking();
            hotelBookingWindow.setVisible(true); 
            
        } else if (action.equals("Payment")) {
            JOptionPane.showMessageDialog(this, "Processing payment...");
            
            // Add functionality for payment
            new Payment();
            
        } else if (action.equals("Review")) {
            JOptionPane.showMessageDialog(this, "Opening customer review...");
            TravelAgencyReviewGUI travelAgencyReviewGUI = new TravelAgencyReviewGUI();
            travelAgencyReviewGUI.setVisible(true);  // Open the review window
            
        } else if (action.equals("Logout")) {
            int response = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login(new DatabaseLoginStrategy()); // Adjust to your Login implementation
            }
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
