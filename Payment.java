package travelbookingsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class BkashAdapter implements PaymentProcessor {
    private BkashPayment bkashPayment;

    public BkashAdapter() {
        this.bkashPayment = new BkashPayment();
    }

    @Override
    public void processPayment(double amount) {
        bkashPayment.makePayment(amount);
    }
}

class NagadAdapter implements PaymentProcessor {
    private NagadPayment nagadPayment;

    public NagadAdapter() {
        this.nagadPayment = new NagadPayment();
    }

    @Override
    public void processPayment(double amount) {
        nagadPayment.pay(amount);
    }
}

public class Payment extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travelbookingsystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "4288";

    public Payment() {
        setTitle("Online Travel Agency Payment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel amountLabel = new JLabel("Enter Payment Amount:");
        JTextField amountField = new JTextField();

        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        JButton bkashButton = new JButton("Pay with Bkash");
        JButton nagadButton = new JButton("Pay with Nagad");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(bkashButton);
        buttonPanel.add(nagadButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        bkashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePayment(amountField, "Bkash", outputArea);
            }
        });

        nagadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePayment(amountField, "Nagad", outputArea);
            }
        });

        setVisible(true);
    }

    private void handlePayment(JTextField amountField, String paymentMethod, JTextArea outputArea) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            PaymentProcessor processor;

            if (paymentMethod.equals("Bkash")) {
                processor = new BkashAdapter();
            } else {
                processor = new NagadAdapter();
            }

            processor.processPayment(amount);
            outputArea.append("Paid BDT " + amount + " via " + paymentMethod + ".\n");
            savePaymentToDatabase(paymentMethod, amount);
        } catch (NumberFormatException ex) {
            outputArea.append("Invalid amount entered.\n");
        } catch (SQLException ex) {
            outputArea.append("Database error: " + ex.getMessage() + "\n");
        }
    }

    private void savePaymentToDatabase(String paymentMethod, double amount) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Payments (payment_method, amount) VALUES (?, ?)"
             )) {
            stmt.setString(1, paymentMethod);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Payment());
    }
}
