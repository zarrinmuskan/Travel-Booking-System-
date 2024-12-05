package travelbookingsystem;

import javax.swing.*;
import java.awt.*;

public class LoadingFrame extends JFrame {
    private JProgressBar progressBar;

    public LoadingFrame() {
        setBounds(500, 300, 600, 300);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel title = new JLabel("Travel Booking System");
        title.setBounds(185, 20, 600, 40);
        title.setFont(new Font("Raleway", Font.BOLD, 20));
        title.setForeground(new Color(133, 193, 233));
        add(title);

        progressBar = new JProgressBar();
        progressBar.setBounds(150, 100, 300, 35);
        progressBar.setStringPainted(true); // Display progress percentage
        add(progressBar);

        JLabel loadingLabel = new JLabel("Loading please wait......");
        loadingLabel.setBounds(230, 130, 150, 30);
        loadingLabel.setFont(new Font("Raleway", Font.PLAIN, 14));
        loadingLabel.setForeground(Color.BLUE);
        add(loadingLabel);
        
        JLabel username = new JLabel("Welcome to the Travel Booking Site");
        username.setBounds(175, 200, 400, 40);
        username.setFont(new Font("Raleway", Font.PLAIN, 16));
        username.setForeground(Color.RED);
        add(username);

        setVisible(true);

        // Simulate loading
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(50); // Simulate loading time
                    progressBar.setValue(i);
                }
                // After loading is complete, open the Dashboard
                setVisible(false);
                new Dashboard(); // Launching the Dashboard after loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public static void main(String[] args) {
        new LoadingFrame(); 
    }
}