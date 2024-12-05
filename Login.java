package travelbookingsystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    private LoginStrategy loginStrategy; // Interface for different login methods
    private JTextField tfUsername;
    private JPasswordField tfPassword;

    // Constructor for the Login class
    public Login(LoginStrategy initialStrategy) {
        this.loginStrategy = initialStrategy;

        // Frame settings
        setSize(900, 400);
        setLocation(350, 200);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        // Left panel with image
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(131, 191, 233));
        p1.setBounds(0, 0, 400, 400);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(100, 120, 200, 200);
        p1.add(image);

        // Right panel with login form
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 30, 450, 300);
        add(p2);

        // Username label and field
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(60, 20, 100, 25);
        lblUsername.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        p2.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(60, 60, 300, 30);
        tfUsername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfUsername);

        // Password label and field
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(60, 110, 100, 25);
        lblPassword.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        p2.add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(60, 150, 300, 30);
        tfPassword.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfPassword);

        // Login button
        JButton login = new JButton("Login");
        login.setBounds(60, 200, 130, 30);
        login.setBackground(new Color(133, 193, 233));
        login.setForeground(Color.WHITE);
        login.setBorder(new LineBorder(new Color(133, 193, 233)));
        p2.add(login);

        // Login button functionality
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = new String(tfPassword.getPassword());

                // Authenticate using the strategy pattern
                if (loginStrategy.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false); // Close the Login window

                    // Show the Loading Frame while the next window is loading
                    LoadingFrame loadingFrame = new LoadingFrame();
                    loadingFrame.setVisible(true);

                    // After the loading frame is complete, open the Dashboard
                    new Thread(() -> {
                        try {
                            Thread.sleep(5000);  // Simulate loading time
                            loadingFrame.setVisible(false);  // Hide the loading frame after loading is done
                            new Dashboard();  // Launch the Dashboard after loading
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }).start();

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Signup button
        JButton signup = new JButton("Signup");
        signup.setBounds(230, 200, 130, 30);
        signup.setBackground(new Color(133, 193, 233));
        signup.setForeground(Color.WHITE);
        signup.setBorder(new LineBorder(new Color(133, 193, 233)));
        p2.add(signup);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Close the Login window
                new Signup(); // Open the Signup window
            }
        });

        // Forgot Password button
        JButton password = new JButton("Forgot Password?");
        password.setBounds(130, 250, 130, 30);
        password.setBackground(new Color(133, 193, 233));
        password.setForeground(Color.WHITE);
        password.setBorder(new LineBorder(new Color(133, 193, 233)));
        p2.add(password);

        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Email", "Phone"};
                String choice = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose a recovery method:",
                        "Forgot Password",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (choice != null) {
                    PasswordRecovery recovery = PasswordRecoveryFactory.getRecoveryMethod(choice.toLowerCase());
                    recovery.recoverPassword();
                }
            }
        });

        setVisible(true);
    }

    // Method to set a new login strategy
    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    // Main method to start the application
    public static void main(String[] args) {
        new Login(new DatabaseLoginStrategy());
    }
}
