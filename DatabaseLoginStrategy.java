package travelbookingsystem;

import java.sql.*;

public class DatabaseLoginStrategy implements LoginStrategy {

    @Override
    public boolean authenticate(String username, String password) {
        // Replace with your actual database connection and validation logic
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelbookingsystem", "root", "4288")) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();  // If a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
