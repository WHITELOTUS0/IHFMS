package ihfms.dao;

import ihfms.model.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ihfms.util.DatabaseConnection;

public class ServiceDAO {
    private DatabaseConnection dbConnection;

    public ServiceDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void saveService(Service service) {
        String sql = "INSERT INTO services (service_id, description, cost) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, service.getServiceId());
            pstmt.setString(2, service.getDescription());
            pstmt.setDouble(3, service.getCost());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
}
