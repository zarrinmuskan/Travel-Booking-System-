package travelbookingsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {
    private JTextField tffirstname, tflastname, tfphone, tfemail, tfusername;
    private JPasswordField tfpassword;

    Signup() {
        setBounds(350, 200, 900, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(133, 193, 233));
        p1.setBounds(0, 0, 500, 400);
        p1.setLayout(null);
        add(p1);

        JLabel lblfirstname = new JLabel("First Name");
        lblfirstname.setBounds(50, 20, 125, 25);
        lblfirstname.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblfirstname);

        tffirstname = new JTextField();
        tffirstname.setBounds(190, 20, 180, 25);
        tffirstname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tffirstname);

        JLabel lbllastname = new JLabel("Last Name");
        lbllastname.setBounds(50, 60, 125, 25);
        lbllastname.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lbllastname);

        tflastname = new JTextField();
        tflastname.setBounds(190, 60, 180, 25);
        tflastname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tflastname);

        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(50, 100, 125, 25);
        lblphone.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(190, 100, 180, 25);
        tfphone.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfphone);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 140, 125, 25);
        lblemail.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(190, 140, 180, 25);
        tfemail.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfemail);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(50, 180, 125, 25);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(190, 180, 180, 25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(50, 220, 125, 25);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        p1.add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(190, 220, 180, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfpassword);

        JButton create = new JButton("Create Account");
        create.setBounds(150, 280, 150, 30);
        create.setBackground(new Color(0, 123, 255));
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Tahoma", Font.BOLD, 12));
        create.addActionListener(this);
        p1.add(create);

        JButton login = new JButton("Already have an account? Login");
        login.setBounds(120, 320, 250, 30);
        login.setBackground(new Color(133, 193, 233));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Tahoma", Font.PLAIN, 12));
        login.setBorder(BorderFactory.createEmptyBorder());
        login.addActionListener(e -> {
            setVisible(false);
            new Login(null);
        });
        p1.add(login);

        JPanel p2 = new JPanel();
        p2.setBounds(500, 0, 400, 400);
        p2.setBackground(Color.WHITE);
        p2.setLayout(null);
        add(p2);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(img));
        image.setBounds(75, 75, 250, 250);
        p2.add(image);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String firstName = tffirstname.getText().trim();
        String lastName = tflastname.getText().trim();
        String phone = tfphone.getText().trim();
        String email = tfemail.getText().trim();
        String username = tfusername.getText().trim();
        String password = new String(tfpassword.getPassword()).trim();

        // Check for empty fields
        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "First Name is required!");
            tffirstname.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Last Name is required!");
            tflastname.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phone Number is required!");
            tfphone.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email is required!");
            tfemail.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required!");
            tfusername.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password is required!");
            tfpassword.requestFocus();
            return;
        }

        // Validate email format
        if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            JOptionPane.showMessageDialog(null, "Invalid email address!");
            tfemail.requestFocus();
            return;
        }

        // Validate phone number (optional: numeric check)
        if (!phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phone Number must be numeric!");
            tfphone.requestFocus();
            return;
        }

        try {
            Conn c = new Conn();

            // Check if email already exists
            String checkEmailQuery = "SELECT * FROM users WHERE email = '" + email + "'";
            ResultSet rsEmail = c.s.executeQuery(checkEmailQuery);
            if (rsEmail.next()) { // Email exists
                JOptionPane.showMessageDialog(null, "Email already exists! Please use a different email.");
                tfemail.requestFocus();
                return;
            }

            // Check if phone already exists
            String checkPhoneQuery = "SELECT * FROM users WHERE phone = '" + phone + "'";
            ResultSet rsPhone = c.s.executeQuery(checkPhoneQuery);
            if (rsPhone.next()) { // Phone exists
                JOptionPane.showMessageDialog(null, "Phone number already exists! Please use a different phone number.");
                tfphone.requestFocus();
                return;
            }

            // Insert new user if email and phone don't exist
            String query = "INSERT INTO users (first_name, last_name, phone, email, username, password) VALUES ('" +
                           firstName + "', '" + lastName + "', '" + phone + "', '" + email + "', '" + username + "', '" + password + "')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Account Created Successfully!");
            setVisible(false);  //Close the signup window
            new Login(null); // Open the login window
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while creating account!");
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}