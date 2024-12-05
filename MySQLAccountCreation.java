package travelbookingsystem;

import java.sql.*;

public class MySQLAccountCreation implements AccountCreationStrategy {
    @Override
    public void createAccount(String firstName, String lastName, String phone, String email, String username, String password) {
        try {
            Conn conn = new Conn();
            String query = "INSERT INTO users (first_name, last_name, phone, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            pstmt.executeUpdate();
            
            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}