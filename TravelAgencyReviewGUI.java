package travelbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface Review {
    void display();
}

class HotelReview implements Review {
    @Override
    public void display() {
        JOptionPane.showMessageDialog(null, "Displaying Hotel Review.", "Hotel Review", JOptionPane.INFORMATION_MESSAGE);
    }
}

class FlightReview implements Review {
    @Override
    public void display() {
        JOptionPane.showMessageDialog(null, "Displaying Flight Review.", "Flight Review", JOptionPane.INFORMATION_MESSAGE);
    }
}

class WebsiteReview implements Review {
    @Override
    public void display() {
        JOptionPane.showMessageDialog(null, "Displaying Website Review.", "Website Review", JOptionPane.INFORMATION_MESSAGE);
    }
}

class ReviewFactory {
    public static Review createReview(String type) {
        switch (type.toLowerCase()) {
            case "hotel":
                return new HotelReview();
            case "flight":
                return new FlightReview();
            case "website":
                return new WebsiteReview();
            default:
                throw new IllegalArgumentException("Unknown review type: " + type);
        }
    }
}

public class TravelAgencyReviewGUI extends JFrame {

    public static void main(String[] args) {
  
        JFrame frame = new JFrame("Online Travel Agency - Review Section");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1));

       
        JLabel label = new JLabel("Select the type of review you want to view:", JLabel.CENTER);
        frame.add(label);

       
        JButton hotelButton = new JButton("Hotel Review");
        JButton flightButton = new JButton("Flight Review");
        JButton websiteButton = new JButton("Website Review");

        frame.add(hotelButton);
        frame.add(flightButton);
        frame.add(websiteButton);

        
        hotelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Review review = ReviewFactory.createReview("hotel");
                review.display();
            }
        });

        flightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Review review = ReviewFactory.createReview("flight");
                review.display();
            }
        });

        websiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Review review = ReviewFactory.createReview("website");
                review.display();
            }
        });
        
        frame.setVisible(true);
    }
}
