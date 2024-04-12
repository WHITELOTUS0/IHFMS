package ihfms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
    private DatabaseConnection dbConnection;

    public AuthenticationService(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?"; // Passwords should be hashed
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password); // In production, use a hashed password
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // If there's a result, the user is authenticated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createUser(String username, String password, String role) throws SQLException {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Consider hashing the password
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        }
    }
}
