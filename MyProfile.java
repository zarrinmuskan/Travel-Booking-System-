package travelbookingsystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MyProfile {
    // UserProfile inner class with Builder Pattern
    public static class UserProfile {
        private String name;
        private String email;
        private String phone;
        private String address;
        private String gender;
        private String picturePath; // Optional field

        private UserProfile(UserProfileBuilder builder) {
            this.name = builder.name;
            this.email = builder.email;
            this.phone = builder.phone;
            this.address = builder.address;
            this.gender = builder.gender;
            this.picturePath = builder.picturePath;
        }

        // Getters
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
        public String getGender() { return gender; }
        public String getPicturePath() { return picturePath; }

        // Builder Class
        public static class UserProfileBuilder {
            private String name;
            private String email;
            private String phone;
            private String address;
            private String gender;
            private String picturePath;

            public UserProfileBuilder setName(String name) {
                this.name = name;
                return this;
            }

            public UserProfileBuilder setEmail(String email) {
                this.email = email;
                return this;
            }

            public UserProfileBuilder setPhone(String phone) {
                this.phone = phone;
                return this;
            }

            public UserProfileBuilder setAddress(String address) {
                this.address = address;
                return this;
            }

            public UserProfileBuilder setGender(String gender) {
                this.gender = gender;
                return this;
            }

            public UserProfileBuilder setPicturePath(String picturePath) {
                this.picturePath = picturePath;
                return this;
            }

            public UserProfile build() {
                return new UserProfile(this);
            }
        }
    }

    // Method to save UserProfile to MySQL database
    private void saveUserProfileToDatabase(UserProfile profile) {
        String url = "jdbc:mysql://localhost:3306/travelbookingsystem";
        String user = "root";
        String password = "4288";
        String query = "INSERT INTO user_profiles (name, email, phone, address, gender, picture) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, profile.getName());
            pstmt.setString(2, profile.getEmail());
            pstmt.setString(3, profile.getPhone());
            pstmt.setString(4, profile.getAddress());
            pstmt.setString(5, profile.getGender());
            pstmt.setString(6, profile.getPicturePath());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Profile saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GUI Implementation
    public void displayGUI() {
        JFrame frame = new JFrame("My Profile");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(140, 20, 200, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 60, 100, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(140, 60, 200, 25);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 100, 100, 25);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(140, 100, 200, 25);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 140, 100, 25);
        JTextField addressField = new JTextField();
        addressField.setBounds(140, 140, 200, 25);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 180, 100, 25);
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setBounds(140, 180, 200, 25);

        JLabel pictureLabel = new JLabel("Picture:");
        pictureLabel.setBounds(20, 220, 100, 25);
        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(140, 220, 200, 25);

        JButton saveButton = new JButton("Save Profile");
        saveButton.setBounds(140, 280, 200, 25);

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(addressLabel);
        frame.add(addressField);
        frame.add(genderLabel);
        frame.add(genderBox);
        frame.add(pictureLabel);
        frame.add(uploadButton);
        frame.add(saveButton);

        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserProfile profile = new UserProfile.UserProfileBuilder()
                        .setName(nameField.getText())
                        .setEmail(emailField.getText())
                        .setPhone(phoneField.getText())
                        .setAddress(addressField.getText())
                        .setGender((String) genderBox.getSelectedItem())
                        .build();

                saveUserProfileToDatabase(profile);
            }
        });
    }

    public static void main(String[] args) {
        MyProfile myProfile = new MyProfile();
        myProfile.displayGUI();
    }
}

