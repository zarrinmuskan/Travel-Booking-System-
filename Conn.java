package travelbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;

    // Constructor to initialize the connection
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///travelbookingsystem", "root", "4288");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }
    }

    // Getter for Statement
    public Statement getStatement() {
        return s;
    }

    // Getter for Connection
    public Connection getConnection() {
        return c;
    }

    // Method to close the connection
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
